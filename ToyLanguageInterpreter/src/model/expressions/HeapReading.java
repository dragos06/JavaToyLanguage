package model.expressions;

import exception.ADTException;
import exception.ExpressionException;
import exception.KeyNotFoundException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.types.MyIType;
import model.types.RefType;
import model.value.MyIValue;
import model.value.RefValue;

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
        if(typ instanceof RefType refType){
            return refType.getInner();
        }
        else{
            throw new ExpressionException("the rH argument is not a Ref Type");
        }
    }

    @Override
    public String toString() {
        return "rH(" + this.expression + ")";
    }
}
