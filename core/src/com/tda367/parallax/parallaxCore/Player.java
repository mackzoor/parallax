package com.tda367.parallax.parallaxCore;

/**
 * Created by Markus on 2017-04-05.
 */
public class Player {

    private int score;
    private String userName;
    private SpaceCraft spaceCraft;

    public Player(SpaceCraft spaceCraft) {
        this.spaceCraft = spaceCraft;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public String getUserName() {
        return userName;
    }

    public SpaceCraft getSpaceCraft() {
        return spaceCraft;
    }
}
