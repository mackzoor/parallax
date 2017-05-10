package com.tda367.parallax.model.cardboardmenu;

import com.tda367.parallax.model.coreabstraction.Updatable;
import com.tda367.parallax.model.parallaxcore.collision.CollisionObserver;
import com.tda367.parallax.model.parallaxcore.collision.CollisionPair;

import javax.vecmath.Vector3f;

/**
 * Created by Rasmus on 2017-05-08.
 */
public class CardboardMenu implements Updatable, CollisionObserver {

    private CardboardExitButton cbExitButton;
    private CardboardStartButton cbStartButton;
    private CardboardMenuModel cbMenuModel;

    public CardboardMenu(){
        cbExitButton = new CardboardExitButton();
        cbExitButton.setPos(new Vector3f(2,20,0));
        cbStartButton = new CardboardStartButton();
        cbStartButton.setPos(new Vector3f(-2,20,0));
        cbMenuModel = new CardboardMenuModel();

        addButtons();
        addCourse();
    }

    @Override
    public void update(int milliSinceLastUpdate) {

    }

    public void addButtons(){
        //modules.add(cbStartButton);
        cbStartButton.addToRenderManager();
        //modules.add(new CBExitButton());
        cbExitButton.addToRenderManager();
    }

    public void addCourse(){
        cbMenuModel.addToRenderManager();
    }

    @Override
    public void respondToCollision(CollisionPair collisionPair) {

    }

    public CardboardExitButton getExitButton(){
        return cbExitButton;
    }
    public CardboardStartButton getStartButton(){
        return cbStartButton;
    }
}
