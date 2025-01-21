package com.example.a7.model.statement;

import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.exception.StatementException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.state.PrgState;
import com.example.a7.model.types.MyIType;

public class NopStatement implements MyIStatement {
    @Override
    public PrgState execute(PrgState prgState) {
        return null;
    }

    @Override
    public MyIStatement deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        return null;
    }

    @Override
    public String toString() {
        return "NopStatement";
    }
}
