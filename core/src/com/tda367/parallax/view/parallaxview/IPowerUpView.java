package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;
import com.tda367.parallax.view.util.Model;
import com.tda367.parallax.view.Renderer3D;

/**
 * View class for {@link com.tda367.parallax.model.parallaxcore.powerups.Missile}
 */
public class IPowerUpView implements View {
    private final String model3dInternalPath = "3dModels/missile/missile.g3db";
    private final IPowerUp powerUp;
    private com.tda367.parallax.view.util.Renderable3dObject renderable3dObject;


    public IPowerUpView(IPowerUp powerUp) {
        this.powerUp = powerUp;
        renderable3dObject = new com.tda367.parallax.view.util.Renderable3dObject(
                powerUp.getPos(),
                powerUp.getRot(),
                new Model("3dModels/missile/missile.g3db"),
                1f
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
