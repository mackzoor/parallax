import com.tda367.parallax.parallaxCore.spaceCraft.Agelion;
import org.junit.Before;
import org.junit.Test;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import static org.junit.Assert.assertTrue;

/**
 * Created by xoxLU on 2017-04-07.
 */
public class AgelionTest {

    Agelion agilion;

    @Before
    public void setUp() throws Exception {
        agilion = new Agelion(5, 0, 0, new Vector3f(0,0,0), new Quat4f());
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
        float previousAcceleration = agilion.getAcceleration();

        float addedAcc = 50;

        agilion.setAcceleration(addedAcc);

        assertTrue(previousAcceleration + addedAcc == agilion.getAcceleration());
    }

    //Test to make sure that the method setPanTarget() changes its value then added a new Vector.
    @Test
    public void setPanPoint() throws Exception {
        Vector2f vec = agilion.getPanTarget();

        agilion.setPanTarget(new Vector2f(1, 1));

        assertTrue(!agilion.getPanTarget().equals(vec));
    }

    @Test
    public void setPanVelocity() throws Exception {
    }

    //Test to check if addPanPoint(0, 1) adds one to the y coordinate.
    @Test
    public void addPanPoint() throws Exception {
        Vector2f vec = new Vector2f(agilion.getPanTarget().getX(), agilion.getPanTarget().getY());

        agilion.addPanTarget(new Vector2f(0, 1));

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

        agilion.incHealth();

        assertTrue(agilion.getHealth() == health + 1);
    }

    @Test
    public void action() {
    }

    @Test
    public void updatePanning(){
        //Saving original position
        Vector3f pos = agilion.getPos();

        //Sets the position we want the ship to pan to. Also set that speed to 1.
        agilion.setPanSpeed(1);
        agilion.setPanTarget(new Vector2f(10,10));

        //Calls the update method and says that 1 second has passed
        agilion.update(1000);

        //Check to see that the ship has panned in at all
        assertTrue((!(pos.getX() == agilion.getPos().getX())) || (!(pos.getZ() == agilion.getPos().getZ())));

        //Checks that the ship has panned to the right position after another 9s
        agilion.update(9000);
        assertTrue(agilion.getPos().getX() == 10 && agilion.getPos().getZ() == 10);

        //Checks that it doesn't keep panning after reaching its target, adds another second
        agilion.update(1000);
        assertTrue(agilion.getPos().getX() == 10 && agilion.getPos().getZ() == 10);

        //testa add metoden

        /*
        agilion.addPanPoint(new Vector2f(10,10));
        agilion.setAccelerateTarget(1);
        agilion.setSpeedTarget(4);
        */

        //agilion.setPanVelocity(new Vector2f(1,1));
    }

    @Test
    public void updateAdvancing(){
        //Saving original position
        Vector3f pos = new Vector3f(agilion.getPos().getX(), agilion.getPos().getY(), agilion.getPos().getZ() );

        //Sets the speed we want to achieve to 4, and the acceleration to 1.
        agilion.setAcceleration(1);
        agilion.setSpeedTarget(4);

        //Calls the update method and says that 1 second has passed
        agilion.update(1000);

        //Checks if the ship moves at all
        assertTrue(!(pos.getY() == agilion.getPos().getY()));

        //Checks that the ship has advanced to the right position after another 9s
        agilion.update(9000);
        assertTrue(agilion.getPos().getY() == 37);
    }

}