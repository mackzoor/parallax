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

        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);
    }

    @Override
    public boolean hasCollided(Collidable first, Collidable second) {
        if (
            first.getCollidableType() == CollidableType.OBSTACLE &&
            second.getCollidableType() == CollidableType.OBSTACLE
            ) {
            return false;
        }
        if (!first.collisionActivated() || !second.collisionActivated()){
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


        CollisionObjectWrapper co0 = new CollisionObjectWrapper(obj1);
        CollisionObjectWrapper co1 = new CollisionObjectWrapper(obj2);

        btCollisionAlgorithmConstructionInfo ci = new btCollisionAlgorithmConstructionInfo();
        ci.setDispatcher1(dispatcher);
        btCollisionAlgorithm algorithm = new btSphereSphereCollisionAlgorithm(null,ci,co0.wrapper,co1.wrapper);
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

        for (int i = 0; i < collidables.size(); i++) {
            for (int j = 0; j < collidables.size(); j++) {
                if (i != j && hasCollided(collidables.get(i), collidables.get(j))) {
                    collisionManager.alertObservers(
                            new CollisionPair(collidables.get(i), collidables.get(j)
                            )
                    );
                }
            }
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
