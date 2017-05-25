package com.tda367.parallax.view.parallaxview;

import com.badlogic.gdx.Gdx;
import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.view.rendering.Renderer3D;
import lombok.Getter;
import lombok.Setter;

/**
 * View class for {@link Parallax}.
 */
public class ParallaxView implements View {

    private final Parallax parallax;
    private WorldView worldView;
    private HudView playerHudView;
    @Getter
    @Setter
    private boolean hudViewActive;

    /**
     * Creates a ParallaxView from a {@link Parallax}.
     *
     * @param parallax to be used to create the ParallaxView.
     */
    public ParallaxView(Parallax parallax, boolean isVr) {
        //Initialize renderer.
        Renderer3D.initialize(
                parallax.getCamera().getFov(),
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(),
                isVr
        );

        this.parallax = parallax;
        this.playerHudView = new HudView(parallax.getPlayer());
        this.worldView = new WorldView(parallax.getWorld());
        this.hudViewActive = false;
    }

    /**
     * Sets render x-resolution.
     *
     * @param width new x-resolution.
     */
    public void setWidth(int width) {
        Renderer3D.getInstance().setWidth(width);
    }

    /**
     * Sets render y-resolution.
     *
     * @param height new y-resolution.
     */
    public void setHeight(int height) {
        Renderer3D.getInstance().setHeight(height);
    }

    @Override
    public void render() {
        Renderer3D.getInstance().setCameraPosition(
                this.parallax.getCamera().getPos().getX(),
                this.parallax.getCamera().getPos().getY(),
                this.parallax.getCamera().getPos().getZ()
        );
        this.worldView.render();
        if (this.hudViewActive) {
            this.playerHudView.render();
        }
        Renderer3D.getInstance().renderFrame();
    }

    @Override
    public boolean isObsolete() {
        return false;
    }
}
