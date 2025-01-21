package com.example.a7.tableViewModels;

import com.example.a7.model.value.MyIValue;

public class SymTableViewModel {
    private String name;
    private String value;

    public SymTableViewModel(String name, MyIValue value) {
        this.name = name;
        this.value = value.toString();
    }

    public String getSymName() {
        return name;
    }

    public String getSymValue() {
        return value;
    }
}
