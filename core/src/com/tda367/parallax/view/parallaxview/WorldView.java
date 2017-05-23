package com.tda367.parallax.view.parallaxview;


import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import com.tda367.parallax.model.core.world.ICourseModule;
import com.tda367.parallax.model.core.world.World;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * View class for the {@link World}.
 */
public class WorldView implements View {

    private final World world;

    private HashMap<ICourseModule, CourseModelView> courseModuleHash;
    private HashMap<ISpaceCraft, ISpaceCraftView> spaceCraftHash;
    private HashMap<IPowerUp, IPowerUpView> powerUpsHash;

    /**
     * Creates a WorldView from a {@link World}.
     * @param world to be used to create the WorldView.
     */
    WorldView(World world) {
        this.world = world;

        courseModuleHash = new HashMap<ICourseModule, CourseModelView>();
        spaceCraftHash = new HashMap<ISpaceCraft, ISpaceCraftView>();
        powerUpsHash = new HashMap<IPowerUp, IPowerUpView>();

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
        for (ICourseModule iCourseModule : courseModuleHash.keySet()) {
            courseModuleHash.get(iCourseModule).render();
        }

        //Render SpaceCraft
        for (ISpaceCraft iSpaceCraft : spaceCraftHash.keySet()) {
            spaceCraftHash.get(iSpaceCraft).render();
        }

        //Render Powerups
        for (IPowerUp iPowerUp : powerUpsHash.keySet()) {
            powerUpsHash.get(iPowerUp).render();
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
        List<IPowerUp> missingPowerUps = syncHash(powerUpsHash,world.getPowerUps());

        for (IPowerUp missingPowerUp : missingPowerUps) {
            powerUpsHash.put(missingPowerUp,new IPowerUpView(missingPowerUp));
        }
    }
    /**
     * Updates the CourseModuleList from the world object
     */
    private void updateCourseModuleList(){

        List<ICourseModule> missingModules = syncHash(courseModuleHash,world.getModules());

        for (ICourseModule missingModule : missingModules) {
            courseModuleHash.put(missingModule,new CourseModelView(missingModule));
        }
    }
    /**
     * Updates the SpaceCraftList from the world object
     */
    private void updateSpaceCraftList(){
        List<ISpaceCraft> missingSpaceCraft = syncHash(spaceCraftHash,world.getSpaceCrafts());

        for (ISpaceCraft iSpaceCraft : missingSpaceCraft) {
            spaceCraftHash.put(iSpaceCraft,new ISpaceCraftView(iSpaceCraft));
        }

    }


    /**
     * Removes obsolete views from hash and finds the missing keys from hash from the list.
     * @param hash hash to be worked on.
     * @param list reference list.
     * @param <T> Object type.
     * @return Missing keys in hash from the list provided.
     */
    private <T> List<T> syncHash(HashMap<T,? extends View> hash, List<T> list){
        //Find obsolete
        ArrayList<T> obsolete = new ArrayList<T>();
        for (T t : hash.keySet()) {
            if (hash.get(t).isObsolete()){
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
            if (!hash.containsKey(t)){
                missing.add(t);
            }
        }

        //Return missing
        return missing;
    }
}
