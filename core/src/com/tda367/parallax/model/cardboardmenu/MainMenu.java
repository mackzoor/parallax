package com.tda367.parallax.model.cardboardmenu;

import com.tda367.parallax.model.core.Camera;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.collision.CollisionObserver;
import com.tda367.parallax.model.core.collision.CollisionResult;
import com.tda367.parallax.model.core.powerups.arsenal.Cannon;
import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.powerups.arsenal.PowerUpFactory;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * The Model class for the Cardboard main menu. Creates the objects in the world and handles logic.
 */

public class MainMenu implements CollisionObserver {

    @Getter private ExitButton3D exitButton;
    @Getter private StartButton3D startButton;
    private List<CardboardMenuObserver> observers;
    @Getter private List<IPowerUp> powerUps;
    @Getter private Camera camera;
    @Getter @Setter private Vector3f aimDirection;

    private static final Vector3f EXIT_BUTTON_POS = new Vector3f(2, 10, 1);
    private static final Quat4f EXIT_BUTTON_ROT = new Quat4f();
    private static final Vector3f START_BUTTON_POS = new Vector3f(-2,10,1);
    private static final Quat4f START_BUTTON_ROT = new Quat4f();

    public MainMenu() {
        exitButton = new ExitButton3D(EXIT_BUTTON_POS, EXIT_BUTTON_ROT);
        startButton = new StartButton3D(START_BUTTON_POS, START_BUTTON_ROT);
        observers = new ArrayList<CardboardMenuObserver>();
        powerUps = new ArrayList<IPowerUp>();
        CollisionManager.getInstance().subscribeToCollisions(this);
        camera = new Camera();
        aimDirection = new Vector3f();
    }

    public void action(){
        Cannon cannon = PowerUpFactory.createCannon();
        powerUps.add(cannon);
        cannon.activate(camera);
        cannon.setEnemyTargetPosition(aimDirection);
    }

    public void update(int milliSinceLastUpdate) {
        for (IPowerUp powerUp : powerUps) {
            powerUp.update(milliSinceLastUpdate);
        }
    }

    @Override
    public void respondToCollision(CollisionResult collisionResult) {
        System.out.println("collision");
        collisionResult.getFirst().handleCollision(collisionResult.getSecond());
        collisionResult.getSecond().handleCollision(collisionResult.getFirst());
    }

}
