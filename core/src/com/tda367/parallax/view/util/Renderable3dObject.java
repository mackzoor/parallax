package com.tda367.parallax.view.util;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Class for 3d renderable objects.
 */
public class Renderable3dObject {

    private Vector3f pos;
    private Quat4f rot;
    private ModelInstance modelInstance;
    private float opacity;
    private boolean highPriority;

    public Renderable3dObject(Vector3f pos, Quat4f rot, ModelInstance modelInstance, float opacity) {
        this(pos, rot, modelInstance, opacity, false);
    }
    public Renderable3dObject(Vector3f pos, Quat4f rot, ModelInstance modelInstance, float opacity, boolean highPriority) {
        this.modelInstance = modelInstance;
        setPos(pos);
        setRot(rot);
        setOpacity(opacity);
        this.highPriority = highPriority;
    }

    public void setPos(Vector3f pos){
        this.pos = pos;
        modelInstance.transform.setTranslation(
                pos.getX(),
                pos.getZ(),
                pos.getY() *-1
        );
    }
    public void setRot(Quat4f rot){
        this.rot = rot;
        modelInstance.transform.rotate(
                rot.getX(),
                rot.getZ(),
                rot.getY() *-1 ,
                rot.getW()
        );
    }
    public void setOpacity(float f){
        opacity = f;

        //Change opacity level
        BlendingAttribute blendingAttribute = new BlendingAttribute();
        blendingAttribute.opacity = opacity;
        Material material = modelInstance.materials.get(0);
        material.set(blendingAttribute);

    }

    public void update(){
        setPos(pos);
        setRot(rot);
    }

    public Vector3f getPos() {
        return pos;
    }
    public Quat4f getRot() {
        return rot;
    }
    public float getOpacity() {
        return opacity;
    }
    public boolean isHighPriority(){
        return highPriority;
    }
    public ModelInstance getModelInstance() {
        return modelInstance;
    }
}
