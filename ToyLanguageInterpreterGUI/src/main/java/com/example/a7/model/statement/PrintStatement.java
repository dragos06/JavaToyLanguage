package com.example.a7.model.statement;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.exception.StatementException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.expressions.MyIExpression;
import com.example.a7.model.state.PrgState;
import com.example.a7.model.types.MyIType;
import com.example.a7.model.value.MyIValue;


public class PrintStatement implements MyIStatement {
    private MyIExpression expression;

    public PrintStatement(MyIExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState prgState) throws ADTException, ExpressionException {
        MyIValue result = this.expression.eval(prgState.getSymTable(), prgState.getHeapTable());
        prgState.getOutput().add(result.toString());
        return null;
    }

    @Override
    public MyIStatement deepCopy() {
        return new PrintStatement(this.expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        this.expression.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }
}
