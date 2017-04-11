import com.tda367.parallax.parallaxCore.spaceCraft.Agelion;
import org.junit.Before;
import org.junit.Test;

import javax.vecmath.Vector2f;

import static org.junit.Assert.assertTrue;

/**
 * Created by xoxLU on 2017-04-07.
 */
public class AgelionTest {

    Agelion agilion;

    @Before
    public void setUp() throws Exception {
        agilion = new Agelion();
    }

    //Test to make sure that the setSpeedTarget() method does what it should (setting the speed to a float value) and is not being interfered with at a different location.
    @Test
    public void setSpeedTarget() throws Exception {
        float previousSpeed = agilion.getTargetSpeed();

        float addedSpeed = 50;

        agilion.setSpeedTarget(addedSpeed);

        assertTrue(previousSpeed + addedSpeed == agilion.getTargetSpeed());
    }

    //Test to make sure that the setAccelerationTarget() method does what it should (setting the Acceleration to a float value) and is not being interfered with at a different location.
    @Test
    public void setAccelerateTarget() throws Exception {
        float previousAcceleration = agilion.getTargetAcceleration();

        float addedAcc = 50;

        agilion.setAccelerateTarget(addedAcc);

        assertTrue(previousAcceleration + addedAcc == agilion.getTargetAcceleration());
    }

    //Test to make sure that the method setPanTarget() changes its value then added a new Vector.
    @Test
    public void setPanPoint() throws Exception {
        Vector2f vec = agilion.getPanTarget();

        agilion.setPanPoint(new Vector2f(1, 1));

        assertTrue(!agilion.getPanTarget().equals(vec));
    }

    @Test
    public void setPanVelocity() throws Exception {
    }

    //Test to check if addPanPoint(0, 1) adds one to the y coordinate.
    @Test
    public void addPanPoint() throws Exception {
        Vector2f vec = new Vector2f(agilion.getPanTarget().getX(), agilion.getPanTarget().getY());

        agilion.addPanPoint(new Vector2f(0, 1));

        assertTrue(!(agilion.getPanTarget().getX() == vec.getX() && agilion.getPanTarget().getY() == vec.getY()));

        assertTrue(agilion.getPanTarget().equals(new Vector2f(vec.getX(), vec.getY() + 1)));
    }

    //Test to check if addPanVelocity(0, 1) adds one to the y coordinate.
    @Test
    public void addPanVelocity() throws Exception {
        Vector2f vec = new Vector2f(agilion.getVelTarget().getX(), agilion.getVelTarget().getY());

        agilion.addPanVelocity(new Vector2f(0, 1));

        assertTrue(!(agilion.getVelTarget().getX() == vec.getX() && agilion.getVelTarget().getY() == vec.getY()));

        assertTrue(agilion.getVelTarget().equals(new Vector2f(vec.getX(), vec.getY() + 1)));
    }

    @Test
    public void incHealth(){
        int health = agilion.getHealth();

        agilion.getHealth();

        assertTrue(agilion.getHealth() == health + 1);
    }

    @Test
    public void action() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }
}