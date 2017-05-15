package com.tda367.parallax.parallaxcore.course;

import com.tda367.parallax.model.coreabstraction.RenderQueue;
import com.tda367.parallax.model.parallaxcore.world.World;
import com.tda367.parallax.model.parallaxcore.spacecraft.Agelion;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorldTest {
    private RenderQueue renderQueue = RenderQueue.getInstance();
    private World world = new World();
    private Agelion agelion1 = new Agelion();
    private Agelion agelion2 = new Agelion();
    private Agelion agelion3 = new Agelion();

    @Test
    public void addSpaceCraft() throws Exception {
        int sizeOne = renderQueue.getRenderables().size();
        world.addSpaceCraft(new Agelion());
        world.addSpaceCraft(new Agelion());
        int sizeTwo = renderQueue.getRenderables().size();
        assertTrue(world.getSpaceCrafts().size() == 2);
        assertTrue(sizeOne + 2 == sizeTwo);
    }

    @Test
    public void removeSpaceCraft() throws Exception {
        world.addSpaceCraft(agelion1);
        world.addSpaceCraft(agelion2);
        world.addSpaceCraft(agelion3);
        int sizeOne = renderQueue.getRenderables().size();
        world.removeSpaceCraft(agelion3);
        world.removeSpaceCraft(agelion2);
        System.out.println(world.getSpaceCrafts().size());
        int sizeTwo = renderQueue.getRenderables().size();
        assertTrue(world.getSpaceCrafts().size() == 1);
        assertTrue(sizeOne - 2 == sizeTwo);
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void setCollisionCalculator() throws Exception {
    }

    @Test
    public void powerUPUsed() throws Exception {
    }

}