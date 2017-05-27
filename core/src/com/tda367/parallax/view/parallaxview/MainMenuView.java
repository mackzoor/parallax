package com.tda367.parallax.view.parallaxview;


import com.badlogic.gdx.Gdx;
import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.menu.MainMenu;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.menu.BackgroundView;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.menu.ExitButton3DView;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.menu.StartButton3DView;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews.PowerUpViewAutomaticTransform;
import com.tda367.parallax.view.rendering.Renderer3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainMenuView {

    private final MainMenu mainMenu;
    private final BackgroundView world;
    private final ExitButton3DView exitButton;
    private final StartButton3DView startButton;
    private final Map<IPowerUp, PowerUpViewAutomaticTransform> powerUpsHash;


    public MainMenuView(MainMenu mainMenu, boolean isVr, boolean particlesEnabled) {
        this.mainMenu = mainMenu;
        this.world = new BackgroundView();
        this.exitButton = new ExitButton3DView(mainMenu.getExitButton());
        this.startButton = new StartButton3DView(mainMenu.getStartButton());
        this.powerUpsHash = new HashMap<IPowerUp, PowerUpViewAutomaticTransform>();
        Renderer3D.initialize(mainMenu.getCamera().getFov(), Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), isVr, particlesEnabled);
        updatePowerupList();
    }

    public void render() {
        updatePowerupList();
        Renderer3D.getInstance().setCameraPosition(
                this.mainMenu.getCamera().getPos().getX(),
                this.mainMenu.getCamera().getPos().getY(),
                this.mainMenu.getCamera().getPos().getZ()
        );
        this.world.render();
        this.exitButton.render();
        this.startButton.render();
        for (final PowerUpViewAutomaticTransform powerUpViewAutomaticTransform : this.powerUpsHash.values()) {
            powerUpViewAutomaticTransform.render();
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

        final List<IPowerUp> missingPowerUps = syncHash(this.powerUpsHash,
                                                        this.mainMenu.getPowerUps());

        for (final IPowerUp missingPowerUp : missingPowerUps) {
            this.powerUpsHash.put(missingPowerUp,
                                  new PowerUpViewAutomaticTransform(missingPowerUp));
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
    private <T> List<T> syncHash(Map<T, ? extends View> hash, List<T> list) {
        //Find obsolete
        final ArrayList<T> obsolete = new ArrayList<T>();
        for (final T t : hash.keySet()) {
            if (hash.get(t).isObsolete()) {
                obsolete.add(t);
            }
        }

        //Remove obsolete
        for (final T t : obsolete) {
            hash.remove(t);
        }

        //Finds missing
        final ArrayList<T> missing = new ArrayList<T>();
        for (final T t : list) {
            if (!hash.containsKey(t)) {
                missing.add(t);
            }
        }

        //Return missing
        return missing;
    }
}




