package repository;

import exception.RepoException;
import model.state.PrgState;

import java.util.List;

public interface IRepository {
    void add(PrgState prgState);

    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> prgList);

    void logPrgStateExec(PrgState prgState) throws RepoException;

    void clearFile() throws RepoException;
}
