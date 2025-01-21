package com.example.a7.model.statement;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.exception.StatementException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.expressions.MyIExpression;
import com.example.a7.model.state.PrgState;
import com.example.a7.model.types.MyBoolType;
import com.example.a7.model.types.MyIType;
import com.example.a7.model.value.MyBoolValue;
import com.example.a7.model.value.MyIValue;

public class WhileStatement implements MyIStatement {
    MyIExpression expression;
    MyIStatement statement;

    public WhileStatement(MyIExpression expression, MyIStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException {
        MyIValue value = this.expression.eval(prgState.getSymTable(), prgState.getHeapTable());
        if (!value.getType().equals(new MyBoolType())) {
            throw new StatementException("Condition expression is not a boolean");
        }
        MyBoolValue boolValue = (MyBoolValue) value;
        if (boolValue.getValue()) {
            prgState.getExeStack().push(this);
            prgState.getExeStack().push(statement);
        }
        return null;
    }

    @Override
    public MyIStatement deepCopy() {
        return new WhileStatement(this.expression.deepCopy(), this.statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        MyIType typexp = this.expression.typecheck(typeEnv);
        if (typexp.equals(new MyBoolType())) {
            this.statement.typecheck(typeEnv.deepCopy());
            return typeEnv;
        } else {
            throw new StatementException("The condition of WHILE has not the type bool");
        }
    }

    @Override
    public String toString() {
        return "While(" + this.expression + "){" + this.statement + "}";
    }
}
