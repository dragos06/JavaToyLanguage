package com.example.a7.model.expressions;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.adt.MyIHeap;
import com.example.a7.model.types.MyIType;
import com.example.a7.model.types.MyIntType;
import com.example.a7.model.value.MyIValue;
import com.example.a7.model.value.MyIntValue;

public class ArithmeticalExpression implements MyIExpression {
    private MyIExpression left;
    private MyIExpression right;
    private AritmeticalOperator operator;

    public ArithmeticalExpression(MyIExpression left, AritmeticalOperator operator, MyIExpression right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public MyIExpression deepCopy() {
        return new ArithmeticalExpression(this.left.deepCopy(), this.operator, this.right.deepCopy());
    }

    @Override
    public MyIValue eval(MyIDictionary<String, MyIValue> sym_table, MyIHeap heap) throws ADTException, ExpressionException {
        MyIValue value1 = this.left.eval(sym_table, heap);
        MyIValue value2 = this.right.eval(sym_table, heap);
        if (!value1.getType().equals(new MyIntType())) {
            throw new ExpressionException("First value is not int");
        }
        if (!value2.getType().equals(new MyIntType())) {
            throw new ExpressionException("Second value is not int");
        }
        MyIntValue int1 = (MyIntValue) value1;
        MyIntValue int2 = (MyIntValue) value2;

        switch (operator) {
            case ADD:
                return new MyIntValue(int1.getValue() + int2.getValue());
            case SUBTRACT:
                return new MyIntValue(int1.getValue() - int2.getValue());
            case MULTIPLY:
                return new MyIntValue(int1.getValue() * int2.getValue());
            case DIVIDE: {
                if (int2.getValue() == 0) {
                    throw new ExpressionException("Division by zero");
                }
                return new MyIntValue(int1.getValue() / int2.getValue());
            }
            default:
                throw new ExpressionException("Unknown operator");
        }
    }

    @Override
    public MyIType typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException {
        MyIType typ1, typ2;
        typ1 = left.typecheck(typeEnv);
        typ2 = right.typecheck(typeEnv);
        if (typ1.equals(new MyIntType())) {
            if (typ2.equals(new MyIntType())) {
                return new MyIntType();
            } else {
                throw new ExpressionException("second operand is not an integer");
            }
        } else {
            throw new ExpressionException("first operand is not an integer");
        }
    }

    @Override
    public String toString() {
        return this.left.toString() + " " + this.operator + " " + this.right.toString();
    }
}
