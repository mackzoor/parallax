package com.tda367.parallax.model.parallaxCore.course;

import com.tda367.parallax.model.CoreAbstraction.Collidable;
import com.tda367.parallax.model.CoreAbstraction.IModel;
import com.tda367.parallax.model.CoreAbstraction.Model;
import com.tda367.parallax.model.CoreAbstraction.RenderManager;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A course module that will represent the visual part of a course.
 */
public class DefaultCourseModule implements ICourseModule, IModel {
    private Vector3f pos;
    private Quat4f rot;
    private float length;

    private List<BoxObstacle> boxObstacles;
    private List<Collidable> usables;
    private Model model;


    DefaultCourseModule(Vector3f pos,int obstacleAmmount){
        this.pos = pos;
        this.pos.setY(pos.getY());
        this.rot = new Quat4f();

        model = new Model("course.g3db", "3dModels/defaultCourse");
        length = 64;
        this.boxObstacles = new ArrayList<BoxObstacle>();
        usables = new ArrayList<Collidable>();

        addObstacles(obstacleAmmount);

        //TODO add usables in courseModule
    }
    public DefaultCourseModule(Vector3f pos){
        this(pos,4);
    }

    private void addObstacles(int i){
        for (int x = 0; x < i; x++){
            BoxObstacle boxObstacleNew = new BoxObstacle();

            Random rand = new Random();
            Vector3f obstaclePos = new Vector3f(
                    rand.nextFloat()*12-6,
                    rand.nextFloat()*length-length/2+(this.pos.getY()),
                    rand.nextFloat()*12-6
            );

            boxObstacleNew.getPos().set(obstaclePos);

            this.boxObstacles.add(boxObstacleNew);
        }
    }

    //ICourseModule
    @Override
    public float getLength(){
        return length;
    }
    @Override
    public List<? extends Collidable> getBoxObstacles() {
        return boxObstacles;
    }
    @Override
    public List<Collidable> getUsables() {
        return usables;
    }

    //Renderable
    @Override
    public void addToRenderManager() {
        RenderManager.getInstance().addRenderTask(this);

        for (BoxObstacle boxObstacle : boxObstacles){
            boxObstacle.addToRenderManager();
        }

    }
    @Override
    public void removeFromRenderManager() {
        RenderManager.getInstance().removeRenderTask(this);

        for (BoxObstacle boxObstacle : boxObstacles){
            boxObstacle.removeFromRenderManager();
        }

    }

    //Transformable
    @Override
    public Vector3f getPos() {
        return pos;
    }
    @Override
    public Quat4f getRot() {
        return rot;
    }

    @Override
    public Model getModel() {
        return model;
    }
}
