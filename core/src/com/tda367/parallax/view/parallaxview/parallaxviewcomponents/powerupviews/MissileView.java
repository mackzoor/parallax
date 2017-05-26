package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class MissileView implements RenderablePowerUp {
    private static final String MODEL_3D_INTERNAL_PATH = "3dModels/missile/missile.g3db";
    private static final ParticleEffectType ROCKET_TRAIL = ParticleEffectType.ROCKET_TRAIL;
    private static final ParticleEffectType EXPLOSION = ParticleEffectType.EXPLOSION;

    private Vector3f pos;
    private Quat4f rot;

    private final Renderable3dObject renderable3dObject;
    private final RenderableParticleEffect rocketTrail;
    private final RenderableParticleEffect explosion;

    private int deathTime;
    private boolean effectsEnabled;


    MissileView() {
        this.pos = new Vector3f();
        this.rot = new Quat4f();

        this.renderable3dObject = new Renderable3dObject(
                this.pos,
                this.rot,
                ResourceLoader.getInstance().getModel(MODEL_3D_INTERNAL_PATH),
                1,
                true
        );

        this.rocketTrail = new RenderableParticleEffect(ROCKET_TRAIL);
        this.rocketTrail.start();
        this.explosion = new RenderableParticleEffect(EXPLOSION);
        this.effectsEnabled = true;
        this.deathTime = 0;
    }

    private void updateTranformation() {
        this.renderable3dObject.setPos(this.pos);
        this.renderable3dObject.setRot(this.rot);

        this.rocketTrail.setPosition(this.pos);
        this.explosion.setPosition(this.pos);
    }

    @Override
    public void render() {
        this.updateTranformation();

        if (this.deathTime == 0) {
            final Vector3f particleOffset = new Vector3f(this.pos);
            particleOffset.add(new Vector3f(0, -0.5f, 0 ));
            this.rocketTrail.setPosition(particleOffset);

            Renderer3D.getInstance().addObjectToFrame(this.renderable3dObject);
            if (this.effectsEnabled) {
                Renderer3D.getInstance().addParticleEffectToFrame(this.rocketTrail);
            }
        } else {
            if (this.effectsEnabled) {
                Renderer3D.getInstance().addParticleEffectToFrame(this.rocketTrail);
                Renderer3D.getInstance().addParticleEffectToFrame(this.explosion);
            }
            this.deathTime++;
        }
    }

    @Override
    public void kill() {
        if (this.deathTime == 0) {
            this.explosion.setPosition(this.pos);
            this.explosion.start();
            this.rocketTrail.kill();
        }
    }

    @Override
    public boolean isDead() {
        return this.deathTime > 60;
    }

    @Override
    public void enableEffects(boolean value) {
        this.effectsEnabled = value;
    }

    @Override
    public void setPosition(Vector3f pos) {
        this.pos = pos;
    }

    @Override
    public void setRotation(Quat4f rot) {
        this.rot = rot;
    }

}
