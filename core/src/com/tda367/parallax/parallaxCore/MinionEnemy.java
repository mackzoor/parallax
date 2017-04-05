package com.tda367.parallax.parallaxCore;

/**
 * An enemy minion. Will try to destroy its target.
 */
public class MinionEnemy implements EnemyAI {

    private com.tda367.parallax.parallaxCore.SpaceCraft spaceCraft;
    private com.tda367.parallax.parallaxCore.Collidable target;


    //TODO Update?

    MinionEnemy(com.tda367.parallax.parallaxCore.SpaceCraft spaceCraft){

    }

    @Override
    public void setTarget(com.tda367.parallax.parallaxCore.Collidable collidableObject) {
        target = collidableObject;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void update(int milliSinceLastUpdate) {
        //TODO Update ai to target enemy and fire if targeted
    }
}
