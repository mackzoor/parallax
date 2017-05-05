package com.tda367.parallax.model.parallaxCore.spaceCraft;

import com.tda367.parallax.model.CoreAbstraction.Collidable;
import com.tda367.parallax.model.CoreAbstraction.Renderable;
import com.tda367.parallax.model.CoreAbstraction.Updatable;
import com.tda367.parallax.model.parallaxCore.powerUps.IPowerUp;

import javax.vecmath.Vector2f;
import java.util.List;

/**
 * Interface for all spaceCraft in the game.
 */
public interface ISpaceCraft extends Collidable, Updatable, Renderable {
    void setForwardSpeedTarget(float speed);
    void setForwardAcceleration(float acceleration);

    void setPanAbsoluteTarget(Vector2f target);
    void offsetAbsolutePanTarget(Vector2f target);
    void setPanAcceleration(Vector2f velocity);

    void setDesiredPanVelocity(Vector2f desiredPanVelocity);
    void setDesiredPanVelocity(float x, float y);

    void action();
    void pushPU(IPowerUp pu);
    void pushPU(List<IPowerUp> listOfPowerUps);
    IPowerUp popPU();

    void addSpaceCraftListener(SpaceCraftListener listener);
    void removeSpaceCraftListener(SpaceCraftListener listener);

    void incHealth();
    void decHealth();
    int getHealth();
}


