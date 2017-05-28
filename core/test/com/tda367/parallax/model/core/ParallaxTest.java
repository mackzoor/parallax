package com.tda367.parallax.model.core;

import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import com.tda367.parallax.model.core.world.World;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import org.junit.Test;

import static org.junit.Assert.*;


public class ParallaxTest {
    Player player;
    World world;
    Camera camera;
    AudioQueue audioQueue;
    boolean gameOver;
    Parallax parallax;

    public ParallaxTest(){
        player = new Player();
        player.addSpaceCraft(SpaceCraftFactory.getAgelionInstance(15));
        parallax = new Parallax(player);
    }

    @Test
    public void setPaused() throws Exception {
    }

    @Test
    public void update() throws Exception {
        int score1 = player.getScore();
        parallax.update(100);
        int score2 = player.getScore();
        assertTrue(score1 < score2);
        assertTrue(score2 == 1);

        parallax.setPaused(true);
        parallax.update(100);
        score2 = player.getScore();
        assertTrue(score2 == 1);

        parallax.getPlayer().getSpaceCraft().setHealth(0);
        parallax.update(100);
        assertFalse(parallax.getPlayer().getScore() > 1);


    }

}