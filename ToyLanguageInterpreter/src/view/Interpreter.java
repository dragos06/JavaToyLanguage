package view;

import controller.Controller;
import exception.ControllerException;
import exception.ExpressionException;
import exception.KeyNotFoundException;
import exception.StatementException;
import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.expressions.*;
import model.state.PrgState;
import model.statement.*;
import model.types.*;
import model.value.MyBoolValue;
import model.value.MyIntValue;
import model.value.StringValue;
import repository.IRepository;
import repository.Repository;
import view.commands.ExitCommand;
import view.commands.RunExampleCommand;

public class Interpreter {
    public static void main() {
        MyIStatement statement1 = new CompStatement(new VarDeclStatement("v", new MyIntType()), new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(2))), new PrintStatement(new VariableExpression("v"))));
        try {
            statement1.typecheck(new MyDictionary<>());
        } catch (StatementException | ExpressionException | KeyNotFoundException e) {
            System.out.println("Statement1: " + e.getMessage());
            return;
        }
        IRepository repository1 = new Repository("log1.txt");
        Controller controller1 = new Controller(repository1);
        try {
            controller1.clearFile();
        } catch (ControllerException e) {
            System.out.println("Controller1: " + e.getMessage());
        }
        controller1.add(statement1);

        MyIStatement statement2 = new CompStatement(new VarDeclStatement("a", new MyIntType()), new CompStatement(new VarDeclStatement("b", new MyIntType()), new CompStatement(new AssignStatement("a", new ArithmeticalExpression(new ValueExpression(new MyIntValue(2)), AritmeticalOperator.ADD, new ArithmeticalExpression(new ValueExpression(new MyIntValue(3)), AritmeticalOperator.MULTIPLY, new ValueExpression(new MyIntValue(5))))), new CompStatement(new AssignStatement("b", new ArithmeticalExpression(new VariableExpression("a"), AritmeticalOperator.ADD, new ValueExpression(new MyIntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        try {
            statement2.typecheck(new MyDictionary<>());
        } catch (StatementException | ExpressionException | KeyNotFoundException e) {
            System.out.println("Statement2: " + e.getMessage());
            return;
        }
        IRepository repository2 = new Repository("log2.txt");
        Controller controller2 = new Controller(repository2);
        try {
            controller2.clearFile();
        } catch (ControllerException e) {
            System.out.println("Controller2: " + e.getMessage());
        }
        controller2.add(statement2);

        MyIStatement statement3 = new CompStatement(new VarDeclStatement("a", new MyBoolType()), new CompStatement(new VarDeclStatement("v", new MyIntType()), new CompStatement(new AssignStatement("a", new ValueExpression(new MyBoolValue(true))), new CompStatement(new IfStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(2))), new AssignStatement("v", new ValueExpression(new MyIntValue(3))), new VariableExpression("a")), new PrintStatement(new VariableExpression("v"))))));
        try {
            statement3.typecheck(new MyDictionary<>());
        } catch (StatementException | ExpressionException | KeyNotFoundException e) {
            System.out.println("Statement3: " + e.getMessage());
            return;
        }
        IRepository repository3 = new Repository("log3.txt");
        Controller controller3 = new Controller(repository3);
        try {
            controller3.clearFile();
        } catch (ControllerException e) {
            System.out.println("Controller3: " + e.getMessage());
        }
        controller3.add(statement3);

        MyIStatement statement4 = new CompStatement(new VarDeclStatement("varf", new StringType()), new CompStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))), new CompStatement(new OpenRFileStatement(new VariableExpression("varf")), new CompStatement(new VarDeclStatement("varc", new MyIntType()), new CompStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"), new CompStatement(new PrintStatement(new VariableExpression("varc")), new CompStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"), new CompStatement(new PrintStatement(new VariableExpression("varc")), new CloseRFileStatement(new VariableExpression("varf"))))))))));
        try {
            statement4.typecheck(new MyDictionary<>());
        } catch (StatementException | ExpressionException | KeyNotFoundException e) {
            System.out.println("Statement4: " + e.getMessage());
            return;
        }
        IRepository repository4 = new Repository("log4.txt");
        Controller controller4 = new Controller(repository4);
        try {
            controller4.clearFile();
        } catch (ControllerException e) {
            System.out.println("Controller4: " + e.getMessage());
        }
        controller4.add(statement4);

        MyIStatement statement5 = new CompStatement(new VarDeclStatement("v", new RefType(new MyIntType())),
                new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(20))),
                        new CompStatement(new PrintStatement(new HeapReading(new VariableExpression("v"))),
                                new CompStatement(new HeapWriting("v", new ValueExpression(new MyIntValue(30))),
                                        new PrintStatement(new ArithmeticalExpression(new HeapReading(new VariableExpression("v")), AritmeticalOperator.ADD, new ValueExpression(new MyIntValue(5))))))));
        try {
            statement5.typecheck(new MyDictionary<>());
        } catch (StatementException | ExpressionException | KeyNotFoundException e) {
            System.out.println("Statement5: " + e.getMessage());
            return;
        }
        IRepository repository5 = new Repository("log5.txt");
        Controller controller5 = new Controller(repository5);
        try {
            controller5.clearFile();
        } catch (ControllerException e) {
            System.out.println("Controller5: " + e.getMessage());
        }
        controller5.add(statement5);

        MyIStatement statement6 = new CompStatement(new VarDeclStatement("v", new RefType(new MyIntType())),
                new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(20))),
                        new CompStatement(new VarDeclStatement("a", new RefType(new RefType(new MyIntType()))),
                                new CompStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(30))),
                                                new PrintStatement(new HeapReading(new HeapReading(new VariableExpression("a")))))))));
        try {
            statement6.typecheck(new MyDictionary<>());
        } catch (StatementException | ExpressionException | KeyNotFoundException e) {
            System.out.println("Statement6: " + e.getMessage());
            return;
        }
        IRepository repository6 = new Repository("log6.txt");
        Controller controller6 = new Controller(repository6);
        try {
            controller6.clearFile();
        } catch (ControllerException e) {
            System.out.println("Controller6: " + e.getMessage());
        }
        controller6.add(statement6);

        MyIStatement statement7 = new CompStatement(new VarDeclStatement("v", new RefType(new MyIntType())),
                new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(20))),
                        new CompStatement(new PrintStatement(new HeapReading(new VariableExpression("v"))),
                                new CompStatement(new HeapWriting("v", new ValueExpression(new MyIntValue(30))),
                                        new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(50))),
                                                new PrintStatement(new ArithmeticalExpression(new HeapReading(new VariableExpression("v")), AritmeticalOperator.ADD, new ValueExpression(new MyIntValue(5)))))))));
        try {
            statement7.typecheck(new MyDictionary<>());
        } catch (StatementException | ExpressionException | KeyNotFoundException e) {
            System.out.println("Statement7: " + e.getMessage());
            return;
        }
        IRepository repository7 = new Repository("log7.txt");
        Controller controller7 = new Controller(repository7);
        try {
            controller7.clearFile();
        } catch (ControllerException e) {
            System.out.println("Controller7: " + e.getMessage());
        }
        controller7.add(statement7);

        MyIStatement statement8 = new CompStatement(new VarDeclStatement("v", new MyIntType()),
                new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(4))),
                        new CompStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new MyIntValue(0)), RelationalOperator.GREATER), new CompStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v", new ArithmeticalExpression(new VariableExpression("v"), AritmeticalOperator.SUBTRACT, new ValueExpression(new MyIntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));
        try {
            statement8.typecheck(new MyDictionary<>());
        } catch (StatementException | ExpressionException | KeyNotFoundException e) {
            System.out.println("Statement8: " + e.getMessage());
            return;
        }
        IRepository repository8 = new Repository("log8.txt");
        Controller controller8 = new Controller(repository8);
        try {
            controller8.clearFile();
        } catch (ControllerException e) {
            System.out.println("Controller8: " + e.getMessage());
        }
        controller8.add(statement8);

        MyIStatement statement9 = new CompStatement(new VarDeclStatement("v", new MyIntType()),
                new CompStatement(new VarDeclStatement("a", new RefType(new MyIntType())),
                        new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(10))),
                                new CompStatement(new HeapAllocationStatement("a", new ValueExpression(new MyIntValue(22))),
                                        new CompStatement(new forkStatement(new CompStatement(new HeapWriting("a", new ValueExpression(new MyIntValue(30))),
                                                new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(32))),
                                                        new CompStatement(new PrintStatement(new VariableExpression("v")),
                                                                new PrintStatement(new HeapReading(new VariableExpression("a"))))))),
                                                new CompStatement(new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new HeapReading(new VariableExpression("a")))))))));
        try {
            statement9.typecheck(new MyDictionary<>());
        } catch (StatementException | ExpressionException | KeyNotFoundException e) {
            System.out.println("Statement9: " + e.getMessage());
            return;
        }
        IRepository repository9 = new Repository("log9.txt");
        Controller controller9 = new Controller(repository9);
        try {
            controller9.clearFile();
        } catch (ControllerException e) {
            System.out.println("Controller9: " + e.getMessage());
        }
        controller9.add(statement9);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", statement1.toString(), controller1));
        menu.addCommand(new RunExampleCommand("2", statement2.toString(), controller2));
        menu.addCommand(new RunExampleCommand("3", statement3.toString(), controller3));
        menu.addCommand(new RunExampleCommand("4", statement4.toString(), controller4));
        menu.addCommand(new RunExampleCommand("5", statement5.toString(), controller5));
        menu.addCommand(new RunExampleCommand("6", statement6.toString(), controller6));
        menu.addCommand(new RunExampleCommand("7", statement7.toString(), controller7));
        menu.addCommand(new RunExampleCommand("8", statement8.toString(), controller8));
        menu.addCommand(new RunExampleCommand("9", statement9.toString(), controller9));
        menu.show();
    }
}
