package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class Orc extends Gameobject{
    private TranslateTransition orcjump;
    private TranslateTransition translate1;
    private ImageView orc;

    private boolean flagonplatform = true;
    private AtomicBoolean flagonplatform1 = new AtomicBoolean(true);

    Orc(){
        orc = new ImageView();
        orc.setImage(new Image((new File("src/main/resources/Orc.png")).toURI().toString()));
        orc.setFitHeight(50);
        orc.setFitWidth(38);
        orc.setY(386);
    }

    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(orc);
    }

    public void jump(){
        orcjump = Animations.translateTransition(orc, 300,0,-70, true, -1);
        orcjump.play();
    }


    public void setX(double x){
        orc.setX(x);
    }

    public void moveforward(){
        flagonplatform = false;
        orcjump.stop();
        TranslateTransition movefor = Animations.translateTransition(orc, 100, 70, 0, false, 1);
        TranslateTransition translate = new TranslateTransition(Duration.millis(300),orc);
        translate.setCycleCount(1);
        translate.setToY(3);
        translate.setAutoReverse(false);
        movefor.play();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        if(another_space == true){
//            movefor.setOnFinished(e ->{
//                TranslateTransition translate1 = new TranslateTransition(Duration.millis(300),hero);
//                translate1.setCycleCount(1);
//                translate1.setByY(5);
//                translate1.setAutoReverse(false);
//                translate1.play();
//                another_space = false;
//                movefor.play();
//                translate.play();
//            });
//        }
        translate.play();
        translate.setOnFinished(e -> {
            if(flagonplatform == false){
                TranslateTransition translate1 = new TranslateTransition(Duration.millis(300),orc);
                translate1.setCycleCount(1);
                translate1.setToY(300);
                translate1.setAutoReverse(false);
                translate1.play();
            }
            else {
                orcjump.play();
            }
//            herojump.play();
        });
    }

    public ImageView getImage(){
        return orc;
    }


    public boolean checkCollision(ImageView imageView, ImageView imageView2){
        if(imageView.getBoundsInParent().intersects(imageView2.getBoundsInParent())){
            return true;
        }
        return false;
    }

    public void setonplatform(boolean f){
        flagonplatform = f;
    }

    public void oncollide(Gameobject hero){
//        System.out.println("here");
    }

}
