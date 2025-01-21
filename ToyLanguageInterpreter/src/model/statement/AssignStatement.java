package model.statement;

import exception.ADTException;
import exception.ExpressionException;
import exception.KeyNotFoundException;
import exception.StatementException;
import model.adt.MyIDictionary;
import model.expressions.MyIExpression;
import model.state.PrgState;
import model.value.MyIValue;
import model.types.MyIType;

public class AssignStatement implements MyIStatement {

    private String variableName;
    private MyIExpression expression;

    public AssignStatement(String variableName, MyIExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public MyIStatement deepCopy() {
        return new AssignStatement(new String(this.variableName), this.expression.deepCopy());
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, ExpressionException {
        if (!prgState.getSymTable().contains(this.variableName)) {
            throw new StatementException("Variable was not found");
        }
        MyIValue value = prgState.getSymTable().getValue(this.variableName);
        MyIValue evalValue = this.expression.eval(prgState.getSymTable(), prgState.getHeapTable());
        if (!value.getType().equals(evalValue.getType())) {
            throw new StatementException("Value type mismatch");
        }
        prgState.getSymTable().insert(this.variableName, evalValue);
        return null;

    }

    @Override
    public MyIDictionary<String, MyIType> typecheck(MyIDictionary<String, MyIType> typeEnv) throws ExpressionException, KeyNotFoundException, StatementException {
        MyIType typevar = typeEnv.getValue(variableName);
        MyIType typexp = expression.typecheck(typeEnv);
        if(typevar.equals(typexp)){
            return typeEnv;
        }
        else{
            throw new StatementException("Assignment: right hand side and left hand side have different types");
        }
    }

    @Override
    public String toString() {
        return this.variableName + " = " + this.expression.toString();
    }

}
