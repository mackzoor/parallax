package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.view.rendering.Renderer3D;
import com.tda367.parallax.view.sound.Sound;

import javax.vecmath.Vector3f;

/**
 * Represents a visible missile that can be rendered.
 */
public class MissileView extends RenderablePowerUpBase implements RenderablePowerUp {
    private static final String MODEL_3D_INTERNAL_PATH = "3dModels/missile/missile.g3db";
    private static final ParticleEffectType ROCKET_TRAIL = ParticleEffectType.ROCKET_TRAIL;
    private static final ParticleEffectType EXPLOSION = ParticleEffectType.EXPLOSION;

    private final Renderable3dObject renderable3dObject;
    private final RenderableParticleEffect rocketTrail;
    private final RenderableParticleEffect explosion;

    private int deathTime;


    MissileView() {
        super();

        this.renderable3dObject = new Renderable3dObject(
                super.getPos(),
                super.getRot(),
                ResourceLoader.getInstance().getModel(MODEL_3D_INTERNAL_PATH),
                1,
                false
        );

        this.rocketTrail = new RenderableParticleEffect(ROCKET_TRAIL);
        this.rocketTrail.start();
        this.explosion = new RenderableParticleEffect(EXPLOSION);
        this.deathTime = 0;
    }

    @Override
    public void playActivationSound() {
        playMissileSound();
    }

    private void playMissileSound() {
        Sound.getInstance().playSound("sounds/effects/MissileDemo.mp3", 0.7f);
    }

    @Override
    public void render() {
        this.updateTransformation();

        if (this.deathTime == 0) {
            final Vector3f particleOffset = new Vector3f(super.getPos());
            particleOffset.add(new Vector3f(0, -0.5f, 0));
            this.rocketTrail.setPosition(particleOffset);

            Renderer3D.getInstance().addObjectToFrame(this.renderable3dObject);
            if (super.isEffectsEnabled()) {
                Renderer3D.getInstance().addParticleEffectToFrame(this.rocketTrail);
            }
        } else {
            if (super.isEffectsEnabled()) {
                Renderer3D.getInstance().addParticleEffectToFrame(this.rocketTrail);
                Renderer3D.getInstance().addParticleEffectToFrame(this.explosion);
            }
            this.deathTime++;
        }
    }

    private void updateTransformation() {
        this.renderable3dObject.setPos(super.getPos());
        this.renderable3dObject.setRot(super.getRot());

        this.rocketTrail.setPosition(super.getPos());
        this.explosion.setPosition(super.getPos());
    }

    @Override
    public void kill() {
        if (this.deathTime == 0) {
            this.explosion.setPosition(super.getPos());
            this.explosion.start();
            this.rocketTrail.kill();
        }
    }

    @Override
    public boolean isDead() {
        return this.deathTime > 60;
    }

}
