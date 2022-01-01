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

    static GameController g = new GameController();

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

    @FXML
    private Group game_over_popup;


    private boolean falg_col = false;

    private ArrayList<Cloud> clouds = new ArrayList<Cloud>();
    private ArrayList<Platform> platform = new ArrayList<Platform>();
    private ArrayList<Gameobject> gameobjects = new ArrayList<Gameobject>();

    private ArrayList<TranslateTransition> moveplatforms = new ArrayList<TranslateTransition>();
    private ArrayList<TranslateTransition> moveorcs = new ArrayList<TranslateTransition>();

    private ArrayList<TranslateTransition> moveplatformsback = new ArrayList<TranslateTransition>();
    private ArrayList<TranslateTransition> moveorcsback = new ArrayList<TranslateTransition>();



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
            pause_gamebutton.toFront();
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


    public void removeplatforms(){
        Platform.setplatform_id(0);Platform.setplatform_id(0);
        for(int i = platform.size() -1; i >= 0; i--){
            platform.get(i).remove(rootmain);
            platform.remove(i);
        }
    }

    public void generateplatforms(){
        int c = 100;
        int r = 0;

        Platform p1 = new Platform(rootmain, 0);
        platform.add(p1);
        platform.get(0).setPlatformx(c+r);
        hero.addplatformd(c+r,p1.getplatformw(0));
        c += platform.get(0).getPlatform().getFitWidth()+ 40;

        r = (int)(Math.random()*120+60);
        platform.get(0).display(rootmain);

        for(int i = 1; i< 13; i++){
            Platform p = new Platform(rootmain);
            platform.add(p);
            platform.get(i).setPlatformx(c+r);
            hero.addplatformd(c+r,p.getplatformw(i));
            c += platform.get(i).getPlatform().getFitWidth()+ 140;
            r = (int)(Math.random()*120+100);
            platform.get(i).display(rootmain);
        }

//        hero.setPlatform(platform);
    }

    public void removegameobj(){
        for(int i = gameobjects.size() -1; i >= 0; i--){
            gameobjects.get(i).remove(rootmain);
            gameobjects.remove(i);
        }
    }
    public void generategameobj(){
        for(int i = 1; i< platform.size(); i++){
            if(platform.get(i).getPlatform().getFitWidth() >= 300){
                if ((int)(Math.random() * 8) == 2) {
                    Chest c = new Chest();
                    System.out.println("In here");
                    c.setX(platform.get(i).getPlatform().getX() + 120);
                    gameobjects.add(c);
                }
                else{
                    Orc o = new Orc();
                    o.setX(platform.get(i).getPlatform().getX()+70);
                    gameobjects.add(o);
                    for(int j = 1; j < Math.random()*platform.get(i).getPlatform().getFitWidth()/200 +1; j++) {
                        if ((int)(Math.random() * 10) == 2) {
                            Chest c = new Chest();
                            System.out.println("In here");
                            c.setX(platform.get(i).getPlatform().getX() + 120 * (j+1));
                            gameobjects.add(c);
                        }
                        else{
                            Orc o1 = new Orc();
                            o1.setX(platform.get(i).getPlatform().getX() + 120 * (j + 1));
                            gameobjects.add(o1);
                        }
                    }
                }

            }
        }
        for(int i = 1; i< gameobjects.size(); i++){
            gameobjects.get(i).display(rootmain);
            gameobjects.get(i).jump();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("IN game");
        hero = new Hero(rootmain);
        hero.jump();
        moveclouds();
        generateplatforms();
        generategameobj();
        Animations.translateTransition(will_hero_name, 1000,0,-3, true, -1).play();
        Animations.translateTransition(Cursor_icon, 300,0,-2, true, -1).play();
        exitgame.start();

        for(int i = 0; i< platform.size(); i++){
            moveplatforms.add(Animations.translateTransition(platform.get(i).getPlatform(), 100, -200, 0, false, 1));
        }
        for(int i = 0; i< gameobjects.size(); i++){
            moveorcs.add(Animations.translateTransition(gameobjects.get(i).getImage(), 100, -200, 0, false, 1));
        }
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

//    public boolean checkCollision(ImageView imageView, ImageView imageView2){
//        if(){
////            System.out.println("on platform");
//            return true;
//        }
//        return false;
//    }

    public static void checkcoll(){
        g.collplatform.start();
    }

    AnimationTimer collplatform  = new AnimationTimer() {
        @Override
        public void handle(long l) {
            boolean flagcool = false;
            for(int i = 0; i< 13; i++){
                if(hero.getImage().getBoundsInParent().intersects(platform.get(i).getPlatform().getBoundsInParent()) == true ){
                    hero.setonplatform(true);
                    falg_col = true;
                    return;
                }
            }
        }
    };

    AnimationTimer collorc  = new AnimationTimer() {
        @Override
        public void handle(long l) {
            boolean flagcool = false;
            for(int i = 0; i< gameobjects.size(); i++){
                if(hero.getImage().getBoundsInParent().intersects(gameobjects.get(i).getImage().getBoundsInParent()) == true ){
                    gameobjects.get(i).oncollide(hero);
                    return;
                }
            }
        }
    };


    AnimationTimer exitgame  = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(hero.isFlagexit() == true) {
                for (int i = 0; i < moveplatformsback.size(); i++) {
                    moveplatformsback.get(i).stop();
                }
                for (int i = 0; i < moveorcsback.size(); i++) {
                    moveorcsback.get(i).stop();
                }
                collplatform.stop();
                collorc.stop();
                onscreen = true;
                game_over_popup.toFront();
                TranslateTransition translate = new TranslateTransition(Duration.millis(400), game_over_popup);
                translate.setToX(-(rootmain.getPrefWidth() + ((Node) game_over_popup).getBoundsInLocal().getWidth()) / 2);
                translate.play();
                exitgame.stop();
            }
        }
    };


    @FXML
    void restart_game(MouseEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        removeplatforms();
        generateplatforms();
        removegameobj();
        generategameobj();
        displaygame(stage);
    }


    @FXML
    void startgame(KeyEvent event) throws InterruptedException {
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
           System.out.println("was in space");
           for (int i = 0; i < moveplatformsback.size(); i++) {
               moveplatformsback.get(i).stop();
           }
           for (int i = 0; i < moveorcsback.size(); i++) {
               moveorcsback.get(i).stop();
           }
           collplatform.stop();
           collorc.stop();
           hero.setAnother_space();
           Thread thread1 = new Thread() {
               @Override
               public void run() {
                   hero.moveforward();
               }
           };

           Thread thread2 = new Thread(){
               @Override
               public void run(){
                   collplatform.start();
                   collorc.start();
               }
           };

           Thread thread4 = new Thread(){
               @Override
               public void run(){
                   for(int i = 0; i< moveorcs.size(); i++){
                       moveorcs.get(i).play();
                   }
               }
           };

           Thread thread3 = new Thread(){
               @Override
               public void run(){
                   for(int i = 0; i< moveplatforms.size(); i++){
                       moveplatforms.get(i).play();
                   }
               }
           };

           thread1.start();
           thread3.start();
           thread2.start();
           thread4.start();

//           try {
//               Thread.sleep(300);
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }
//           moveplatforms.get(moveplatforms.size()-1).setOnFinished(e->{
//               double temp = -70;
//               System.out.println(temp);
//               for (int i = 0; i < moveplatformsback.size(); i++) {
//                   moveplatformsback.remove(i);
//               }
//               for (int i = 0; i < moveorcsback.size(); i++) {
//                   moveorcsback.remove(i);
//               }
//               for (int i = 0; i < platform.size(); i++) {
//                   moveplatformsback.add(Animations.translateTransition(platform.get(i).getPlatform(), 2000, temp, 0, false, 1));
//               }
//               for (int i = 0; i < gameobjects.size(); i++) {
//                   moveorcsback.add(Animations.translateTransition(gameobjects.get(i).getImage(), 2000, temp, 0, false, 1));
//               }
//               for (int i = 0; i < moveplatformsback.size(); i++) {
//                   moveplatformsback.get(i).play();
//               }
//               for (int i = 0; i < moveorcsback.size(); i++) {
//                   moveorcsback.get(i).play();
//               }
//               hero.moveback();
//           }
//           );


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