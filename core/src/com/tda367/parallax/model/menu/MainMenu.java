package com.tda367.parallax.model.menu;

import com.tda367.parallax.model.core.Camera;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.collision.CollisionObserver;
import com.tda367.parallax.model.core.collision.CollisionResult;
import com.tda367.parallax.model.core.powerups.arsenal.Cannon;
import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.powerups.arsenal.PowerUpFactory;
import com.tda367.parallax.model.menu.button3d.ExitButton3D;
import com.tda367.parallax.model.menu.button3d.StartButton3D;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * The Model class for the Cardboard main menu.
 * Creates the objects in the world and handles logic.
 */

public class MainMenu implements CollisionObserver {

    private static final Vector3f EXIT_BUTTON_POS = new Vector3f(2, 10, 1);
    private static final Quat4f EXIT_BUTTON_ROT = new Quat4f();
    private static final Vector3f START_BUTTON_POS = new Vector3f(-2, 10, 1);
    private static final Quat4f START_BUTTON_ROT = new Quat4f();

    @Getter
    private final ExitButton3D exitButton;
    @Getter
    private final StartButton3D startButton;
    @Getter
    private final List<IPowerUp> powerUps;
    @Getter
    private final Camera camera;
    @Getter
    @Setter
    private Vector3f aimDirection;


    public MainMenu() {
        this.exitButton = new ExitButton3D(EXIT_BUTTON_POS, EXIT_BUTTON_ROT);
        this.startButton = new StartButton3D(START_BUTTON_POS, START_BUTTON_ROT);
        this.powerUps = new ArrayList<IPowerUp>();
        CollisionManager.getInstance().subscribeToCollisions(this);
        this.camera = new Camera();
        this.aimDirection = new Vector3f();
    }

    public void action() {
        final Cannon cannon = PowerUpFactory.createCannon();
        this.powerUps.add(cannon);
        cannon.activate(this.camera);
        this.aimDirection.normalize();
        this.aimDirection.scale(30);
        cannon.setVelocity(this.aimDirection);
    }

    public void update(int milliSinceLastUpdate) {
        for (final IPowerUp powerUp : this.powerUps) {
            powerUp.update(milliSinceLastUpdate);
        }
    }

    @Override
    public void respondToCollision(CollisionResult collisionResult) {
        collisionResult.getFirst().handleCollision(collisionResult.getSecond());
        collisionResult.getSecond().handleCollision(collisionResult.getFirst());
    }

}
