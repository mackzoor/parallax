package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.spacecraftviews;

import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;
/**
 * View class for {@link ISpaceCraft} that will automatically transform to match the spacecraft given.
 */
public class SpaceCraftAutomaticTransform implements View {

    private final ISpaceCraft spaceCraft;
    private final RenderableSpaceCraft renderableSpaceCraft;

    public SpaceCraftAutomaticTransform(ISpaceCraft spaceCraft) {
        this.spaceCraft = spaceCraft;
        this.renderableSpaceCraft = getRenderableSpaceCraft(spaceCraft);
    }

    @Override
    public void render() {
        this.renderableSpaceCraft.setPosition(this.spaceCraft.getPos());
        this.renderableSpaceCraft.setRotation(this.spaceCraft.getRot());


        if (this.spaceCraft.getHealth() > 1) {
            this.renderableSpaceCraft.setCriticalDamage(false);
        } else {
            this.renderableSpaceCraft.setCriticalDamage(true);
        }
        this.renderableSpaceCraft.render();
    }

    @Override
    public boolean isObsolete() {
        return this.spaceCraft.getHealth() < 1;
    }


    private RenderableSpaceCraft getRenderableSpaceCraft(ISpaceCraft spaceCraft) {
        RenderableSpaceCraft returnCraft;
        switch (spaceCraft.getType()) {
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
