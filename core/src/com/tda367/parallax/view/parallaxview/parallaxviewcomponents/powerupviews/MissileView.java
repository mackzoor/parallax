package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class MissileView implements RenderablePowerUp{
    private final static String MODEL_3D_INTERNAL_PATH = "3dModels/missile/missile.g3db";
    private final static ParticleEffectType ROCKET_TRAIL = ParticleEffectType.ROCKET_TRAIL;
    private final static ParticleEffectType EXPLOSION = ParticleEffectType.EXPLOSION;

    private Vector3f pos;
    private Quat4f rot;

    private Renderable3dObject renderable3dObject;
    private RenderableParticleEffect rocketTrail;
    private RenderableParticleEffect explosion;

    private int deathTime;
    private boolean effectsEnabled;


    MissileView() {
        this.pos = new Vector3f();
        this.rot = new Quat4f();

        this.renderable3dObject = new Renderable3dObject(
                pos,
                rot,
                ResourceLoader.getInstance().getModel(MODEL_3D_INTERNAL_PATH),
                1,
                true
        );

        rocketTrail = new RenderableParticleEffect(ROCKET_TRAIL);
        rocketTrail.start();
        explosion = new RenderableParticleEffect(EXPLOSION);
        effectsEnabled = true;
        deathTime = 0;
    }

    private void updateTranformation(){
        this.renderable3dObject.setPos(pos);
        this.renderable3dObject.setRot(rot);

        this.rocketTrail.setPosition(pos);
        this.explosion.setPosition(pos);
    }

    @Override
    public void render() {
        updateTranformation();

        if (deathTime == 0) {
            Vector3f particleOffset = new Vector3f(pos);
            particleOffset.add(new Vector3f(0,-0.5f,0));
            rocketTrail.setPosition(particleOffset);

            Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
            if (effectsEnabled){
                Renderer3D.getInstance().addParticleEffectToFrame(rocketTrail);
            }
        } else {
            if (effectsEnabled) {
                Renderer3D.getInstance().addParticleEffectToFrame(rocketTrail);
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
            rocketTrail.kill();
        }
    }

    @Override
    public boolean isDead() {
        return deathTime > 60;
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