package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Sword extends Weapon{

    public Sword(){
        weapon = new ImageView();
        weapon.setImage(new Image((new File("src/main/resources/Sword.png")).toURI().toString()));
        weapon.setFitHeight(55);
        weapon.setFitWidth(70);
        weapon.setY(375);
    }

}
