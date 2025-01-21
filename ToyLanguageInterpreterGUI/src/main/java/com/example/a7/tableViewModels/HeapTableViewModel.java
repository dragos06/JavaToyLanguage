package com.example.a7.tableViewModels;

import com.example.a7.model.value.MyIValue;

public class HeapTableViewModel {
    private String address;
    private String value;

    public HeapTableViewModel(Integer address, MyIValue value) {
        this.address = Integer.toString(address);
        this.value = value.toString();
    }

    public String getHeapAddress() {
        return address;
    }

    public String getHeapValue() {
        return value;
    }
}
