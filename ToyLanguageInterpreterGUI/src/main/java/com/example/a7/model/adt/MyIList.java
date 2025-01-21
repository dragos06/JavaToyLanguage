package com.example.a7.model.adt;

import java.util.List;

public interface MyIList<T> {
    void add(T element);

    List<T> getAll();


    String toString();
    List<T> toStringGUI();
}
