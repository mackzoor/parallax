package com.tda367.parallax.parallaxCore.enemies;

import com.tda367.parallax.parallaxCore.spaceCraft.ISpaceCraft;

/**
 * An enemy minion. Will try to destroy its target.
 */
public class MinionEnemy extends HunterAI {
    private ISpaceCraft spaceCraft;

    public MinionEnemy(ISpaceCraft spaceCraft){
        this.spaceCraft = spaceCraft;
    }

    private void operate(){

//        spaceCraft.setAccelerateTarget(1);

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

    public ISpaceCraft getSpaceCraft() {
        return spaceCraft;
    }
}
