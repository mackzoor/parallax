package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;



import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.view.rendering.Renderer3D;

public class ShieldView extends RenderablePowerUpBase implements RenderablePowerUp{

    private static final String SHIELD_3D_MODEL = "3dModels/shield/shield.g3db";
    private static final ParticleEffectType EXPLOSION = ParticleEffectType.EXPLOSION;

    private Renderable3dObject shieldModel;
    private RenderableParticleEffect explosion;


    private int deathTime;

    ShieldView(){
        super();
        deathTime = 0;

        shieldModel = new Renderable3dObject(
                super.getPos(),
                super.getRot(),
                ResourceLoader.getInstance().getModel(SHIELD_3D_MODEL),
                0.3f,
                true
        );

        explosion = new RenderableParticleEffect(EXPLOSION);
    }

    private void updatePosition(){
        shieldModel.setPos(super.getPos());
        shieldModel.setRot(super.getRot());

        explosion.setPosition(super.getPos());
    }

    @Override
    public void render() {
        updatePosition();

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
            deathTime = 1;
        }
    }

    @Override
    public boolean isDead() {
        return deathTime > 120;
    }
}
