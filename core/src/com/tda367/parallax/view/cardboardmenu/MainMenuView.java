package com.tda367.parallax.view.cardboardmenu;


import com.badlogic.gdx.Gdx;
import com.tda367.parallax.model.cardboardmenu.MainMenu;
import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.view.parallaxview.IPowerUpView;
import com.tda367.parallax.view.parallaxview.View;
import com.tda367.parallax.view.rendering.Renderer3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainMenuView {

    private final MainMenu mainMenu;
    private BackgroundView world;
    private ExitButton3DView exitButton;
    private StartButton3DView startButton;
    private HashMap<IPowerUp, IPowerUpView> powerUpsHash;


    public MainMenuView(MainMenu mainMenu, boolean isVr) {
        this.mainMenu = mainMenu;
        world = new BackgroundView();
        exitButton = new ExitButton3DView(mainMenu.getExitButton());
        startButton = new StartButton3DView(mainMenu.getStartButton());
        powerUpsHash = new HashMap<IPowerUp, IPowerUpView>();
        Renderer3D.initialize(mainMenu.getCamera().getFov(), Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), isVr);
        updatePowerupList();
    }

    public void render() {
        updatePowerupList();
        Renderer3D.getInstance().setCameraPosition(
                mainMenu.getCamera().getPos().getX(),
                mainMenu.getCamera().getPos().getY(),
                mainMenu.getCamera().getPos().getZ()
        );
        world.render();
        exitButton.render();
        startButton.render();
        for (IPowerUp iPowerUp : powerUpsHash.keySet()) {
            powerUpsHash.get(iPowerUp).render();
        }
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

    /**
     * Updates the PowerUpList from the world object
     */
    private void updatePowerupList() {
        List<IPowerUp> missingPowerUps = syncHash(powerUpsHash, mainMenu.getPowerUps());

        for (IPowerUp missingPowerUp : missingPowerUps) {
            powerUpsHash.put(missingPowerUp, new IPowerUpView(missingPowerUp));
        }
    }

    /**
     * Removes obsolete views from hash and finds the missing keys from hash from the list.
     *
     * @param hash hash to be worked on.
     * @param list reference list.
     * @param <T>  Object type.
     * @return Missing keys in hash from the list provided.
     */
    private <T> List<T> syncHash(HashMap<T, ? extends View> hash, List<T> list) {
        //Find obsolete
        ArrayList<T> obsolete = new ArrayList<T>();
        for (T t : hash.keySet()) {
            if (hash.get(t).isObsolete()) {
                obsolete.add(t);
            }
        }

        //Remove obsolete
        for (T t : obsolete) {
            hash.remove(t);
        }

        //Finds missing
        ArrayList<T> missing = new ArrayList<T>();
        for (T t : list) {
            if (!hash.containsKey(t)) {
                missing.add(t);
            }
        }

        //Return missing
        return missing;
    }
}




