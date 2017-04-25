package com.tda367.parallax.platform;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.tda367.parallax.parallaxCore.Parallax;
import com.tda367.parallax.parallaxCore.Player;
import com.tda367.parallax.platform.inputControllers.LibGdxGameController;
import com.tda367.parallax.platform.inputControllers.LibGdxKeyboardHandler;
import com.tda367.parallax.platform.inputControllers.gamePadController.LibGdxGamePadHandler;

import javax.vecmath.Vector2f;

/**
 * Created by Markus on 2017-04-11.
 */
public class ParallaxLibGDXController implements IScreenControllerListener, LibGdxGameController{

    private Parallax parallax;
    private LibGdxGamePadHandler gamePadHandler;
    private LibGdxKeyboardHandler keyboardHandler;
    private Player player;
    OnScreenTouchpad onScreenTouchpad;
    private float panSpeed;

    ParallaxLibGDXController(Parallax parallax) {
        gamePadHandler = new LibGdxGamePadHandler(this);
        keyboardHandler = new LibGdxKeyboardHandler(this);
        this.parallax = parallax;
        this.player = parallax.getPlayer();
        this.panSpeed = 5f;
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            onScreenTouchpad = new OnScreenTouchpad();
            onScreenTouchpad.setListener(this);
        }
    }

    @Override
    public void onUpdate(float x, float y) {
        float panSpeed = 5;

        float xMove = panSpeed*x;
        float yMove = panSpeed*y;
        player.getSpaceCraft().setPanVelocity(xMove,yMove);
    }

    public void drawTouchpad(){
        onScreenTouchpad.drawTouchpad();
    }

    @Override
    public void actionButtonPressed() {
        player.getSpaceCraft().action();
    }

    @Override
    public void secondaryActionButtonPressed() {

    }

    @Override
    public void pauseButtonPressed() {

    }

    @Override
    public void upButtonPressed() {
        float xValue = player.getSpaceCraft().getPanVelocity().getX();
        float yValue = panSpeed;
        player.getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void upButtonUp() {
        float xValue = player.getSpaceCraft().getPanVelocity().getX();
        float yValue = 0;
        player.getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void rightButtonPressed() {
        float xValue = panSpeed;
        float yValue = player.getSpaceCraft().getPanVelocity().getY();
        player.getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void rightButtonUp() {
        float xValue = 0;
        float yValue = player.getSpaceCraft().getPanVelocity().getY();
        player.getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void downButtonPressed() {
        float xValue = player.getSpaceCraft().getPanVelocity().getX();
        float yValue = -panSpeed;
        player.getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void downButtonUp() {
        float xValue = player.getSpaceCraft().getPanVelocity().getX();
        float yValue = 0;
        player.getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void leftButtonPressed() {
        float xValue = -panSpeed;
        float yValue = player.getSpaceCraft().getPanVelocity().getY();
        player.getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void leftButtonUp() {
        float xValue = 0;
        float yValue = player.getSpaceCraft().getPanVelocity().getY();
        player.getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void xAxisJoystickMovement(float xValue) {
        xValue = xValue * panSpeed;
        float yValue = player.getSpaceCraft().getPanVelocity().getY();
        player.getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        yValue = yValue * panSpeed;
        float xValue = player.getSpaceCraft().getPanVelocity().getX();
        player.getSpaceCraft().setPanVelocity(xValue, yValue);
    }
}
