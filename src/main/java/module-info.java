module com.example.balatroremake {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.balatroremake to javafx.fxml;
    exports com.example.balatroremake;
    exports com.example.balatroremake.Objects;
    opens com.example.balatroremake.Objects to javafx.fxml;
    exports com.example.balatroremake.Scenes;
    opens com.example.balatroremake.Scenes to javafx.fxml;
}