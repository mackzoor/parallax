package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import com.tda367.parallax.model.core.powerups.arsenal.PowerUpType;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * View class that will show a powerUp and is manually controlled
 * and doesn't need a IPowerUp from the model to be rendered.
 */
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

    public void playActivationSound() {
        this.renderablePowerUp.playActivationSound();
    }

    @Override
    public boolean isObsolete() {
        return false;
    }

    private RenderablePowerUp getRenderablePowerUp(PowerUpType powerUp) {
        RenderablePowerUp returnRenderablePowerUp;
        switch (powerUp) {
            case CANNON:
                returnRenderablePowerUp = new CannonView();
                break;
            case MISSILE:
                returnRenderablePowerUp = new MissileView();
                break;
            default:
                returnRenderablePowerUp = new ShieldView();
                break;
        }
        return returnRenderablePowerUp;
    }
}
