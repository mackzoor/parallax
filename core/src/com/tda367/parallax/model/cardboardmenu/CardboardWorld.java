package com.tda367.parallax.model.cardboardmenu;


import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;


public class CardboardWorld {

    private Vector3f pos;
    private Quat4f rot;


    public CardboardWorld(){
        pos = new Vector3f();
        rot = new Quat4f();

    }

    public Quat4f getRot(){
        return rot;
    }

    public Vector3f getPos(){
        return pos;
    }
}
