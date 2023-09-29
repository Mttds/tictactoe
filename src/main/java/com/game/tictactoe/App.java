package com.game.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.game.tictactoe.model.Tile;

/**
 * JavaFX App
 */
public class App extends Application {
    private static boolean turnX = true;
    private static boolean playable = true;
    private static List<Combo> combos = new ArrayList<>();

    private Tile[][] board = new Tile[3][3];
    private static Pane root = new Pane();

    private Parent createContent() {
        root.setPrefSize(600, 600);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);

                root.getChildren().add(tile);

                board[j][i] = tile;
            }
        }

        // horizontal
        for (int y = 0; y < 3; y++) {
            App.combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
        }

        // vertical
        for (int x = 0; x < 3; x++) {
            App.combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
        }

        // diagonals
        App.combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
        App.combos.add(new Combo(board[2][0], board[1][1], board[0][2]));

        return root;
    }

    public static boolean getTurnX() {
        return App.turnX;
    }

    public static void setTurnX(boolean b) {
        App.turnX = b;
    }

    public static boolean getPlayable() {
        return App.playable;
    }

    public static void setPlayable(boolean b) {
        App.playable = b;
    }

    public static void checkState() {
        for (Combo combo : App.combos) {
            if (combo.isComplete()) {
                App.playable = false;
                App.playWinAnimation(combo);
                break;
            }
        }
    }

    private static void playWinAnimation(Combo combo) {
        Line line = new Line();

        // basically a point
        line.setStartX(combo.tiles[0].getCenterX());
        line.setStartY(combo.tiles[0].getCenterY());
        line.setEndX(combo.tiles[0].getCenterX());
        line.setEndY(combo.tiles[0].getCenterY());

        root.getChildren().add(line);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(line.endXProperty(), combo.tiles[2].getCenterX()),
                        new KeyValue(line.endYProperty(), combo.tiles[2].getCenterY())));

        timeline.play();
    }

    private class Combo {
        private Tile[] tiles;

        public Combo(Tile... tiles) {
            this.tiles = tiles;
        }

        public boolean isComplete() {
            if (tiles[0].getValue().isEmpty())
                return false;

            return tiles[0].getValue().equals(tiles[1].getValue())
                    && tiles[0].getValue().equals(tiles[2].getValue());
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
