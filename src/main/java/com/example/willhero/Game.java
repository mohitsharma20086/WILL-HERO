package com.example.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;



public class Game extends Application implements Serializable{
    private Stage greeting_stage;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        this.greeting_stage = stage;
        stage.setResizable(false);
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Will Hero");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("greeting_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600,Color.rgb(29,200,255,1));
        GameController c = new GameController();
        stage.setScene(scene);
        stage.show();

        Timeline display = new Timeline( new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    c.displaygame(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }));
        display.play();

    }

    public static void main(String[] args) {
        launch();
    }
}



//    public void generateplatforms(){
//        int c = 100;
//        int r = 0;
//
//        Platform p1 = new Platform(rootmain, 0);
//        platform.add(p1);
//        platform.get(0).setPlatformx(c+r);
//        c += platform.get(0).getPlatform().getFitWidth();
//        r = 50;
//        platform.get(0).display(rootmain);
//
//        for(int i = 1; i< 13; i++){
//            Platform p = new Platform(rootmain);
//            platform.add(p);
//            platform.get(i).setPlatformx(c+r);
//            c += platform.get(i).getPlatform().getFitWidth();
//            r = 50;
//            platform.get(i).display(rootmain);
//            System.out.println(platform.get(i).getPlatform().getFitWidth());
//        }
//    }



//    public void generateorcs(){
//        for(int i = 1; i< platform.size(); i++){
//            if(platform.get(i).getPlatform().getFitWidth() >= 300){
//                for(int j = 0; j < Math.random()*platform.get(i).getPlatform().getFitWidth()/200 +1; j++){
//                    Orc o = new Orc();
//                    orcs.add(o);
//                }
//            }
//            Orc o = new Orc();
//            orcs.add(o);
//
//        }
//    }


