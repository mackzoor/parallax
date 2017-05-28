package com.tda367.parallax.model.core;

import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import com.tda367.parallax.model.core.world.World;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;

/**
 * The startup class for the game "Parallax".
 */

public class Parallax {

    private static Random rand = new Random();
    private static final String MUSIC_DIRECTORY = "sounds/music";

    @Getter
    private boolean gameOver;
    @Getter
    private final World world;
    @Getter
    private final Camera camera;
    @Getter
    private final Player player;
    @Getter
    @Setter
    private int totalPlayingTime;
    @Getter
    private boolean paused;


    public Parallax(Player player) {
        this.gameOver = false;
        this.paused = false;

        this.world = new World();
        this.world.addSpaceCraft(player.getSpaceCraft());
        player.getSpaceCraft().getPos().set(0, -110, 0);
        player.getSpaceCraft().setForwardAcceleration(0.2f);

        this.camera = new Camera();
        this.camera.trackTo(player.getSpaceCraft());
        this.player = player;
    }

    public void setPaused(Boolean paused) {
        this.paused = paused;
    }

    public void update(int milliSinceLastUpdate) {
        if (this.player.getSpaceCraft().getHealth() > 0) {
            if (!this.paused) {
                final int updateTime = getUpdateTime(milliSinceLastUpdate);
                this.world.update(updateTime);
                this.camera.update(updateTime);
                calculatePlayerScore(milliSinceLastUpdate);
            }
        } else {
            this.gameOver = true;
        }
    }

    private void calculatePlayerScore(int milliSinceLastUpdate) {
        this.totalPlayingTime = this.totalPlayingTime + milliSinceLastUpdate;
        this.player.setScore(this.totalPlayingTime / 100);
    }

    public List<ISpaceCraft> getSpaceCraft() {
        return this.world.getSpaceCrafts();
    }

    private int getUpdateTime(int milliSinceLastUpdate) {
        return milliSinceLastUpdate > 100 ? 100 : milliSinceLastUpdate;
    }
}
