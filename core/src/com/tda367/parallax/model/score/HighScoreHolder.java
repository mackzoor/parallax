package com.tda367.parallax.model.score;

import lombok.Getter;

public class HighScoreHolder {

    @Getter private String name;
    @Getter private int score;

    HighScoreHolder(String name, int score){
        this.name = name;
        this.score = score;
    }
}
