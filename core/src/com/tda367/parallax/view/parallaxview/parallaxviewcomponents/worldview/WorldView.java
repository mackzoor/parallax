package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.worldview;


import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import com.tda367.parallax.model.core.world.ICourseModule;
import com.tda367.parallax.model.core.world.World;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.menu.BackgroundView;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.powerupviews.PowerUpViewAutomaticTransform;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.spacecraftviews.SpaceCraftAutomaticTransform;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.worldview.courseviews.CourseModelView;
import com.tda367.parallax.view.rendering.Renderable3dObject;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * View class for the {@link World}.
 */
public class WorldView {

    private final World world;

    private final Map<ICourseModule, CourseModelView> courseModuleHash;
    private final Map<ISpaceCraft, SpaceCraftAutomaticTransform> spaceCraftHash;
    private final Map<IPowerUp, PowerUpViewAutomaticTransform> powerUpsHash;
    private final CourseIntroView startingGrid;

    /**
     * Creates a WorldView from a {@link World}.
     *
     * @param world to be used to create the WorldView.
     */
    public WorldView(World world) {
        this.world = world;

        this.startingGrid = new CourseIntroView();


        this.courseModuleHash = new HashMap<ICourseModule, CourseModelView>();
        this.spaceCraftHash = new HashMap<ISpaceCraft, SpaceCraftAutomaticTransform>();
        this.powerUpsHash = new HashMap<IPowerUp, PowerUpViewAutomaticTransform>();

        updateSpaceCraftList();
        updateCourseModuleList();
        updatePowerupList();
    }

    public void render() {
        updateSpaceCraftList();
        updateCourseModuleList();
        updatePowerupList();

        startingGrid.render();


        //Render Course modules
        for (final CourseModelView courseModelView : this.courseModuleHash.values()) {
            courseModelView.render();
        }

        //Render SpaceCraft
        for (final SpaceCraftAutomaticTransform spaceCraftAutomaticTransform : this.spaceCraftHash.values()) {
            spaceCraftAutomaticTransform.render();
        }


        //Render Powerups
        for (final PowerUpViewAutomaticTransform powerUpViewAutomaticTransform : this.powerUpsHash.values()) {
            powerUpViewAutomaticTransform.render();
        }
        for (final IPowerUp iPowerUp : this.powerUpsHash.keySet()) {
            this.powerUpsHash.get(iPowerUp).render();
        }
    }

    private void updatePowerupList() {
        final List<IPowerUp> missingPowerUps = syncHash(this.powerUpsHash, this.world.getPowerUps());

        for (final IPowerUp missingPowerUp : missingPowerUps) {
            this.powerUpsHash.put(missingPowerUp, new PowerUpViewAutomaticTransform(missingPowerUp));
        }
    }

    /**
     * Updates the CourseModuleList from the world object.
     */
    private void updateCourseModuleList() {

        final List<ICourseModule> missingModules = syncHash(this.courseModuleHash, this.world.getModules());

        for (final ICourseModule missingModule : missingModules) {
            this.courseModuleHash.put(missingModule, new CourseModelView(missingModule));
        }
    }

    /**
     * Updates the SpaceCraftList from the world object.
     */
    private void updateSpaceCraftList() {
        final List<ISpaceCraft> missingSpaceCraft = syncHash(this.spaceCraftHash, this.world.getSpaceCrafts());

        for (final ISpaceCraft iSpaceCraft : missingSpaceCraft) {
            this.spaceCraftHash.put(iSpaceCraft, new SpaceCraftAutomaticTransform(iSpaceCraft));
        }

    }

    private <T> List<T> syncHash(Map<T, ? extends View> hash, List<T> list) {
        //Find obsolete
        final List<T> obsolete = new ArrayList<T>();
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
        final List<T> missing = new ArrayList<T>();
        for (final T t : list) {
            if (!hash.containsKey(t)) {
                missing.add(t);
            }
        }

        //Return missing
        return missing;
    }
}
