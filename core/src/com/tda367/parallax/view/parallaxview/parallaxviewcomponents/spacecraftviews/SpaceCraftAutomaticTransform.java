package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.spacecraftviews;

import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftType;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;

public class SpaceCraftAutomaticTransform implements View {

    private ISpaceCraft spaceCraft;
    private RenderableSpaceCraft renderableSpaceCraft;

    public SpaceCraftAutomaticTransform(ISpaceCraft spaceCraft){
        this.spaceCraft = spaceCraft;
        this.renderableSpaceCraft = getRenderableSpaceCraft(spaceCraft);
    }

    @Override
    public void render(){
        renderableSpaceCraft.setPosition(spaceCraft.getPos());
        renderableSpaceCraft.setRotation(spaceCraft.getRot());


        if (spaceCraft.getHealth() > 1){
            renderableSpaceCraft.setCriticalDamage(false);
        } else {
            renderableSpaceCraft.setCriticalDamage(true);
        }
        renderableSpaceCraft.render();
    }

    @Override
    public boolean isObsolete() {
        return spaceCraft.getHealth() < 1;
    }


    private RenderableSpaceCraft getRenderableSpaceCraft(ISpaceCraft spaceCraft){
        if (spaceCraft.getType() == SpaceCraftType.AGELION) {
            return new AgelionView();
        } else {
            return new AgelionView();
        }
    }
}
