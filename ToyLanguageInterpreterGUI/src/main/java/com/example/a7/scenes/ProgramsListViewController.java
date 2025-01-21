package com.example.a7.scenes;

import com.example.a7.HelloApplication;
import com.example.a7.exception.ExpressionException;
import com.example.a7.exception.KeyNotFoundException;
import com.example.a7.exception.StatementException;
import com.example.a7.model.adt.MyDictionary;
import com.example.a7.model.expressions.*;
import com.example.a7.model.statement.*;
import com.example.a7.model.types.*;
import com.example.a7.model.value.MyBoolValue;
import com.example.a7.model.value.MyIntValue;
import com.example.a7.model.value.StringValue;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgramsListViewController {
    @FXML
    private ListView<String> listView;

    @FXML
    private Button runButton;

    private Stage mainStage;

    private List<MyIStatement> statementsList = new ArrayList<>();
    private MyIStatement currentStatement;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private void runButtonHandler(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainWindow.fxml"));
        Parent root;
        Scene scene;
        try {
            this.currentStatement.typecheck(new MyDictionary<String, MyIType>());
            root = fxmlLoader.load();
            scene = new Scene(root, 600, 400);
        } catch (IOException | StatementException | ExpressionException | KeyNotFoundException e) {
            Stage errorStage = new Stage();
            errorStage.setTitle("Error");
            errorStage.initModality(Modality.WINDOW_MODAL);
            errorStage.initOwner(mainStage);
            errorStage.setScene(new Scene(new StackPane(new Label("Error: " + e.getMessage())), 500, 75));
            errorStage.show();
            return;
        }
        Stage newWindow = new Stage();
        newWindow.setTitle("Running Program");
        newWindow.setScene(scene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(mainStage);
        MainWindowController mainWindowController = fxmlLoader.getController();
        mainWindowController.setInitialStatement(this.currentStatement);
        mainWindowController.setMainStage(newWindow);
        newWindow.setOnCloseRequest(e -> {
            mainWindowController.stopExecution();
        });

        newWindow.show();
    }

    @FXML
    public void initialize() {
        MyIStatement statement1 = new CompStatement(new VarDeclStatement("v", new MyIntType()), new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(2))), new PrintStatement(new VariableExpression("v"))));
        MyIStatement statement2 = new CompStatement(new VarDeclStatement("a", new MyIntType()), new CompStatement(new VarDeclStatement("b", new MyIntType()), new CompStatement(new AssignStatement("a", new ArithmeticalExpression(new ValueExpression(new MyIntValue(2)), AritmeticalOperator.ADD, new ArithmeticalExpression(new ValueExpression(new MyIntValue(3)), AritmeticalOperator.MULTIPLY, new ValueExpression(new MyIntValue(5))))), new CompStatement(new AssignStatement("b", new ArithmeticalExpression(new VariableExpression("a"), AritmeticalOperator.ADD, new ValueExpression(new MyIntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        MyIStatement statement3 = new CompStatement(new VarDeclStatement("a", new MyBoolType()), new CompStatement(new VarDeclStatement("v", new MyIntType()), new CompStatement(new AssignStatement("a", new ValueExpression(new MyBoolValue(true))), new CompStatement(new IfStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(2))), new AssignStatement("v", new ValueExpression(new MyIntValue(3))), new VariableExpression("a")), new PrintStatement(new VariableExpression("v"))))));
        MyIStatement statement4 = new CompStatement(new VarDeclStatement("varf", new StringType()), new CompStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))), new CompStatement(new OpenRFileStatement(new VariableExpression("varf")), new CompStatement(new VarDeclStatement("varc", new MyIntType()), new CompStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"), new CompStatement(new PrintStatement(new VariableExpression("varc")), new CompStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"), new CompStatement(new PrintStatement(new VariableExpression("varc")), new CloseRFileStatement(new VariableExpression("varf"))))))))));
        MyIStatement statement5 = new CompStatement(new VarDeclStatement("v", new RefType(new MyIntType())), new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(20))), new CompStatement(new PrintStatement(new HeapReading(new VariableExpression("v"))), new CompStatement(new HeapWriting("v", new ValueExpression(new MyIntValue(30))), new PrintStatement(new ArithmeticalExpression(new HeapReading(new VariableExpression("v")), AritmeticalOperator.ADD, new ValueExpression(new MyIntValue(5))))))));
        MyIStatement statement6 = new CompStatement(new VarDeclStatement("v", new RefType(new MyIntType())), new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(20))), new CompStatement(new VarDeclStatement("a", new RefType(new RefType(new MyIntType()))), new CompStatement(new HeapAllocationStatement("a", new VariableExpression("v")), new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(30))), new PrintStatement(new HeapReading(new HeapReading(new VariableExpression("a")))))))));
        MyIStatement statement7 = new CompStatement(new VarDeclStatement("v", new RefType(new MyIntType())), new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(20))), new CompStatement(new PrintStatement(new HeapReading(new VariableExpression("v"))), new CompStatement(new HeapWriting("v", new ValueExpression(new MyIntValue(30))), new CompStatement(new HeapAllocationStatement("v", new ValueExpression(new MyIntValue(50))), new PrintStatement(new ArithmeticalExpression(new HeapReading(new VariableExpression("v")), AritmeticalOperator.ADD, new ValueExpression(new MyIntValue(5)))))))));
        MyIStatement statement8 = new CompStatement(new VarDeclStatement("v", new MyIntType()), new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(4))), new CompStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new MyIntValue(0)), RelationalOperator.GREATER), new CompStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v", new ArithmeticalExpression(new VariableExpression("v"), AritmeticalOperator.SUBTRACT, new ValueExpression(new MyIntValue(1)))))), new PrintStatement(new VariableExpression("v")))));
        MyIStatement statement9 = new CompStatement(new VarDeclStatement("v", new MyIntType()), new CompStatement(new VarDeclStatement("a", new RefType(new MyIntType())), new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(10))), new CompStatement(new HeapAllocationStatement("a", new ValueExpression(new MyIntValue(22))), new CompStatement(new forkStatement(new CompStatement(new HeapWriting("a", new ValueExpression(new MyIntValue(30))), new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(32))), new CompStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReading(new VariableExpression("a"))))))), new CompStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReading(new VariableExpression("a")))))))));
        //MyIStatement statement9 = new CompStatement(new VarDeclStatement("v", new MyIntType()), new CompStatement(new VarDeclStatement("a", new RefType(new MyIntType())), new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(10))), new CompStatement(new HeapAllocationStatement("a", new ValueExpression(new MyIntValue(22))), new CompStatement(new forkStatement(new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(32))), new CompStatement(new AssignStatement("v", new ValueExpression(new MyIntValue(32))), new CompStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReading(new VariableExpression("a"))))))), new CompStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReading(new VariableExpression("a")))))))));

        statementsList.add(statement1);
        statementsList.add(statement2);
        statementsList.add(statement3);
        statementsList.add(statement4);
        statementsList.add(statement5);
        statementsList.add(statement6);
        statementsList.add(statement7);
        statementsList.add(statement8);
        statementsList.add(statement9);

        //Platform.runLater(() -> {
        for (MyIStatement statement : statementsList) {
            listView.getItems().add(statement.toString());
        }

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                int selectedIndex = listView.getSelectionModel().getSelectedIndex();
                currentStatement = statementsList.get(selectedIndex);
            }
        });

        listView.getSelectionModel().select(0);
        runButton.setOnAction(this::runButtonHandler);
        //});
    }
}
