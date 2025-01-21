package model.state;

import exception.ADTException;
import exception.ExpressionException;
import exception.StatementException;
import model.adt.*;
import model.statement.MyIStatement;
import model.value.MyIValue;
import model.value.StringValue;

import java.io.BufferedReader;

public class PrgState {
    private MyIDictionary<String, MyIValue> symTable;
    private MyIStack<MyIStatement> exeStack;
    private MyIList<String> output;
    private MyIStatement initialState;
    private IFileTable<StringValue, BufferedReader> fileTable;
    private MyIHeap heapTable;
    private int id;
    private static int lastIndex;

    public PrgState(MyIDictionary<String, MyIValue> symTable, MyIStack<MyIStatement> exeStack, MyIList<String> output, MyIStatement initialState, IFileTable<StringValue, BufferedReader> fileTable, MyIHeap heapTable) {
        this.symTable = symTable;
        this.exeStack = exeStack;
        this.output = output;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.initialState = initialState.deepCopy();
        this.exeStack.push(this.initialState);
        this.id = manageID();
    }

    public static synchronized int manageID() {
        lastIndex += 1;
        return lastIndex;
    }

    public int getId() {
        return this.id;
    }

    public MyIStack<MyIStatement> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, MyIValue> getSymTable() {
        return symTable;
    }

    public MyIList<String> getOutput() {
        return output;
    }

    public MyIStatement getInitialState() {
        return initialState;
    }

    public IFileTable<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public MyIHeap getHeapTable() {
        return heapTable;
    }

    public Boolean isNotCompleted() {
        return !this.exeStack.isEmpty();
    }

    public PrgState oneStep() throws StatementException, ADTException, ExpressionException {
        if (this.exeStack.isEmpty()) {
            throw new ADTException("prgstate stack is empty");
        }
        MyIStatement crtStmt = this.exeStack.pop();
        return crtStmt.execute(this);
    }

    @Override
    public String toString() {
        return this.id + "\n" + symTable.toString() + "\n" + exeStack.toString() + "\n" + output.toString() + "\n" + fileTable.toString() + "\n" + heapTable.toString() + "\n-----------------------------\n";
    }
}
