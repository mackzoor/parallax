package com.tda367.parallax.model.core;

import com.tda367.parallax.model.core.enemies.AbstractHunterAI;
import com.tda367.parallax.model.core.enemies.MinionEnemy;
import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import com.tda367.parallax.model.core.world.World;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The startup class for the game "Parallax".
 */

public class Parallax {

    private static Random rand = new Random();
    private static final String MUSIC_DIRECTORY = "sounds/music";


    private final AudioQueue audioQueue;
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
    private String backgroundMusic;
    private String pauseMusic;

    private final List<AbstractHunterAI> ais;

    public Parallax(Player player) {
        this.audioQueue = AudioQueue.getInstance();

        this.gameOver = false;
        this.paused = false;

        this.world = new World();
        this.world.addSpaceCraft(player.getSpaceCraft());
        player.getSpaceCraft().getPos().set(0, -50, 0);
        player.getSpaceCraft().setForwardAcceleration(0.2f);

        this.camera = new Camera();
        this.camera.trackTo(player.getSpaceCraft());
        this.player = player;

        this.ais = new ArrayList<AbstractHunterAI>();

        //createTestEnemy();

        startBackgroundMusic();
    }

    public void setPaused(Boolean paused) {
        this.paused = paused;

        if (paused) {
            AudioQueue.getInstance().pauseActiveMusic(this.backgroundMusic);

            final int randomSong = rand.nextInt(100 - 1 + 1) + 1;

            if (randomSong == 1) {
                this.pauseMusic = "sounds/music/spanishFlea.mp3";
                AudioQueue.getInstance().playMusic("spanishFlea.mp3", MUSIC_DIRECTORY, 0.7f);
            } else {
                this.pauseMusic = "sounds/music/spaceInTime.mp3";
                AudioQueue.getInstance().playMusic("spaceInTime.mp3", MUSIC_DIRECTORY, 0.7f);
            }
        } else {
            AudioQueue.getInstance().unPauseActiveMusic(this.backgroundMusic);

            AudioQueue.getInstance().stopActiveMusic(this.pauseMusic);
        }
    }

    public void update(int milliSinceLastUpdate) {
        if (this.player.getSpaceCraft().getHealth() > 0) {
            if (!this.paused) {
                int updateTime = milliSinceLastUpdate;

                if (milliSinceLastUpdate > 100) {
                    updateTime = 100;
                }

                for (final AbstractHunterAI ai : this.ais) {
                    ai.update(updateTime);
                }

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

    private void startBackgroundMusic() {
        final int randomSong = rand.nextInt(100 - 1 + 1) + 1;

        if (randomSong == 1) {
            this.audioQueue.playMusic("secretTrack.mp3", MUSIC_DIRECTORY);
            this.backgroundMusic = "sounds/music/secretTrack.mp3";
        } else if (randomSong == 2) {
            this.audioQueue.playMusic("trippingBalls.mp3", MUSIC_DIRECTORY, 0.7f);
            this.backgroundMusic = "sounds/music/trippingBalls.mp3";
        } else {
            /*
            Dj Smack's Youtube: http://www.youtube.com/Djsmack100
            Dj Smack's Soundcloud: http://soundcloud.com/dj-smack-1
            Dj Smack's Facebook: http://www.facebook.com/pages/Dj-Smac...
             */

            this.audioQueue.playMusic("soundsGreat.mp3", MUSIC_DIRECTORY, 0.5f);
            this.backgroundMusic = "sounds/music/soundsGreat.mp3";
        }
    }

    //Debug only
    private void createTestEnemy() {
        final MinionEnemy minionEnemy = new MinionEnemy(SpaceCraftFactory.getAgelionInstance(
                13,
                new Vector3f(1.5f, -2, 1),
                new Quat4f()
        ));
        minionEnemy.getSpaceCraft().setForwardAcceleration(-3f);
        this.world.addSpaceCraft(minionEnemy.getSpaceCraft());
        this.ais.add(minionEnemy);
        minionEnemy.setTarget(this.player.getSpaceCraft());
    }
}
