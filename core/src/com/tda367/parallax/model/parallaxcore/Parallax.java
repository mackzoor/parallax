package com.tda367.parallax.model.parallaxcore;

import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.model.parallaxcore.spacecraft.SpaceCraftFactory;
import com.tda367.parallax.model.parallaxcore.util.Updatable;
import com.tda367.parallax.model.parallaxcore.world.World;
import com.tda367.parallax.model.parallaxcore.enemies.HunterAI;
import com.tda367.parallax.model.parallaxcore.enemies.MinionEnemy;
import com.tda367.parallax.model.parallaxcore.spacecraft.Agelion;
import com.tda367.parallax.model.parallaxcore.spacecraft.ISpaceCraft;
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

public class Parallax implements Updatable {
    private AudioQueue audioQueue;
    @Getter private World world;
    @Getter private Camera camera;
    @Getter private Player player;
    @Getter @Setter private int totalPlayingTime;

    private List<HunterAI> ais;

    public Parallax(Player player){
        audioQueue = AudioQueue.getInstance();

        world = new World();
        world.addSpaceCraft(player.getSpaceCraft());

        camera = new Camera();
        camera.trackTo(player.getSpaceCraft());
        this.player = player;

        ais = new ArrayList<HunterAI>();

//        createTestEnemy();

        startBackgroundMusic();
    }

    @Override
    public void update(int milliSinceLastUpdate) {

        int updateTime = milliSinceLastUpdate;

        if (milliSinceLastUpdate > 100){
            updateTime = 100;
        }

        for (HunterAI ai : ais){
            ai.update(updateTime);
        }

        world.update(updateTime);
        camera.update(updateTime);
        calculatePlayerScore(milliSinceLastUpdate);

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

        if(randomSong == 50){
            audioQueue.playMusic("secretTrack.mp3","sounds/music");
        } else {
            audioQueue.playMusic("track.mp3","sounds/music", 0.7f);
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
