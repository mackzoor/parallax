package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.extraviews;


import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.collision.CollisionObserver;
import com.tda367.parallax.model.core.collision.CollisionResult;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.view.rendering.Renderer3D;
import com.tda367.parallax.view.sound.Sound;
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

    private static final float FLASH_BANG_VOLUME = 0.2f;

    private final List<RenderableParticleEffect> activeEffects;
    private final CollisionManager collisionManager;
    @Getter
    @Setter
    private boolean enabled;

    public CollisionEffect() {
        this.activeEffects = new ArrayList<RenderableParticleEffect>();
        this.enabled = true;
        this.collisionManager = CollisionManager.getInstance();
        this.collisionManager.subscribeToCollisions(this);
    }

    @Override
    public void respondToCollision(CollisionResult collisionResult) {

        handleExplosion(collisionResult.getFirst());
        handleExplosion(collisionResult.getSecond());

        final Vector3f explosionPoint = collisionResult.getContactPoint();
        final CollidableType first = collisionResult.getFirst().getCollidableType();
        final CollidableType second = collisionResult.getSecond().getCollidableType();

        if (isDestructive(first) || isDestructive(second)) {
            createExplosionParticleEffect(explosionPoint);
        }
    }

    private boolean isDestructive(CollidableType type) {
        return type == OBSTACLE || type == HARMFUL;
    }

    private void handleExplosion(Collidable collidable) {
        if (collidable.getCollidableType() == CONTAINER) {
            createAscendParticleEffect(collidable.getPos());
        }
    }

    private void createAscendParticleEffect(Vector3f position) {
        final RenderableParticleEffect ascendEffect = new RenderableParticleEffect(ParticleEffectType.ASCEND);
        ascendEffect.setPosition(position);
        this.activeEffects.add(ascendEffect);
        ascendEffect.start();
    }

    private void createExplosionParticleEffect(Vector3f explosionPoint) {
        final RenderableParticleEffect explosionParticle = new RenderableParticleEffect(ParticleEffectType.EXPLOSION);
        explosionParticle.setPosition(explosionPoint);
        this.activeEffects.add(explosionParticle);

        explosionParticle.start();
        Sound.getInstance().playSound("sounds/effects/flashBang.mp3", FLASH_BANG_VOLUME);
    }

    public void render() {
        final Renderer3D renderer = Renderer3D.getInstance();
        for (final RenderableParticleEffect particleExplosion : this.activeEffects) {
            renderer.addParticleEffectToFrame(particleExplosion);
        }
    }
}
