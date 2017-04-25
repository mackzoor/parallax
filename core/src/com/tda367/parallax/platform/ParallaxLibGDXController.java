package com.tda367.parallax.platform;

import com.tda367.parallax.parallaxCore.Parallax;
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
    private float panSpeed;

    ParallaxLibGDXController(Parallax parallax) {
        gamePadHandler = new LibGdxGamePadHandler();
        gamePadHandler.setListener(this);
        keyboardHandler = new LibGdxKeyboardHandler();
        keyboardHandler.setListener(this);
        touchHandler = new LibGdxTouchHandler();
        touchHandler.setListener(this);
        this.parallax = parallax;
        this.panSpeed = 5f;
    }

    void drawTouchpad(){
        touchHandler.drawTouchpad();
    }

    @Override
    public void actionButtonPressed() {
        parallax.getPlayer().getSpaceCraft().action();
    }

    @Override
    public void secondaryActionButtonPressed() {

    }

    @Override
    public void pauseButtonPressed() {

    }

    @Override
    public void upButtonPressed() {
        float xValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getX();
        float yValue = panSpeed;
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void upButtonUp() {
        float xValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getX();
        float yValue = 0;
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void rightButtonPressed() {
        float xValue = panSpeed;
        float yValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getY();
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void rightButtonUp() {
        float xValue = 0;
        float yValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getY();
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void downButtonPressed() {
        float xValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getX();
        float yValue = -panSpeed;
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void downButtonUp() {
        float xValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getX();
        float yValue = 0;
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void leftButtonPressed() {
        float xValue = -panSpeed;
        float yValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getY();
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void leftButtonUp() {
        float xValue = 0;
        float yValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getY();
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void xAxisJoystickMovement(float xValue) {
        xValue = xValue * panSpeed;
        float yValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getY();
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        yValue = yValue * panSpeed;
        float xValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getX();
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }
}
