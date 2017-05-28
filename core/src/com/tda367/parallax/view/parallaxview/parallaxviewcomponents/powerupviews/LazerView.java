package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.view.rendering.Renderer3D;

public class LazerView extends RenderablePowerUpBase implements RenderablePowerUp {
    private static final String LAZER_3D_MODEL = "3dModels/laser/laser.g3db";
    private static final ParticleEffectType EXPLOSION = ParticleEffectType.EXPLOSION;

    private final Renderable3dObject renderable3dObject;
    private final RenderableParticleEffect explosion;

    private int deathTime;
    private boolean effectsEnabled;


    LazerView() {
        super();
        this.renderable3dObject = new Renderable3dObject(
                super.getPos(),
                super.getRot(),
                ResourceLoader.getInstance().getModel(LAZER_3D_MODEL),
                1,
                false
        );

        this.explosion = new RenderableParticleEffect(EXPLOSION);
        this.deathTime = 0;
        this.effectsEnabled = true;
    }

    private void updateTransformation() {
        this.renderable3dObject.setPos(super.getPos());
        this.renderable3dObject.setRot(super.getRot());

        this.explosion.setPosition(super.getPos());
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
            this.explosion.setPosition(super.getPos());
            this.explosion.start();
            this.deathTime = 1;
        }
    }

    @Override
    public boolean isDead() {
        return this.deathTime > 120;
    }
}
