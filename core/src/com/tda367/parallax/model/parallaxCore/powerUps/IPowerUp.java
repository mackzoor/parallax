package com.tda367.parallax.model.parallaxCore.powerUps;

import com.tda367.parallax.model.CoreAbstraction.Usable;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by Rasmus on 2017-05-02.
 */
public interface IPowerUp extends Usable{

    void usePU(Vector3f pos, Quat4f rot);

}
