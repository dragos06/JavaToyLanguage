package model.expressions;

import exception.ADTException;
import exception.ExpressionException;
import exception.KeyNotFoundException;
import model.adt.MyIDictionary;
import model.adt.MyIHeap;
import model.types.MyBoolType;
import model.types.MyIType;
import model.types.MyIntType;
import model.value.MyBoolValue;
import model.value.MyIValue;
import model.value.MyIntValue;

public class RelationalExpression implements MyIExpression {
    private MyIExpression left;
    private MyIExpression right;
    private RelationalOperator operator;

    public RelationalExpression(MyIExpression left, MyIExpression right, RelationalOperator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public MyIValue eval(MyIDictionary<String, MyIValue> sym_table, MyIHeap heap) throws ADTException, ExpressionException {
        MyIValue eval_left = this.left.eval(sym_table, heap);
        MyIValue eval_right = this.right.eval(sym_table, heap);
        if (!eval_left.getType().equals(new MyIntType())) {
            throw new ExpressionException("Left expression is not of type int");
        }
        if (!eval_right.getType().equals(new MyIntType())) {
            throw new ExpressionException("Right expression is not of type int");
        }
        MyIntValue int1 = (MyIntValue) eval_left;
        MyIntValue int2 = (MyIntValue) eval_right;
        switch (this.operator) {
            case LESS:
                return new MyBoolValue(int1.getValue() < int2.getValue());
            case LESS_EQUAL:
                return new MyBoolValue(int1.getValue() <= int2.getValue());
            case EQUAL:
                return new MyBoolValue(int1.getValue() == int2.getValue());
            case DIFFERENT:
                return new MyBoolValue(int1.getValue() != int2.getValue());
            case GREATER:
                return new MyBoolValue(int1.getValue() > int2.getValue());
            case GREATER_EQUAL:
                return new MyBoolValue(int1.getValue() >= int2.getValue());
            default:
                throw new ExpressionException("Unknown operator");
        }
    }

    @Override
    public MyIExpression deepCopy() {
        return new RelationalExpression(this.left.deepCopy(), this.right.deepCopy(), this.operator);
    }

    @Override
    public MyIType typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException {
        MyIType typ1, typ2;
        typ1 = left.typecheck(typeEnv);
        typ2 = right.typecheck(typeEnv);
        if (typ1.equals(new MyIntType())) {
            if (typ2.equals(new MyIntType())) {
                return new MyBoolType();
            } else {
                throw new ExpressionException("second operand is not an integer");
            }
        } else {
            throw new ExpressionException("first operand is not an integer");
        }
    }

    @Override
    public String toString() {
        return this.left.toString() + " " + this.operator.toString() + " " + this.right.toString();
    }
}
