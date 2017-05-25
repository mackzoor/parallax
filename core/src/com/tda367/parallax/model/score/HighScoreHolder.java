package com.tda367.parallax.model.score;

import lombok.Getter;

/*
 * Storing a combination of name and score, class for simplified usage of data storage in real time.
*/

class HighScoreHolder {

    @Getter
    private String name;
    @Getter
    private int score;

    HighScoreHolder(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
