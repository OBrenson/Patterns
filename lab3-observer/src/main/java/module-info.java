module com.example.lab3observer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab3observer to javafx.fxml;
    exports com.example.lab3observer;
}