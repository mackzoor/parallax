package com.tda367.parallax.parallaxCore.powerUps;

import com.tda367.parallax.parallaxCore.spaceCraft.ISpaceCraft;

import javax.swing.text.Position;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by Rasmus on 2017-05-02.
 */
public interface IPowerUp {

    void activate(ISpaceCraft agelion);

    void usePU(Vector3f pos, Quat4f rot);


    void update(int milliSinceLastUpdate);

}
