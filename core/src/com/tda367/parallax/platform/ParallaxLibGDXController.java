package com.tda367.parallax.platform;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.tda367.parallax.parallaxCore.Parallax;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.tda367.parallax.parallaxCore.Player;

import javax.vecmath.Vector2f;

/**
 * Created by Markus on 2017-04-11.
 */
public class ParallaxLibGDXController implements InputProcessor,IScreenControllerListener, ControllerListener {

    private Parallax parallax;
    private Controller controller;
    private Player player;
    OnScreenTouchpad onScreenTouchpad;

    ParallaxLibGDXController(Parallax parallax) {
        Gdx.input.setInputProcessor(this);
        this.parallax = parallax;
        this.player = parallax.getPlayer();

        Controllers.addListener(this);
        for (Controller controller : Controllers.getControllers()) {
            System.out.println(controller.getName());
            this.controller = controller;
            controller.addListener(this);
        }

        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            onScreenTouchpad = new OnScreenTouchpad();
            onScreenTouchpad.setListener(this);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        float panSpeed = 5;
        spaceShipTurn(keycode, panSpeed);
        usePowerUp(keycode);
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

    private void usePowerUp(int keycode){
        if(keycode == Input.Keys.SPACE){
            player.getSpaceCraft().action();
        }
    }

    private void spaceShipTurn(int keycode, float panSpeed){
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP){
            player.getSpaceCraft().addPanVelocity(new Vector2f(0,panSpeed));
        } else if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT){
            player.getSpaceCraft().addPanVelocity(new Vector2f(-panSpeed,0));
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN){
            player.getSpaceCraft().addPanVelocity(new Vector2f(0,-panSpeed));
        } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT){
            player.getSpaceCraft().addPanVelocity(new Vector2f(panSpeed,0));
        }
    }

    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {

        // Xbox 360 controller axis
        // 0 == Left yAxis +DOWN
        // 1 == Left xAxis +RIGHT
        // 2 == Right yAxis +DOWN
        // 3 == Right xAxis +RIGHT

        System.out.println(axisCode);

        float panSpeed = 10;

        float xMove = panSpeed*controller.getAxis(0);
        float yMove = panSpeed*controller.getAxis(1)*-1;

        player.getSpaceCraft().setPanVelocity(
                new Vector2f(
                        xMove,
                        yMove
                )
        );


        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }

    @Override
    public void onUpdate(float x, float y) {
        joystickPosition(x,y);
    }


    public void drawTouchpad(){
        onScreenTouchpad.drawTouchpad();
    }

    public void joystickPosition(float x, float y){
        float panSpeed = 10;

        float xMove = panSpeed*x;
        float yMove = panSpeed*y;

        player.getSpaceCraft().setPanVelocity(
                new Vector2f(
                        xMove,
                        yMove
                )
        );
    }
}





