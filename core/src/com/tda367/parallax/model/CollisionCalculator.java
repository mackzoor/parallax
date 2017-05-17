package com.tda367.parallax.model;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.*;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.tda367.parallax.model.parallaxcore.collision.*;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that calculates collisions of {@link Collidable}with the use of bullet physics.
 */

public class CollisionCalculator implements ICollisionCalculator {

    private btSphereShape sphereShape;
    private btDefaultCollisionConfiguration collisionConfig;
    private btCollisionDispatcher dispatcher;
    private CollisionObjectWrapper co0;
    private CollisionObjectWrapper co1;
    private btCollisionAlgorithmConstructionInfo ci;
    private btSphereSphereCollisionAlgorithm algorithm;
    private btDispatcherInfo info;
    private btManifoldResult result;
    private btCollisionObject obj1;
    private btCollisionObject obj2;

    public CollisionCalculator() {
        Bullet.init();

        //These are needed
        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);

        createCollisionObjects();
        createResultPart();
        createAlgorithm();
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

        //Translate collision shapes
        obj1.setWorldTransform(obj1.getWorldTransform().setTranslation(
                first.getPos().getX(),
                first.getPos().getZ(),
                first.getPos().getY()*-1
        ));

        obj2.setWorldTransform(obj2.getWorldTransform().setTranslation(
                second.getPos().getX(),
                second.getPos().getZ(),
                second.getPos().getY()*-1
        ));



        algorithm.processCollision(co0.wrapper, co1.wrapper, info, result);
        boolean res = result.getPersistentManifold().getNumContacts() > 0;


        return res;
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
        System.out.println(collidables.size());
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

    private void createResultPart(){
        ci = new btCollisionAlgorithmConstructionInfo();
        ci.setDispatcher1(dispatcher);
        info = new btDispatcherInfo();
        result = new btManifoldResult(co0.wrapper, co1.wrapper);
    }
    private void disposeResultPart(){
        ci.dispose();
        info.dispose();
        result.dispose();
    }

    private void createAlgorithm(){
        algorithm = new btSphereSphereCollisionAlgorithm(null,ci,co0.wrapper,co1.wrapper);
    }
    private void disposeAlgorithm(){
        algorithm.dispose();
    }

    private void createCollisionObjects(){
        if (obj1 == null && obj2 == null){
            //Create collision shapes
            sphereShape = new btSphereShape(1f);

            obj1 = new btCollisionObject();
            obj1.setCollisionShape(sphereShape);

            obj2 = new btCollisionObject();
            obj2.setCollisionShape(sphereShape);

            co0 = new CollisionObjectWrapper(obj1);
            co1 = new CollisionObjectWrapper(obj2);
        }
    }
    private void disposeCollisionObjects(){
        sphereShape.dispose();

        obj1.dispose();
        obj2.dispose();

        co0.dispose();
        co1.dispose();


        //Make em null
        obj1 = null;
        obj2 = null;

        co0 = null;
        co1 = null;

        sphereShape = null;
    }

    public void dispose(){
        //Dispose c++ objects, SUPER IMPORTANT. Will cause a memory leak if not done.
        disposeResultPart();
        disposeAlgorithm();
        disposeCollisionObjects();

        collisionConfig.dispose();
        dispatcher.dispose();
    }
}
