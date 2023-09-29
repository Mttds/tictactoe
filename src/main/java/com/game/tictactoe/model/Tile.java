package com.game.tictactoe.model;

import com.game.tictactoe.App;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    private Text text = new Text();

    public Tile() {
        Rectangle border = new Rectangle(200, 200);
        border.setFill(null); // transparent
        border.setStroke(Color.BLACK);

        text.setFont(Font.font(72));
        setAlignment(Pos.CENTER);

        getChildren().addAll(border, text);

        setOnMouseClicked(event -> {
            if (!App.getPlayable())
                return;

            if (event.getButton() == MouseButton.PRIMARY) {
                if (!App.getTurnX())
                    return;

                drawX();
                App.setTurnX(false);
                App.checkState();
            } else if (event.getButton() == MouseButton.SECONDARY) {
                if (App.getTurnX())
                    return;

                drawO();
                App.setTurnX(true);
                App.checkState();
            }
        });
    }

    public double getCenterX() {
        return getTranslateX() + 200 / 2;
    }

    public double getCenterY() {
        return getTranslateY() + 200 / 2;
    }

    public String getValue() {
        return text.getText();
    }

    private void drawX() {
        text.setText("X");
    }

    private void drawO() {
        text.setText("O");
    }
}
