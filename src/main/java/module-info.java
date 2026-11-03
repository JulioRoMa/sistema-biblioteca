module com.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    exports com.biblioteca;
    opens com.biblioteca to javafx.fxml;
}
