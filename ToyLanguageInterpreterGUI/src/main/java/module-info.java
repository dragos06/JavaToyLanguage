module com.example.a7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens com.example.a7 to javafx.fxml;
    opens com.example.a7.scenes to javafx.fxml;
    opens com.example.a7.tableViewModels to javafx.base;
    opens com.example.a7.repository to javafx.base;

    exports com.example.a7;
    exports com.example.a7.scenes;
}