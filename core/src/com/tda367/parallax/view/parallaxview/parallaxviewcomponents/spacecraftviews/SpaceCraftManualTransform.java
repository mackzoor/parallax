package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.spacecraftviews;

import com.tda367.parallax.model.core.spacecraft.SpaceCraftType;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class SpaceCraftManualTransform implements View {

    private final SpaceCraftType type;
    private final RenderableSpaceCraft renderableSpaceCraft;

    public SpaceCraftManualTransform(SpaceCraftType type) {
        this.type = type;
        this.renderableSpaceCraft = getRenderableSpaceCraft(this.type);
    }

    @Override
    public void render() {
        this.renderableSpaceCraft.render();
    }

    public void setCriticalDamage(boolean value) {
        this.renderableSpaceCraft.setCriticalDamage(value);
    }

    @Override
    public boolean isObsolete() {
        return false;
    }

    public void setPos(Vector3f pos) {
        this.renderableSpaceCraft.setPosition(pos);
    }

    public void setRot(Quat4f rot) {
        this.renderableSpaceCraft.setRotation(rot);
    }


    private RenderableSpaceCraft getRenderableSpaceCraft(SpaceCraftType type) {
        RenderableSpaceCraft returnCraft;
        switch (type) {
            case AGELION:
                returnCraft = new AgelionView();
                break;
            default:
                returnCraft = new AgelionView();
                break;
        }
        return returnCraft;
    }

}
