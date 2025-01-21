package com.example.a7.model.statement;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.exception.StatementException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.state.PrgState;
import com.example.a7.model.types.MyIType;

public interface MyIStatement {
    PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException;

    MyIStatement deepCopy();

    String toString();

    MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException;
}
