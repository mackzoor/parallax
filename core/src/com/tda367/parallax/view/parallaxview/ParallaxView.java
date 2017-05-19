package com.tda367.parallax.view.parallaxview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.tda367.parallax.model.parallaxcore.Parallax;
import com.tda367.parallax.view.Renderer3D;

/**
 * View class for {@link Parallax}.
 */
public class ParallaxView implements View{

    private final Parallax parallax;
    private WorldView worldView;
    private HudView playerHudView;

    /**
     * Creates a ParallaxView from a {@link Parallax}.
     * @param parallax to be used to create the ParallaxView.
     */
    public ParallaxView(Parallax parallax, boolean isVr){
        //Initialize renderer.
        Renderer3D.initialize(
                        parallax.getCamera().getFov(),
                        Gdx.graphics.getWidth(),
                        Gdx.graphics.getHeight(),
                        isVr
        );

        this.parallax = parallax;
        playerHudView = new HudView(parallax.getPlayer());
        worldView = new WorldView(parallax.getWorld());
    }

    /**
     * Sets render x-resolution.
     * @param width new x-resolution.
     */
    public void setWidth(int width){
        Renderer3D.getInstance().setWidth(width);
    }
    /**
     * Sets render y-resolution.
     * @param height new y-resolution.
     */
    public void setHeight(int height){
        Renderer3D.getInstance().setHeight(height);
    }

    @Override
    public void render(){
        Renderer3D.getInstance().setCameraPosition(
                parallax.getCamera().getPos().getX(),
                parallax.getCamera().getPos().getY(),
                parallax.getCamera().getPos().getZ()
        );
        worldView.render();
        playerHudView.render();
        Renderer3D.getInstance().renderFrame();
    }
    @Override
    public boolean isObsolete() {
        return false;
    }
}
