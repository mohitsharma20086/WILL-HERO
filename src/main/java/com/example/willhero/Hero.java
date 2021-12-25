package com.example.willhero;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;

public class Hero {
    private TranslateTransition herojump;
    private TranslateTransition translate1;
    private ImageView hero;
    private boolean flag = false;
    private boolean flagonplatform = true;


//    private TranslateTransition moveup;
//    private TranslateTransition movedown;

    Hero(AnchorPane mainpane){
        hero = new ImageView();
        hero.setImage(new Image((new File("src/main/resources/knight.png")).toURI().toString()));
        hero.setFitHeight(50);
        hero.setFitWidth(38);
        hero.setY(386);
        hero.setX(320);
        mainpane.getChildren().add(hero);
//
//        moveup = Animations.translateTransitionmovetoY(hero,300, 0 , -55, false, 1);
//        movedown = Animations.translateTransitionmovetoY(hero,600, 0 , 110, false, 1);
//        moveup.setOnFinished(e -> {
//            movedown.play();
//        });
    }

    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(hero);
    }

    public void jumphero(){
        herojump = Animations.translateTransition(hero, 300,0,-55, true, -1);
//        herojump.setOnFinished(e ->{
//            if(flagonplatform == false){
//                System.out.println("gone");
//            }
//            else herojump.play();
//        });
        herojump.play();
    }

    public void moveforward(){
//        movedown.stop();
//        moveup.stop();
        flagonplatform = false;
        herojump.stop();
        TranslateTransition movefor = Animations.translateTransition(hero, 100, 70, 0, false, 1);
        TranslateTransition translate = new TranslateTransition(Duration.millis(300),hero);
        translate.setCycleCount(1);
        translate.setToY(3);
        translate.setAutoReverse(false);
//        if(flag) translate1.stop();
//        else flag = true;
        movefor.play();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        translate.play();
        translate.setOnFinished(e -> {
            if(flagonplatform == false){
                System.out.println("gone");
                TranslateTransition translate1 = new TranslateTransition(Duration.millis(300),hero);
                translate1.setCycleCount(1);
                translate1.setToY(300);
                translate1.setAutoReverse(false);
                translate1.play();
            }
            else herojump.play();
//            herojump.play();
        });
    }

    public ImageView getHero(){
        return hero;
    }

//    public void moveupplay(){
//        moveup.play();
//    }
//
//    public TranslateTransition getMovedown() {
//        return movedown;
//    }
//
//    public TranslateTransition getMoveup() {
//        return moveup;
//    }

//
//    AnimationTimer coll  = new AnimationTimer() {
//        @Override
//        public void handle(long l) {
//            boolean flagcool = false;
//            for(int i = 0; i< 13; i++){
//                if(checkCollision(hero, platform.get(i).getPlatform()) == true ){
//                    falg_col = true;
//                    hero.getMovedown().stop();
//                    hero.moveupplay();
//                    return;
//                }
//            }
//        }
//    };

    public boolean checkCollision(ImageView imageView, ImageView imageView2){
        if(imageView.getBoundsInParent().intersects(imageView2.getBoundsInParent())){
            return true;
        }
        return false;
    }

    public void setonplatform(boolean f){
        flagonplatform = f;
    }
}
