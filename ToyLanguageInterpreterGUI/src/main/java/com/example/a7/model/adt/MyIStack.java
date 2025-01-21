package com.example.a7.model.adt;

import com.example.a7.exception.EmptyStackException;

import java.util.List;
import java.util.Stack;

public interface MyIStack<T> {
    void push(T element);

    T pop() throws EmptyStackException;

    int size();

    boolean isEmpty();

    String toString();

    List<String> toStringGUI();
    List<String> toStringGUI2();
}
