package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.parallaxcore.Parallax;
import com.tda367.parallax.view.Renderer3D;

/**
 * View class for the game Parallax.
 */
public class ParallaxView {

    private final Parallax parallax;
    private WorldView worldView;
    private HudView playerHudView;

    public ParallaxView(Parallax parallax){
        this.parallax = parallax;
        playerHudView = new HudView(parallax.getPlayer());
        worldView = new WorldView(parallax.getWorld());
    }

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
}
