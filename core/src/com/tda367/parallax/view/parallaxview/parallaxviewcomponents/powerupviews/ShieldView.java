package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;



import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.view.rendering.Renderer3D;

/**
 * Represents a visible shield that can be rendered.
 */
public class ShieldView extends RenderablePowerUpBase implements RenderablePowerUp {

    private static final String SHIELD_3D_MODEL = "3dModels/shield/shield.g3db";
    private static final ParticleEffectType EXPLOSION = ParticleEffectType.EXPLOSION;
    private static final float OPACITY = 0.3f;
    private static final int DEATH_DELAY = 120;

    private final Renderable3dObject shieldModel;
    private final RenderableParticleEffect explosion;


    private int deathTime;

    ShieldView() {
        super();
        this.deathTime = 0;

        this.shieldModel = new Renderable3dObject(
                super.getPos(),
                super.getRot(),
                ResourceLoader.getInstance().getModel(SHIELD_3D_MODEL),
                OPACITY,
                true
        );

        this.explosion = new RenderableParticleEffect(EXPLOSION);
    }

    private void updatePosition() {
        this.shieldModel.setPos(super.getPos());
        this.shieldModel.setRot(super.getRot());

        this.explosion.setPosition(super.getPos());
    }

    @Override
    public void playActivationSound() {
        //No sound played.
    }

    @Override
    public void render() {
        this.updatePosition();

        if (this.deathTime == 0) {
            Renderer3D.getInstance().addObjectToFrame(this.shieldModel);
        } else {
            if (super.isEffectsEnabled()) {
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
        return this.deathTime > DEATH_DELAY;
    }
}
