package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.worldview.courseviews;

import com.tda367.parallax.model.core.powerups.container.Container;
import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews.PowerUpViewManualTransform;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Quat4f;

/**
 * View class for {@link Container}.
 */
public class ContainerView {

    private static final String MODEL_3D_INTERNAL_PATH = "3dModels/containerround/containerround.g3db";

    private final Container container;
    private final Renderable3dObject container3dObject;
    private final Quat4f powerUpRotation;

    private final PowerUpViewManualTransform internalPowerUp;

    /**
     * Creates a ContainerView from a {@link Container}.
     *
     * @param container to be used to create the ContainerView.
     */
    ContainerView(Container container) {
        this.container = container;
        this.powerUpRotation = new Quat4f(0, 0, 0, 1);
        this.container3dObject = new Renderable3dObject(
                container.getPos(),
                container.getRot(),
                ResourceLoader.getInstance().getModel(MODEL_3D_INTERNAL_PATH),
                1f,
                true
        );

        this.internalPowerUp = new PowerUpViewManualTransform(container.getPowerUp().getPowerUpType());
        this.internalPowerUp.setPosition(this.container.getPos());
        this.internalPowerUp.effectsEnabled(false);
    }

    public void render() {
        if (!this.container.isCollected()) {
            updatePowerUpRotation();
            this.internalPowerUp.render();
            Renderer3D.getInstance().addObjectToFrame(this.container3dObject);
        }
    }

    private void updatePowerUpRotation() {
        //Set rotation of 2 degrees per update.
        final Quat4f rotation = new Quat4f(0, 0, 0.035f, 0.999f);
        rotation.normalize();
        this.powerUpRotation.mul(rotation);
        this.powerUpRotation.normalize();
        this.internalPowerUp.setRotation(this.powerUpRotation);
    }

    public boolean isCollected() {
        return !this.container.isCollected();
    }
}
