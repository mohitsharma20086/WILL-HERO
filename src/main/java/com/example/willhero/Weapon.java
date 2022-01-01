package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Weapon {
    protected ImageView weapon;

    public Weapon(){

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
