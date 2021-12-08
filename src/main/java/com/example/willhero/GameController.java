package com.example.willhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class GameController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button start_button;

    public void gotogame(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//        scene = new Scene(fxmlLoader.load(), 934.0, 511.0);
        scene = new Scene(root, Color.rgb(29,200,255,1));
        scene.setFill(Color.rgb(29,200,255,1));
        stage.setScene(scene);
        stage.show();
    }

//    class greeting_page extends Thread{
//        @Override
//        public void run(){
//            try{
//                Thread.sleep(1000);
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
//                Scene scene = new Scene(fxmlLoader.load(), 934.0, 511.0, Color.rgb(29,200,255,1));
//                stage.setScene(scene);
//                stage.show();
//                rootPane.getScene()
//            }
//            catch (InterruptedException | IOException e){
//                System.out.println(e);
//            }
//
//        }
//    }
}
