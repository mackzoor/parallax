package com.tda367.parallax.parallaxCore;

import com.tda367.parallax.parallaxCore.Collision.ICollisionCalculator;
import com.tda367.parallax.parallaxCore.course.Course;
import com.tda367.parallax.parallaxCore.enemies.HunterAI;
import com.tda367.parallax.parallaxCore.enemies.MinionEnemy;
import com.tda367.parallax.parallaxCore.spaceCraft.Agelion;
import com.tda367.parallax.parallaxCore.spaceCraft.ISpaceCraft;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The startup class for the game "Parallax".
 */
public class Parallax implements com.tda367.parallax.CoreAbstraction.Updatable {

    private com.tda367.parallax.CoreAbstraction.RenderManager renderManager;
    private com.tda367.parallax.CoreAbstraction.SoundManager soundManager;
    private Course course;
    private Camera camera;
    private Player player;

    private List<HunterAI> ais;

    public Parallax(Player player){
        renderManager = com.tda367.parallax.CoreAbstraction.RenderManager.getInstance();
        soundManager = com.tda367.parallax.CoreAbstraction.SoundManager.getInstance();

        course = new Course();
        course.addSpaceCraft(player.getSpaceCraft());

        camera = new Camera();
        camera.trackTo(player.getSpaceCraft());
        this.player = player;

        ais = new ArrayList<HunterAI>();

        createTestEnemy();

        startBackgroundMusic();
    }

    @Override
    public void update(int milliSinceLastUpdate) {

        if (milliSinceLastUpdate > 100){
            milliSinceLastUpdate = 100;
        }

        for (HunterAI ai : ais){
            ai.update(milliSinceLastUpdate);
        }

        course.update(milliSinceLastUpdate);
        camera.update(milliSinceLastUpdate);
    }
    public void setCollisionCalculator(ICollisionCalculator collisionCalculator){
        course.setCollisionCalculator(collisionCalculator);
    }

    public Player getPlayer() {
        return player;
    }

    public List<ISpaceCraft> getSpaceCraft(){
        return course.getSpaceCrafts();
    }

    public com.tda367.parallax.CoreAbstraction.RenderManager getRenderManager(){
        return renderManager;
    }

    public Camera getCamera(){
        return camera;
    }

    private void startBackgroundMusic(){
        Random rand = new Random();
        int randomSong = rand.nextInt(100 - 1 + 1) + 1;

        if(randomSong == 50){
            soundManager.playMusic("secretTrack.mp3","sounds/music");
        } else {
            soundManager.playMusic("track.mp3","sounds/music", new Float(0.7f));
        }
    }

    //Debug only
    private void createTestEnemy() {
        MinionEnemy minionEnemy = new MinionEnemy(new Agelion(
                new Vector3f(1.5f, -2, 1),
                new Quat4f(),
                13
        ));
        minionEnemy.getSpaceCraft().setForwardAcceleration(-3f);
        course.addSpaceCraft(minionEnemy.getSpaceCraft());
        ais.add(minionEnemy);
        minionEnemy.setTarget(player.getSpaceCraft());
    }
}
