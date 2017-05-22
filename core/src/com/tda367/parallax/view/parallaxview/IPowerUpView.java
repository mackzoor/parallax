package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.util.Renderable3dObject;
import com.tda367.parallax.view.util.ResourceLoader;

/**
 * View class for {@link com.tda367.parallax.model.parallaxcore.powerups.Missile}
 */
public class IPowerUpView implements View {
    private final static String MODEL_3D_INTERNAL_PATH = "3dModels/missile/missile.g3db";
    private final IPowerUp powerUp;
    private Renderable3dObject renderable3dObject;


    /**
     * Creates a IPowerUpView from a {@link IPowerUp}.
     * @param powerUp to be used to create the IPowerUpView.
     */
    public IPowerUpView(IPowerUp powerUp) {
        this.powerUp = powerUp;
        renderable3dObject = new Renderable3dObject(
                powerUp.getPos(),
                powerUp.getRot(),
                ResourceLoader.getInstance().getModel(MODEL_3D_INTERNAL_PATH),
                1
        );
    }

    @Override
    public void render() {
        if (powerUp.isActive()){
            renderable3dObject.setPos(powerUp.getPos());
            renderable3dObject.setRot(powerUp.getRot());
            Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
        }
    }
    @Override
    public boolean isObsolete() {
        return powerUp.isDead();
    }
}
