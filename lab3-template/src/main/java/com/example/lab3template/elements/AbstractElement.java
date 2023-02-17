package com.example.lab3template.elements;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractElement implements Runnable {

    private Node node;

    private static Map<Integer, Double> map;
    private static Map<Integer, Double> mapX;

    public AbstractElement(Pane pane) {
        node = getElement();
        node.setTranslateY(250);
        node.setTranslateX(250);
        pane.getChildren().add(node);
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(node);
        if (map == null) {
            map = new HashMap<>();
            mapX = new HashMap<>();
            for (int i = -2500; i <= 2500; i = i + 1) {
                map.put(i, Math.sin(i/300.0) * 130 + 110);
                mapX.put(i, i/10.0);
            }
        }
    }

    @Override
    public void run() {
        final int[] i = {2400};
        final boolean[] lToR = {true};
        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(.00100),
                (ActionEvent event) -> {
            i[0] = lToR[0] ? i[0] + 1 : i[0] -1;
            node.setTranslateX(mapX.get(i[0]));
            node.setTranslateY(map.get(i[0]));
            if (i[0] >= 2500 || i[0] <= -2500) {
                lToR[0] = !lToR[0];
            }
        });

        tl.getKeyFrames().add(moveBall);
        tl.play();
    }

    protected abstract Node getElement();
}
