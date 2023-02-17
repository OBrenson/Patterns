module com.example.lab3template {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab3template to javafx.fxml;
    exports com.example.lab3template;
}