package controller;

import exception.*;
import model.adt.*;
import model.state.PrgState;
import model.statement.CompStatement;
import model.statement.MyIStatement;
import model.value.MyIValue;
import model.value.RefValue;
import model.value.StringValue;
import repository.IRepository;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;
    private ExecutorService executor;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public void clearFile() throws ControllerException {
        try {
            this.repository.clearFile();
        } catch (RepoException e) {
            throw new ControllerException("File not found");
        }
    }

    public void add(MyIStatement statement) {
        PrgState my_state = new PrgState(new MyDictionary<String, MyIValue>(), new MyStack<MyIStatement>(), new MyList<String>(), statement, new FileTable<StringValue, BufferedReader>(), new MyHeap());
        this.repository.add(my_state);
    }

    public void allStep() throws ControllerException {
        this.executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repository.getPrgList());
        while (prgList.size() > 0) {
            prgList.forEach(prg -> {
                prg.getHeapTable().setHeap(safeGarbageCollector(getAddrFromSymTable(prg.getSymTable().getMap().values()), getAddrFromHeapTable(prg.getHeapTable().getHeap().values()), prg.getHeapTable().getHeap()));
            });
            try {
                oneStepForAllPrg(prgList);
            } catch (RuntimeException | InterruptedException e) {
                throw new ControllerException(e.getMessage());
            }
            prgList = removeCompletedPrg(repository.getPrgList());
        }
        executor.shutdownNow();
    }

    public Map<Integer, MyIValue> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapTableAddr, Map<Integer, MyIValue> heapTable) {
        return heapTable.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()) || heapTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddrFromSymTable(Collection<MyIValue> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    public List<Integer> getAddrFromHeapTable(Collection<MyIValue> heapTableValues) {
        return heapTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }

    void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (RepoException e) {
                throw new RuntimeException(e.getMessage());
            }
        });

        List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>) (p::oneStep)).toList();

        List<PrgState> newPrgList = executor.invokeAll(callList).stream().map(future -> {
            try {
                return future.get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }).filter(Objects::nonNull).toList();

        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (RepoException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
        repository.setPrgList(prgList);
    }
}
