package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.powerups.arsenal.Missile;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Vector3f;

/**
 * View class for {@link Missile}
 */
public class IPowerUpView implements View {
    private final static String MODEL_3D_INTERNAL_PATH = "3dModels/missile/missile.g3db";
    private final static ParticleEffectType ROCKET_TRAIL = ParticleEffectType.ROCKET_TRAIL;
    private final static ParticleEffectType EXPLOSION = ParticleEffectType.EXPLOSION;

    private final IPowerUp powerUp;

    private Renderable3dObject renderable3dObject;
    private RenderableParticleEffect rocketTrail;
    private RenderableParticleEffect explosion;

    private int deathTime;

    /**
     * Creates a IPowerUpView from a {@link IPowerUp}.
     *
     * @param powerUp to be used to create the IPowerUpView.
     */
    public IPowerUpView(IPowerUp powerUp) {
        this.powerUp = powerUp;
        this.renderable3dObject = new Renderable3dObject(
                powerUp.getPos(),
                powerUp.getRot(),
                ResourceLoader.getInstance().getModel(MODEL_3D_INTERNAL_PATH),
                1
        );

        rocketTrail = new RenderableParticleEffect(ROCKET_TRAIL);
        rocketTrail.start();
        explosion = new RenderableParticleEffect(EXPLOSION);
        deathTime = 0;
    }

    @Override
    public void render() {
        if (powerUp.isActive()){
            renderable3dObject.setPos(powerUp.getPos());
            renderable3dObject.setRot(powerUp.getRot());

            Vector3f particleOffset = new Vector3f(powerUp.getPos());
            particleOffset.add(new Vector3f(0,-0.5f,0));
            rocketTrail.setPosition(particleOffset);

            Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
            Renderer3D.getInstance().addParticleEffectToFrame(rocketTrail);
        } else if (powerUp.isDead()){
            if (deathTime == 0){
                Renderer3D.getInstance().addParticleEffectToFrame(explosion);
                explosion.setPosition(powerUp.getPos());
                explosion.start();
                rocketTrail.kill();
            }
            Renderer3D.getInstance().addParticleEffectToFrame(rocketTrail);
            deathTime++;
        }
    }

    @Override
    public boolean isObsolete() {
        return powerUp.isDead() && deathTime > 120;
    }
}
