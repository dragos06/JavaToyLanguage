package com.example.a7.scenes;

import com.example.a7.controller.Controller;
import com.example.a7.exception.ControllerException;
import com.example.a7.exception.RepoException;
import com.example.a7.model.adt.*;
import com.example.a7.model.state.PrgState;
import com.example.a7.model.statement.MyIStatement;
import com.example.a7.model.value.MyIValue;
import com.example.a7.model.value.StringValue;
import com.example.a7.repository.IRepository;
import com.example.a7.repository.Repository;
import com.example.a7.tableViewModels.HeapTableViewModel;
import com.example.a7.tableViewModels.SymTableViewModel;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;

import static java.util.stream.Collectors.toList;

public class MainWindowController {
    private MyIStatement initialStatement;
    private Stage mainStage;

    public void setInitialStatement(MyIStatement initialStatement) {
        this.initialStatement = initialStatement;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    public Button runOneStepButton;

    @FXML
    public ListView<String> prgStatesTableView;

    @FXML
    private TableView<HeapTableViewModel> heapTableView;

    @FXML
    private TableColumn<HeapTableViewModel, Integer> heapAddressColumn;

    @FXML
    private TableColumn<HeapTableViewModel, MyIValue> heapValueColumn;

    @FXML
    private ListView<String> fileTableView;

    @FXML
    private TextField noPrgStatesField;

    @FXML
    private TableView<SymTableViewModel> symTableView;

    @FXML
    private TableColumn<SymTableViewModel, String> symNameColumn;

    @FXML
    private TableColumn<SymTableViewModel, MyIValue> symValueColumn;

    @FXML
    private ListView<String> outputListView;

    @FXML
    private ListView<String> exeListView;

    private IRepository repository;
    private Controller controller;
    private PrgState currentPrgState;

    InvalidationListener listener = new InvalidationListener() {
        @Override
        public void invalidated(Observable observable) {
            update();
        }
    };

    @FXML
    private void runOneStepButtonHandler(ActionEvent e) {
        try {
            this.controller.oneStepGUI();
        } catch (ControllerException ex) {
            Stage errorStage = new Stage();
            errorStage.setTitle("Error");
            errorStage.initModality(Modality.WINDOW_MODAL);
            errorStage.initOwner(mainStage);
            errorStage.setScene(new Scene(new StackPane(new Label("Error: " + ex.getMessage())), 500, 75));
            errorStage.show();
        }
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            repository = new Repository("log.txt");
            try {
                repository.clearFile();
            } catch (RepoException e) {
                Stage errorStage = new Stage();
                errorStage.setTitle("Error");
                errorStage.initModality(Modality.WINDOW_MODAL);
                errorStage.initOwner(mainStage);
                errorStage.setScene(new Scene(new StackPane(new Label("Error" + e.getMessage())), 500, 75));
                errorStage.show();
                return;
            }
            currentPrgState = new PrgState(new MyDictionary<String, MyIValue>(), new MyStack<MyIStatement>(), new MyList<String>(), this.initialStatement, new FileTable<StringValue, BufferedReader>(), new MyHeap());
            repository.add(currentPrgState);
            repository.addListener(listener);
            controller = new Controller(repository);
            controller.startThreads();

            this.heapAddressColumn.setCellValueFactory(new PropertyValueFactory<>("heapAddress"));
            this.heapValueColumn.setCellValueFactory(new PropertyValueFactory<>("heapValue"));
            this.symNameColumn.setCellValueFactory(new PropertyValueFactory<>("symName"));
            this.symValueColumn.setCellValueFactory(new PropertyValueFactory<>("symValue"));

            prgStatesTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    int selectedIndex = prgStatesTableView.getSelectionModel().getSelectedIndex();
                    if (selectedIndex >= 0) {
                        currentPrgState = repository.getPrgList().get(selectedIndex);
                        populateUncommonViews();
                    }
                }
            });
            update();
            runOneStepButton.setOnAction(this::runOneStepButtonHandler);
        });
    }

    public void stopExecution() {
        this.controller.endThreads();
    }

    private void populateUncommonViews() {
        symTableView.getItems().setAll(currentPrgState.getSymTable().getMap().entrySet().stream().map(e -> new SymTableViewModel(e.getKey(), e.getValue())).toList());
        exeListView.getItems().setAll(currentPrgState.getExeStack().toStringGUI());
    }

    public void update() {
        noPrgStatesField.setText(Integer.toString(repository.getPrgList().size()));
        heapTableView.getItems().setAll(currentPrgState.getHeapTable().getHeap().entrySet().stream().map(e -> new HeapTableViewModel(e.getKey(), e.getValue())).toList());
        symTableView.getItems().setAll(currentPrgState.getSymTable().getMap().entrySet().stream().map(e -> new SymTableViewModel(e.getKey(), e.getValue())).toList());
        fileTableView.getItems().setAll(currentPrgState.getFileTable().toStringGUI());
        outputListView.getItems().setAll(currentPrgState.getOutput().toStringGUI());
        exeListView.getItems().setAll(currentPrgState.getExeStack().toStringGUI());
        prgStatesTableView.getItems().setAll(repository.getPrgList().stream().map(e -> Integer.toString(e.getId())).toList());
    }
}
