package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.spacecraftviews;

import com.tda367.parallax.model.core.spacecraft.SpaceCraftType;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class SpaceCraftManualTransform implements View{

    private SpaceCraftType type;
    private RenderableSpaceCraft renderableSpaceCraft;

    public SpaceCraftManualTransform(SpaceCraftType type){
        this.type = this.type;
        this.renderableSpaceCraft = getRenderableSpaceCraft(this.type);
    }

    @Override
    public void render(){
        renderableSpaceCraft.render();
    }

    public void setCriticalDamage(boolean value) {
        renderableSpaceCraft.setCriticalDamage(value);
    }

    @Override
    public boolean isObsolete() {
        return false;
    }

    public void setPos(Vector3f pos){
        renderableSpaceCraft.setPosition(pos);
    }

    public void setRot(Quat4f rot) {
        renderableSpaceCraft.setRotation(rot);
    }


    private RenderableSpaceCraft getRenderableSpaceCraft(SpaceCraftType type){
        if (type == SpaceCraftType.AGELION) {
            return new AgelionView();
        } else {
            return new AgelionView();
        }
    }

}