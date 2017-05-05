package com.tda367.parallax.model.parallaxCore.enemies;

import com.tda367.parallax.model.parallaxCore.spaceCraft.ISpaceCraft;

/**
 * An enemy minion. Will try to destroy its target.
 */

public class MinionEnemy extends HunterAI {
    private ISpaceCraft spaceCraft;

    public MinionEnemy(ISpaceCraft spaceCraft){
        this.spaceCraft = spaceCraft;
    }

    private void operate(){
        //TODO implement minion operate method. (Remove commented lines)
        /* spaceCraft.setAccelerateTarget(1);

        //Track to target
        //System.out.println(spaceCraft.getRot().getX() + " " + spaceCraft.getRot().getY() + " " + spaceCraft.getRot().getZ() + " " + spaceCraft.getRot().getW());

        //spaceCraft.getRot().setZ(1f);
        //spaceCraft.getRot().setW(1f);
        //spaceCraft.getRot().setX(1f);
        //spaceCraft.getRot().setY(1f);

       // spaceCraft.getRot().

        //System.out.println(spaceCraft.getRot().getX() + " " + spaceCraft.getRot().getY() + " " + spaceCraft.getRot().getZ() + " " + spaceCraft.getRot().getW());

        //spaceCraft.getRot().getY();
        //spaceCraft.getRot().getZ();
        //spaceCraft.getRot().getW();


        //spaceCraft.getRot().set(new AxisAngle4f(spaceCraft.getRot().getX(),spaceCraft.getRot().getY(),spaceCraft.getRot().getZ(), 90 ));

        //spaceCraft.getRot().set( spaceCraft.getRot().getX(), (spaceCraft.getRot().getY()),  spaceCraft.getRot().getZ(),  spaceCraft.getRot().getW());

        */

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
