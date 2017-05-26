package com.tda367.parallax.model.core.world.courseobstacles;

import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import org.junit.Test;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import static org.junit.Assert.*;


public class MovingBoxObstacleTest {

    MovingBoxObstacle randomBox = ObstacleFactory.getMovingBoxInstance(new Vector3f(1,1,1),true);
    MovingBoxObstacle box = ObstacleFactory.getMovingBoxInstance(new Vector3f(1,1,1),false);

    @Test
    public void update() throws Exception {
        Quat4f boxRot1 = box.getRot();
        Vector3f boxPos1 = box.getPos();
        box.update(1000);
        assertTrue(boxPos1.equals(box.getPos()) && boxRot1.equals(box.getRot()));

        Quat4f randomBoxRot = randomBox.getRot();
        Vector3f randomBoxPos = randomBox.getPos();
        randomBox.update(1000);
        assertFalse(randomBoxPos.equals(box.getPos()) && randomBoxRot.equals(box.getRot()));
    }

}