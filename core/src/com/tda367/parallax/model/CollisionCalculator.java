package com.tda367.parallax.model;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.physics.bullet.*;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.tda367.parallax.utilities.ResourceLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class that calculates collisions of {@link com.tda367.parallax.model.core.collision.Collidable}with the use of bullet physics.
 */

public class CollisionCalculator implements com.tda367.parallax.model.core.collision.ICollisionCalculator {

    private btCollisionAlgorithmConstructionInfo ci;
    private btDispatcherInfo info;
    private btManifoldResult result;
    private btDefaultCollisionConfiguration collisionConfig;
    private btCollisionDispatcher dispatcher;


    private List<btCollisionAlgorithm> algorithmList;
    private HashMap<com.tda367.parallax.model.core.collision.Collidable, CollisionObjectWrapper> loadedCollidables;

    public CollisionCalculator() {
        Bullet.init();
        loadedCollidables = new HashMap<com.tda367.parallax.model.core.collision.Collidable, CollisionObjectWrapper>();
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
    public boolean hasCollided(com.tda367.parallax.model.core.collision.Collidable first, com.tda367.parallax.model.core.collision.Collidable second) {

        if (!collisionCheckNeeded(first,second)) {
            return false;
        }

        if (loadedCollidables.size() > 100){
            clearLoadedCollidables();
        }

        if (algorithmList.size() > 100) {
            clearAlgorithmList();
        }

        CollisionObjectWrapper co0 = getCollisionWrapper(first);
        CollisionObjectWrapper co1 = getCollisionWrapper(second);

        result.setBody0Wrap(co0.wrapper);
        result.setBody1Wrap(co1.wrapper);


        algorithmList.add(dispatcher.findAlgorithm(co0.wrapper, co1.wrapper));
        algorithmList.get(algorithmList.size()-1).processCollision(co0.wrapper, co1.wrapper, info, result);

        dispatcher.freeCollisionAlgorithm(algorithmList.get(algorithmList.size()-1).getCPointer());
        dispatcher.getInternalManifoldPool().dispose();

        boolean r = result.getPersistentManifold().getNumContacts() > 0;
        result.getPersistentManifold().dispose();

        return r;
    }

    private void clearAlgorithmList() {
        for (btCollisionAlgorithm btCollisionAlgorithm : algorithmList) {
            btCollisionAlgorithm.dispose();
        }
        algorithmList.clear();
    }

    private CollisionObjectWrapper getCollisionWrapper(com.tda367.parallax.model.core.collision.Collidable collidable){
        CollisionObjectWrapper wrapper = loadedCollidables.get(collidable);

        if (wrapper == null){
            wrapper = loadCollidable(collidable);
        }

        updateCollisionObject(wrapper,collidable);

        return wrapper;
    }
    private CollisionObjectWrapper loadCollidable(com.tda367.parallax.model.core.collision.Collidable collidable) {

        btCollisionShape shape = ResourceLoader.getInstance().getCollisionShape(collidable.getCollisionModelPath());

        btCollisionObject collisionObject = new btCollisionObject();
        collisionObject.setCollisionShape(shape);

        CollisionObjectWrapper wrapper = new CollisionObjectWrapper(collisionObject);
        updateCollisionObject(wrapper,collidable);
        loadedCollidables.put(collidable,wrapper);

        return wrapper;
    }

    private void updateCollisionObject(CollisionObjectWrapper collisionObject, com.tda367.parallax.model.core.collision.Collidable collidable){
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
    private boolean collisionCheckNeeded(com.tda367.parallax.model.core.collision.Collidable first, com.tda367.parallax.model.core.collision.Collidable second){
        //Return false if:

        //Collision is disabled on one of the collidables
        if (!first.collisionActivated() || !second.collisionActivated()){
            return false;
        }

        //If both are containers
        if (first.getCollidableType() == com.tda367.parallax.model.core.collision.CollidableType.CONTAINER &&
                first.getCollidableType() == second.getCollidableType()
            ) {
            return false;
        }


        //If both are obstacles
        if (
                first.getCollidableType() == com.tda367.parallax.model.core.collision.CollidableType.OBSTACLE &&
                        second.getCollidableType() == com.tda367.parallax.model.core.collision.CollidableType.OBSTACLE
                ) {
            return false;
        }

        //If none of them are either a spaceCraft of a harmful type
        if (
                (first.getCollidableType() == com.tda367.parallax.model.core.collision.CollidableType.SPACECRAFT || first.getCollidableType() == com.tda367.parallax.model.core.collision.CollidableType.HARMFUL) ||
                (second.getCollidableType() == com.tda367.parallax.model.core.collision.CollidableType.SPACECRAFT || second.getCollidableType() == com.tda367.parallax.model.core.collision.CollidableType.HARMFUL)
            ) {
            return true;
        } else {
            return false;
        }
    }
    private void clearLoadedCollidables(){
        for (CollisionObjectWrapper collisionObjectWrapper : loadedCollidables.values()) {
            collisionObjectWrapper.dispose();
        }
        loadedCollidables.clear();
    }


    @Override
    public List<com.tda367.parallax.model.core.collision.CollisionPair> getCollisions(List<? extends com.tda367.parallax.model.core.collision.Collidable> collidables) {

        //Find collisions and save them into pairs.
        List<com.tda367.parallax.model.core.collision.CollisionPair> pairs = new ArrayList<com.tda367.parallax.model.core.collision.CollisionPair>();
        for (int i = 0; i < collidables.size(); i++) {
            for (int j = i; j < collidables.size(); j++) {
                if (i != j && hasCollided(collidables.get(i), collidables.get(j))) {
                    pairs.add(
                            new com.tda367.parallax.model.core.collision.CollisionPair(collidables.get(i), collidables.get(j)
                            )
                    );
                }
            }
        }

        //Return all collision pairs.
        return pairs;
    }

    @Override
    public List<com.tda367.parallax.model.core.collision.CollisionPair> getCollisions(List<? extends com.tda367.parallax.model.core.collision.Collidable> firstGroup,
                                                                                      List<? extends com.tda367.parallax.model.core.collision.Collidable> secondGroup) {
        List<com.tda367.parallax.model.core.collision.CollisionPair> collisionPairs = new ArrayList<com.tda367.parallax.model.core.collision.CollisionPair>();

        for (int i = 0; i < firstGroup.size(); i++){
            for (int j = 0; j < secondGroup.size(); j++){
                if (hasCollided(firstGroup.get(i),secondGroup.get(j))){
                    collisionPairs.add(new com.tda367.parallax.model.core.collision.CollisionPair(
                            firstGroup.get(i),
                            secondGroup.get(j)
                    ));
                }
            }
        }

        return collisionPairs;
    }

    @Override
    public void run() {
        //Get collidables
        com.tda367.parallax.model.core.collision.CollisionManager collisionManager = com.tda367.parallax.model.core.collision.CollisionManager.getInstance();
        List<com.tda367.parallax.model.core.collision.Collidable> collidables = collisionManager.getCollidables();

        //Find collisions
        List<com.tda367.parallax.model.core.collision.CollisionPair> pairs = getCollisions(collidables);

        //Report collisions
        for (com.tda367.parallax.model.core.collision.CollisionPair pair : pairs) {
            collisionManager.alertObservers(pair);
        }
    }

    public void dispose(){}
}