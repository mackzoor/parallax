package com.tda367.parallax.model;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.*;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.tda367.parallax.model.parallaxcore.collision.*;
import com.tda367.parallax.util.ResourceLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that calculates collisions of {@link Collidable}with the use of bullet physics.
 */

public class CollisionCalculator implements ICollisionCalculator {

    private final btSphereShape ballOne;
    private final btSphereShape ballTwo;
    private final btCollisionObject objectOne;
    private final btCollisionObject objectTwo;
    private btDefaultCollisionConfiguration collisionConfig;
    private btCollisionDispatcher dispatcher;

    public CollisionCalculator() {
        Bullet.init();

        //These are needed
        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);


        //Collision shapes
        ballOne = new btSphereShape(0.5f);
        ballTwo = new btSphereShape(0.5f);


        //First collision object
        objectOne = new btCollisionObject();
        objectOne.setCollisionShape(ballTwo);
        objectOne.setWorldTransform(new Matrix4());


        //Second collision object
        objectTwo = new btCollisionObject();
        objectTwo.setCollisionShape(ballOne);
        objectTwo.setWorldTransform(new Matrix4());
}


    @Override
    public boolean hasCollided(Collidable first, Collidable second) {

        if (!first.collisionActivated() || !second.collisionActivated()){
            return false;
        }

        if (first.getCollidableType() == CollidableType.CONTAINER &&
                first.getCollidableType() == second.getCollidableType()
                ) {
            return false;
        }

        if (
                first.getCollidableType() == CollidableType.OBSTACLE &&
                        second.getCollidableType() == CollidableType.OBSTACLE
                ) {
            return false;
        }


        //Set collision shape
        btCollisionShape fcs = ResourceLoader.getInstance().getCollisionShape(first.getCollisionModelPath());
        btCollisionShape scs = ResourceLoader.getInstance().getCollisionShape(second.getCollisionModelPath());

        objectOne.setCollisionShape(fcs);
        objectTwo.setCollisionShape(scs);

        //Translate collision shapes
        objectOne.setWorldTransform(new Matrix4());
        objectOne.setWorldTransform(objectOne.getWorldTransform().setTranslation(
                first.getPos().getX(),
                first.getPos().getZ(),
                first.getPos().getY()*-1
        ));

        objectOne.setWorldTransform(objectOne.getWorldTransform().rotate(
                new Quaternion(
                        first.getRot().getX(),
                        first.getRot().getZ(),
                        first.getRot().getY() * -1,
                        first.getRot().getW()
                )
        ));

        objectTwo.setWorldTransform(new Matrix4());
        objectTwo.setWorldTransform(objectTwo.getWorldTransform().setTranslation(
                second.getPos().getX(),
                second.getPos().getZ(),
                second.getPos().getY()*-1
        ));

        objectTwo.setWorldTransform(objectTwo.getWorldTransform().rotate(
                new Quaternion(
                        second.getRot().getX(),
                        second.getRot().getZ(),
                        second.getRot().getY() * -1,
                        second.getRot().getW()
                )
        ));

        CollisionObjectWrapper co0 = new CollisionObjectWrapper(objectOne);
        CollisionObjectWrapper co1 = new CollisionObjectWrapper(objectTwo);

        btCollisionAlgorithmConstructionInfo ci = new btCollisionAlgorithmConstructionInfo();
        ci.setDispatcher1(dispatcher);
        btCollisionAlgorithm algorithm = dispatcher.findAlgorithm(co0.wrapper, co1.wrapper);

        btDispatcherInfo info = new btDispatcherInfo();
        btManifoldResult result = new btManifoldResult(co0.wrapper, co1.wrapper);

        algorithm.processCollision(co0.wrapper, co1.wrapper, info, result);

        boolean r = result.getPersistentManifold().getNumContacts() > 0;

        result.dispose();
        info.dispose();
        algorithm.dispose();
        ci.dispose();
        co1.dispose();
        co0.dispose();

        return r;
    }

    @Override
    public List<CollisionPair> getCollisions(List<? extends Collidable> collidables) {
        return new ArrayList<CollisionPair>();
    }

    @Override
    public List<CollisionPair> getCollisions(List<? extends Collidable> firstGroup,
                                              List<? extends Collidable> secondGroup) {
        List<CollisionPair> collisionPairs = new ArrayList<CollisionPair>();

        for (int i = 0; i < firstGroup.size(); i++){
            for (int j = 0; j < secondGroup.size(); j++){
                if (hasCollided(firstGroup.get(i),secondGroup.get(j))){
                    collisionPairs.add(new CollisionPair(
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
        CollisionManager collisionManager = CollisionManager.getInstance();
        List<Collidable> collidables = collisionManager.getCollidables();
        List<CollisionPair> pairs = new ArrayList<CollisionPair>();
        for (int i = 0; i < collidables.size(); i++) {
            for (int j = i; j < collidables.size(); j++) {
                if (i != j && hasCollided(collidables.get(i), collidables.get(j))) {
                    pairs.add(
                            new CollisionPair(collidables.get(i), collidables.get(j)
                            )
                    );
                }
            }
        }

        for (CollisionPair pair : pairs) {
            collisionManager.alertObservers(pair);
        }

    }

    public void dispose(){}
}