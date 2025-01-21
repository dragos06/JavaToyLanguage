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

public class HeapWriting implements MyIStatement {
    private String var_name;
    private MyIExpression expression;

    public HeapWriting(String var_name, MyIExpression expression) {
        this.var_name = var_name;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException {
        if (!prgState.getSymTable().contains(this.var_name)) {
            throw new StatementException("Variable is not in SymTable");
        }
        MyIValue varValue = prgState.getSymTable().getValue(this.var_name);
        if (!(varValue.getType() instanceof RefType)) {
            throw new StatementException("Variable is not of RefType");
        }
        RefValue refValue = (RefValue) varValue;
        if (!prgState.getHeapTable().contains(refValue.getAddress())) {
            throw new StatementException("Variable is not allocated");
        }
        MyIValue evalValue = this.expression.eval(prgState.getSymTable(), prgState.getHeapTable());
        if (!evalValue.getType().equals(refValue.getLocationType())) {
            throw new StatementException("Variable type in SymTable and HeapTable doesn't match");
        }
        prgState.getHeapTable().update(refValue.getAddress(), evalValue);
        return null;
    }

    @Override
    public MyIStatement deepCopy() {
        return new HeapWriting(new String(this.var_name), this.expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        MyIType typevar = typeEnv.getValue(this.var_name);
        MyIType typexp = this.expression.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp))) {
            return typeEnv;
        } else {
            throw new StatementException("HeapWriting: right hand side and left hand side have different types");
        }
    }

    @Override
    public String toString() {
        return "wH(" + this.var_name + "," + this.expression + ")";
    }
}
