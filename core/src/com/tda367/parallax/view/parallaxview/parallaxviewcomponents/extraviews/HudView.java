package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.extraviews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Vector3f;

/**
 * View class for rendering a {@link Player} hud.
 */
public class HudView {

    private static final String PATH_TO_3D_MODEL = "3dModels/hudpane/hudPane.g3db";
    private final Player player;
    private final Renderable3dObject hudPane;

    private final HudTextureGenerator htg;

    /**
     * Creates a HudView from a {@link Player}.
     *
     * @param player to be used to create the PlayerView.
     */
    public HudView(Player player) {
        this.player = player;
        this.hudPane = new Renderable3dObject(
                player.getSpaceCraft().getPos(),
                player.getSpaceCraft().getRot(),
                ResourceLoader.getInstance().getModel(PATH_TO_3D_MODEL),
                0.75f,
                false
        );

        this.htg = new HudTextureGenerator(5);
    }

    private void setHudPaneTexture(Texture texture) {
        final Material material = this.hudPane.getModelInstance().materials.get(0);
        final TextureAttribute textureAttribute = new TextureAttribute(TextureAttribute.Diffuse, texture);
        material.set(textureAttribute);
    }

    public void render() {

        //Get player spaceCraft

        /*
        The render should consist of:
            How many lives are left,
            What weapon is equipped,
            Stats about the spaceCraft,
            etc.
         */
        this.htg.setLives(this.player.getSpaceCraft().getHealth());
        this.htg.setScore(this.player.getScore());
        this.setHudPaneTexture(this.htg.generateTexture());

        final Vector3f nextPos = new Vector3f(this.player.getSpaceCraft().getPos());
        nextPos.add(new Vector3f(1.5f, 1, 0.8f));
        this.hudPane.setPos(nextPos);

        Renderer3D.getInstance().addObjectToFrame(this.hudPane);
    }

}
