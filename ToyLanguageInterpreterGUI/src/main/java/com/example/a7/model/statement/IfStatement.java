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

public class IfStatement implements MyIStatement {
    private MyIStatement statementThan;
    private MyIStatement statementElse;
    private MyIExpression expression;

    public IfStatement(MyIStatement statementThan, MyIStatement statementElse, MyIExpression expression) {
        this.statementThan = statementThan;
        this.statementElse = statementElse;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException {
        MyIValue value = expression.eval(prgState.getSymTable(), prgState.getHeapTable());
        if (!value.getType().equals(new MyBoolType())) {
            throw new StatementException("Expression is not boolean");
        }
        if (((MyBoolValue) value).getValue()) {
            prgState.getExeStack().push(this.statementThan);
        } else {
            prgState.getExeStack().push(this.statementElse);
        }

        return null;
    }

    @Override
    public MyIStatement deepCopy() {
        return new IfStatement(this.statementThan.deepCopy(), this.statementElse.deepCopy(), this.expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        MyIType typexp = this.expression.typecheck(typeEnv);
        if (typexp.equals(new MyBoolType())) {
            this.statementThan.typecheck(typeEnv.deepCopy());
            this.statementElse.typecheck(typeEnv.deepCopy());
            return typeEnv;
        } else {
            throw new StatementException("The condition of IF has not the type bool");
        }
    }

    @Override
    public String toString() {
        return "if(" + expression + "){" + this.statementThan + "}else{" + this.statementElse + "}";
    }
}
