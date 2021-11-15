module com.parkermeyers.texteditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;


    opens com.parkermeyers.texteditor to javafx.fxml;
    exports com.parkermeyers.texteditor;
}