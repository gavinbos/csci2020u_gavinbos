module com.example.lab_05 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.lab_05 to javafx.fxml;
    exports com.example.lab_05;
}