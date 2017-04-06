package com.tda367.parallax.parallaxCore;

/**
 * An enemy minion. Will try to destroy its target.
 */
public class MinionEnemy extends HunterAI {
    private ISpaceCraft spaceCraft;

    MinionEnemy(ISpaceCraft spaceCraft){
        this.spaceCraft = spaceCraft;
    }

    private void operate(){
        //TODO implement minion operate method.
        //Track to target

        //If pointing at target
            //Shoot
        //else
            //Do nothing
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        operate();
    }
}
