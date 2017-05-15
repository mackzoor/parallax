package com.tda367.parallax.view.parallaxview;


import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;
import com.tda367.parallax.model.parallaxcore.spacecraft.ISpaceCraft;
import com.tda367.parallax.model.parallaxcore.world.BoxObstacle;
import com.tda367.parallax.model.parallaxcore.world.ICourseModule;
import com.tda367.parallax.model.parallaxcore.world.World;
import java.util.ArrayList;
import java.util.List;

/**
 * View class for the world model class.
 */
public class WorldView implements View {

    private final World world;
    private List<CourseModelView> courseModelViews;
    private List<AgelionView> spaceCraftsViews;
    private List<IPowerUpView> powerUps;


    WorldView(World world) {
        this.world = world;
        courseModelViews = new ArrayList<CourseModelView>();
        spaceCraftsViews = new ArrayList<AgelionView>();
        powerUps = new ArrayList<IPowerUpView>();
    }

    @Override
    public void render() {
        updateSpaceCraftList();
        updateCourseModuleList();
        updatePowerupList();

        //Render Course modules
        for (CourseModelView courseModelView : courseModelViews) {
            courseModelView.render();
        }

        //Render SpaceCraft
        for (AgelionView spaceCraft : spaceCraftsViews) {
            spaceCraft.render();
        }

        //Render Powerups
        for (IPowerUpView powerUp : powerUps) {
            powerUp.render();
        }
    }

    @Override
    public boolean isObsolete() {
        return false;
    }

    private void updatePowerupList() {
        //TODO Optimise
        powerUps.clear();
        for (IPowerUp iPowerUp : world.getPowerUps()) {
            powerUps.add(new IPowerUpView(iPowerUp));
        }
    }
    private void updateCourseModuleList(){
//        removeObsolete(courseModelViews);

        //TODO Optimise
        courseModelViews.clear();
        for (ICourseModule iCourseModule : world.getModules()) {
            courseModelViews.add(new CourseModelView(iCourseModule));
        }
    }
    private void updateSpaceCraftList(){
//        removeObsolete(spaceCraftsViews);

        //TODO Optimise
        spaceCraftsViews.clear();
        for (ISpaceCraft iSpaceCraft : world.getSpaceCrafts()) {
            spaceCraftsViews.add(new AgelionView(iSpaceCraft));
        }

    }

    private void removeObsolete(List<? extends View> list){
        //get obsolete views
        List<Integer> obsoleteViews = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isObsolete()){
                obsoleteViews.add(i);
            }
        }

        //Remove obsolete views
        for (Integer integer : obsoleteViews) {
            list.remove(integer);
        }
    }

}
