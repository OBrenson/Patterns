module com.example.lab4mvc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab4mvc to javafx.fxml;
    opens com.example.lab4mvc.model;
    exports com.example.lab4mvc;
}