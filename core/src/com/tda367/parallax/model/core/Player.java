package com.tda367.parallax.model.core;

import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import lombok.Getter;
import lombok.Setter;

public class Player {

    @Getter
    private final String userName;
    @Getter
    @Setter
    private int score;
    @Getter
    @Setter
    private ISpaceCraft spaceCraft;

    public Player(String usernName) {
        this.userName = usernName;
        this.score = 0;
    }
}
