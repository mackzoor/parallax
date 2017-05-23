package com.tda367.parallax.model.core;

import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import com.tda367.parallax.model.core.world.World;
import com.tda367.parallax.model.core.enemies.MinionEnemy;
import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
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
    private AudioQueue audioQueue;
    @Getter private boolean gameOver;
    @Getter private World world;
    @Getter private com.tda367.parallax.model.core.Camera camera;
    @Getter private Player player;
    @Getter @Setter private int totalPlayingTime;
    @Getter @Setter private boolean paused;

    private List<com.tda367.parallax.model.core.enemies.HunterAI> ais;

    public Parallax(Player player){
        audioQueue = AudioQueue.getInstance();

        this.gameOver = false;
        this.paused = false;

        world = new World();
        world.addSpaceCraft(player.getSpaceCraft());

        camera = new com.tda367.parallax.model.core.Camera();
        camera.trackTo(player.getSpaceCraft());
        this.player = player;

        ais = new ArrayList<com.tda367.parallax.model.core.enemies.HunterAI>();

        //createTestEnemy();

        startBackgroundMusic();
    }



    public void update(int milliSinceLastUpdate) {
        if (player.getSpaceCraft().getHealth() > 0) {
            if (!paused) {
                int updateTime = milliSinceLastUpdate;

                if (milliSinceLastUpdate > 100){
                    updateTime = 100;
                }

                for (com.tda367.parallax.model.core.enemies.HunterAI ai : ais){
                    ai.update(updateTime);
                }

                world.update(updateTime);
                camera.update(updateTime);
                calculatePlayerScore(milliSinceLastUpdate);
            }
        } else {
            gameOver = true;
        }
    }

    private void calculatePlayerScore(int milliSinceLastUpdate){
        totalPlayingTime = totalPlayingTime + milliSinceLastUpdate;
        player.setScore(totalPlayingTime/100);
    }

    public List<ISpaceCraft> getSpaceCraft(){
        return world.getSpaceCrafts();
    }

    private void startBackgroundMusic(){
        Random rand = new Random();
        int randomSong = rand.nextInt(100 - 1 + 1) + 1;

        if(randomSong == 1){
            audioQueue.playMusic("secretTrack.mp3","sounds/music");
        } else if(randomSong == 2){
            audioQueue.playMusic("track.mp3","sounds/music", 0.7f);
        } else {
            /*
            Dj Smack's Youtube: http://www.youtube.com/Djsmack100
            Dj Smack's Soundcloud: http://soundcloud.com/dj-smack-1
            Dj Smack's Facebook: http://www.facebook.com/pages/Dj-Smac...
             */

            audioQueue.playMusic("track2.mp3","sounds/music", 0.5f);
        }
    }

    //Debug only
    private void createTestEnemy() {
        MinionEnemy minionEnemy = new MinionEnemy(SpaceCraftFactory.getAgelionInstance(
            13,
            new Vector3f(1.5f, -2, 1),
            new Quat4f()
        ));
        minionEnemy.getSpaceCraft().setForwardAcceleration(-3f);
        world.addSpaceCraft(minionEnemy.getSpaceCraft());
        ais.add(minionEnemy);
        minionEnemy.setTarget(player.getSpaceCraft());
    }
}