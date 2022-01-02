package com.example.willhero;

import java.util.ArrayList;

//patter problem - singleton

public class HighScore {
    private static HighScore highScore = null;
    private static ArrayList<String> usersname = new ArrayList<>();
    private static ArrayList<String> hidghscore = new ArrayList<>();
//
//    private HighScore() {
//    }
//
//    public static void addhighscore() {
//        if (highScore == null) {
//            highScore = new HighScore();
//        }
//        return comp;
//        public void setCurrentScore(int currentscore) {
//            this.currentScore = currentscore;
//            highScore=Math.max(highScore,currentscore);
//        }
//    }
//
//    private LengthComparator() {
//    }
//
//    public int compare(String s1, String s2) {
//        return s1.length() - s2.length();
//
//    }
}
