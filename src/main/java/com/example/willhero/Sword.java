package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Sword extends Weapon{

    private TranslateTransition jumpwithhero;

    public Sword(Hero h){
        super(h);
        weapon = new ImageView();
        weapon.setImage(new Image((new File("src/main/resources/Sword.png")).toURI().toString()));
        weapon.setFitHeight(15);
        weapon.setFitWidth(70);
        weapon.setY(410);
        weapon.setX(325);
        jumpwithhero = Animations.translateTransition(weapon, 300,0,-70, true, -1);
    }

    public void move(AnchorPane pane){

    }

    public void movewithhero(){
        System.out.println("here");
        jumpwithhero.play();
    }

    public void stopmovewithhero(){
        jumpwithhero.stop();
    }
}
