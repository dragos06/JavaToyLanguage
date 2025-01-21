package model.statement;

import exception.ExpressionException;
import exception.KeyNotFoundException;
import exception.StatementException;
import model.adt.MyIDictionary;
import model.state.PrgState;
import model.types.MyIType;

public class NopStatement implements MyIStatement {
    @Override
    public PrgState execute(PrgState prgState) {
        return null;
    }

    @Override
    public MyIStatement deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        return null;
    }

    @Override
    public String toString() {
        return "NopStatement";
    }
}
