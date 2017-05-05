package com.tda367.parallax.parallaxCore.menuModels.menuModelsTest;

import com.tda367.parallax.model.parallaxCore.Parallax;
import com.tda367.parallax.model.parallaxCore.Player;
import com.tda367.parallax.model.parallaxCore.spaceCraft.Agelion;
import com.tda367.parallax.model.parallaxCore.spaceCraft.ISpaceCraft;
import com.tda367.parallax.parallaxCore.menuModels.PauseMenu;
import org.junit.Test;

import javax.vecmath.Vector3f;

import static org.junit.Assert.*;

/**
 * Created by Rasmus on 2017-04-24.
 */
public class PauseMenuTest {
    PauseMenu pauseMenu = new PauseMenu();
    ISpaceCraft spaceCraft = new Agelion();
    Player player = new Player();
    Parallax parallax = new Parallax(player);

    @Test
    public void startGame() throws Exception {
        player.addSpaceCraft(new Agelion(10));
        Vector3f vector1 = parallax.getPlayer().getSpaceCraft().getPos();
        pauseMenu.startGame();
        Vector3f vector2 = parallax.getPlayer().getSpaceCraft().getPos();
        assertTrue(vector1.getY() != vector2.getY());
    }

    @Test
    public void exitGame() throws Exception {
        pauseMenu.exitGame();
        //assertTrue(parallax.getState() == MAIN_MENU);
    }

    @Test
    public void restartGame() throws Exception {
        player.addSpaceCraft(new Agelion(10));
        Vector3f vector = parallax.getPlayer().getSpaceCraft().getPos();
        pauseMenu.restartGame();
        assertTrue(parallax.getPlayer().getScore() == 0);

    }

}