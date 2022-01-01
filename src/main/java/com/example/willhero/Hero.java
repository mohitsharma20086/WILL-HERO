//store platform in an arraylist and then do with thw list
package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Hero extends Gameobject {
    private int coin_collected =  0;
    private Weapon[] w = new Weapon[2];
    private Weapon currentweapon = null;
    private AnchorPane rootmain;
    private ArrayList<Double> platformstarts = new ArrayList<Double>();
    private ArrayList<Double> platformsize = new ArrayList<Double>();

    private TranslateTransition herojump;
    private ImageView hero;

    private boolean flagonplatform = true;
    private AtomicBoolean another_space = new AtomicBoolean(false);
    private boolean flagexit = false;
    private boolean another_space1 = false;
    private int spacecount = 0;


    Hero(AnchorPane mainpane){
        rootmain = mainpane;
        hero = new ImageView();
        hero.setImage(new Image((new File("src/main/resources/knight.png")).toURI().toString()));
        hero.setFitHeight(50);
        hero.setFitWidth(38);
        hero.setY(386);
        hero.setX(320);
        mainpane.getChildren().add(hero);
    }

    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(hero);
    }

    public void addcoin(int x){
        coin_collected += x;
    }

    public void addweapon(int i){
        if(w[i] == null){
            if(i == 0) {
                w[i] = new Sword();
                w[i].display(rootmain);
            }
            else {
                w[i] = new Throwing_knives();
            }
        }
    }

    public void setcurrentweapon(int i){
        currentweapon = w[i];
    }

    public void addplatformd(double i, double j){
        platformstarts.add(i);
        platformsize.add(j);
    }

    public void jump(){
        herojump = Animations.translateTransition(hero, 300,0,-70, true, -1);
        herojump.play();
    }

    public void moveforward(){
        flagonplatform = false;
        herojump.stop();
//        another_space.set(false);

        TranslateTransition movefor = Animations.translateTransition(hero, 70, 5, 0, false, 1);
        TranslateTransition translate = new TranslateTransition(Duration.millis(300),hero);
        translate.setCycleCount(1);
        translate.setToY(3);
        translate.setAutoReverse(false);
        movefor.setOnFinished(e -> {
            TranslateTransition translateup = new TranslateTransition(Duration.millis(60), hero);
            translateup.setCycleCount(1);
            translateup.setByY(-50);
            translateup.setAutoReverse(false);
            translateup.play();

            translate.play();
        });
        movefor.play();

        translate.setOnFinished(e -> {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            if(flagonplatform == false ){     // plat start >=
                Double t = hero.getX();
                int f = -1;
                for(int i = 1; i < platformstarts.size();i++) {
                    if (Double.compare(platformstarts.get(i - 1) - spacecount*(200), t) < 0 && Double.compare(platformstarts.get(i) - spacecount*(200), t) > 0) {
                        if (Double.compare(platformstarts.get(i - 1)  - spacecount*(200) + platformsize.get(i - 1), t) > 0) {
                            System.out.println("on platform");
                            herojump.play();
                            f = 0;
                        }
                    }
                }
                if(f == -1){
                    flagexit = true;
                    TranslateTransition translate1 = new TranslateTransition(Duration.millis(300),hero);
                    translate1.setCycleCount(1);
                    translate1.setToY(300);
                    translate1.setAutoReverse(false);
                    translate1.play();
                    }
            }
                else{
                    herojump.play();
            }
        });
    }

    public ImageView getImage(){
        return hero;
    }


    public void setonplatform(boolean f){
        flagonplatform = f;
    }

    public boolean isFlagexit() {
        return flagexit;
    }

    public void setAnother_space() {
        System.out.println("In set space");
        another_space1 = true;
        this.another_space.set(true);
        spacecount++;
    }

    public void moveback(){
        Animations.translateTransition(hero, 3000, -70, 0, false, 1).play();
    }

    @Override
    public void oncollide(Gameobject g){

    }
    @Override
    public void remove(AnchorPane mainpane) {
    }


}
