package com.example.willhero;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;


//#0098cc
//rgba(0,152,204,204)
public class Game extends Application{
    static Stage greeting_stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.greeting_stage = stage;
        stage.setResizable(false);
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Will Hero");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("greeting_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 934.0, 511.0,Color.rgb(29,200,255,1));
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}
