package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.spacecraftviews;


import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class AgelionView implements RenderableSpaceCraft {

    private final String AGELION_3D_MODEL = "3dModels/agelion/agelion.g3db";
    private final ParticleEffectType BOOST_TRAIL = ParticleEffectType.BOOST_TRAIL;
    private final ParticleEffectType FIRE_EFFECT = ParticleEffectType.FIRE;

    private Quat4f rot;
    private Vector3f pos;


    private Renderable3dObject spaceCraftModel;
    private RenderableParticleEffect trail;
    private RenderableParticleEffect damaged;

    private boolean criticalDamage;

    AgelionView() {
        this.pos = new Vector3f();
        this.rot = new Quat4f();

        this.spaceCraftModel = new Renderable3dObject(
                pos,
                rot,
                ResourceLoader.getInstance().getModel(AGELION_3D_MODEL),
                1f,
                true
        );

        criticalDamage = false;

        trail = new RenderableParticleEffect(BOOST_TRAIL);
        damaged = new RenderableParticleEffect(FIRE_EFFECT);
    }

    private void updateTransformation() {
        spaceCraftModel.setPos(pos);
        spaceCraftModel.setRot(rot);
    }

    @Override
    public void render() {
        updateTransformation();


        Vector3f particleOffset = new Vector3f(pos);
        particleOffset.add(new Vector3f(0, -1f, 0.15f));
        trail.setPosition(particleOffset);

        Renderer3D.getInstance().addObjectToFrame(spaceCraftModel);
        Renderer3D.getInstance().addParticleEffectToFrame(trail);

        if (criticalDamage) {
            Vector3f fireOffset = new Vector3f(pos);
            fireOffset.add(new Vector3f(0, 0, 0.25f));
            damaged.setPosition(fireOffset);

            Renderer3D.getInstance().addParticleEffectToFrame(damaged);
        }
    }

    @Override
    public void setCriticalDamage(boolean value) {
        criticalDamage = value;
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
