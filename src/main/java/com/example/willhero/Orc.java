package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Orc extends Gameobject{
    private TranslateTransition orcjump;
    private TranslateTransition translate1;
    private ImageView orc;

    private static ArrayList<Double> platformstarts = new ArrayList<Double>();
    private static ArrayList<Double> platformsize = new ArrayList<Double>();


    private boolean flagonplatform = true;
    private AtomicBoolean flagonplatform1 = new AtomicBoolean(true);

    Orc(){
        orc = new ImageView();
        orc.setImage(new Image((new File("src/main/resources/Orc.png")).toURI().toString()));
        orc.setFitHeight(50);
        orc.setFitWidth(38);
        orc.setY(386);
    }

    public static void addplatformd(double i, double j){
        platformstarts.add(i);
        platformsize.add(j);
    }

    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(orc);
    }

    public void jump(){
        orcjump = Animations.translateTransition(orc, 300,0,-70, true, -1);
    }

    public void remove(AnchorPane mainpane){
        mainpane.getChildren().remove(orc);
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
        orcjump = Animations.translateTransition(orc, 300,0,-70, true, -1);
        orcjump.stop();
        TranslateTransition translate1 = new TranslateTransition(Duration.millis(100),orc);
        translate1.setCycleCount(1);
        translate1.setByX(60);
        translate1.setAutoReverse(false);
        translate1.play();
//        translate1.setOnFinished(e ->{
            TranslateTransition translate = new TranslateTransition(Duration.millis(100),orc);
            translate.setCycleCount(1);
            translate.setToY(3);
            translate.setAutoReverse(false);
            translate.play();
            translate.setOnFinished(e1 ->{
                if(flagonplatform == false ){     // plat start >=
                    int temp = ((Hero)hero).getSpacecount();
                    Double t = orc.getX();
                    int f = -1;
                    for(int i = 0; i < platformstarts.size();i++) {
                        System.out.println("start " +(platformstarts.get(i) - temp*(200))+ " " +t);
                        System.out.println("size " + (platformstarts.get(i) -temp*(200) + platformsize.get(i))+ " " +t);
                        if (Double.compare(platformstarts.get(i) - temp*(200), t) < 0 && Double.compare(platformstarts.get(i+1) - temp*(200), t) > 0) {
                            if (Double.compare((platformstarts.get(i) -temp*(200) + platformsize.get(i)), t) > 30) {
                                System.out.println("on platform  "+(i));
                                orcjump.play();
                                f = 0;
//                                temp = i;
                                break;
                            }
                        }
                    }
                    if(f == -1){
                        TranslateTransition translate2 = new TranslateTransition(Duration.millis(300),orc);
                        translate2.setCycleCount(1);
                        translate2.setToY(300);
                        translate2.setAutoReverse(false);
                        translate2.play();
                    }
                }
                else{
                    orcjump.play();
                }
            });
    }

}
