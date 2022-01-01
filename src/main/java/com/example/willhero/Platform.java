package com.example.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

// no platform7   add the image

public class Platform{
    private static int platform_id = 0;
    private static int distance = 0;
    private ImageView platform;
    private boolean onscreen;
    private ArrayList<Orc> orcs = new ArrayList<Orc>();

    Platform(AnchorPane mainpane, int i){
        platform = new ImageView();
        onscreen = false;
        int r = (int)(Math.random()*10+1);
        if(i == 0){                 //set first platform
            platform.setImage(new Image((new File("src/main/resources/platforml.png")).toURI().toString()));
            platform.setFitHeight(125);
            platform.setFitWidth(450);
        }
        else{
            String path = "src/main/resources/platform"+r+".png";
            platform.setImage(new Image((new File(path)).toURI().toString()));
            platform.setFitHeight(120);
            platform.setFitWidth(450);
            if(r == 1){

                platform.setFitHeight(80);
                platform.setFitWidth(90);
            }
            else if(r == 2) {
//                platform.setImage(new Image((new File("src/main/resources/platform2.png")).toURI().toString()));
                platform.setFitHeight(100);
                platform.setFitWidth(83);
            }
            else if(r == 3) {
//                platform.setImage(new Image((new File("src/main/resources/platform3.png")).toURI().toString()));
                platform.setFitHeight(120);
                platform.setFitWidth(90);
            }
            else if(r == 4) {
//                platform.setImage(new Image((new File("src/main/resources/platform4.png")).toURI().toString()));
                platform.setFitHeight(120);
                platform.setFitWidth(90);
            }
            else if(r == 5) {
//                shi krna h
//                platform.setImage(new Image((new File("src/main/resources/platforml.png")).toURI().toString()));
                platform.setFitHeight(120);
                platform.setFitWidth(450);
            }
            else if(r == 6) {
//                platform.setImage(new Image((new File("src/main/resources/platform6.png")).toURI().toString()));
                platform.setFitHeight(120);
                platform.setFitWidth(500);
            }
            else if(r == 7) {
//                platform.setImage(new Image((new File("src/main/resources/platform8.png")).toURI().toString()));
                platform.setFitHeight(120);
                platform.setFitWidth(800);
            }
        }
        platform_id++;
        platform.setX(400);
        platform.setY(435);
    }

    Platform(AnchorPane mainpane){
        this(mainpane, 1);
    }

    public void setPlatformx(double x){
        platform.setX(x);
    }

    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(platform);
        platform.toFront();
    }

    public void remove(AnchorPane mainpane){
        mainpane.getChildren().remove(platform);
    }

    public void setPlatformy(double y){
        platform.setY(y);
    }

    public void setOnscreen(boolean onscreen) {
        this.onscreen = onscreen;
    }

    public ImageView getPlatform(){
        return platform;
    }

    public void move(AnchorPane mainpane, double start, double end, double time){
        if(!onscreen) {
            onscreen = true;
            mainpane.getChildren().add(platform);
        }
        Animations.translateTransitionmoveto(platform, time, start, end, false, 1).play();
    }

    public void moveetoe(AnchorPane mainpane){
        if(!onscreen) {
            onscreen = true;
            mainpane.getChildren().add(platform);
        }
        int k1 = (int)(Math.random()*250-20);
        int k2 = (int)(Math.random()*2);

        setPlatformx(1000);
        if(k2 == 1){
            setPlatformy(k1);
        }
        else{
            setPlatformy(-1*k1);
        }
        Animations.translateTransitionmoveto(platform, 50000, 0, -1500, false, 1).play();
    }

    public boolean collide() {
        return false;
    }

    public static void setplatform_id(int i){
        platform_id = i;
    }
}

