module com.game.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.game.tictactoe to javafx.fxml;
    exports com.game.tictactoe;
}
