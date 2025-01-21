package com.example.a7.model.expressions;

import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.adt.MyIHeap;
import com.example.a7.model.types.MyIType;
import com.example.a7.model.value.MyIValue;

public class ValueExpression implements MyIExpression {
    private MyIValue value;

    public ValueExpression(MyIValue value) {
        this.value = value;
    }

    @Override
    public MyIValue eval(MyIDictionary<String, MyIValue> sym_table, MyIHeap heap) {
        return value;
    }

    @Override
    public MyIExpression deepCopy() {
        return new ValueExpression(this.value);
    }

    @Override
    public MyIType typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException {
        return value.getType();
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
