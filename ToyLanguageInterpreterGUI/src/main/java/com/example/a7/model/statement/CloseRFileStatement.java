package com.example.a7.model.statement;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.exception.StatementException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.expressions.MyIExpression;
import com.example.a7.model.state.PrgState;
import com.example.a7.model.types.MyIType;
import com.example.a7.model.types.StringType;
import com.example.a7.model.value.MyIValue;
import com.example.a7.model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements MyIStatement {
    private MyIExpression exp;

    public CloseRFileStatement(MyIExpression exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException {
        MyIValue evalValue = exp.eval(prgState.getSymTable(), prgState.getHeapTable());
        if (!evalValue.getType().equals(new StringType())) {
            throw new StatementException("Value type is not string");
        }
        if (!prgState.getFileTable().contains((StringValue) evalValue)) {
            throw new StatementException("File not opened");
        }
        BufferedReader bufferedReader = prgState.getFileTable().getValue((StringValue) evalValue);
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new StatementException("Couldn't close buffer");
        }
        prgState.getFileTable().remove((StringValue) evalValue);
        return null;
    }

    @Override
    public MyIStatement deepCopy() {
        return new CloseRFileStatement(this.exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        this.exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "File closed: " + this.exp.toString();
    }
}
