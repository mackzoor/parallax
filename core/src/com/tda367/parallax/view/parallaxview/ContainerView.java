package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.core.powerups.container.Container;
import com.tda367.parallax.view.rendering.Renderer3D;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.utilities.ResourceLoader;

import javax.vecmath.Quat4f;

/**
 * View class for {@link Container}.
 */
public class ContainerView implements View {

    private final static String MODEL_3D_INTERNAL_PATH = "3dModels/containerround/containerround.g3db";

    //TODO Change path to the particular powerup.
    private final static String MODEL_3D_INTERNAL_PATH_MISSILE = "3dModels/missile/missile.g3db";
    private final Container container;
    private Renderable3dObject container3dObject;
    private Renderable3dObject powerUp3dObject;
    private Quat4f powerUpRotation;

    /**
     * Creates a ContainerView from a {@link Container}.
     * @param container to be used to create the ContainerView.
     */
    ContainerView(Container container) {
        this.container = container;
        powerUpRotation = new Quat4f(0,0,0,1);
        container3dObject = new Renderable3dObject(
                container.getPos(),
                container.getRot(),
                ResourceLoader.getInstance().getModel(MODEL_3D_INTERNAL_PATH),
                1f
        );

        powerUp3dObject = new Renderable3dObject(
                container.getPos(),
                powerUpRotation,
                ResourceLoader.getInstance().getModel(MODEL_3D_INTERNAL_PATH_MISSILE),
                1f,
                true
        );
    }

    @Override
    public void render() {
        if (!container.isCollected()){
            updatePowerUpRotation();
            Renderer3D.getInstance().addObjectToFrame(powerUp3dObject);
            Renderer3D.getInstance().addObjectToFrame(container3dObject);
        }
    }

    private void updatePowerUpRotation(){
        //Set rotation of 2 degrees per update.
        Quat4f rotation = new Quat4f(0,0,   0.035f, 0.999f);
        rotation.normalize();
        powerUpRotation.mul(rotation);
        powerUpRotation.normalize();
        powerUp3dObject.setRot(powerUpRotation);
    }

    @Override
    public boolean isObsolete() {
        return !container.isCollected();
    }

}
