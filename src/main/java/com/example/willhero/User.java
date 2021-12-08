package com.example.willhero;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private int currentScore;
    private int highScore;

    User(String name) {
        this.name=name;
        this.currentScore=0;
        this.highScore=0;

    }
    public void setCurrentScore(int currentscore) {
        this.currentScore = currentscore;
        highScore=Math.max(highScore,currentscore);
    }

    public String getName() {return this.name;}
    public int getCurrentscore() {
        return currentScore;
    }
}
