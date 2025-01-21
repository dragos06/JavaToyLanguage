package com.example.a7.model.statement;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.exception.StatementException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.state.PrgState;
import com.example.a7.model.types.MyIType;

public class VarDeclStatement implements MyIStatement {
    private String name;
    private MyIType type;

    public VarDeclStatement(String name, MyIType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException {
        if (prgState.getSymTable().contains(this.name)) {
            throw new StatementException("Variable already exists");
        }

        prgState.getSymTable().insert(this.name, this.type.getDefaultValue());
        return null;
    }

    @Override
    public MyIStatement deepCopy() {
        return new VarDeclStatement(new String(this.name), this.type.deepCopy());
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        typeEnv.insert(this.name, this.type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return this.type + " " + this.name;
    }
}
