package com.example.a7.repository;

import com.example.a7.exception.RepoException;
import com.example.a7.model.state.PrgState;
import javafx.beans.InvalidationListener;

import java.util.List;

public interface IRepository {
    void add(PrgState prgState);

    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> prgList);

    void logPrgStateExec(PrgState prgState) throws RepoException;

    void clearFile() throws RepoException;

    void addListener(InvalidationListener listener);
    void removeListener(InvalidationListener listener);
    void notifyListeners();
}
