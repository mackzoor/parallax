package com.tda367.parallax.view.cardboardmenu;


import com.badlogic.gdx.Gdx;
import com.tda367.parallax.model.cardboardmenu.MainMenu;
import com.tda367.parallax.view.rendering.Renderer3D;


public class MainMenuView {

    private final MainMenu mainMenu;
    private BackgroundView world;
    private ExitButton3DView exitButton;
    private StartButton3DView startButton;


    public MainMenuView(MainMenu mainMenu, boolean isVr) {
        this.mainMenu = mainMenu;
        world = new BackgroundView();
        exitButton = new ExitButton3DView(mainMenu.getExitButton());
        startButton = new StartButton3DView(mainMenu.getStartButton());
        Renderer3D.initialize(mainMenu.getCamera().getFov(), Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), isVr);
    }

    public void render() {
        Renderer3D.getInstance().setCameraPosition(
                mainMenu.getCamera().getPos().getX(),
                mainMenu.getCamera().getPos().getY(),
                mainMenu.getCamera().getPos().getZ()
        );
        world.render();
        exitButton.render();
        startButton.render();
        Renderer3D.getInstance().renderFrame();
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

}



