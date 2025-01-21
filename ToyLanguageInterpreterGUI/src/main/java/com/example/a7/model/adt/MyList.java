package com.example.a7.model.adt;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> list;

    public MyList() {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        this.list.add(element);
    }

    @Override
    public List<T> getAll() {
        return this.list;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (T element : this.list) {
            str.append(element).append("\n");
        }
        return "MyList contains " + str + "\n";
    }

    @Override
    public List<T> toStringGUI() {
        return this.list;
    }
}
