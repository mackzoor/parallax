package com.tda367.parallax.view.parallaxview;


import com.tda367.parallax.model.core.collision.*;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.view.rendering.Renderer3D;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

import static com.tda367.parallax.model.core.collision.CollidableType.CONTAINER;
import static com.tda367.parallax.model.core.collision.CollidableType.HARMFUL;
import static com.tda367.parallax.model.core.collision.CollidableType.OBSTACLE;

/**
 * Listens to collisions and creates explosion effects at the location.
 */
public class CollisionEffect implements CollisionObserver {

    private List<RenderableParticleEffect> activeEffects;
    private CollisionManager collisionManager;
    @Setter @Getter private boolean enabled;

    CollisionEffect() {
        activeEffects = new ArrayList<RenderableParticleEffect>();
        enabled = true;
        collisionManager = CollisionManager.getInstance();
        collisionManager.subscribeToCollisions(this);
    }

    @Override
    public void respondToCollision(CollisionResult collisionResult) {

        handleExplosion(collisionResult.getFirst());
        handleExplosion(collisionResult.getSecond());

        final Vector3f explosionPoint = collisionResult.getContactPoint();
        final CollidableType first = collisionResult.getFirst().getCollidableType();
        final CollidableType second = collisionResult.getSecond().getCollidableType();

        if (isDestructive(first)  || isDestructive(second)) {
            createExplosion(explosionPoint);
        }
    }

    private boolean isDestructive(CollidableType type) {
        return type == OBSTACLE || type == HARMFUL;
    }

    private void handleExplosion(Collidable collidable) {
        if (collidable.getCollidableType() == CONTAINER) {
            createAscend(collidable.getPos());
        }
    }

    private void createAscend(Vector3f position) {
        final RenderableParticleEffect ascendEffect = new RenderableParticleEffect(ParticleEffectType.ASCEND);
        ascendEffect.setPosition(position);
        activeEffects.add(ascendEffect);
        ascendEffect.start();
    }

    private void createExplosion(Vector3f explosionPoint) {
        final RenderableParticleEffect explosionParticle = new RenderableParticleEffect(ParticleEffectType.EXPLOSION);
        explosionParticle.setPosition(explosionPoint);
        activeEffects.add(explosionParticle);
        explosionParticle.start();
    }

    public void render() {
        final Renderer3D renderer = Renderer3D.getInstance();
        for (final RenderableParticleEffect particleExplosion : activeEffects) {
            renderer.addParticleEffectToFrame(particleExplosion);
        }
    }

}
