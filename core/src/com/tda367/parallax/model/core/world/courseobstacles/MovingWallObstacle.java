package com.tda367.parallax.model.core.world.courseobstacles;


import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class MovingWallObstacle extends WallObstacle {

    private static Quat4f ROTATION = new Quat4f(0,0.009f,0,1);


    MovingWallObstacle(Vector3f pos, Quat4f rot) {
        super(pos, rot);
    }


    @Override
    public void update(int milliSinceLastUpdate){
        super.update(milliSinceLastUpdate);
        ROTATION.normalize();
        super.getRot().mul(ROTATION);
        System.out.println(super.getRot());
    }
}
