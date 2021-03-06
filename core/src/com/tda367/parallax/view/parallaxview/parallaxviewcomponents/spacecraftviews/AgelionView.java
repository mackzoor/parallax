package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.spacecraftviews;


import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Represents a visible agelion spacecraft that can be rendered.
 */
public class AgelionView implements RenderableSpaceCraft {

    private static final String AGELION_3D_MODEL = "3dModels/agelion/agelion.g3db";
    private static final ParticleEffectType BOOST_TRAIL = ParticleEffectType.BOOST_TRAIL;
    private static final ParticleEffectType FIRE_EFFECT = ParticleEffectType.FIRE;
    private static final Vector3f PARTICLE_OFFSET = new Vector3f(0, -1f, 0);
    private static final Vector3f FIRE_OFFSET = new Vector3f(0, 0, 0.1f);

    private Quat4f rot;
    private Vector3f pos;

    private final Renderable3dObject spaceCraftModel;
    private final RenderableParticleEffect trail;
    private final RenderableParticleEffect damaged;

    private boolean criticalDamage;

    AgelionView() {
        this.pos = new Vector3f();
        this.rot = new Quat4f();

        this.spaceCraftModel = new Renderable3dObject(
                this.pos,
                this.rot,
                ResourceLoader.getInstance().getModel(AGELION_3D_MODEL),
                1f
        );

        this.criticalDamage = false;

        this.trail = new RenderableParticleEffect(BOOST_TRAIL);
        this.damaged = new RenderableParticleEffect(FIRE_EFFECT);
    }

    private void updateTransformation() {
        this.spaceCraftModel.setPos(this.pos);
        this.spaceCraftModel.setRot(this.rot);
    }

    @Override
    public void render() {
        this.updateTransformation();


        final Vector3f particleOffset = new Vector3f(this.pos);
        particleOffset.add(PARTICLE_OFFSET);
        this.trail.setPosition(particleOffset);

        Renderer3D.getInstance().addObjectToFrame(this.spaceCraftModel);
        Renderer3D.getInstance().addParticleEffectToFrame(this.trail);

        if (this.criticalDamage) {
            final Vector3f fireOffset = new Vector3f(this.pos);
            fireOffset.add(FIRE_OFFSET);
            this.damaged.setPosition(fireOffset);

            Renderer3D.getInstance().addParticleEffectToFrame(this.damaged);
        }
    }

    @Override
    public void setCriticalDamage(boolean value) {
        this.criticalDamage = value;
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
