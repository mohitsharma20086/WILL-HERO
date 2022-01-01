package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;

public class Chest extends Gameobject{
    private ImageView chest;
    private TranslateTransition chestjump;
    private boolean opened =  false;

    public Chest(){
        chest = new ImageView();
        chest.setImage(new Image((new File("src/main/resources/ChestClosed.png")).toURI().toString()));
        chest.setFitHeight(55);
        chest.setFitWidth(70);
        chest.setY(383);
    }

    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(chest);
    }

    public void setX(double x){
        chest.setX(x);
    }

    public void oncollide(Gameobject g){
        if(g instanceof Hero ){
            if(opened == false){
                chest.setImage(null);
                chest.setImage(new Image((new File("src/main/resources/ChestOpen.png")).toURI().toString()));
//                if((int)(Math.random()*3) == 1){
                    ((Hero)g).addweapon((int)(Math.random()*2));
                ((Hero)g).addweapon(1);
//                }
//                else{
//                    ((Hero)g).addcoin(5);
//                }
                opened = true;
            }
        }
        else if(g instanceof Weapon){
            chest.setImage(null);
            chest.setImage(new Image((new File("src/main/resources/ChestOpen.png")).toURI().toString()));
//                if((int)(Math.random()*3) == 1){
//            ((Weapon) g).getHero().addweapon((int)(Math.random()*2));
            ((Weapon) g).getHero().addweapon(1);
//                }
//                else{
//                    ((Weapon) g).getHero().addcoin(5);
//                }
            opened = true;
        }
        else{
            TranslateTransition movefor = Animations.translateTransition(chest, 100, 70, 0, false, 1);
            movefor.play();
        }
    }

    public void jump(){
        chestjump = Animations.translateTransition(chest, 300,0,-5, true, -1);
        chestjump.play();
    }

    public ImageView getImage(){
        return chest;
    }

    @Override
    public void remove(AnchorPane mainpane) {
            mainpane.getChildren().remove(chest);
    }
}
