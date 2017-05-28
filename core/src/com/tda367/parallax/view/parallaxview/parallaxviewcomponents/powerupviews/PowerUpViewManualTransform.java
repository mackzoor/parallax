package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import com.tda367.parallax.model.core.powerups.arsenal.PowerUpType;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;


public class PowerUpViewManualTransform implements View {

    private final RenderablePowerUp renderablePowerUp;

    public PowerUpViewManualTransform(PowerUpType type) {
        this.renderablePowerUp = getRenderablePowerUp(type);
    }

    @Override
    public void render() {
        this.renderablePowerUp.render();
    }

    public void setPosition(Vector3f pos) {
        this.renderablePowerUp.setPos(pos);
    }

    public void setRotation(Quat4f rot) {
        this.renderablePowerUp.setRot(rot);
    }

    public void kill() {
        this.renderablePowerUp.kill();
    }

    public void effectsEnabled(boolean value) {
        this.renderablePowerUp.enableEffects(value);
    }

    @Override
    public boolean isObsolete() {
        return false;
    }

    private RenderablePowerUp getRenderablePowerUp(PowerUpType powerUp) {
        if (powerUp == PowerUpType.LAZER) {
            return new LazerView();
        } else if (powerUp == PowerUpType.MISSILE) {
            return new MissileView();
        } else if (powerUp == PowerUpType.SHIELD) {
            return new ShieldView();
        } else {
            return new LazerView();
        }
    }
}
