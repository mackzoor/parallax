package com.tda367.parallax.view.parallaxview;


import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import com.tda367.parallax.model.core.world.ICourseModule;
import com.tda367.parallax.model.core.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * View class for the {@link World}.
 */
public class WorldView implements View {

    private final World world;

    private Map<ICourseModule, CourseModelView> courseModuleHash;
    private Map<ISpaceCraft, ISpaceCraftView> spaceCraftHash;
    private Map<IPowerUp, IPowerUpView> powerUpsHash;

    /**
     * Creates a WorldView from a {@link World}.
     *
     * @param world to be used to create the WorldView.
     */
    WorldView(World world) {
        this.world = world;

        this.courseModuleHash = new HashMap<ICourseModule, CourseModelView>();
        this.spaceCraftHash = new HashMap<ISpaceCraft, ISpaceCraftView>();
        this.powerUpsHash = new HashMap<IPowerUp, IPowerUpView>();

        updateSpaceCraftList();
        updateCourseModuleList();
        updatePowerupList();
    }

    @Override
    public void render() {
        updateSpaceCraftList();
        updateCourseModuleList();
        updatePowerupList();


        //Render Course modules
        for (ICourseModule iCourseModule : this.courseModuleHash.keySet()) {
            this.courseModuleHash.get(iCourseModule).render();
        }

        //Render SpaceCraft
        for (ISpaceCraft iSpaceCraft : this.spaceCraftHash.keySet()) {
            this.spaceCraftHash.get(iSpaceCraft).render();
        }

        //Render Powerups
        for (IPowerUp iPowerUp : this.powerUpsHash.keySet()) {
            this.powerUpsHash.get(iPowerUp).render();
        }
    }

    @Override
    public boolean isObsolete() {
        return false;
    }


    /**
     * Updates the PowerUpList from the world object
     */
    private void updatePowerupList() {
        List<IPowerUp> missingPowerUps = syncHash(this.powerUpsHash, this.world.getPowerUps());

        for (IPowerUp missingPowerUp : missingPowerUps) {
            this.powerUpsHash.put(missingPowerUp, new IPowerUpView(missingPowerUp));
        }
    }

    /**
     * Updates the CourseModuleList from the world object
     */
    private void updateCourseModuleList() {

        List<ICourseModule> missingModules = syncHash(this.courseModuleHash, this.world.getModules());

        for (ICourseModule missingModule : missingModules) {
            this.courseModuleHash.put(missingModule, new CourseModelView(missingModule));
        }
    }

    /**
     * Updates the SpaceCraftList from the world object
     */
    private void updateSpaceCraftList() {
        List<ISpaceCraft> missingSpaceCraft = syncHash(this.spaceCraftHash, this.world.getSpaceCrafts());

        for (ISpaceCraft iSpaceCraft : missingSpaceCraft) {
            this.spaceCraftHash.put(iSpaceCraft, new ISpaceCraftView(iSpaceCraft));
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
