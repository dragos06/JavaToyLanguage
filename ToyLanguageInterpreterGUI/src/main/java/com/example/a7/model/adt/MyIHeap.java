package com.example.a7.model.adt;

import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.model.value.MyIValue;

import java.util.Map;
import java.util.Set;

public interface MyIHeap {
    public Map<Integer, MyIValue> getHeap();

    void setHeap(Map<Integer, MyIValue> heap);

    Integer insert(MyIValue value);

    void update(Integer key, MyIValue value) throws KeyNotFoundException;

    MyIValue getValue(Integer key) throws KeyNotFoundException;

    void remove(Integer key) throws KeyNotFoundException;

    boolean contains(Integer key);

    String toString();

    Set<Integer> getKeys();
}
