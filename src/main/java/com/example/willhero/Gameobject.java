package com.example.willhero;

import javafx.scene.layout.AnchorPane;

public abstract class Gameobject {
    public abstract void move(AnchorPane mainpane);
    public abstract boolean collide();
}
