package com.example.willhero;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Hero {
    private SequentialTransition jump;
    private TranslateTransition translate1;
    private ImageView hero;


    private boolean going_up = true;
    private TranslateTransition translate2;
    private TranslateTransition translate3;

    Hero(){
        translate1 = new TranslateTransition(Duration.millis(300));
        translate1.setNode(hero);
        translate1.setCycleCount(Animation.INDEFINITE);
        translate1.setFromY(0);
        translate1.setToY(-55);
        translate1.setAutoReverse(true);


        translate2 = new TranslateTransition(Duration.millis(300));
        translate2.setNode(hero);
        translate2.setCycleCount(Animation.INDEFINITE);
        translate2.setFromY(0);
        translate2.setToY(-55);
        translate2.setAutoReverse(false);
        translate3 = new TranslateTransition(Duration.millis(300));
        translate3.setNode(hero);
        translate3.setCycleCount(Animation.INDEFINITE);
        translate3.setFromY(0);
        translate3.setToY(55);
        translate3.setAutoReverse(false);
    }

    public void jumphero(){
    }

}
