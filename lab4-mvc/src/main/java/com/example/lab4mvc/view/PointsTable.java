package com.example.lab4mvc.view;

import com.example.lab4mvc.controller.Controller;
import com.example.lab4mvc.model.Point;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class PointsTable {

    private final Controller controller;

    public PointsTable(Pane pane, Controller controller) {
        createTable(pane);
        this.controller = controller;
    }

    private void createTable(Pane pane) {

        TableView<Point> table = new TableView<>();

        table.setPrefWidth(200);
        table.setPrefHeight(400);
        table.setEditable(true);
        TableColumn<Point, String> xCol = new TableColumn<>("X");
        xCol.setCellValueFactory(new PropertyValueFactory<>("x"));
        xCol.setCellFactory(TextFieldTableCell.<Point>forTableColumn());
        xCol.setOnEditCommit(
                this::editPoint
        );
        xCol.setPrefWidth(50);
        table.getColumns().add(xCol);

        TableColumn<Point, String> yCol = new TableColumn<>("Y");
        yCol.setCellValueFactory(new PropertyValueFactory<>("y"));
        yCol.setCellFactory(TextFieldTableCell.<Point>forTableColumn());
        yCol.setPrefWidth(50);
        yCol.setEditable(false);
        table.getColumns().add(yCol);

        TableColumn<Point, Void> delCol = new TableColumn<>("Delete");
        Callback<TableColumn<Point, Void>, TableCell<Point, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Point, Void> call(final TableColumn<Point, Void> param) {
                final TableCell<Point, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Point point = getTableView().getItems().get(getIndex());
                            controller.remove(point);
                            getTableView().getItems().remove(getIndex());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        delCol.setCellFactory(cellFactory);
        table.getColumns().add(delCol);

        pane.getChildren().add(table);

        Button addBtn = new Button("ADD");
        addBtn.setOnMouseClicked(event -> {
            table.getItems().add(new Point());
        });
        pane.getChildren().add(addBtn);
    }

    private void editPoint(TableColumn.CellEditEvent<Point, String> t) {
        try {
            Point point = controller.addPoint(Double.parseDouble(t.getNewValue()));
            t.getTableView().getItems().set(t.getTablePosition().getRow(), point);
        } catch (NumberFormatException ignored) {

        }
    }
}
