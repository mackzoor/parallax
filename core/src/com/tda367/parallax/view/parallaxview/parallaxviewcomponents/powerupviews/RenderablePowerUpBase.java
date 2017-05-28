package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;


import com.tda367.parallax.view.rendering.ParticleEffectType;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public abstract class RenderablePowerUpBase implements RenderablePowerUp {

    private static final String MODEL_3D_INTERNAL_PATH = "3dModels/missile/missile.g3db";
    private static final ParticleEffectType ROCKET_TRAIL = ParticleEffectType.ROCKET_TRAIL;
    private static final ParticleEffectType EXPLOSION = ParticleEffectType.EXPLOSION;

    @Getter
    @Setter
    private Vector3f pos;
    @Getter
    @Setter
    private Quat4f rot;
    @Setter
    @Getter
    private boolean effectsEnabled;

    RenderablePowerUpBase() {
        this.pos = new Vector3f();
        this.rot = new Quat4f();

        this.effectsEnabled = true;
    }

    @Override
    public abstract void render();

    @Override
    public abstract void kill();

    @Override
    public abstract boolean isDead();

    @Override
    public void enableEffects(boolean value) {
        this.effectsEnabled = value;
    }
}
