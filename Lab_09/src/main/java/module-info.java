module com.example.lab_09 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab_09 to javafx.fxml;
    exports com.example.lab_09;
}