package com.example.a7.model.expressions;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.adt.MyIHeap;
import com.example.a7.model.types.MyIType;
import com.example.a7.model.types.RefType;
import com.example.a7.model.value.MyIValue;
import com.example.a7.model.value.RefValue;

public class HeapReading implements MyIExpression {
    MyIExpression expression;

    public HeapReading(MyIExpression expression) {
        this.expression = expression;
    }

    @Override
    public MyIValue eval(MyIDictionary<String, MyIValue> sym_table, MyIHeap heap) throws ADTException, ExpressionException {
        MyIValue evalValue = this.expression.eval(sym_table, heap);
        if (!(evalValue.getType() instanceof RefType)) {
            throw new ExpressionException("Expression value is not a RefValue");
        }
        RefValue refValue = (RefValue) evalValue;
        int adr = refValue.getAddress();
        return heap.getValue(adr);
    }

    @Override
    public MyIExpression deepCopy() {
        return new HeapReading(this.expression.deepCopy());
    }

    @Override
    public MyIType typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException {
        MyIType typ = expression.typecheck(typeEnv);
        if (typ instanceof RefType refType) {
            return refType.getInner();
        } else {
            throw new ExpressionException("the rH argument is not a Ref Type");
        }
    }

    @Override
    public String toString() {
        return "rH(" + this.expression + ")";
    }
}
