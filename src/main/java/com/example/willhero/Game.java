package com.example.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;


//#0098cc
//rgba(0,152,204,204)
public class Game extends Application implements Serializable {
    private Stage greeting_stage;

    public static Timeline delay(double time)
    {
        return new Timeline(new KeyFrame(Duration.millis(time), e -> { }));
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.greeting_stage = stage;
        stage.setResizable(false);
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Will Hero");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("greeting_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600,Color.rgb(29,200,255,1));
        stage.setScene(scene);
        stage.show();
//        startgame();
    }

    public void startgame() {
        new SequentialTransition(delay(10000)).play();
        try {
            new SequentialTransition(delay(1000)).play();
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("game.fxml"));
            Stage stage = (Stage)(greeting_stage).getScene().getWindow();
            Scene scene = new Scene(root, Color.rgb(29,200,255,1));
            scene.setFill(Color.rgb(29,200,255,1));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}


//
//package com.example.willhero;
//
//        import javafx.animation.KeyFrame;
//        import javafx.animation.SequentialTransition;
//        import javafx.animation.Timeline;
//        import javafx.application.Application;
//        import javafx.event.ActionEvent;
//        import javafx.fxml.FXML;
//        import javafx.fxml.FXMLLoader;
//        import javafx.fxml.Initializable;
//        import javafx.scene.Node;
//        import javafx.scene.Parent;
//        import javafx.scene.Scene;
//        import javafx.scene.control.Button;
//        import javafx.scene.image.Image;
//        import javafx.scene.paint.Color;
//        import javafx.stage.Stage;
//        import javafx.util.Duration;
//
//        import java.io.IOException;
//        import java.io.Serializable;
//        import java.net.URL;
//        import java.util.ResourceBundle;
//
//
////#0098cc
////rgba(0,152,204,204)
//public class Game extends Application implements Initializable, Serializable {
//
//    public static Timeline delay(double time)
//    {
//        return new Timeline(new KeyFrame(Duration.millis(time), e -> { }));
//    }
//
//    @Override
//    public void start(Stage stage) throws IOException {
//        stage.setResizable(false);
//        Image icon = new Image("icon.png");
//        stage.getIcons().add(icon);
//        stage.setTitle("Will Hero");
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("greeting_page.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1280, 720,Color.rgb(29,200,255,1));
//        stage.setScene(scene);
//        stage.show();
//
//    }
////
////    @Override
////    public void initialize(URL url, ResourceBundle resourceBundle) {
////        new SequentialTransition(delay(1000)).play();
//
////        try {
////            new SequentialTransition(delay(1000)).play();
////            Parent root = null;
////            root = FXMLLoader.load(getClass().getResource("game.fxml"));
////            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
////            Scene scene = new Scene(root, Color.rgb(29,200,255,1));
////            scene.setFill(Color.rgb(29,200,255,1));
////            stage.setScene(scene);
////            stage.show();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}
