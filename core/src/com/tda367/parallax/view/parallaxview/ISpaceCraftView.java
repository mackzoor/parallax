package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.core.spacecraft.Agelion;
import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftType;
import com.tda367.parallax.view.rendering.Renderer3D;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.utilities.ResourceLoader;

/**
 * View class for the spacecraft {@link Agelion}
 */
public class ISpaceCraftView implements View {

    private final String model3dInternalPath;
    private final ISpaceCraft iSpaceCraft;
    private Renderable3dObject spaceCraftModel;

    /**
     * Creates a ISpaceCraftView from an {@link ISpaceCraft}.
     * @param iSpaceCraft to be used to create the ContainerView.
     */
    public ISpaceCraftView(ISpaceCraft iSpaceCraft) {
        this.iSpaceCraft = iSpaceCraft;
        model3dInternalPath = getSpaceCraftModel(iSpaceCraft);

        spaceCraftModel = new Renderable3dObject(
                iSpaceCraft.getPos(),
                iSpaceCraft.getRot(),
                ResourceLoader.getInstance().getModel(model3dInternalPath),
                1f,
                true
        );

    }

    @Override
    public void render() {
        if (!isObsolete()){
            spaceCraftModel.setPos(iSpaceCraft.getPos());
            spaceCraftModel.setRot(iSpaceCraft.getRot());
            Renderer3D.getInstance().addObjectToFrame(spaceCraftModel);
        }
    }
    @Override
    public boolean isObsolete() {
        return iSpaceCraft.getHealth() < 1;
    }

    /**
     * Returns the path to the spaceCraftModel from the {@link ISpaceCraft}'s spacecraft type.
     * @param spaceCraft to be used to get model.
     * @return Specific path to model if spaceCraft type is know, otherwise returns a path to a generic 3d model.
     */
    private String getSpaceCraftModel(ISpaceCraft spaceCraft){
        if (spaceCraft.getType() == SpaceCraftType.AGELION){
            return "3dModels/agelion/agelion.g3db";
        } else {
            return "3dModels/agelion/agelion.g3db";
        }
    }
}