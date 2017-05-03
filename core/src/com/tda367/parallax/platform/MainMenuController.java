package com.tda367.parallax.platform;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tda367.parallax.platform.gameModeStates.GameModeState;
import com.tda367.parallax.platform.inputControllers.InputControlsListener;


/**
 * Created by Rasmus on 2017-05-02.
 */
public class MainMenuController implements InputControlsListener {

    private MainMenuState mainMenuView;
    private ImageButton currentButton;
    private ImageButton playButton;


    public MainMenuController(MainMenuState view, GameModeState gameModeState) {
        mainMenuView = view;
        gameModeState.addInputDevices(this);
        currentButton = mainMenuView.getPlayButton();
        if(Gdx.app.getType() == Application.ApplicationType.Android){
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
    public void upButtonPressed() {
        currentButton.setChecked(false);
        currentButton = mainMenuView.getPlayButton();
        currentButton.setChecked(true);
    }

    @Override
    public void upButtonUp() {

    }

    @Override
    public void rightButtonPressed() {

    }

    @Override
    public void rightButtonUp() {

    }

    @Override
    public void downButtonPressed() {
        currentButton.setChecked(false);
        currentButton = mainMenuView.getExitButton();
        currentButton.setChecked(true);
    }

    @Override
    public void downButtonUp() {

    }

    @Override
    public void leftButtonPressed() {

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
            upButtonPressed();
        } else if (yValue < 0) {
            downButtonPressed();
        }
    }

    @Override
    public void onScreenClick(int xValue, int yValue) {

    }
}
