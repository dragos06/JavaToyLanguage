package model.statement;

import exception.ADTException;
import exception.ExpressionException;
import exception.KeyNotFoundException;
import exception.StatementException;
import model.adt.MyIDictionary;
import model.state.PrgState;
import model.expressions.MyIExpression;
import model.types.MyIType;
import model.value.MyIValue;


public class PrintStatement implements MyIStatement {
    private MyIExpression expression;

    public PrintStatement(MyIExpression expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState prgState) throws ADTException, ExpressionException {
        MyIValue result = this.expression.eval(prgState.getSymTable(), prgState.getHeapTable());
        prgState.getOutput().add(result.toString());
        return null;
    }

    @Override
    public MyIStatement deepCopy() {
        return new PrintStatement(this.expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        this.expression.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }
}
