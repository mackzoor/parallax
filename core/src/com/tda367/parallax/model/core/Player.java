package com.tda367.parallax.model.core;

import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import lombok.Getter;
import lombok.Setter;

public class Player {

    @Getter @Setter private int score;
    @Getter private String userName = "LingonBoy";
    @Getter private ISpaceCraft spaceCraft;

    public Player() {
        this.score = 0;
    }

    public void addSpaceCraft(ISpaceCraft spaceCraft){
        this.spaceCraft = spaceCraft;
    }
}
