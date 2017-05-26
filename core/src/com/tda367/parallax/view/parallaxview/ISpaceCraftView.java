package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.core.spacecraft.Agelion;
import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftType;
import com.tda367.parallax.view.rendering.ParticleEffectType;
import com.tda367.parallax.view.rendering.RenderableParticleEffect;
import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * View class for the spacecraft {@link Agelion}
 */
public class ISpaceCraftView implements View {

    private final String model3dInternalPath;
    private final ISpaceCraft iSpaceCraft;
    private Renderable3dObject spaceCraftModel;
    private RenderableParticleEffect trail;
    private RenderableParticleEffect damaged;

    /**
     * Creates a ISpaceCraftView from an {@link ISpaceCraft}.
     *
     * @param iSpaceCraft to be used to create the ContainerView.
     */
    ISpaceCraftView(ISpaceCraft iSpaceCraft) {
        this.iSpaceCraft = iSpaceCraft;
        this.model3dInternalPath = getSpaceCraftModel(iSpaceCraft);

        this.spaceCraftModel = new Renderable3dObject(
                iSpaceCraft.getPos(),
                iSpaceCraft.getRot(),
                ResourceLoader.getInstance().getModel(this.model3dInternalPath),
                1f,
                true
        );

        trail = new RenderableParticleEffect(ParticleEffectType.BOOST_TRAIL);
        damaged = new RenderableParticleEffect(ParticleEffectType.FIRE);
    }

    @Override
    public void render() {
        if (!isObsolete()){
            spaceCraftModel.setPos(iSpaceCraft.getPos());
            spaceCraftModel.setRot(iSpaceCraft.getRot());

            Vector3f particleOffset = new Vector3f(iSpaceCraft.getPos());
            particleOffset.add(new Vector3f(0,-1f,0.15f));

            Quat4f rot = new Quat4f(0,0,1,0);

            trail.setRotation(rot);
            trail.setPosition(particleOffset);
            Renderer3D.getInstance().addObjectToFrame(spaceCraftModel);

            Renderer3D.getInstance().addParticleEffectToFrame(trail);
            if (iSpaceCraft.getHealth() < 2) {
                Vector3f fireOffset = new Vector3f(iSpaceCraft.getPos());
                fireOffset.add(new Vector3f(0,0,0.25f));
                damaged.setPosition(fireOffset);
                Renderer3D.getInstance().addParticleEffectToFrame(damaged);
            }
        }
    }

    @Override
    public boolean isObsolete() {
        return this.iSpaceCraft.getHealth() < 1;
    }

    /**
     * Returns the path to the spaceCraftModel from the {@link ISpaceCraft}'s spacecraft type.
     *
     * @param spaceCraft to be used to get model.
     * @return Specific path to model if spaceCraft type is know,
     * otherwise returns a path to a generic 3d model.
     */
    private String getSpaceCraftModel(ISpaceCraft spaceCraft) {
        if (spaceCraft.getType() == SpaceCraftType.AGELION) {
            return "3dModels/agelion/agelion.g3db";
        } else {
            return "3dModels/agelion/agelion.g3db";
        }
    }
}
