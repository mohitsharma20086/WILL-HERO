package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Weapon {
    private ImageView weapon;

    public Weapon(){
        weapon = new ImageView();
        weapon.setImage(new Image((new File("src/main/resources/ChestClosed.png")).toURI().toString()));
        weapon.setFitHeight(55);
        weapon.setFitWidth(70);
        weapon.setY(375);
    }

    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(weapon);
    }

    public void setX(double x){
        weapon.setX(x);
    }

    public ImageView getImage(){
        return weapon;
    }
}
