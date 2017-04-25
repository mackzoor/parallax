package com.tda367.parallax.platform;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.tda367.parallax.parallaxCore.Parallax;
import com.tda367.parallax.parallaxCore.Player;
import com.tda367.parallax.platform.inputControllers.LibGdxGameController;
import com.tda367.parallax.platform.inputControllers.LibGdxKeyboardHandler;
import com.tda367.parallax.platform.inputControllers.gamePadController.LibGdxGamePadHandler;

import javax.vecmath.Vector2f;

/**
 * Created by Markus on 2017-04-11.
 */
public class ParallaxLibGDXController implements InputProcessor,IScreenControllerListener, LibGdxGameController{

    private Parallax parallax;
    private LibGdxGamePadHandler gamePadHandler;
    private LibGdxKeyboardHandler keyboardHandler;
    private Player player;
    OnScreenTouchpad onScreenTouchpad;
    private float panSpeed;

    ParallaxLibGDXController(Parallax parallax) {
        Gdx.input.setInputProcessor(this);
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
    public boolean keyDown(int keycode) {
        /*spaceShipTurn(keycode);
        if(keycode == Input.Keys.SPACE){
            usePowerUp();
        }*/
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        /*spaceShipTurn(keycode);*/
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

    private void usePowerUp(){
        player.getSpaceCraft().action();
    }

    private void spaceShipTurn(int keycode){
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
    public void onUpdate(float x, float y) {
        float panSpeed = 5;

        float xMove = panSpeed*x;
        float yMove = panSpeed*y;
        setVelocity(xMove,yMove);
    }


    public void drawTouchpad(){
        onScreenTouchpad.drawTouchpad();
    }

    public void setVelocity(float x, float y){
        player.getSpaceCraft().setPanVelocity(
                new Vector2f(
                        x,
                        y
                )
        );
    }

    @Override
    public void actionButtonPressed() {
        usePowerUp();
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
        setVelocity(xValue, yValue);
    }

    @Override
    public void upButtonUp() {
        float xValue = player.getSpaceCraft().getPanVelocity().getX();
        float yValue = 0;
        setVelocity(xValue, yValue);
    }

    @Override
    public void rightButtonPressed() {
        float xValue = panSpeed;
        float yValue = player.getSpaceCraft().getPanVelocity().getY();
        setVelocity(xValue, yValue);
    }

    @Override
    public void rightButtonUp() {
        float xValue = 0;
        float yValue = player.getSpaceCraft().getPanVelocity().getY();
        setVelocity(xValue, yValue);
    }

    @Override
    public void downButtonPressed() {
        float xValue = player.getSpaceCraft().getPanVelocity().getX();
        float yValue = -panSpeed;
        setVelocity(xValue, yValue);
    }

    @Override
    public void downButtonUp() {
        float xValue = player.getSpaceCraft().getPanVelocity().getX();
        float yValue = 0;
        setVelocity(xValue, yValue);
    }

    @Override
    public void leftButtonPressed() {
        float xValue = -panSpeed;
        float yValue = player.getSpaceCraft().getPanVelocity().getY();
        setVelocity(xValue, yValue);
    }

    @Override
    public void leftButtonUp() {
        float xValue = 0;
        float yValue = player.getSpaceCraft().getPanVelocity().getY();
        setVelocity(xValue, yValue);
    }

    @Override
    public void xAxisJoystickMovement(float xValue) {
        xValue = xValue * panSpeed;
        float yValue = player.getSpaceCraft().getPanVelocity().getY();
        setVelocity(xValue, yValue);
    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        yValue = yValue * panSpeed;
        float xValue = player.getSpaceCraft().getPanVelocity().getX();
        setVelocity(xValue, yValue);
    }
}
