package com.example.willhero;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController implements Initializable {

    private Hero hero;

    @FXML
    private Parent root;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    private static User currentUser;
    private boolean onscreen = false;       //to check if any other pop-up is on
    private boolean onhomescreen = true;

    @FXML
    private AnchorPane rootmain;

    @FXML
    private ImageView Cursor_icon;

    @FXML
    private ImageView will_hero_name;
    @FXML
    private ImageView tap_icon;

    @FXML
    private ImageView setting_logo;
    @FXML
    private ImageView addnew_user;
    @FXML
    private ImageView save_load;
    @FXML
    private ImageView view_highscore;
    @FXML
    private ImageView quit_game;


    @FXML
    private Group add_new_user_popup;
    @FXML
    private ImageView addnewuser_exit;

    @FXML
    private Group viewhighscore_popup;
    @FXML
    private ImageView viewhighscore_exit;

    @FXML
    private Group saveloadgame_popup;
    @FXML
    private ImageView saveloadgame_exit;

    @FXML
    private Group exitgame_popup;
    @FXML
    private ImageView exitgame_exit;

    @FXML
    private Group setting_popup;
    @FXML
    private ImageView hide_setting_popup;

    @FXML
    private ImageView pause_gamebutton;
    @FXML
    private Group pause_game_popup;
    @FXML
    private ImageView hide_pause_game_popup;


    private boolean falg_col = false;

    private ArrayList<Cloud> clouds = new ArrayList<Cloud>();
    private ArrayList<Platform> platform = new ArrayList<Platform>();
    private ArrayList<Orc> orcs = new ArrayList<Orc>();
//    private ArrayList<ImageView> platform = new ArrayList<ImageView>();


    public void displaygame(Stage greeting_stage) throws IOException, InterruptedException {
        root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage = (Stage)(greeting_stage).getScene().getWindow();
        scene = new Scene(root, Color.rgb(29,200,255,1));
        scene.setFill(Color.rgb(29,200,255,1));
        stage.setScene(scene);
        stage.show();
    }

    public void moveclouds(){
        for(int i = 0; i< 13; i++){
            Cloud c = new Cloud();
            clouds.add(c);
        }

        int arry[] = {10,110,65,-20,75};
        int arrx[] = {-10,300,350,200,400};
        int arrt[] = {30000, 30000, 30000, 30000, 40000};
        for(int i = 0; i< 5; i++){
            clouds.get(i).setcloudx(arrx[i]);
            clouds.get(i).setcloudy(arry[i]);
            clouds.get(i).setOnscreen(true);
            rootmain.getChildren().add(clouds.get(i).getCloud());
            clouds.get(i).move(rootmain, arrx[i], -1500, arrt[i]);
        }

        clouds.get(6).moveetoe(rootmain);
        clouds.get(5).moveetoe(rootmain);
        AtomicInteger count = new AtomicInteger(7);
        Timeline t = new Timeline(new KeyFrame(Duration.seconds(5),e->{
            clouds.get(count.get()).moveetoe(rootmain);
            count.getAndIncrement();
            if(count.get() > 12)count.set(0);
            pause_gamebutton.toFront();
            tap_icon.toFront();
            will_hero_name.toFront();
            Cursor_icon.toFront();
            setting_logo.toFront();
        }));
        t.setCycleCount(Animation.INDEFINITE);
        t.play();
    }

    public void generateplatforms(){
        int c = 100;
        int r = 0;
        for(int i = 0; i< 13; i++){
            Platform p = new Platform(rootmain);
            platform.add(p);
            platform.get(i).setPlatformx(c+r);
            c += platform.get(i).getPlatform().getFitWidth()+ 40;
            r = (int)(Math.random()*120+100);
            System.out.println("  " + c+"   "+platform.get(i).getPlatform().getFitWidth()+"   "+i);
            platform.get(i).display(rootmain);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("IN game");
        hero = new Hero(rootmain);
        hero.jumphero();
        moveclouds();
        generateplatforms();
        Animations.translateTransition(will_hero_name, 1000,0,-3, true, -1).play();
        Animations.translateTransition(Cursor_icon, 300,0,-2, true, -1).play();


}


    @FXML
    void show_settingpopup(MouseEvent event) {
        if(!onscreen){
            setting_popup.toFront();
            TranslateTransition translate = new TranslateTransition(Duration.millis(400), setting_popup);
            translate.setToY((rootmain.getPrefHeight()+((Node)setting_popup).getBoundsInLocal().getWidth())/2);
            translate.play();
            onscreen = true;
        }
    }
    @FXML
    void hide_settingpopup(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.millis(300), setting_popup);
        translate.setToY(0);
        translate.play();
        onscreen = false;
    }

    @FXML
    void show_addnewuser_popup(MouseEvent event) {
        if(!onscreen){
            add_new_user_popup.toFront();
            TranslateTransition translate = new TranslateTransition(Duration.millis(400), add_new_user_popup);
            translate.setToX((rootmain.getPrefWidth()+((Node)add_new_user_popup).getBoundsInLocal().getWidth())/2);
            translate.play();
            onscreen = true;
        }
    }
    @FXML
    void hide_adduser_popup(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.millis(300), add_new_user_popup);
        translate.setToX(0);
        translate.play();
        onscreen = false;
    }

    @FXML
    void show_saveload_popup(MouseEvent event) {
        if(!onscreen) {
            saveloadgame_popup.toFront();
            TranslateTransition translate = new TranslateTransition(Duration.millis(400), saveloadgame_popup);
            translate.setToX((rootmain.getPrefWidth()+((Node)saveloadgame_popup).getBoundsInLocal().getWidth())/2);
            translate.play();
            onscreen = true;
        }
    }
    @FXML
    void hide_saveload_popup(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.millis(300), saveloadgame_popup);
        translate.setToX(0);
        translate.play();
        onscreen = false;
    }

    @FXML
    void show_highscore_popup(MouseEvent event) {
        if(!onscreen){
            viewhighscore_popup.toFront();
            TranslateTransition translate = new TranslateTransition(Duration.millis(400), viewhighscore_popup);
            translate.setToX((rootmain.getPrefWidth()+((Node)viewhighscore_popup).getBoundsInLocal().getWidth())/2);
            translate.play();
            onscreen = true;
        }
    }

    @FXML
    void hide_viewhighscore_popup(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.millis(300), viewhighscore_popup);
        translate.setToX(0);
        translate.play();
        onscreen = false;
    }

    @FXML
    void show_exit_popup(MouseEvent event) {
        if(!onscreen) {
            exitgame_popup.toFront();
            TranslateTransition translate = new TranslateTransition(Duration.millis(400), exitgame_popup);
            translate.setToX((rootmain.getPrefWidth()+((Node)exitgame_popup).getBoundsInLocal().getWidth())/2);
            translate.play();
            onscreen = true;
        }
    }


    @FXML
    void hide_exit_popup(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.millis(300), exitgame_popup);
        translate.setToX(0);
        translate.play();
        onscreen = false;
    }

    @FXML
    void exitgame(MouseEvent event) {
        //code to save the data
        stage = (Stage) rootmain.getScene().getWindow();
        stage.close();

    }

    @FXML
    void save_load_memory(MouseEvent event) {
        //code to save the data
        TranslateTransition translate = new TranslateTransition(Duration.millis(300), saveloadgame_popup);
        translate.setToX(0);
        translate.play();
        onscreen = false;
    }

    @FXML
    void addnewuser(MouseEvent event) {
        //code to add new user
        TranslateTransition translate = new TranslateTransition(Duration.millis(300), add_new_user_popup);
        translate.setToX(0);
        translate.play();
        onscreen = false;
    }
    @FXML
    void savesetting(MouseEvent event) {
        //save setting code
        TranslateTransition translate = new TranslateTransition(Duration.millis(300), setting_popup);
        translate.setToY(0);
        translate.play();
        onscreen = false;
    }

    @FXML
    void pause_gamepopup(MouseEvent event) {
        if(pause_gamebutton.getImage() != null) {
            if (!onscreen) {
                pause_game_popup.toFront();
                TranslateTransition translate = new TranslateTransition(Duration.millis(400), pause_game_popup);
                translate.setToY((rootmain.getPrefHeight() + ((Node) pause_game_popup).getBoundsInLocal().getWidth()) / 2);
                translate.play();
                onscreen = true;
            }
        }
    }

    @FXML
    void hide_pause_gamepopup(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.millis(300), pause_game_popup);
        translate.setToY(0);
        translate.play();
        onscreen = false;
    }
    @FXML
    void pause_exit_popup(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.millis(300), pause_game_popup);
        translate.setToY(0);
        translate.play();
        onscreen = false;
        if(!onscreen) {
            TranslateTransition translate1 = new TranslateTransition(Duration.millis(400), exitgame_popup);
            translate1.setToX((rootmain.getPrefWidth()+((Node)exitgame_popup).getBoundsInLocal().getWidth())/2);
            translate1.play();
            onscreen = true;
        }
    }

    public boolean checkCollision(ImageView imageView, ImageView imageView2){
        if(imageView.getBoundsInParent().intersects(imageView2.getBoundsInParent())){
//            System.out.println("on platform");
            return true;
        }
        return false;
    }

    AnimationTimer coll  = new AnimationTimer() {
        @Override
        public void handle(long l) {
            boolean flagcool = false;
            for(int i = 0; i< 13; i++){
                if(checkCollision(hero.getHero(), platform.get(i).getPlatform()) == true ){
                    hero.setonplatform(true);
                    System.out.println("on platform");
                    falg_col = true;
//                    hero.getMovedown().stop();
//                    hero.moveupplay();
                    return;
                }
            }
        }
    };


    AnimationTimer exitgame  = new AnimationTimer() {
        @Override
        public void handle(long l) {
            exitgame_popup.toFront();
            TranslateTransition translate = new TranslateTransition(Duration.millis(400), exitgame_popup);
            translate.setToX((rootmain.getPrefWidth()+((Node)exitgame_popup).getBoundsInLocal().getWidth())/2);
            translate.play();
            onscreen = true;
//            boolean flagcool = false;
//            for(int i = 0; i< 13; i++){
//                if(checkCollision(hero.getHero(), platform.get(i).getPlatform()) == true ){
//                    hero.setonplatform(true);
//                    System.out.println("on platform");
//                    falg_col = true;
////                    hero.getMovedown().stop();
////                    hero.moveupplay();
//                    return;
//                }
//            }
        }
    };



    @FXML
    void startgame(KeyEvent event) {
        if(!onscreen){
            if(event.getCode() == KeyCode.SPACE && onhomescreen){
                //Move All the Icons out of the screen
                Animations.translateTransition(will_hero_name, 400, rootmain.getPrefWidth(), 0, false, 1).play();
                Animations.translateTransition(Cursor_icon, 400, rootmain.getPrefWidth(), 0, false, 1).play();
                Animations.translateTransition(setting_logo, 200, -100, 0, false, 1).play();
                Animations.translateTransition(addnew_user, 200, -100, 0, false, 1).play();
                Animations.translateTransition(save_load, 200, -100, 0, false, 1).play();
                Animations.translateTransition(quit_game, 200, -100, 0, false, 1).play();
                Animations.translateTransition(view_highscore, 200, -100, 0, false, 1).play();
                FadeTransition tapf = new FadeTransition(Duration.millis(300), tap_icon);
                tapf.setFromValue(1.0);
                tapf.setToValue(0.0);
                tapf.play();
                onhomescreen = false;
                File im = new File("src/main/resources/pause_logo.png");
                pause_gamebutton.setImage(new Image(im.toURI().toString()));
            }
        }
       if(event.getCode() == KeyCode.SPACE && !onhomescreen && !onscreen){
           System.out.println("spacce detected");
            coll.stop();
           Thread thread1 = new Thread() {
               @Override
               public void run() {
                   hero.moveforward();
               }
           };


           Thread thread2 = new Thread(){
               @Override
               public void run(){
                   coll.start();
                   if(falg_col) {
                       System.out.println("on platform");
                   }
               }
           };



        Thread thread3 = new Thread(){
            @Override
            public void run(){
                int count = 5;
                for(int i = 0; i< 13; i++){
                    Animations.translateTransition(platform.get(i).getPlatform(), 300, -150, 0, false, 1).play();
                }
            }
        };

           thread1.start();
           thread3.start();
           thread2.start();

       }
        if(String.valueOf(event.getCode()) == "q" || String.valueOf(event.getCode()) == "Q"){
            if(!onscreen) {
//                root.getChildren().get(root.getChildren().size() - 1).toBack();
                exitgame_popup.toFront();
                TranslateTransition translate = new TranslateTransition(Duration.millis(400), exitgame_popup);
                translate.setToX((rootmain.getPrefWidth()+((Node)exitgame_popup).getBoundsInLocal().getWidth())/2);
                translate.play();
                onscreen = true;
            }
        }
    }

}