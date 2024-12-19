module com.example.balatroremake {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.balatroremake to javafx.fxml;
    exports com.example.balatroremake;
}