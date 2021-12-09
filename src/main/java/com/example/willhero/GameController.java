package com.example.willhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class GameController {

    private static User currentUser;
    private boolean ocscreen = false;       //to check if any other pop-up is on

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private ImageView Cursor_icon;

    @FXML
    private Group add_new_user_popup;

    @FXML
    private ImageView quit_game;

    @FXML
    private ImageView save_load;

    @FXML
    private ImageView saveloadgame_exit;

    @FXML
    private Group saveloadgame_popup;

    @FXML
    private ImageView setting_logo;

    @FXML
    private ImageView tap_icon;

    @FXML
    private ImageView view_highscore;

    @FXML
    private ImageView will_hero_name;
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
    void show_exit_popup(MouseEvent event) {
        if(!ocscreen){
            if(saveloadgame_popup.getOpacity() == 0)
                saveloadgame_popup.setOpacity(1);
            ocscreen = true;
        }
        else {
            if (saveloadgame_popup.getOpacity() == 1)
                saveloadgame_popup.setOpacity(0);
            ocscreen = false;
        }
    }


    @FXML
    void hide_exit_popup(MouseEvent event) {

    }

    @FXML
    void show_saveload_popup(MouseEvent event) {
        if(!ocscreen){
            if(saveloadgame_popup.getOpacity() == 0)
                saveloadgame_popup.setOpacity(1);
            ocscreen = true;
        }
        else {
            if (saveloadgame_popup.getOpacity() == 1)
                saveloadgame_popup.setOpacity(0);
            ocscreen = false;
        }
    }

    @FXML
    void hide_saveload_popup(MouseEvent event) {
        if(saveloadgame_popup.getOpacity() == 1) {
            saveloadgame_popup.setOpacity(0);
            ocscreen = false;
        }
    }
}
