package com.tda367.parallax.model.externalcollsioncalculators;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.tda367.parallax.model.core.collision.*;
import com.tda367.parallax.utilities.ResourceLoader;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculates collisions between {@link Collidable} with the use of bullet physics.
 */
public class CollisionCalculator implements ICollisionCalculator {

    private static final int SIZE_LIMIT = 100;

    private final btCollisionAlgorithmConstructionInfo constructionInfo;
    private final btDispatcherInfo info;
    private final btManifoldResult result;
    private final btDefaultCollisionConfiguration collisionConfig;
    private final btCollisionDispatcher dispatcher;

    private final List<btCollisionAlgorithm> algorithmList;
    private final Map<Collidable, CollisionObjectWrapper> loadedCollidables;

    public CollisionCalculator() {
        Bullet.init();
        this.loadedCollidables = new HashMap<Collidable, CollisionObjectWrapper>();
        this.algorithmList = new ArrayList<btCollisionAlgorithm>();

        this.collisionConfig = new btDefaultCollisionConfiguration();
        this.dispatcher = new btCollisionDispatcher(this.collisionConfig);

        this.constructionInfo = new btCollisionAlgorithmConstructionInfo();
        this.info = new btDispatcherInfo();
        this.constructionInfo.setDispatcher1(this.dispatcher);

        this.result = new btManifoldResult();
    }


    @Override
    public void run() {
        final CollisionManager collisionManager = CollisionManager.getInstance();
        final List<Collidable> collidables = collisionManager.getCollidables();

        final List<CollisionResult> results = getAllCollisions(collidables);

        for (final CollisionResult result : results) {
            collisionManager.alertObservers(result);
        }
    }

    @Override
    public List<CollisionResult> getAllCollisions(List<? extends Collidable> collidables) {
        final List<CollisionResult> collisions = new ArrayList<CollisionResult>();
        for (int i = 0; i < collidables.size(); i++) {
            for (int j = i + 1; j < collidables.size(); j++) {
                final CollisionResult result = checkCollision(collidables.get(i), collidables.get(j));
                if (result.isCollided()) {
                    collisions.add(result);
                }
            }
        }

        return collisions;
    }

    @Override
    public CollisionResult checkCollision(Collidable first, Collidable second) {
        CollisionResult result;
        if (collisionCheckNeeded(first, second)) {
            clearLists();

            final btPersistentManifold contactResult = processCollision(first, second);

            final CollisionResult collisionResult = createResult(contactResult, first, second);
            contactResult.dispose();

            result = collisionResult;
        } else {
            result = noCollisionResult(first, second);
        }
        return result;
    }

    private boolean collisionCheckNeeded(Collidable first, Collidable second) {
        return !(!first.collisionActivated()
                || !second.collisionActivated())
                && isNoteworthyCollision(first, second);
    }

    private boolean isNoteworthyCollision(Collidable first, Collidable second) {
        return !(first.getCollidableType() == CollidableType.CONTAINER
                && first.getCollidableType() == second.getCollidableType()
                || first.getCollidableType() == CollidableType.OBSTACLE
                && second.getCollidableType() == CollidableType.OBSTACLE)
                && isCraftOrHarmfulCollision(first, second);
    }

    private boolean isCraftOrHarmfulCollision(Collidable first, Collidable second) {
        return first.getCollidableType() == CollidableType.SPACECRAFT
                || first.getCollidableType() == CollidableType.HARMFUL
                || second.getCollidableType() == CollidableType.SPACECRAFT
                || second.getCollidableType() == CollidableType.HARMFUL;
    }

    private btPersistentManifold processCollision(Collidable first, Collidable second) {
        final CollisionObjectWrapper co0 = getCollisionWrapper(first);
        final CollisionObjectWrapper co1 = getCollisionWrapper(second);

        this.result.setBody0Wrap(co0.wrapper);
        this.result.setBody1Wrap(co1.wrapper);

        this.algorithmList.add(this.dispatcher.findAlgorithm(co0.wrapper, co1.wrapper));
        this.algorithmList.get(this.algorithmList.size() - 1).processCollision(co0.wrapper,
                co1.wrapper,
                this.info, this.result);

        this.dispatcher.freeCollisionAlgorithm(this.algorithmList.get(this.algorithmList.size() - 1).getCPointer());
        this.dispatcher.getInternalManifoldPool().dispose();

        return this.result.getPersistentManifold();
    }

    private void updateCollisionObject(CollisionObjectWrapper collisionObject, Collidable collidable) {
        final btCollisionObject collObject = collisionObject.wrapper.getCollisionObject();

        collObject.setWorldTransform(new Matrix4());
        collObject.setWorldTransform(collObject.getWorldTransform().setTranslation(
                collidable.getPos().getX(),
                collidable.getPos().getZ(),
                collidable.getPos().getY() * -1
        ));

        collObject.setWorldTransform(collObject.getWorldTransform().rotate(
                new Quaternion(
                        collidable.getRot().getX(),
                        collidable.getRot().getZ(),
                        collidable.getRot().getY() * -1,
                        collidable.getRot().getW())
        ));
    }

    private CollisionObjectWrapper getCollisionWrapper(Collidable collidable) {
        CollisionObjectWrapper wrapper = this.loadedCollidables.get(collidable);

        if (wrapper == null) {
            wrapper = loadCollidable(collidable);
        }

        this.updateCollisionObject(wrapper, collidable);

        return wrapper;
    }

    private CollisionObjectWrapper loadCollidable(Collidable collidable) {

        final btCollisionShape shape = ResourceLoader.getInstance().getCollisionShape(collidable.getCollisionModelPath());

        final btCollisionObject collisionObject = new btCollisionObject();
        collisionObject.setCollisionShape(shape);

        final CollisionObjectWrapper wrapper = new CollisionObjectWrapper(collisionObject);
        this.updateCollisionObject(wrapper, collidable);
        this.loadedCollidables.put(collidable, wrapper);

        return wrapper;
    }

    private CollisionResult noCollisionResult(Collidable first, Collidable second) {
        return new CollisionResult(
                first,
                second,
                false,
                new Vector3f(),
                new Vector3f(),
                new Vector3f()
        );
    }

    private CollisionResult createResult(btPersistentManifold manifold, Collidable first, Collidable second) {
        final boolean collision = manifold.getNumContacts() > 0;

        Vector3f contactPoint;
        if (collision) {
            final Vector3 btContactPoint = new Vector3();
            this.result.getPersistentManifold().getContactPoint(0).getPositionWorldOnA(btContactPoint);
            contactPoint = new Vector3f(
                    btContactPoint.x,
                    btContactPoint.z * -1,
                    btContactPoint.y
            );
        } else {
            contactPoint = new Vector3f();
        }


        return new CollisionResult(
                first,
                second,
                collision,
                contactPoint,
                new Vector3f(),
                new Vector3f()
        );

    }

    private void clearLists() {
        if (this.loadedCollidables.size() > SIZE_LIMIT) {
            clearLoadedCollidables();
        }

        if (this.algorithmList.size() > SIZE_LIMIT) {
            clearAlgorithmList();
        }
    }

    private void clearAlgorithmList() {
        for (final btCollisionAlgorithm btCollisionAlgorithm : this.algorithmList) {
            btCollisionAlgorithm.dispose();
        }
        this.algorithmList.clear();
    }

    private void clearLoadedCollidables() {
        for (final CollisionObjectWrapper collisionObjectWrapper : this.loadedCollidables.values()) {
            collisionObjectWrapper.dispose();
        }
        this.loadedCollidables.clear();
    }


    /**
     * Removes c++ objects. Use only if you're never going to use the current object again.
     */
    public void dispose() {
        this.clearLoadedCollidables();
        this.clearAlgorithmList();
        this.info.dispose();
        this.result.dispose();
        this.collisionConfig.dispose();
        this.dispatcher.dispose();
        this.constructionInfo.dispose();
    }

    /**
     * Clears all the lists to reset the collision calculator and free used memory.
     */
    public void clear() {
        this.clearAlgorithmList();
        this.clearLoadedCollidables();
    }

}
