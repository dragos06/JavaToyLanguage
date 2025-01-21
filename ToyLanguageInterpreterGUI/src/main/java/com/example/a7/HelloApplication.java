package com.example.a7;

import com.example.a7.model.expressions.*;
import com.example.a7.model.statement.*;
import com.example.a7.model.types.MyBoolType;
import com.example.a7.model.types.MyIntType;
import com.example.a7.model.types.RefType;
import com.example.a7.model.types.StringType;
import com.example.a7.model.value.MyBoolValue;
import com.example.a7.model.value.MyIntValue;
import com.example.a7.model.value.StringValue;
import com.example.a7.scenes.ProgramsListViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
//    MyIStatement statement1 = new CompStatement(new VarDeclStatement("v", new MyIntType()), new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(2))), new PrintStatement(new VariableExpression("v"))));
//    MyIStatement statement2 = new CompStatement(new VarDeclStatement("a", new MyIntType()), new CompStatement(new VarDeclStatement("b", new MyIntType()), new CompStatement(new AssignStatement("a", new ArithmeticalExpression(new ValueExpression(new MyIntValue(2)), AritmeticalOperator.ADD, new ArithmeticalExpression(new ValueExpression(new MyIntValue(3)), AritmeticalOperator.MULTIPLY, new ValueExpression(new MyIntValue(5))))), new CompStatement(new AssignStatement("b", new ArithmeticalExpression(new VariableExpression("a"), AritmeticalOperator.ADD, new ValueExpression(new MyIntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
//    MyIStatement statement3 = new CompStatement(new VarDeclStatement("a", new MyBoolType()), new CompStatement(new VarDeclStatement("v", new MyIntType()), new CompStatement(new AssignStatement("a", new ValueExpression(new MyBoolValue(true))), new CompStatement(new IfStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(2))), new AssignStatement("v", new ValueExpression(new MyIntValue(3))), new VariableExpression("a")), new PrintStatement(new VariableExpression("v"))))));
//    MyIStatement statement4 = new CompStatement(new VarDeclStatement("varf", new StringType()), new CompStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))), new CompStatement(new OpenRFileStatement(new VariableExpression("varf")), new CompStatement(new VarDeclStatement("varc", new MyIntType()), new CompStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"), new CompStatement(new PrintStatement(new VariableExpression("varc")), new CompStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"), new CompStatement(new PrintStatement(new VariableExpression("varc")), new CloseRFileStatement(new VariableExpression("varf"))))))))));
//    MyIStatement statement5 = new CompStatement(new VarDeclStatement("v", new RefType(new MyIntType())), new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(20))), new CompStatement(new PrintStatement(new HeapReading(new VariableExpression("v"))), new CompStatement(new HeapWriting("v", new ValueExpression(new MyIntValue(30))), new PrintStatement(new ArithmeticalExpression(new HeapReading(new VariableExpression("v")), AritmeticalOperator.ADD, new ValueExpression(new MyIntValue(5))))))));
//    MyIStatement statement6 = new CompStatement(new VarDeclStatement("v", new RefType(new MyIntType())), new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(20))), new CompStatement(new VarDeclStatement("a", new RefType(new RefType(new MyIntType()))), new CompStatement(new HeapAllocationStatement("a", new VariableExpression("v")), new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(30))), new PrintStatement(new HeapReading(new HeapReading(new VariableExpression("a")))))))));
//    MyIStatement statement7 = new CompStatement(new VarDeclStatement("v", new RefType(new MyIntType())), new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(20))), new CompStatement(new PrintStatement(new HeapReading(new VariableExpression("v"))), new CompStatement(new HeapWriting("v", new ValueExpression(new MyIntValue(30))), new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(50))), new PrintStatement(new ArithmeticalExpression(new HeapReading(new VariableExpression("v")), AritmeticalOperator.ADD, new ValueExpression(new MyIntValue(5)))))))));
//    MyIStatement statement8 = new CompStatement(new VarDeclStatement("v", new MyIntType()), new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(4))), new CompStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new MyIntValue(0)), RelationalOperator.GREATER), new CompStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v", new ArithmeticalExpression(new VariableExpression("v"), AritmeticalOperator.SUBTRACT, new ValueExpression(new MyIntValue(1)))))), new PrintStatement(new VariableExpression("v")))));
//    MyIStatement statement9 = new CompStatement(new VarDeclStatement("v", new MyIntType()), new CompStatement(new VarDeclStatement("a", new RefType(new MyIntType())), new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(10))), new CompStatement(new HeapAllocationStatement("a", new ValueExpression(new MyIntValue(22))), new CompStatement(new forkStatement(new CompStatement(new HeapWriting("a", new ValueExpression(new MyIntValue(30))), new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(32))), new CompStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReading(new VariableExpression("a"))))))), new CompStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReading(new VariableExpression("a")))))))));

    @Override
    public void start(Stage stage) throws IOException {
//        List<MyIStatement> prgs = new ArrayList<>();
//        prgs.add(statement1);
//        prgs.add(statement2);
//        prgs.add(statement3);
//        prgs.add(statement4);
//        prgs.add(statement5);
//        prgs.add(statement6);
//        prgs.add(statement7);
//        prgs.add(statement8);
//        prgs.add(statement9);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProgramsListView.fxml"));
        Parent root = fxmlLoader.load();
        ProgramsListViewController controller = fxmlLoader.getController();
        controller.setMainStage(stage);

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("ToyLanguage Programs");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}