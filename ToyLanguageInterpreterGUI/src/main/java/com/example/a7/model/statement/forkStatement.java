package com.example.a7.model.statement;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.exception.StatementException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.adt.MyStack;
import com.example.a7.model.state.PrgState;
import com.example.a7.model.types.MyIType;

public class forkStatement implements MyIStatement {
    MyIStatement statement;

    public forkStatement(MyIStatement statement) {
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException {
        return new PrgState(prgState.getSymTable().deepCopy(), new MyStack<MyIStatement>(), prgState.getOutput(), this.statement, prgState.getFileTable(), prgState.getHeapTable());
    }

    @Override
    public MyIStatement deepCopy() {
        return new forkStatement(this.statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        this.statement.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork(" + this.statement + ")";
    }
}
