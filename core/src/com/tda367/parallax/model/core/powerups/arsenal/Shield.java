package com.tda367.parallax.model.core.powerups.arsenal;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.util.Transformable;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Gives the player a shield that last for several seconds.
 */

public class Shield extends PowerUpBase {

    private static final CollidableType COLLIDABLE_TYPE = CollidableType.SPACECRAFT;
    private static final String COLLISION_MODEL = "3dModels/shield/hitbox.obj";
    private Transformable objectToShield;

    Shield() {
        super();
    }

    @Override
    public void activate(Transformable transformable) {
        this.objectToShield = transformable;
        super.activate(transformable);

        super.getPos().set(positionOffset(transformable.getPos()));
        super.setRot(new Quat4f(0, 0, 0, 1));

        super.addToCollisionManager();
        super.enableCollision();
    }

    private Vector3f positionOffset(Vector3f basePosition) {
        final Vector3f offsettedPosition = new Vector3f(basePosition);
        offsettedPosition.add(new Vector3f(0, 2, 0));
        return offsettedPosition;
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        if (super.isActive()) {
            super.setPos(this.positionOffset(this.objectToShield.getPos()));
        }
    }

    @Override
    public CollidableType getCollidableType() {
        return COLLIDABLE_TYPE;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if (collidable.getCollidableType() != CollidableType.CONTAINER
            && collidable.getCollidableType() != CollidableType.SPACECRAFT) {
            super.setDead(true);
            super.setActive(false);
            super.setCollisionEnabled(false);
            super.removeFromCollisionManager();
            super.disableCollision();
        }
    }

    @Override
    public String getCollisionModelPath() {
        return COLLISION_MODEL;
    }

    @Override
    public PowerUpType getPowerUpType() {
        return PowerUpType.SHIELD;
    }
}
