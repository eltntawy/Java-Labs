package main.java.javafx.lab4;

import javafx.animation.*;
import javafx.animation.PathTransition.OrientationType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.ArcToBuilder;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathBuilder;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLCarRoudGameController implements Initializable {

    @FXML
    Path carPath;

    @FXML
    Path carPath1;

    @FXML
    ImageView car;

    Timeline timeline;
    PathTransition anim;

    SimpleIntegerProperty dir = new SimpleIntegerProperty(-1);
    SimpleIntegerProperty startValue = new SimpleIntegerProperty(-10);

    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

        startValue.bind(dir);
        startValue.multiply(dir);

        Path path = PathBuilder
                .create()
                .elements(
                        new MoveTo(100, 100),
                        ArcToBuilder.create().x(200).y(200).radiusX(500)
                                .radiusY(500).sweepFlag(true).build()).build();
        // carPath.setStyle(
        // ".path { -fx-fill: yellow;"
        // + "-fx-stroke: green;" + "-fx-stroke-width: 5;"
        // + "-fx-stroke-dash-array: 12 2 4 2;"
        // + "-fx-stroke-dash-offset: 6;"
        // + "-fx-stroke-line-cap: butt;}");

        car.setImage(new Image(getClass().getResourceAsStream("car_toy.png")));

        anim = PathTransitionBuilder.create().duration(new Duration(10000))
                .node(car).path(carPath)
                .orientation(OrientationType.ORTHOGONAL_TO_TANGENT)
                .interpolator(Interpolator.LINEAR)
                .cycleCount(Timeline.INDEFINITE).autoReverse(false).build();

        timeline = TimelineBuilder
                .create()
                .autoReverse(false)
                .cycleCount(Timeline.INDEFINITE)
                .keyFrames(
                        new KeyFrame(new Duration(0.0), new KeyValue(
                                startValue, 0)),
                        new KeyFrame(new Duration(0.0), new KeyValue(

                                startValue, 500)))
                .cycleCount(Timeline.INDEFINITE).build();

        anim.play();
        //carPath.setStyle("-fx-stroke-dash-offset: 6;-fx-stroke-dash-array: 12 2 4 2; -fx-stroke: green;");
        //timeline.setCycleCount(-1);

        carPath1.getStrokeDashArray().add(50.0);
        carPath1.getStrokeDashArray().add(50.0);
        carPath1.getStrokeDashArray().add(50.0);
        carPath1.getStrokeDashArray().add(50.0);
        carPath1.getStrokeDashArray().add(50.0);
        carPath1.getStrokeDashArray().add(50.0);
        carPath1.getStrokeDashArray().add(50.0);
        carPath1.getStrokeDashArray().add(50.0);

        car.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                // TODO Auto-generated method stub
                anim.setRate(anim.getRate() * -1);

                startValue.get();
                System.out.println("mouse click - " + startValue);
            }
        });
    }
}
