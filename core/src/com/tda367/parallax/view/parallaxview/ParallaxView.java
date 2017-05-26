package com.tda367.parallax.view.parallaxview;

import com.badlogic.gdx.Gdx;
import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.view.rendering.Renderer3D;

import lombok.Getter;
import lombok.Setter;

/**
 * View class for {@link Parallax}.
 */
public class ParallaxView implements View{

    private final Parallax parallax;
    private WorldView worldView;
    private HudView playerHudView;
    private CollisionEffect collisionEffect;
    @Getter @Setter private boolean hudViewActive;

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
        this.playerHudView = new HudView(parallax.getPlayer());
        this.worldView = new WorldView(parallax.getWorld());
        this.hudViewActive = false;
        this.collisionEffect = new CollisionEffect();
    }

    @Override
    public void render(){
        Renderer3D.getInstance().setCameraPosition(
                parallax.getCamera().getPos().getX(),
                parallax.getCamera().getPos().getY(),
                parallax.getCamera().getPos().getZ()
        );
        worldView.render();
        if (hudViewActive) {
            playerHudView.render();
        }

        collisionEffect.render();
        Renderer3D.getInstance().renderFrame();
    }
    @Override
    public boolean isObsolete() {
        return false;
    }

    public void setWidth(int width){
        Renderer3D.getInstance().setWidth(width);
    }
    public void setHeight(int height){
        Renderer3D.getInstance().setHeight(height);
    }
}
