package com.example.a7.model.adt;

import com.example.a7.exception.EmptyStackException;
import com.example.a7.model.statement.CompStatement;
import com.example.a7.model.statement.MyIStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<>();
    }

    public Stack<T> getStack() {
        return stack;
    }

    @Override
    public void push(T element) {
        this.stack.push(element);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (this.stack.isEmpty()) {
            throw new EmptyStackException("Stack is empty");
        }
        return this.stack.pop();
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (T element : this.stack) {
            str.append(element).append("\n");
        }
        return "MyStack contains " + str + "\n";
    }

    @Override
    public List<String> toStringGUI() {
        List<String> stack = new ArrayList<>();
        for (T element : this.stack.reversed()) {
            if (element instanceof CompStatement compElement) {
                stack.add(compElement.getStatement1().toString());
                while (compElement.getStatement2() instanceof CompStatement next) {
                    compElement = next;
                    stack.add(compElement.getStatement1().toString());
                }
                stack.add(compElement.getStatement2().toString());

            } else {
                stack.add(element.toString());
            }
        }
        return stack;
    }

    @Override
    public List<String> toStringGUI2() {
        List<String> stack = new ArrayList<>();
        for (T element : this.stack) {
            stack.addFirst(element.toString());
        }
        return stack;
    }
}
