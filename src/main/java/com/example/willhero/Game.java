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


//#0098cc
//rgba(0,152,204,204)
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

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            TimeUnit.MILLISECONDS.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource("game.fxml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Stage stage1 = (Stage)(greeting_stage).getScene().getWindow();
//        Scene scene = new Scene(root, Color.rgb(29,200,255,1));
//        scene.setFill(Color.rgb(29,200,255,1));
//        stage1.setScene(scene);
//        stage1.show();
//    }

    public static void main(String[] args) {
        launch();
    }
}

