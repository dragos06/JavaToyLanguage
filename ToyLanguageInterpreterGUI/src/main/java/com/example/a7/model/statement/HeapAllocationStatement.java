package com.example.a7.model.statement;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.exception.StatementException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.expressions.MyIExpression;
import com.example.a7.model.state.PrgState;
import com.example.a7.model.types.MyIType;
import com.example.a7.model.types.RefType;
import com.example.a7.model.value.MyIValue;
import com.example.a7.model.value.RefValue;

public class HeapAllocationStatement implements MyIStatement {
    private String variableName;
    private MyIExpression expression;

    public HeapAllocationStatement(String variableName, MyIExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException {
        if (!prgState.getSymTable().contains(this.variableName)) {
            throw new StatementException("Variable is not in the SymTable, cannot allocate");
        }
        MyIValue varValue = prgState.getSymTable().getValue(this.variableName);
        if (!(varValue.getType() instanceof RefType)) {
            throw new StatementException("Variable in SymTable is not of RefType");
        }
        MyIValue evalValue = this.expression.eval(prgState.getSymTable(), prgState.getHeapTable());
        RefValue refValue = (RefValue) varValue;
        if (!evalValue.getType().equals(refValue.getLocationType())) {
            throw new StatementException("The type of the variable does not match the type of the allocated value");
        }
        Integer adr = prgState.getHeapTable().insert(evalValue);
        prgState.getSymTable().insert(this.variableName, new RefValue(adr, evalValue.getType()));
        return null;
    }

    @Override
    public MyIStatement deepCopy() {
        return new HeapAllocationStatement(new String(this.variableName), this.expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        MyIType typevar = typeEnv.getValue(this.variableName);
        MyIType typexp = this.expression.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp))) {
            return typeEnv;
        } else {
            throw new StatementException("NEW stmt: right hand side and left hand side have different types");
        }
    }

    @Override
    public String toString() {
        return this.variableName + "=new(" + this.expression + ")";
    }
}
