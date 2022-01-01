package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class Throwing_knives extends Weapon {
    private ImageView knives;

    private boolean flagonplatform = true;
    private AtomicBoolean flagonplatform1 = new AtomicBoolean(true);

    Throwing_knives(){
        knives = new ImageView();
        knives.setImage(new Image((new File("src/main/resources/Knife.png")).toURI().toString()));
        knives.setFitHeight(50);
        knives.setFitWidth(38);
        knives.setY(386);
    }

    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(knives);
    }


    public void setX(double x){
        knives.setX(x);
    }



}
