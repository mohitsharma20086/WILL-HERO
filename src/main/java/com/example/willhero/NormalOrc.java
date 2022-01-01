package com.example.willhero;
// orc hit with weapon
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class NormalOrc extends Orc{
    private TranslateTransition orcjump;
    private TranslateTransition translate1;
    private ImageView normalorc;
    private double health = 1;

    NormalOrc(){
        super( 1);
        normalorc = new ImageView();
        normalorc.setImage(new Image((new File("src/main/resources/NormalOrc.png")).toURI().toString()));
        normalorc.setFitHeight(50);
        normalorc.setFitWidth(38);
        normalorc.setY(386);
    }

    @Override
    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(normalorc);
    }

    @Override
    public void remove(AnchorPane mainpane){
        mainpane.getChildren().remove(normalorc);
    }

    @Override
    public void setX(double x){
        normalorc.setX(x);
    }
    @Override
    public ImageView getImage(){
        return normalorc;
    }

    @Override
    public void jump(){
        orcjump = Animations.translateTransition(normalorc, 300,0,-70, true, -1);
//        orcjump.play();
    }

    @Override
    public void oncollide(Gameobject g){
        orcjump = Animations.translateTransition(normalorc, 300,0,-70, true, -1);
        orcjump.stop();
        if(g instanceof Weapon) health--;
        TranslateTransition translate1 = new TranslateTransition(Duration.millis(100),normalorc);
        translate1.setCycleCount(1);
        translate1.setByX(60);
        translate1.setAutoReverse(false);
        translate1.play();

        TranslateTransition translate = new TranslateTransition(Duration.millis(100),normalorc);
        translate.setCycleCount(1);
        translate.setToY(3);
        translate.setAutoReverse(false);
        translate.play();
        translate.setOnFinished(e1 ->{
            if(health <= 0){
                TranslateTransition translate2 = new TranslateTransition(Duration.millis(300), normalorc);
                translate2.setCycleCount(1);
                translate2.setToY(150);
                translate2.setAutoReverse(false);
                translate2.play();
                translate2.setOnFinished(e->{
                    normalorc.setImage(null);
                });

            }
            else {
                if (flagonplatform == false) {     // plat start >=
                    int temp = ((Hero) g).getSpacecount();
                    Double t = normalorc.getX();
                    int f = -1;
                    for (int i = 0; i < platformstarts.size(); i++) {
                        if (Double.compare(platformstarts.get(i) - temp * (200), t) < 0 && Double.compare(platformstarts.get(i + 1) - temp * (200), t) > 0) {
                            if (Double.compare((platformstarts.get(i) - temp * (200) + platformsize.get(i)), t) > 30) {
                                System.out.println("on platform  " + (i));
                                orcjump.play();
                                f = 0;
                                break;
                            }
                        }
                    }
                    if (f == -1) {
                        TranslateTransition translate2 = new TranslateTransition(Duration.millis(300), normalorc);
                        translate2.setCycleCount(1);
                        translate2.setToY(300);
                        translate2.setAutoReverse(false);
                        translate2.play();
                    }
                } else {
                    orcjump.play();
                }
            }
            });

    }

}
