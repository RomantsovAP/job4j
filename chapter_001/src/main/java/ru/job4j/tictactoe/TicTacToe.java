package ru.job4j.tictactoe;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Игра крестики-нолики
 * @author Petr Arsentev
 * @version 1.0.0.0
 * @since 24.04.18
 */
public class TicTacToe extends Application {
    private static final String JOB4J = "Крестики-нолики www.job4j.ru";
    private final int size = 3;
    private final Figure3T[][] cells = new Figure3T[size][size];
    private final Logic3T logic = new Logic3T(cells);

    /**
     * рисуем белый квадрат с черными границами
     * @param x - координата X, 1,2,3
     * @param y - координата Y, 1,2,3
     * @param size - размер стороны квадрата в пикселах
     * @return - полученный квадрат
     */
    private Figure3T buildRectangle(int x, int y, int size) {
        Figure3T rect = new Figure3T();
        rect.setX(x * size);
        rect.setY(y * size);
        rect.setHeight(size);
        rect.setWidth(size);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        return rect;
    }

    /**
     * Рисуем нолик в клетке поля
     * @param x - координата X, 1,2,3
     * @param y - координата Y, 1,2,3
     * @param size - максимальный диаметр нолика
     * @return - группу, содержающую ровно 1 нолик
     */
    private Group buildMarkO(double x, double y, int size) {
        Group group = new Group();
        int radius = size / 2;
        Circle circle = new Circle(x + radius, y + radius, radius - 10);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        group.getChildren().add(circle);
        return group;
    }

    /**
     * Сообщение пользователю
     * @param message - текст сообщения
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(JOB4J);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Проверяет есть ли свободные клетки для хода
     * @return - есть ли свободные клетки для хода
     */
    private boolean checkState() {
        boolean gap = this.logic.hasGap();
        if (!gap) {
            this.showAlert("Все поля запонены! Начните новую Игру!");
        }
        return gap;
    }

    /**
     * Оповещает пользователя о выигрыше
     */
    private void checkWinner() {
        if (this.logic.isWinnerX()) {
            this.showAlert("Победили Крестики! Начните новую Игру!");
        } else if (this.logic.isWinnerO()) {
            this.showAlert("Победили Нолики! Начните новую Игру!");
        }
    }

    /**
     * Проверяет не закончилась ли игра победой одного из игроков
     * @return - есть победитель
     */
    private boolean weHaveAWinner() {
        return this.logic.isWinnerO() || this.logic.isWinnerX();
    }

    /**
     * Строит крестик в ячейке
     * @param x - координата X, 1,2,3
     * @param y - координата Y, 1,2,3
     * @param size - максимальный размер крестика
     * @return - группу с единственным крестиком внутри
     */
    private Group buildMarkX(double x, double y, int size) {
        Group group = new Group();
        group.getChildren().addAll(
                new Line(
                        x + 10, y  + 10,
                        x + size - 10, y + size - 10
                ),
                new Line(
                        x + size - 10, y + 10,
                        x + 10, y + size - 10
                )
        );
        return group;
    }

    /**
     * Обработчик события для игрового поля.
     * проверяет возможен ли следующий ход, если возможен - размещает очередной крестик или нолик
     * на игровом поле в ячейке на которую нажали левой или правой кнопкой мыши
     * @param panel - панель основной формы
     * @return событие мыши
     */
    private EventHandler<MouseEvent> buildMouseEvent(Group panel) {
        return event -> {
            Figure3T rect = (Figure3T) event.getTarget();
            if (this.checkState() && !rect.hasMarkX() && !rect.hasMarkO() && !weHaveAWinner()) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    rect.take(true);
                    panel.getChildren().add(
                            this.buildMarkX(rect.getX(), rect.getY(), 50)
                    );
                } else {
                    rect.take(false);
                    panel.getChildren().add(
                            this.buildMarkO(rect.getX(), rect.getY(), 50)
                    );
                }
                this.checkWinner();
                this.checkState();
            }
        };
    }

    /**
     * Строит сетку игрового поля, к каждой клетке добавляет обработчик события мыши
     * @return подготовленное игровое поле
     */
    private Group buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != this.size; y++) {
            for (int x = 0; x != this.size; x++) {
                Figure3T rect = this.buildRectangle(x, y, 50);
                this.cells[y][x] = rect;
                panel.getChildren().add(rect);
                rect.setOnMouseClicked(this.buildMouseEvent(panel));
            }
        }
        return panel;
    }

    /**
     * Начало игры
     * @param stage - окно приложения
     */
    @Override
    public void start(Stage stage) {
        BorderPane border = new BorderPane();
        HBox control = new HBox();
        control.setPrefHeight(40);
        control.setSpacing(10.0);
        control.setAlignment(Pos.BASELINE_CENTER);
        Button start = new Button("Начать");
        start.setOnMouseClicked(
                event -> border.setCenter(this.buildGrid())
        );
        control.getChildren().addAll(start);
        border.setBottom(control);
        border.setCenter(this.buildGrid());
        stage.setScene(new Scene(border, 300, 300));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
    }
}
