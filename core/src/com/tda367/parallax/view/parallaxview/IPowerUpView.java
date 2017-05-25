package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.powerups.arsenal.Missile;
import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

/**
 * View class for {@link Missile}
 */
public class IPowerUpView implements View {
    private final static String MODEL_3D_INTERNAL_PATH = "3dModels/missile/missile.g3db";
    private final IPowerUp powerUp;
    private Renderable3dObject renderable3dObject;


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
    }

    @Override
    public void render() {
        if (this.powerUp.isActive()) {
            this.renderable3dObject.setPos(this.powerUp.getPos());
            this.renderable3dObject.setRot(this.powerUp.getRot());
            Renderer3D.getInstance().addObjectToFrame(this.renderable3dObject);
        }
    }

    @Override
    public boolean isObsolete() {
        return this.powerUp.isDead();
    }
}
