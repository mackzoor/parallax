package com.tda367.parallax.model.cardboardmenu;

import com.tda367.parallax.model.parallaxcore.Camera;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import com.tda367.parallax.model.parallaxcore.collision.CollisionObserver;
import com.tda367.parallax.model.parallaxcore.collision.CollisionPair;
import com.tda367.parallax.model.parallaxcore.powerups.Cannon;
import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;
import com.tda367.parallax.model.parallaxcore.util.Updatable;
import com.tda367.parallax.model.parallaxcore.Player;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/*
The Model class for the Cardboard main menu.
Creates the objects in the world and handles logic.
 */
public class MainMenu implements Updatable, CollisionObserver {

    //TODO make an abstract 3DButton class

    Player player;
    private ExitButton3D exitButton;
    private StartButton3D startButton;
    private List<CardboardMenuObserver> observers;
    private List<IPowerUp> powerUps;
    private Camera camera;

    public MainMenu() {
        exitButton = new ExitButton3D(new Vector3f(2, 20, 0),new Quat4f());
        startButton = new StartButton3D(new Vector3f(-2,20,0),new Quat4f());
        observers = new ArrayList<CardboardMenuObserver>();
        powerUps = new ArrayList<IPowerUp>();
        CollisionManager.getInstance().subscribeToCollisions(this);
        camera = new Camera();

    }





    public ExitButton3D getExitButton() {
        return exitButton;
    }

    public StartButton3D getStartButton() {
        return startButton;
    }

    public Camera getCamera(){
        return camera;
    }


    public void action(){
        Cannon cannon = new Cannon();
        powerUps.add(cannon);
        cannon.activate(camera);
    }



    @Override
    public void update(int milliSinceLastUpdate) {
        if(startButton.isCollided()){
            for (CardboardMenuObserver observer : observers) {
                observer.cardboardStartButtonAction();
            }
        }else if(exitButton.isCollided()){
            for (CardboardMenuObserver observer : observers) {
                observer.cardboardExitButtonAction();
            }
        }

        for (IPowerUp powerUp : powerUps) {
            powerUp.update(milliSinceLastUpdate);
        }
        //TODO Fix to work with new render structure
        //TODO Set camera rotation, see commented code below.
        /*
        camera.getRot().set((new Quat4f(
                RenderQueue.getInstance().getCamXQuat(),
                RenderQueue.getInstance().getCamYQuat(),
                RenderQueue.getInstance().getCamZQuat(),
                RenderQueue.getInstance().getCamWQuat(
        );
        */
    }

    @Override
    public void respondToCollision(CollisionPair collisionPair) {
        System.out.println("collision");
        collisionPair.getFirstCollidable().handleCollision(collisionPair.getSecondCollidable());
        collisionPair.getSecondCollidable().handleCollision(collisionPair.getFirstCollidable());
    }

    public void addObservers(CardboardMenuObserver observer){
        observers.add(observer);
    }

    public void removeObservers(CardboardMenuObserver observer){
        observers.remove(observer);
    }

}
