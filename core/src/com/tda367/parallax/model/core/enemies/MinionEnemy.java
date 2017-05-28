package com.tda367.parallax.model.core.enemies;

import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import com.tda367.parallax.utilities.MathUtilities;
import lombok.Getter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * An enemy minion. Will try to destroy its target.
 */

public class MinionEnemy extends AbstractHunterAI {

    private final static float TARGET_ANGLE_THRESHOLD = 0.4f;
    private final static float ROTATION_SPEED_MODIFIER = 0.95f;

    @Getter
    private final ISpaceCraft spaceCraft;

    public MinionEnemy(ISpaceCraft spaceCraft) {
        super();
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
        final Vector3f currentHeading = new Vector3f(0, 1, 0);
        MathUtilities.rotateVectorByQuat(currentHeading, this.spaceCraft.getRot());

        final Vector3f targetDirection = getTargetDirection();
        return currentHeading.angle(targetDirection) < TARGET_ANGLE_THRESHOLD;
    }

    private Vector3f getTargetDirection() {
        final Vector3f currentPos = new Vector3f(this.spaceCraft.getPos());
        final Vector3f targetPos = new Vector3f(super.getTarget().getPos());

        final Vector3f targetDirection = new Vector3f(targetPos);
        targetDirection.sub(currentPos);

        targetDirection.normalize();

        return targetDirection;
    }

    private void rotateToTarget() {
        final Vector3f targetDirection = this.getTargetDirection();

        final Quat4f rotationNew = MathUtilities.vectorToQuat(targetDirection);
        rotationNew.interpolate(this.spaceCraft.getRot(), ROTATION_SPEED_MODIFIER);
        this.spaceCraft.getRot().set(rotationNew);
    }

    private void shoot() {
        this.spaceCraft.action();
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        this.operate();
    }
}
