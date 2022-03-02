module com.example.lab_04 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.lab_04 to javafx.fxml;
    exports com.example.lab_04;
}