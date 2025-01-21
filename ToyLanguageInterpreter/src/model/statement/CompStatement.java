package model.statement;

import exception.ExpressionException;
import exception.KeyNotFoundException;
import exception.StatementException;
import model.adt.MyIDictionary;
import model.state.PrgState;
import model.types.MyIType;

public class CompStatement implements MyIStatement {
    private MyIStatement statement1;
    private MyIStatement statement2;

    public CompStatement(MyIStatement statement1, MyIStatement statement2) {
        this.statement1 = statement1;
        this.statement2 = statement2;
    }

    @Override
    public PrgState execute(PrgState prgState) {
        prgState.getExeStack().push(statement2);
        prgState.getExeStack().push(statement1);

        return null;
    }

    @Override
    public MyIStatement deepCopy() {
        return new CompStatement(this.statement1.deepCopy(), this.statement2.deepCopy());
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        return statement2.typecheck(statement1.typecheck(typeEnv));
    }

    @Override
    public String toString() {
        return statement1.toString() + ";" + statement2.toString();
    }
}
