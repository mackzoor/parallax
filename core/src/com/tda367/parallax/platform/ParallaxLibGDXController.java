package com.tda367.parallax.platform;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.tda367.parallax.parallaxCore.Player;

import javax.vecmath.Vector2f;

/**
 * Created by Markus on 2017-04-11.
 */
public class ParallaxLibGDXController implements InputProcessor, IScreenControllerListener {

    Player player;
    OnScreenController onScreenController;


    ParallaxLibGDXController(Player player) {
        Gdx.input.setInputProcessor(this);
        this.player = player;
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            onScreenController = new OnScreenController();
            onScreenController.setListener(this);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        float panSpeed = 5;
        spaceShipTurn(keycode, panSpeed);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        float panSpeed = -5;
        spaceShipTurn(keycode, panSpeed);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private void spaceShipTurn(int keycode, float panSpeed) {
        System.out.println("desktop");
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            player.getSpaceCraft().addPanVelocity(new Vector2f(0, panSpeed));
        } else if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            player.getSpaceCraft().addPanVelocity(new Vector2f(-panSpeed, 0));
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            player.getSpaceCraft().addPanVelocity(new Vector2f(0, -panSpeed));
        } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            player.getSpaceCraft().addPanVelocity(new Vector2f(panSpeed, 0));
        }
    }

    public void touchTurn(float panSpeed, int direction) {
        System.out.println("touchturn");
        if (1 == direction) {
            System.out.println("Up");
            player.getSpaceCraft().addPanVelocity(new Vector2f(0, panSpeed));
        } else if (2 == direction) {
            System.out.println("Left");
            player.getSpaceCraft().addPanVelocity(new Vector2f(-panSpeed, 0));
        } else if (3 == direction) {
            System.out.println("down");
            player.getSpaceCraft().addPanVelocity(new Vector2f(0, -panSpeed));
        } else if (4 == direction) {
            System.out.println("right");
            player.getSpaceCraft().addPanVelocity(new Vector2f(panSpeed, 0));
        }

    }

    @Override
    public void onUpdate() {
        if (onScreenController.isUpPressed()) {
            System.out.println("up");
            touchTurn(2, 1);
        } else if (onScreenController.isLeftPressed()) {
            touchTurn(2, 2);
        } else if (onScreenController.isDownPressed()) {
            touchTurn(2, 3);
        } else if (onScreenController.isRightPressed()) {
            touchTurn(2, 4);
        }
    }

    public void draw() {
        onScreenController.draw();
    }
}





