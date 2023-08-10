module com.hirbu.game2048 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.hirbu.game2048 to javafx.fxml;
    exports com.hirbu.game2048;
    exports com.hirbu.game2048.controllers;
    opens com.hirbu.game2048.controllers to javafx.fxml;
}