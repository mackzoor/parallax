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

    private final ParticleEffect particleEffect;
    @Getter
    private boolean dead;
    private boolean enabled;
    @Getter
    @Setter
    private Vector3f position;
    @Getter
    @Setter
    private Quat4f rotation;

    public RenderableParticleEffect(ParticleEffectType type) {
        this.position = new Vector3f();
        this.rotation = new Quat4f(0, 0,  0, 1);

        this.particleEffect = ResourceLoader.getInstance().getParticleEffect(type.getFilePath());
        this.particleEffect.init();
    }

    private void updateEffectTransform() {
        this.particleEffect.setTransform(new Matrix4());

        this.particleEffect.translate(new Vector3(
                this.position.getX(),
                this.position.getZ(),
                this.position.getY() * -1
        ));

        this.particleEffect.rotate(new Quaternion(
                this.rotation.getX(),
                this.rotation.getZ(),
                this.rotation.getY() * -1,
                this.rotation.getW()
        ));
    }

    public void start() {
        if (!this.dead && !this.enabled) {
            this.enabled = true;
            this.particleEffect.start();
        }
    }

    public void kill() {
        this.dead = true;

        final Emitter emitter = this.particleEffect.getControllers().first().emitter;
        if (emitter instanceof RegularEmitter) {
            final RegularEmitter reg = (RegularEmitter) emitter;
            reg.setEmissionMode(RegularEmitter.EmissionMode.EnabledUntilCycleEnd);
        }
    }

    public void dispose() {
        this.particleEffect.dispose();
    }

    public ParticleEffect getParticleEffect() {
        this.updateEffectTransform();
        return this.particleEffect;
    }
}
