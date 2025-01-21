package com.example.a7.model.statement;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.exception.StatementException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.expressions.MyIExpression;
import com.example.a7.model.state.PrgState;
import com.example.a7.model.types.MyIType;
import com.example.a7.model.value.MyIValue;

public class AssignStatement implements MyIStatement {

    private String variableName;
    private MyIExpression expression;

    public AssignStatement(String variableName, MyIExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public MyIStatement deepCopy() {
        return new AssignStatement(new String(this.variableName), this.expression.deepCopy());
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException {
        if (!prgState.getSymTable().contains(this.variableName)) {
            throw new StatementException("Variable was not found");
        }
        MyIValue value = prgState.getSymTable().getValue(this.variableName);
        MyIValue evalValue = this.expression.eval(prgState.getSymTable(), prgState.getHeapTable());
        if (!value.getType().equals(evalValue.getType())) {
            throw new StatementException("Value type mismatch");
        }
        prgState.getSymTable().insert(this.variableName, evalValue);
        return null;

    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        MyIType typevar = typeEnv.getValue(variableName);
        MyIType typexp = expression.typecheck(typeEnv);
        if (typevar.equals(typexp)) {
            return typeEnv;
        } else {
            throw new StatementException("Assignment: right hand side and left hand side have different types");
        }
    }

    @Override
    public String toString() {
        return this.variableName + " = " + this.expression.toString();
    }

}
