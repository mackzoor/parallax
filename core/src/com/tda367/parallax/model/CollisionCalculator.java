package com.tda367.parallax.model;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.*;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.tda367.parallax.model.core.collision.*;
import com.tda367.parallax.utilities.ResourceLoader;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Calculates collisions between {@link Collidable} with the use of bullet physics.
 */
public class CollisionCalculator implements ICollisionCalculator {

    private btCollisionAlgorithmConstructionInfo ci;
    private btDispatcherInfo info;
    private btManifoldResult result;
    private btDefaultCollisionConfiguration collisionConfig;
    private btCollisionDispatcher dispatcher;

    private List<btCollisionAlgorithm> algorithmList;
    private HashMap<Collidable, CollisionObjectWrapper> loadedCollidables;

    public CollisionCalculator() {
        Bullet.init();
        loadedCollidables = new HashMap<Collidable, CollisionObjectWrapper>();
        algorithmList = new ArrayList<btCollisionAlgorithm>();

        //These are needed
        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);

        ci = new btCollisionAlgorithmConstructionInfo();
        info = new btDispatcherInfo();
        ci.setDispatcher1(dispatcher);

        result = new btManifoldResult();
    }


    @Override
    public void run() {
        CollisionManager collisionManager = CollisionManager.getInstance();
        List<Collidable> collidables = collisionManager.getCollidables();

        List<CollisionResult> results = getAllCollisions(collidables);

        for (CollisionResult result : results) {
            collisionManager.alertObservers(result);
        }
    }

    @Override
    public List<CollisionResult> getAllCollisions(List<? extends Collidable> collidables) {
        //Find collisions.
        List<CollisionResult> collisions = new ArrayList<CollisionResult>();
        for (int i = 0; i < collidables.size(); i++) {
            for (int j = i+1; j < collidables.size(); j++) {
                CollisionResult result = checkCollision(collidables.get(i),collidables.get(j));
                if (result.isCollided()){
                    collisions.add(result);
                }
            }
        }

        return collisions;
    }

    @Override
    public CollisionResult checkCollision(Collidable first, Collidable second) {

        if (!collisionCheckNeeded(first,second)) {
            return noCollisionResult(first, second);
        }

        clearLists();

        btPersistentManifold contactResult = processCollision(first,second);

        CollisionResult collisionResult = createResult(contactResult, first, second);
        contactResult.dispose();

        return collisionResult;
    }

    private boolean collisionCheckNeeded(Collidable first, Collidable second){
        //Return false if:

        //Collision is disabled on one of the collidables
        if (!first.collisionActivated() || !second.collisionActivated()){
            return false;
        }

        //If both are containers
        if (first.getCollidableType() == CollidableType.CONTAINER &&
                first.getCollidableType() == second.getCollidableType()
                ) {
            return false;
        }


        //If both are obstacles
        if (
                first.getCollidableType() == CollidableType.OBSTACLE &&
                        second.getCollidableType() == CollidableType.OBSTACLE
                ) {
            return false;
        }

        //If none of them are either a spaceCraft of a harmful type
        if (
                (first.getCollidableType() == CollidableType.SPACECRAFT || first.getCollidableType() == CollidableType.HARMFUL) ||
                        (second.getCollidableType() == CollidableType.SPACECRAFT || second.getCollidableType() == CollidableType.HARMFUL)
                ) {
            return true;
        } else {
            return false;
        }
    }
    private btPersistentManifold processCollision(Collidable first, Collidable second) {
        CollisionObjectWrapper co0 = getCollisionWrapper(first);
        CollisionObjectWrapper co1 = getCollisionWrapper(second);

        result.setBody0Wrap(co0.wrapper);
        result.setBody1Wrap(co1.wrapper);

        algorithmList.add(dispatcher.findAlgorithm(co0.wrapper, co1.wrapper));
        algorithmList.get(algorithmList.size()-1).processCollision(co0.wrapper, co1.wrapper, info, result);

        dispatcher.freeCollisionAlgorithm(algorithmList.get(algorithmList.size()-1).getCPointer());
        dispatcher.getInternalManifoldPool().dispose();

        return result.getPersistentManifold();
    }
    private void updateCollisionObject(CollisionObjectWrapper collisionObject, Collidable collidable){
        btCollisionObject collObject = collisionObject.wrapper.getCollisionObject();

        //Translate
        collObject.setWorldTransform(new Matrix4());
        collObject.setWorldTransform(collObject.getWorldTransform().setTranslation(
                collidable.getPos().getX(),
                collidable.getPos().getZ(),
                collidable.getPos().getY() * -1
        ));

        //Rotate
        collObject.setWorldTransform(collObject.getWorldTransform().rotate(
                new Quaternion(
                        collidable.getRot().getX(),
                        collidable.getRot().getZ(),
                        collidable.getRot().getY() * -1,
                        collidable.getRot().getW()
                )
        ));
    }

    private CollisionObjectWrapper getCollisionWrapper(Collidable collidable){
        CollisionObjectWrapper wrapper = loadedCollidables.get(collidable);

        if (wrapper == null){
            wrapper = loadCollidable(collidable);
        }

        updateCollisionObject(wrapper,collidable);

        return wrapper;
    }
    private CollisionObjectWrapper loadCollidable(Collidable collidable) {

        btCollisionShape shape = ResourceLoader.getInstance().getCollisionShape(collidable.getCollisionModelPath());

        btCollisionObject collisionObject = new btCollisionObject();
        collisionObject.setCollisionShape(shape);

        CollisionObjectWrapper wrapper = new CollisionObjectWrapper(collisionObject);
        updateCollisionObject(wrapper,collidable);
        loadedCollidables.put(collidable,wrapper);

        return wrapper;
    }

    private CollisionResult noCollisionResult(Collidable first, Collidable second){
        return new CollisionResult(
                first,
                second,
                false,
                new Vector3f(),
                new Vector3f(),
                new Vector3f()
        );
    }
    private CollisionResult createResult(btPersistentManifold manifold, Collidable first, Collidable second){
        boolean collision = manifold.getNumContacts() > 0;

        Vector3f contactPoint;
        if (collision) {
            Vector3 btContactPoint = new Vector3();
            result.getPersistentManifold().getContactPoint(0).getPositionWorldOnA(btContactPoint);
            contactPoint = new Vector3f(
                    btContactPoint.x,
                    btContactPoint.z,
                    btContactPoint.y *-1
            );
        } else {
            contactPoint = new Vector3f();
        }


        CollisionResult collisionResult =  new CollisionResult(
                first,
                second,
                collision,
                contactPoint,
                new Vector3f(),
                new Vector3f()
        );

        return collisionResult;
    }

    private void clearLists(){
        if (loadedCollidables.size() > 100){
            clearLoadedCollidables();
        }

        if (algorithmList.size() > 100) {
            clearAlgorithmList();
        }
    }
    private void clearAlgorithmList() {
        for (btCollisionAlgorithm btCollisionAlgorithm : algorithmList) {
            btCollisionAlgorithm.dispose();
        }
        algorithmList.clear();
    }
    private void clearLoadedCollidables(){
        for (CollisionObjectWrapper collisionObjectWrapper : loadedCollidables.values()) {
            collisionObjectWrapper.dispose();
        }
        loadedCollidables.clear();
    }


    /**
     * Removes c++ objects. Use only if you're never going to use the current object again.
     */
    public void dispose(){
        clearLoadedCollidables();
        clearAlgorithmList();
        info.dispose();
        result.dispose();
        collisionConfig.dispose();
        dispatcher.dispose();
        ci.dispose();
    }

    /**
     * Clears all the lists to reset the collision calculator and free used memory.
     */
    public void clear(){
        clearAlgorithmList();
        clearLoadedCollidables();
    }
}