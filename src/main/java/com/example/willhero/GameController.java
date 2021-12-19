package com.example.willhero;
//image.setImage(null);
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

//    private ImageView clouds[];
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






    @FXML
    private ImageView hero;
    @FXML
    private ImageView cloud1;
    @FXML
    private ImageView cloud2;
    @FXML
    private ImageView cloud3;
    @FXML
    private ImageView cloud4;
    @FXML
    private ImageView cloud5;
    @FXML
    private ImageView cloud6;
     @FXML
    private ImageView cloud7;
     @FXML
    private ImageView cloud8;
    @FXML
    private ImageView cloud9;
    @FXML
    private ImageView cloud10;
    @FXML
    private ImageView cloud11;
    @FXML
    private ImageView cloud12;
    @FXML
    private ImageView cloud13;
    private ArrayList<ImageView> clouds = new ArrayList<ImageView>();
    private ArrayList<TranslateTransition> cloudstransitions = new ArrayList<TranslateTransition>();

    private TranslateTransition herojump;
    private TranslateTransition translate1;
    private SequentialTransition s;
    private boolean flag = false;
    private boolean flagcloud = false;


    public TranslateTransition translateTransitionmoveto(Node n, double time, double distanceXs ,double distanceXe, boolean repeat, int cycle){
        TranslateTransition translate = new TranslateTransition(Duration.millis(time),n);
        if(cycle == -1){
            translate.setCycleCount(TranslateTransition.INDEFINITE);
        }
        else{
            translate.setCycleCount(cycle);
        }
        translate.setToX(distanceXe);
        translate.setFromX(distanceXs);
        translate.setAutoReverse(repeat);
        return translate;
    }


    public void moveclouds(){
        clouds.add(cloud1);     clouds.add(cloud2);
        clouds.add(cloud3);     clouds.add(cloud4);
        clouds.add(cloud5);
        clouds.add(cloud6);     clouds.add(cloud7);
        clouds.add(cloud8);     clouds.add(cloud9);
        clouds.add(cloud10);     clouds.add(cloud11);
        clouds.add(cloud12);     clouds.add(cloud13);

        if(flagcloud == false) {
            flagcloud = true;
            for (int i = 0; i < 5; i++) {
                cloudstransitions.add(translateTransition(clouds.get(i), 15000, -1050, 0, false, 1));
                cloudstransitions.get(i).play();
            }
        }
        AtomicInteger count = new AtomicInteger(5);
        Timeline t = new Timeline(new KeyFrame(Duration.seconds(2),e->{
            int k = (int)(Math.random()*300);
            int k1 = (int)(Math.random()*90);
            int k2 = (int)(Math.random()*2);

            // random number should be in a limit
            //going too up or down
            if(k2 == 1){
                clouds.get(count.get()).setY(k1);
            }
            else{
                clouds.get(count.get()).setY(-1*k1);
            }
            translateTransitionmoveto(clouds.get(count.get()), 15000, k, -1500, false, 1).play();
            count.getAndIncrement();
            if(count.get() > 12)count.set(5);
        }));
        t.setCycleCount(Animation.INDEFINITE);
        t.play();
    }


    public void displaygame(Stage greeting_stage) throws IOException, InterruptedException {
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        Stage stage = (Stage)(greeting_stage).getScene().getWindow();
        Scene scene = new Scene(root, Color.rgb(29,200,255,1));
        scene.setFill(Color.rgb(29,200,255,1));
        stage.setScene(scene);
        stage.show();
    }

    public TranslateTransition translateTransition(Node n, double time, double distanceX,double distanceY, boolean repeat, int cycle){
        TranslateTransition translate = new TranslateTransition(Duration.millis(time),n);
        if(cycle == -1){
            translate.setCycleCount(TranslateTransition.INDEFINITE);
        }
        else{
            translate.setCycleCount(cycle);
        }
        translate.setByX(distanceX);
        translate.setByY(distanceY);
        translate.setAutoReverse(repeat);
        return translate;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translate1 = new TranslateTransition(Duration.millis(300),hero);
        translate1.setCycleCount(Animation.INDEFINITE);
        translate1.setToY(-50);
        translate1.setAutoReverse(true);
        herojump = translateTransition(hero, 300,0,-50, true, -1);
        herojump.play();
        translateTransition(will_hero_name, 1000,0,-3, true, -1).play();
        translateTransition(Cursor_icon, 300,0,-2, true, -1).play();
        if(cloud2 != null){
            moveclouds();
        }

}

    @FXML
    void gotogame(MouseEvent event)  throws IOException {
        root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, Color.rgb(29,200,255,1));
        scene.setFill(Color.rgb(29,200,255,1));

        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void show_settingpopup(MouseEvent event) {
        if(!onscreen){
            TranslateTransition translate = new TranslateTransition(Duration.millis(400), setting_popup);
            translate.setToY((rootmain.getPrefHeight()+((Node)setting_popup).getBoundsInLocal().getWidth())/2);
//            translate.setToY(setting_popup.getLayoutX() + rootmain.getPrefHeight()/2);
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
            TranslateTransition translate = new TranslateTransition(Duration.millis(400), saveloadgame_popup);
            translate.setToX((rootmain.getPrefWidth()+((Node)saveloadgame_popup).getBoundsInLocal().getWidth())/2);
//            translate.setToX(saveloadgame_popup.getLayoutX() + (rootmain.getPrefWidth()) / 2);
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
            TranslateTransition translate = new TranslateTransition(Duration.millis(400), viewhighscore_popup);
            translate.setToX((rootmain.getPrefWidth()+((Node)viewhighscore_popup).getBoundsInLocal().getWidth())/2);
//            translate.setToX(viewhighscore_popup.getLayoutX() + rootmain.getPrefWidth()/2);
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
            TranslateTransition translate = new TranslateTransition(Duration.millis(400), exitgame_popup);
            translate.setToX((rootmain.getPrefWidth()+((Node)exitgame_popup).getBoundsInLocal().getWidth())/2);
//            translate.setToX(exitgame_popup.getLayoutX() + (rootmain.getPrefWidth()) / 2);
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

    @FXML
    void startgame(KeyEvent event) {
        if(onscreen == false){
            if(event.getCode() == KeyCode.SPACE && onhomescreen){
                translateTransition(will_hero_name, 400, rootmain.getPrefWidth(), 0, false, 1).play();
                translateTransition(Cursor_icon, 400, rootmain.getPrefWidth(), 0, false, 1).play();
                translateTransition(setting_logo, 200, -100, 0, false, 1).play();
                translateTransition(addnew_user, 200, -100, 0, false, 1).play();
                translateTransition(save_load, 200, -100, 0, false, 1).play();
                translateTransition(quit_game, 200, -100, 0, false, 1).play();
                translateTransition(view_highscore, 200, -100, 0, false, 1).play();
//                moveclouds();
                FadeTransition tapf = new FadeTransition(Duration.millis(300), tap_icon);
                tapf.setFromValue(1.0);
                tapf.setToValue(0.0);
                tapf.play();
                onhomescreen = false;
                File im = new File("src/main/resources/pause_logo.png");
                pause_gamebutton.setImage(new Image(im.toURI().toString()));
            }
        }
       if(event.getCode() == KeyCode.SPACE && !onhomescreen){
           herojump.stop();
           TranslateTransition movefor = translateTransition(hero, 100, 50, 0, false, 1);
           TranslateTransition translate = new TranslateTransition(Duration.millis(300),hero);
           translate.setCycleCount(1);
           translate.setToY(0);
           translate.setAutoReverse(false);

           if(flag == false){
               flag = true;
               s = new SequentialTransition(movefor,translate,translate1);
               s.play();
           }
           else{
               s.stop();
               s = new SequentialTransition(movefor,translate,translate1);
               s.play();
           }
        }
        if(String.valueOf(event.getCode()) == "q" || String.valueOf(event.getCode()) == "Q"){
            if(!onscreen) {
                TranslateTransition translate = new TranslateTransition(Duration.millis(400), exitgame_popup);
                translate.setToX((rootmain.getPrefWidth()+((Node)exitgame_popup).getBoundsInLocal().getWidth())/2);
                translate.play();
                onscreen = true;
            }
        }
    }


}
