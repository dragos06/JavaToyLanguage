package model.expressions;

import exception.ExpressionException;
import exception.KeyNotFoundException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.types.MyIType;
import model.value.MyIValue;

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
