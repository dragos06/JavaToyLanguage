package model.statement;

import exception.ADTException;
import exception.ExpressionException;
import exception.KeyNotFoundException;
import exception.StatementException;
import model.adt.MyIDictionary;
import model.expressions.MyIExpression;
import model.state.PrgState;
import model.types.MyBoolType;
import model.types.MyIType;
import model.value.MyBoolValue;
import model.value.MyIValue;

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
        }
        else{
            throw new StatementException("The condition of IF has not the type bool");
        }
    }

    @Override
    public String toString() {
        return "if(" + expression + "){" + this.statementThan + "}else{" + this.statementElse + "}";
    }
}
