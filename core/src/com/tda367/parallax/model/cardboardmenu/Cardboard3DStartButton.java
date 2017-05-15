package com.tda367.parallax.model.cardboardmenu;

import com.tda367.parallax.view.Model;
import com.tda367.parallax.view.Renderable;
import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import com.tda367.parallax.view.Renderer3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by Rasmus on 2017-05-08.
 */
public class Cardboard3DStartButton implements Renderable,Collidable {

    private Vector3f pos;
    private Quat4f rot;
    private boolean isHit;

    private Model model;

    private String collisionModel;

    private boolean collisionEnabled;

    Cardboard3DStartButton(){
        pos = new Vector3f();
        rot = new Quat4f();
        model = new Model("boxObstacle.g3db", "3dModels/boxObstacle");
        collisionModel = "3dModels/boxObstacle/boxObstacle.g3db";
        collisionEnabled = true;
    }


    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void addToRenderManager() {
        Renderer3D.getInstance().addObjectToFrame(this);
    }

    @Override
    public void removeFromRenderManager() {
        Renderer3D.getInstance().addObjectToFrame(this);
    }

    @Override
    public Vector3f getPos() {
        return pos;
    }

    @Override
    public Quat4f getRot() {
        return rot;
    }


    public boolean isActive() {
        return collisionEnabled;
    }

    @Override
    public boolean collisionActivated() {
        return collisionEnabled;
    }

    @Override
    public void disableCollision() {
        collisionEnabled = false;
    }

    @Override
    public void enableCollision() {
        collisionEnabled = true;
    }

    @Override
    public String getCollisionModelPath() {
        return collisionModel;
    }

    @Override
    public void addToCollisionManager() {
        CollisionManager.getInstance().addCollisionCheck(this);
    }

    @Override
    public void removeFromCollisionManager() {
        CollisionManager.getInstance().removeCollisionCheck(this);
    }

    @Override
    public CollidableType getCollidableType() {
        return CollidableType.OBSTACLE;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if(collidable.getCollidableType() == CollidableType.HARMFUL){
            isHit = true;
        }
    }
    public void setPos(Vector3f vector3f){
        pos = vector3f;
    }
    public boolean isCollided(){
        return isHit;
    }
}
