package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.extraviews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.core.powerups.arsenal.PowerUpType;
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

    private PowerUpType powerUpType;
    private int lives;
    private int score;

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
                true
        );

        this.htg = new HudTextureGenerator(5);
    }

    public void render() {
        updateHudTexture();
        renderHud();
    }

    private void updateHudTexture() {
        if (playerStatsChange()) {
            this.htg.setLives(this.player.getSpaceCraft().getHealth());
            this.htg.setScore(this.player.getScore());
            this.htg.setWeapon(this.powerUpType);
            this.setHudPaneTexture(this.htg.generateTexture());
        }
    }
    private boolean playerStatsChange() {
        boolean changed = false;
        if (this.powerUpType != this.player.getSpaceCraft().getPowerUpType()) {
            changed = true;
            this.powerUpType = this.player.getSpaceCraft().getPowerUpType();
        }

        if (this.lives != this.player.getSpaceCraft().getHealth()) {
            changed = true;
            this.lives = this.player.getSpaceCraft().getHealth();
        }

        if (this.score != this.player.getScore()) {
            changed = true;
            this.score = this.player.getScore();
        }

        return changed;
    }

    public void renderHud() {
        final Vector3f nextPos = new Vector3f(this.player.getSpaceCraft().getPos());
        nextPos.add(new Vector3f(1.5f, 1, 0.8f));
        this.hudPane.setPos(nextPos);

        Renderer3D.getInstance().addObjectToFrame(this.hudPane);
    }
    private void setHudPaneTexture(Texture texture) {
        final Material material = this.hudPane.getModelInstance().materials.get(0);
        final TextureAttribute textureAttribute = new TextureAttribute(TextureAttribute.Diffuse, texture);
        material.set(textureAttribute);
    }
}
