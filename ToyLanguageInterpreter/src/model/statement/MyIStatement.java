package model.statement;

import exception.ADTException;
import exception.ExpressionException;
import exception.KeyNotFoundException;
import exception.StatementException;
import model.adt.MyIDictionary;
import model.state.PrgState;
import model.types.MyIType;

public interface MyIStatement {
    PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException;

    MyIStatement deepCopy();

    String toString();

    MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException;
}
