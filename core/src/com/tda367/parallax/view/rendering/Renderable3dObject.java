package com.tda367.parallax.view.rendering;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Quaternion;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * A 3d object that is able to be rendered.
 */
public class Renderable3dObject {

    private Vector3f pos;
    private Quat4f rot;
    private ModelInstance modelInstance;
    private float opacity;
    private boolean isTransparent;

    /**
     * Creates a Renderable 3d Object.
     *
     * @param pos           position of the 3d object.
     * @param rot           rotation of the 3d object.
     * @param modelInstance The 3d model to be used to render the 3d model.
     * @param opacity       Opacity of the rendered 3d model.
     * @param isTransparent  if true will render before 3d models that are set to false.
     */
    public Renderable3dObject(Vector3f pos,
                              Quat4f rot,
                              ModelInstance modelInstance,
                              float opacity,
                              boolean isTransparent) {

        this.modelInstance = modelInstance;
        setPos(pos);
        setRot(rot);
        setOpacity(opacity);
        this.isTransparent = isTransparent;
    }

    public Renderable3dObject(Vector3f pos, Quat4f rot, ModelInstance modelInstance, float opacity) {
        this(pos, rot, modelInstance, opacity, opacity < 1);
    }

    /**
     * Sets the position of the 3d model.
     *
     * @param pos the new position.
     */
    public void setPos(Vector3f pos) {
        this.pos = pos;
        this.modelInstance.transform.setTranslation(
                pos.getX(),
                pos.getZ(),
                pos.getY() * -1
        );
    }

    /**
     * Sets the rotation of the 3d model.
     *
     * @param rot the new rotation.
     */
    public void setRot(Quat4f rot) {
        this.rot = rot;

        //Reset transform matrix
        this.modelInstance.transform.idt();

        //Rotate to rotation
        this.modelInstance.transform.rotate(
                new Quaternion(
                        rot.getX(),
                        rot.getZ(),
                        rot.getY() * -1,
                        rot.getW())
        );

        //Reset position.
        this.setPos(this.pos);
    }

    /**
     * Sets the opacity of the 3d model.
     *
     * @param opacity the new opacity.
     */
    public void setOpacity(float opacity) {
        this.opacity = opacity;

        //Change opacity level
        final BlendingAttribute blendingAttribute = new BlendingAttribute();
        blendingAttribute.opacity = this.opacity;
        final Material material = this.modelInstance.materials.get(0);
        material.set(blendingAttribute);

    }

    public Vector3f getPos() {
        return this.pos;
    }

    public Quat4f getRot() {
        return this.rot;
    }

    public float getOpacity() {
        return this.opacity;
    }

    boolean isTransparent() {
        return this.isTransparent;
    }

    /**
     * Returns the 3d model ({@link ModelInstance}).
     *
     * @return the 3d model.
     */
    public ModelInstance getModelInstance() {
        return this.modelInstance;
    }
}
