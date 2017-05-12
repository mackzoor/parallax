package com.tda367.parallax.model;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.*;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.tda367.parallax.model.parallaxcore.collision.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that calculates collisions of {@link Collidable}with the use of bullet physics.
 */

public class CollisionCalculator implements ICollisionCalculator {

    private final btDefaultCollisionConfiguration collisionConfig;
    private final btCollisionDispatcher dispatcher;
    private final CollisionObjectWrapper co0;
    private final CollisionObjectWrapper co1;
    private final btCollisionAlgorithmConstructionInfo ci;
    private final btSphereSphereCollisionAlgorithm algorithm;
    private final btDispatcherInfo info;
    private final btManifoldResult result;
    private btCollisionObject obj1;
    private btCollisionObject obj2;

    public CollisionCalculator() {
        Bullet.init();

        btCollisionShape sphereShape = new btSphereShape(1f);
        obj1 = new btCollisionObject();
        obj1.setCollisionShape(sphereShape);


        btCollisionShape sphereShape2 = new btSphereShape(1f);
        obj2 = new btCollisionObject();
        obj2.setCollisionShape(sphereShape2);

        co0 = new CollisionObjectWrapper(obj1);
        co1 = new CollisionObjectWrapper(obj2);

        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);

        ci = new btCollisionAlgorithmConstructionInfo();
        ci.setDispatcher1(dispatcher);
        algorithm = new btSphereSphereCollisionAlgorithm(null,ci,co0.wrapper,co1.wrapper);
        info = new btDispatcherInfo();
        result = new btManifoldResult(co0.wrapper, co1.wrapper);
    }

    @Override
    public boolean hasCollided(Collidable first, Collidable second) {
        if (!first.collisionActivated() || !second.collisionActivated()){
            return false;
        }
        if (
            first.getCollidableType() == CollidableType.OBSTACLE &&
            second.getCollidableType() == CollidableType.OBSTACLE
            ) {
            return false;
        }

        obj1.setWorldTransform(obj1.getWorldTransform().setTranslation(new Vector3(
                first.getPos().getX(),
                first.getPos().getZ(),
                first.getPos().getY()*-1
        )));

        obj2.setWorldTransform(obj2.getWorldTransform().setTranslation(new Vector3(
                second.getPos().getX(),
                second.getPos().getZ(),
                second.getPos().getY()*-1
        )));

        algorithm.processCollision(co0.wrapper, co1.wrapper, info, result);

        return result.getPersistentManifold().getNumContacts() > 0;
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
            for (int j = 0; j < collidables.size(); j++) {
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

    //
    // TODO, Make these methods not return at a later date.
    private btCollisionObject collidableConverter(Collidable coll) {
        btCollisionObject collisionObject = new btCollisionObject();

        return null;
    }

    private btCollisionShape getCollisionShape(Collidable coll) {

        return null;
    }


}
