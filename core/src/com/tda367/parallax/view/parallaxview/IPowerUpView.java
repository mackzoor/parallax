package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;
import com.tda367.parallax.model.util.Model;
import com.tda367.parallax.view.Renderer3D;

/**
 * View class for {@link com.tda367.parallax.model.parallaxcore.powerups.Missile}
 */
public class IPowerUpView implements View {
    private final String model3dInternalPath = "3dModels/missile/missile.g3db";
    private final IPowerUp powerUp;
    private Renderable3dObject renderable3dObject;


    public IPowerUpView(IPowerUp powerUp) {
        this.powerUp = powerUp;
        renderable3dObject = new Renderable3dObject(
                powerUp.getPos(),
                powerUp.getRot(),
                new Model("3dModels/missile/missile.g3db")
        );
    }

    @Override
    public void render() {
            Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
    }
    @Override
    public boolean isObsolete() {
        return powerUp.isDead();
    }
}
