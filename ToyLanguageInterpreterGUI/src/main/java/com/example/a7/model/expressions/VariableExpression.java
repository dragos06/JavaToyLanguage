package com.example.a7.model.expressions;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.adt.MyIHeap;
import com.example.a7.model.types.MyIType;
import com.example.a7.model.value.MyIValue;

public class VariableExpression implements MyIExpression {
    private String variable;

    public VariableExpression(String variable) {
        this.variable = variable;
    }

    @Override
    public MyIValue eval(MyIDictionary<String, MyIValue> sym_table, MyIHeap heap) throws ADTException {
        return sym_table.getValue(variable);
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public MyIType typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException {
        return typeEnv.getValue(variable);
    }

    @Override
    public MyIExpression deepCopy() {
        return new VariableExpression(new String(this.variable));
    }
}
