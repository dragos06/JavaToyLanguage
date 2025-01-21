package model.expressions;

import exception.ADTException;
import exception.ExpressionException;
import exception.KeyNotFoundException;
import model.adt.MyIHeap;
import model.types.MyIType;
import model.value.MyIValue;
import model.adt.MyIDictionary;


public interface MyIExpression {
    MyIValue eval(MyIDictionary<String, MyIValue> sym_table, MyIHeap heap) throws ADTException, ExpressionException;

    MyIExpression deepCopy();

    String toString();

    MyIType typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException;
}
