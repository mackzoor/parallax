package com.tda367.parallax.view.parallaxview;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.util.Renderable3dObject;
import com.tda367.parallax.utilities.ResourceLoader;
import javax.vecmath.Vector3f;

/**
 * View class for rendering a {@link Player} hud.
 */
public class HudView implements View{

    private static final String PATH_TO_3D_MODEL = "3dModels/hudpane/hudPane.g3db";
    private final Player player;
    private Renderable3dObject hudPane;

    private HudTextureGenerator htg;

    /**
     * Creates a HudView from a {@link Player}.
     * @param player to be used to create the PlayerView.
     */
    HudView(Player player) {
        this.player = player;
        hudPane = new Renderable3dObject(
                player.getSpaceCraft().getPos(),
                player.getSpaceCraft().getRot(),
                ResourceLoader.getInstance().getModel(PATH_TO_3D_MODEL),
                0.75f,
                false
        );

        htg = new HudTextureGenerator(5);
    }

    private void setHudPaneTexture(Texture texture){
        Material material = hudPane.getModelInstance().materials.get(0);
        TextureAttribute textureAttribute = new TextureAttribute(TextureAttribute.Diffuse, texture);
        material.set(textureAttribute);
    }

    @Override
    public void render() {

        //Get player spaceCraft

        /*
        The render should consist of:
            How many lives are left,
            What weapon is equipped,
            Stats about the spaceCraft,
            etc.
         */
        htg.setLives(player.getSpaceCraft().getHealth());
        htg.setScore(player.getScore());
        setHudPaneTexture(htg.generateTexture());

        Vector3f nextPos = new Vector3f(player.getSpaceCraft().getPos());
        nextPos.add(new Vector3f(1.5f,1,0.8f));
        hudPane.setPos(nextPos);

        Renderer3D.getInstance().addObjectToFrame(hudPane);

    }
    @Override
    public boolean isObsolete() {
        return false;
    }
}
