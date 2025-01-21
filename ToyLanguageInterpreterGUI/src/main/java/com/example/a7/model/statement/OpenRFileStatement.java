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
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;

public class OpenRFileStatement implements MyIStatement {
    private MyIExpression exp;

    public OpenRFileStatement(MyIExpression exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException {
        MyIValue evalValue = this.exp.eval(prgState.getSymTable(), prgState.getHeapTable());
        if (!evalValue.getType().equals(new StringType())) {
            throw new StatementException("Value type is not String");
        }
        if (prgState.getSymTable().contains(evalValue.toString())) {
            throw new StatementException("Key already exists");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(((StringValue) evalValue).toString()));
            prgState.getFileTable().insert((StringValue) evalValue, bufferedReader);
        } catch (IOException | IOError e) {
            throw new StatementException("File does not exist");
        }
        return null;
    }

    @Override
    public MyIStatement deepCopy() {
        return new OpenRFileStatement(this.exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        this.exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "File opened: " + this.exp.toString();
    }
}
