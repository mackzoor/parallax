package com.tda367.parallax.model.score;

import lombok.Getter;

/*
 * Storing a combination of name and score, class for simplified usage of data storage in real time.
*/

class HighScoreHolder {

    @Getter
    private final String name;
    @Getter
    private final int score;

    HighScoreHolder(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
