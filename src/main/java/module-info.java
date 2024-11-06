module com.example.mycart {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mycart to javafx.fxml;
    exports com.example.mycart;
}