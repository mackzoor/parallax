package com.tda367.parallax.platform;

import com.tda367.parallax.parallaxCore.Parallax;
import com.tda367.parallax.parallaxCore.Player;
import com.tda367.parallax.platform.inputControllers.LibGdxGameController;
import com.tda367.parallax.platform.inputControllers.LibGdxKeyboardHandler;
import com.tda367.parallax.platform.inputControllers.LibGdxTouchHandler;
import com.tda367.parallax.platform.inputControllers.gamePadController.LibGdxGamePadHandler;

/**
 * Created by Markus on 2017-04-11.
 */
public class ParallaxLibGDXController implements LibGdxGameController{

    private Parallax parallax;
    private LibGdxGamePadHandler gamePadHandler;
    private LibGdxKeyboardHandler keyboardHandler;
    private LibGdxTouchHandler touchHandler;
    private Player player;
    private float panSpeed;

    ParallaxLibGDXController(Parallax parallax) {
        gamePadHandler = new LibGdxGamePadHandler(this);
        keyboardHandler = new LibGdxKeyboardHandler(this);
        touchHandler = new LibGdxTouchHandler(this);
        this.parallax = parallax;
        this.player = parallax.getPlayer();
        this.panSpeed = 5f;
    }

    public void drawTouchpad(){
        touchHandler.drawTouchpad();
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
