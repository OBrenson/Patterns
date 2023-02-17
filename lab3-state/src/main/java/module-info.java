module com.example.lab3state {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab3state to javafx.fxml;
    exports com.example.lab3state;
}