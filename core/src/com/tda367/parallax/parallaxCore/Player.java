package com.tda367.parallax.parallaxCore;

import com.tda367.parallax.parallaxCore.spaceCraft.ISpaceCraft;

/**
 * Created by Markus on 2017-04-05.
 */
public class Player {

    private int score;
    private String userName;
    private ISpaceCraft spaceCraft;

    public Player(ISpaceCraft spaceCraft) {
        this.spaceCraft = spaceCraft;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public String getUserName() {
        return userName;
    }

    public ISpaceCraft getSpaceCraft() {
        return spaceCraft;
    }
}
