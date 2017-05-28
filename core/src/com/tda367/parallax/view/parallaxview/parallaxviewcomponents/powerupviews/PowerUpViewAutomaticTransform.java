package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.powerups.arsenal.PowerUpType;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;

/**
 * View class for {@link IPowerUp}.
 */
public class PowerUpViewAutomaticTransform implements View {

    private final IPowerUp powerUp;
    private final RenderablePowerUp renderablePowerUp;

    public PowerUpViewAutomaticTransform(IPowerUp powerUp) {
        this.powerUp = powerUp;
        this.renderablePowerUp = getRenderablePowerUp(powerUp);
    }

    @Override
    public void render() {
        this.renderablePowerUp.setPos(this.powerUp.getPos());
        this.renderablePowerUp.setRot(this.powerUp.getRot());


        if (this.powerUp.isActive()) {
            this.renderablePowerUp.render();
        } else if (this.powerUp.isDead()) {
            this.renderablePowerUp.kill();
        }
    }

    public void effectsEnable(boolean value) {
        this.renderablePowerUp.enableEffects(value);
    }

    @Override
    public boolean isObsolete() {
        return this.powerUp.isDead() && this.renderablePowerUp.isDead();
    }


    private RenderablePowerUp getRenderablePowerUp(IPowerUp powerUp) {
        if (powerUp.getPowerUpType() == PowerUpType.LAZER) {
            return new LazerView();
        } else if (powerUp.getPowerUpType() == PowerUpType.MISSILE) {
            return new MissileView();
        } else if (powerUp.getPowerUpType() == PowerUpType.SHIELD) {
            return new ShieldView();
        } else {
            return new LazerView();
        }
    }
}
