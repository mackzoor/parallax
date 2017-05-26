package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews;

import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.powerups.arsenal.PowerUpType;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;

/**
 * View class for {@link IPowerUp}
 */
public class PowerUpViewAutomaticTransform implements View{

    private IPowerUp powerUp;
    private RenderablePowerUp renderablePowerUp;

    public PowerUpViewAutomaticTransform(IPowerUp powerUp){
        this.powerUp = powerUp;
        this.renderablePowerUp = getRenderablePowerUp(powerUp);
    }

    @Override
    public void render(){
        renderablePowerUp.setPosition(powerUp.getPos());
        renderablePowerUp.setRotation(powerUp.getRot());


        if (powerUp.isActive()){
            renderablePowerUp.render();
        }else if (powerUp.isDead()){
            renderablePowerUp.kill();
        }
    }

    public void effectsEnable(boolean value) {
        this.renderablePowerUp.enableEffects(value);
    }

    @Override
    public boolean isObsolete() {
        return powerUp.isDead() && renderablePowerUp.isDead();
    }


    private RenderablePowerUp getRenderablePowerUp(IPowerUp powerUp){
        if (powerUp.getPowerUpType() == PowerUpType.LAZER) {
            return new LazerView();
        } else if (powerUp.getPowerUpType() == PowerUpType.MISSILE) {
            return new MissileView();
        } else {
            return new LazerView();
        }
    }
}
