package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.view.rendering.Renderer3D;
import com.tda367.parallax.view.sound.Sound;

import java.util.Random;

public class CannonView extends RenderablePowerUpBase implements RenderablePowerUp {
    private static final String LAZER_3D_MODEL = "3dModels/laser/laser.g3db";
    private static final ParticleEffectType EXPLOSION = ParticleEffectType.EXPLOSION;
    private static final String SOUND_DIRECTORY = "sounds/effects/";

    private final Renderable3dObject renderable3dObject;
    private final RenderableParticleEffect explosion;

    private int deathTime;
    private final boolean effectsEnabled;


    CannonView() {
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
    public void playActivationSound() {
        playCannonSound();
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

    private void playCannonSound() {
        Random rand = new Random();
        final int randomSong = rand.nextInt(200 - 1 + 1) + 1;

        //Plays a funny sound every 200 shots, [Disabled]
        if (randomSong > 200) {
            Sound.getInstance().playSound(SOUND_DIRECTORY + "cannonLow.mp3", 0.3f);
        } else {
            Sound.getInstance().playSound(SOUND_DIRECTORY + "cannon.mp3", 0.8f);
        }
    }

    @Override
    public boolean isDead() {
        return this.deathTime > 120;
    }
}
