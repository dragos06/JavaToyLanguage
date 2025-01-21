package com.example.a7.model.expressions;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.adt.MyIHeap;
import com.example.a7.model.types.MyBoolType;
import com.example.a7.model.types.MyIType;
import com.example.a7.model.value.MyBoolValue;
import com.example.a7.model.value.MyIValue;

public class LogicalExpression implements MyIExpression {
    private MyIExpression left;
    private MyIExpression right;
    private LogicalOperator operator;

    public LogicalExpression(MyIExpression left, MyIExpression right, LogicalOperator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public MyIValue eval(MyIDictionary<String, MyIValue> sym_table, MyIHeap heap) throws ADTException, ExpressionException {
        MyIValue evaluatedExpressionLeft = this.left.eval(sym_table, heap);
        MyIValue evaluatedExpressionRight = this.right.eval(sym_table, heap);
        if (!evaluatedExpressionLeft.getType().equals(new MyBoolType())) {
            throw new ExpressionException("Left expression is not of type BoolType");
        }
        if (!evaluatedExpressionRight.getType().equals(new MyBoolType())) {
            throw new ExpressionException("Right expression is not of type BoolType");
        }
        MyBoolValue bool1 = (MyBoolValue) evaluatedExpressionLeft;
        MyBoolValue bool2 = (MyBoolValue) evaluatedExpressionRight;
        switch (operator) {
            case AND:
                return new MyBoolValue(bool1.getValue() && bool2.getValue());
            case OR:
                return new MyBoolValue(bool1.getValue() || bool2.getValue());
            default:
                throw new ExpressionException("Unknown operator");
        }
    }

    @Override
    public MyIExpression deepCopy() {
        return new LogicalExpression(this.left.deepCopy(), this.right.deepCopy(), this.operator);
    }

    @Override
    public MyIType typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException {
        MyIType typ1, typ2;
        typ1 = left.typecheck(typeEnv);
        typ2 = right.typecheck(typeEnv);
        if (typ1.equals(new MyBoolType())) {
            if (typ2.equals(new MyBoolType())) {
                return new MyBoolType();
            } else {
                throw new ExpressionException("second operand is not a bool");
            }
        } else {
            throw new ExpressionException("first operand is not a bool");
        }
    }

    @Override
    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }
}
