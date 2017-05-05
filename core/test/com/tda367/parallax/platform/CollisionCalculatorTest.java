package com.tda367.parallax.platform;

import com.badlogic.gdx.physics.bullet.Bullet;
import com.tda367.parallax.parallaxCore.course.BoxObstacle;
import com.tda367.parallax.platform.CollisionCalculator;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by amk19 on 25/04/2017.
 */
public class CollisionCalculatorTest {

    static CollisionCalculator collisionCalculator;

    @BeforeClass
    public static void setUp(){

        Bullet.init();
        collisionCalculator = new CollisionCalculator();

    }

    @Test
    public void hasCollided() throws Exception {

        BoxObstacle obstacle0 = new BoxObstacle();
        BoxObstacle obstacle1 = new BoxObstacle();
        BoxObstacle obstacle2 = new BoxObstacle();

        obstacle0.getPos().setY(0f);

        obstacle1.getPos().setY(1.1f);

        obstacle2.getPos().setY(2.2f);

        //Obs0 y = 0
        //Obs1 y = 1
        //Obs2 y = 2

        assertTrue(collisionCalculator.hasCollided(obstacle0, obstacle1));
        assertTrue(collisionCalculator.hasCollided(obstacle1, obstacle0));

        assertFalse(collisionCalculator.hasCollided(obstacle0, obstacle2));
        assertFalse(collisionCalculator.hasCollided(obstacle2, obstacle0));


        assertTrue(collisionCalculator.hasCollided(obstacle1, obstacle2));
        assertTrue(collisionCalculator.hasCollided(obstacle2, obstacle1));

        obstacle0.disableCollision();
        assertFalse(collisionCalculator.hasCollided(obstacle0,obstacle1));

    }

    @Test
    public void getCollisions() throws Exception {



    }

    @Test
    public void getCollisions1() throws Exception {

    }


    @AfterClass
    public static void end(){

    }
}