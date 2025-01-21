package com.example.a7.model.expressions;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.adt.MyIHeap;
import com.example.a7.model.types.MyIType;
import com.example.a7.model.value.MyIValue;


public interface MyIExpression {
    MyIValue eval(MyIDictionary<String, MyIValue> sym_table, MyIHeap heap) throws ADTException, ExpressionException;

    MyIExpression deepCopy();

    String toString();

    MyIType typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException;
}
