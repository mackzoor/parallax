package com.tda367.parallax.model.core.enemies;

import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import com.tda367.parallax.utilities.MathUtilities;
import lombok.Getter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * An enemy minion. Will try to destroy its target.
 */

public class MinionEnemy extends HunterAI {
    @Getter
    private final ISpaceCraft spaceCraft;

    public MinionEnemy(ISpaceCraft spaceCraft) {
        this.spaceCraft = spaceCraft;
        this.spaceCraft.enableIndipendantRotation(true);
    }

    private void operate() {
        rotateToTarget();

        if (isPointingAtTarget()) {
            shoot();
        }
    }

    private boolean isPointingAtTarget() {
        Vector3f currentHeading = new Vector3f(0,1,0);
        MathUtilities.rotateVectorByQuat(currentHeading, spaceCraft.getRot());

        Vector3f targetDirection = getTargetDirection();
        System.out.println(currentHeading.angle(targetDirection));
        return currentHeading.angle(targetDirection) < 0.4;
    }

    private Vector3f getTargetDirection(){
        Vector3f currentPos = new Vector3f(this.spaceCraft.getPos());
        Vector3f targetPos = new Vector3f(super.getTarget().getPos());

        Vector3f targetDirection = new Vector3f(targetPos);
        targetDirection.sub(currentPos);

        targetDirection.normalize();

        return targetDirection;
    }

    private void rotateToTarget() {
        Vector3f targetDirection = getTargetDirection();

        Quat4f rotationNew = MathUtilities.vectorToQuat(targetDirection);
        rotationNew.interpolate(this.spaceCraft.getRot(),0.95f);
        this.spaceCraft.getRot().set(rotationNew);
    }

    private void shoot() {
        spaceCraft.action();
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        this.operate();
    }
}
