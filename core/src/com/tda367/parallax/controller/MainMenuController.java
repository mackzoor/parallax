package com.tda367.parallax.controller;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tda367.parallax.platform.GameStateManager;
import com.tda367.parallax.platform.MainMenuState;
import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;


/**
 * Created by Rasmus on 2017-05-02.
 */
public class MainMenuController implements InputControlsListener {

    private MainMenuState mainMenuView;
    private ImageButton currentButton;


    public MainMenuController(MainMenuState view, Device device) {
        mainMenuView = view;
        device.addInputDevices(this);
        currentButton = mainMenuView.getPlayButton();
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            Gdx.input.setInputProcessor(mainMenuView.getStage());
            mainMenuView.playButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    GameStateManager.getInstance().setState(GameStateManager.State.PLAY);
                }
            });
            mainMenuView.exitButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Gdx.app.exit();
                }
            });
        }
    }

    @Override
    public void actionButtonPressed() {
        if (currentButton == mainMenuView.getPlayButton()) {
            GameStateManager.getInstance().setState(GameStateManager.State.PLAY);
        } else
            Gdx.app.exit();
    }

    @Override
    public void secondaryActionButtonPressed() {

    }

    @Override
    public void pauseButtonPressed() {

    }

    @Override
    public void upButtonDown() {
        currentButton.setChecked(false);
        currentButton = mainMenuView.getPlayButton();
        currentButton.setChecked(true);
    }

    @Override
    public void upButtonUp() {

    }

    @Override
    public void rightButtonDown() {

    }

    @Override
    public void rightButtonUp() {

    }

    @Override
    public void downButtonDown() {
        currentButton.setChecked(false);
        currentButton = mainMenuView.getExitButton();
        currentButton.setChecked(true);
    }

    @Override
    public void downButtonUp() {

    }

    @Override
    public void leftButtonDown() {

    }

    @Override
    public void leftButtonUp() {

    }

    @Override
    public void xAxisJoystickMovement(float xValue) {

    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        if(yValue > 0){
            upButtonDown();
        } else if (yValue < 0) {
            downButtonDown();
        }
    }

    @Override
    public void onScreenClick(int xValue, int yValue) {

    }
}
