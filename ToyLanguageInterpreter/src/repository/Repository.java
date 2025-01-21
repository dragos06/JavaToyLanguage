package repository;

import exception.RepoException;
import model.state.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<PrgState> prgStateList;
    private String filename;

    public Repository(String filename) {
        this.prgStateList = new ArrayList<>();
        this.filename = filename;
    }

    @Override
    public void add(PrgState prgState) {
        this.prgStateList.add(prgState);
    }

    @Override
    public List<PrgState> getPrgList() {
        return this.prgStateList;
    }

    @Override
    public void setPrgList(List<PrgState> prgList) {
        this.prgStateList = prgList;
    }

    @Override
    public void logPrgStateExec(PrgState prgState) throws RepoException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, true)));
            logFile.println(prgState.toString());
            logFile.close();
        } catch (IOException err) {
            throw new RepoException("File does not exist");
        }
    }

    @Override
    public void clearFile() throws RepoException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, false)));
            logFile.write("");
        } catch (IOException err) {
            throw new RepoException("Could not clear the file");
        }
    }
}
