package com.example.a7.model.statement;

import com.example.a7.exception.ADTException;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.exception.StatementException;
import com.example.a7.model.adt.MyIDictionary;
import com.example.a7.model.expressions.MyIExpression;
import com.example.a7.model.state.PrgState;
import com.example.a7.model.types.MyIType;
import com.example.a7.model.types.MyIntType;
import com.example.a7.model.types.StringType;
import com.example.a7.model.value.MyIValue;
import com.example.a7.model.value.MyIntValue;
import com.example.a7.model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements MyIStatement {
    private MyIExpression exp;
    private String var_name;

    public ReadFileStatement(MyIExpression exp, String var_name) {
        this.exp = exp;
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException {
        if (!prgState.getSymTable().contains(this.var_name)) {
            throw new StatementException("Variable does not exist");
        }
        if (!prgState.getSymTable().getValue(this.var_name).getType().equals(new MyIntType())) {
            throw new StatementException("Variable is not of type int");
        }
        MyIValue evalValue = exp.eval(prgState.getSymTable(), prgState.getHeapTable());
        if (!evalValue.getType().equals(new StringType())) {
            throw new StatementException("Expression is not of type string");
        }
        if (!prgState.getFileTable().contains((StringValue) evalValue)) {
            throw new StatementException("File isn't opened");
        }

        BufferedReader bufferedReader = prgState.getFileTable().getValue((StringValue) evalValue);
        try {
            String line = bufferedReader.readLine();
            MyIntValue intValue;
            if (line.equals("")) {
                intValue = new MyIntValue(0);
            } else {
                intValue = new MyIntValue(Integer.parseInt(line));
            }
            prgState.getSymTable().insert(this.var_name, intValue);
        } catch (IOException e) {
            throw new StatementException("Line cannot be read");
        }
        return null;
    }

    @Override
    public MyIStatement deepCopy() {
        return new ReadFileStatement(this.exp.deepCopy(), new String(var_name));
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        MyIType typevar = typeEnv.getValue(this.var_name);
        MyIType typexp = this.exp.typecheck(typeEnv);
        if (typevar instanceof MyIntType) {
            if (typexp instanceof StringType) {
                return typeEnv;
            } else {
                throw new StatementException("ReadFile: Expression isn't of type string");
            }
        } else {
            throw new StatementException("ReadFile: Variable isn't of type int");
        }
    }

    @Override
    public String toString() {
        return "File: " + this.exp.toString() + " -> " + this.var_name;
    }
}
