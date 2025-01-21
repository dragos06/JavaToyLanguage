package model.statement;

import exception.ADTException;
import exception.ExpressionException;
import exception.KeyNotFoundException;
import exception.StatementException;
import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.adt.MyStack;
import model.state.PrgState;
import model.types.MyIType;

public class forkStatement implements MyIStatement {
    MyIStatement statement;

    public forkStatement(MyIStatement statement) {
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException {
        return new PrgState(prgState.getSymTable().deepCopy(), new MyStack<MyIStatement>(), prgState.getOutput(), this.statement, prgState.getFileTable(), prgState.getHeapTable());
    }

    @Override
    public MyIStatement deepCopy() {
        return new forkStatement(this.statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        this.statement.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork(" + this.statement + ")";
    }
}
