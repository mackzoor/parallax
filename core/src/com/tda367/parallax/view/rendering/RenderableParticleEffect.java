package com.tda367.parallax.view.rendering;

import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter;
import com.badlogic.gdx.graphics.g3d.particles.emitters.RegularEmitter;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.tda367.parallax.utilities.ResourceLoader;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Particle effect that can be rendered by {@link Renderer3D}.
 */
public class RenderableParticleEffect {

    private ParticleEffect particleEffect;
    @Getter private boolean dead;
    private boolean enabled;
    @Getter @Setter private Vector3f position;
    @Getter @Setter private Quat4f rotation;

    public RenderableParticleEffect(ParticleEffectType type) {
        position = new Vector3f();
        rotation = new Quat4f(0,0,0,1);

        particleEffect = ResourceLoader.getInstance().getParticleEffect(type.getFilePath());
        particleEffect.init();
    }

    private void updateEffectTransform() {
        particleEffect.setTransform(new Matrix4());

        particleEffect.translate( new Vector3(
                position.getX(),
                position.getZ(),
                position.getY() * -1
        ));

        particleEffect.rotate(new Quaternion(
                rotation.getX(),
                rotation.getZ(),
                rotation.getY() * -1,
                rotation.getW()
        ));
    }

    public void start() {
        if (!dead && !enabled) {
            enabled = true;
            this.particleEffect.start();
        }
    }
    public void kill() {
        this.dead = true;

        final Emitter emitter = particleEffect.getControllers().first().emitter;
        if (emitter instanceof RegularEmitter) {
            final RegularEmitter reg = (RegularEmitter) emitter;
            reg.setEmissionMode(RegularEmitter.EmissionMode.EnabledUntilCycleEnd);
        }
    }

    public void dispose() {
        this.particleEffect.dispose();
    }

    public ParticleEffect getParticleEffect() {
        updateEffectTransform();
        return particleEffect;
    }
}
