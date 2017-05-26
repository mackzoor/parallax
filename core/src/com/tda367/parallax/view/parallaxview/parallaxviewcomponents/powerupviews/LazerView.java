package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;


public class LazerView implements RenderablePowerUp {
    private final static String LAZER_3D_MODEL = "3dModels/laser/laser.g3db";
    private final static ParticleEffectType EXPLOSION = ParticleEffectType.EXPLOSION;

    private Vector3f pos;
    private Quat4f rot;

    private Renderable3dObject renderable3dObject;
    private RenderableParticleEffect explosion;

    private int deathTime;
    private boolean effectsEnabled;


    LazerView() {
        pos = new Vector3f();
        rot = new Quat4f();

        this.renderable3dObject = new Renderable3dObject(
                pos,
                rot,
                ResourceLoader.getInstance().getModel(LAZER_3D_MODEL),
                1,
                true
        );

        explosion = new RenderableParticleEffect(EXPLOSION);
        deathTime = 0;
        effectsEnabled = true;
    }

    private void updateTransformation() {
        this.renderable3dObject.setPos(pos);
        this.renderable3dObject.setRot(rot);

        this.explosion.setPosition(pos);
    }

    @Override
    public void render() {
        updateTransformation();

        if (deathTime == 0) {
            Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
        } else {
            if (effectsEnabled) {
                Renderer3D.getInstance().addParticleEffectToFrame(explosion);
            }
            deathTime++;
        }
    }

    @Override
    public void kill() {
        if (deathTime == 0){
            explosion.setPosition(pos);
            explosion.start();
        }
    }

    @Override
    public boolean isDead() {
        return deathTime > 120;
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
