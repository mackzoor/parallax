package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;


public class LazerView implements RenderablePowerUp {
    private static final String LAZER_3D_MODEL = "3dModels/laser/laser.g3db";
    private static final ParticleEffectType EXPLOSION = ParticleEffectType.EXPLOSION;

    private Vector3f pos;
    private Quat4f rot;

    private final Renderable3dObject renderable3dObject;
    private final RenderableParticleEffect explosion;

    private int deathTime;
    private boolean effectsEnabled;


    LazerView() {
        this.pos = new Vector3f();
        this.rot = new Quat4f();

        this.renderable3dObject = new Renderable3dObject(
                this.pos,
                this.rot,
                ResourceLoader.getInstance().getModel(LAZER_3D_MODEL),
                1,
                true
        );

        this.explosion = new RenderableParticleEffect(EXPLOSION);
        this.deathTime = 0;
        this.effectsEnabled = true;
    }

    private void updateTransformation() {
        this.renderable3dObject.setPos(this.pos);
        this.renderable3dObject.setRot(this.rot);

        this.explosion.setPosition(this.pos);
    }

    @Override
    public void render() {
        this.updateTransformation();

        if (this.deathTime == 0) {
            Renderer3D.getInstance().addObjectToFrame(this.renderable3dObject);
        } else {
            if (this.effectsEnabled) {
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
        }
    }

    @Override
    public boolean isDead() {
        return this.deathTime > 120;
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
