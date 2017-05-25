package com.tda367.parallax.model.core.world;

import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import com.tda367.parallax.model.core.world.World;
import com.tda367.parallax.model.core.spacecraft.Agelion;
import org.junit.Test;

import static org.junit.Assert.*;
//TODO Fix to work with new render structure
public class WorldTest {
//    private RenderQueue renderQueue = RenderQueue.getInstance();
    private World world = new World();
    
    private Agelion agelion1 = SpaceCraftFactory.getAgelionInstance(20);
    private Agelion agelion2 = SpaceCraftFactory.getAgelionInstance(20);
    private Agelion agelion3 = SpaceCraftFactory.getAgelionInstance(20);

    @Test
    public void addSpaceCraft() throws Exception {
//        int sizeOne = renderQueue.getRenderables().size();
        world.addSpaceCraft(SpaceCraftFactory.getAgelionInstance(20));
        world.addSpaceCraft(SpaceCraftFactory.getAgelionInstance(20));
//        int sizeTwo = renderQueue.getRenderables().size();
        assertTrue(world.getSpaceCrafts().size() == 2);
//        assertTrue(sizeOne + 2 == sizeTwo);
    }

    @Test
    public void removeSpaceCraft() throws Exception {
        world.addSpaceCraft(agelion1);
        world.addSpaceCraft(agelion2);
        world.addSpaceCraft(agelion3);
//        int sizeOne = renderQueue.getRenderables().size();
        world.removeSpaceCraft(agelion3);
        world.removeSpaceCraft(agelion2);
        System.out.println(world.getSpaceCrafts().size());
//        int sizeTwo = renderQueue.getRenderables().size();
        assertTrue(world.getSpaceCrafts().size() == 1);
//        assertTrue(sizeOne - 2 == sizeTwo);
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