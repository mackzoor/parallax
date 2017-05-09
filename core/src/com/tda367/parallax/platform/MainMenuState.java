package com.tda367.parallax.platform;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tda367.parallax.controller.MainMenuController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.devicestates.Device;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Class that renders and starts the Main Menu
 */

//TODO Create GUI for the menu
//TODO Get TrueTypeFontFactory

public class MainMenuState implements ApplicationListener {

    @Getter private Stage stage;
    @Getter public ImageButton playButton;
    @Getter public ImageButton exitButton;
    private ImageButton.ImageButtonStyle playButtonStyle;
    private ImageButton.ImageButtonStyle exitButtonStyle;
    private Skin playButtonSkin;
    private Skin playButtonFocusSkin;
    private Skin exitButtonSkin;
    private Skin exitButtonFocusSkin;
    private Skin backgroundSkin;
    private SpriteBatch batch;
    private Drawable playButtonDrawable;
    private Drawable playButtonFocusDrawable;
    private Drawable backgroundDrawable;
    private Drawable exitButtonDrawable;
    private Drawable exitButtonFocusDrawable;
    private Table table;
    private GameStateManager gameStateManager;
    List<ImageButton> buttons;
    private Device device;
    private MainMenuController mainMenuController;

    private float w = Gdx.graphics.getWidth(); //the width of the window
    private float h = Gdx.graphics.getHeight();//the height of the window

    MainMenuState(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        device = DeviceManager.getGameModeState(this);

        stage = new Stage(new FitViewport(w, h));
        batch = new SpriteBatch();
        table = new Table();
        playButtonSkin = new Skin();
        playButtonFocusSkin = new Skin();
        backgroundSkin = new Skin();
        exitButtonSkin = new Skin();
        exitButtonFocusSkin = new Skin();
        playButtonStyle = new ImageButton.ImageButtonStyle();
        exitButtonStyle = new ImageButton.ImageButtonStyle();


        backgroundSkin.add("background", new Texture("gridBg.jpg"));
        backgroundDrawable = backgroundSkin.getDrawable("background");


        playButtonSkin.add("playButtonBackground", new Texture("playWhite.png"));
        playButtonDrawable = playButtonSkin.getDrawable("playButtonBackground");
        playButtonFocusSkin.add("playButtonFocus", new Texture("playButtonFocus.png"));
        playButtonFocusDrawable = playButtonFocusSkin.getDrawable("playButtonFocus");

        playButtonStyle.imageUp = playButtonDrawable;
        playButtonStyle.imageChecked = playButtonFocusDrawable;

        exitButtonSkin.add("exitButtonBackground", new Texture("exitWhite.png"));
        exitButtonDrawable = exitButtonSkin.getDrawable("exitButtonBackground");
        exitButtonFocusSkin.add("exitButtonFocus", new Texture("exitButtonFocus.png"));
        exitButtonFocusDrawable = exitButtonFocusSkin.getDrawable("exitButtonFocus");

        exitButtonStyle.imageUp = exitButtonDrawable;
        exitButtonStyle.imageChecked = exitButtonFocusDrawable;

        playButton = new ImageButton(playButtonStyle);
        exitButton = new ImageButton(exitButtonStyle);

        table.setFillParent(true);
        table.add(playButton).size(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
        table.row();
        table.add(exitButton).size(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
        table.setBackground(backgroundDrawable);


        stage.addActor(table);
        System.out.println("menu");
        //mainMenuController = new MainMenuController(this, device);

    }


    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {
        table.setFillParent(true);
        stage.setViewport(new FitViewport(width, height));
        stage.getViewport().update(width, height, true);
        table.getCell(playButton).size(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
        table.getCell(exitButton).size(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //batch.begin();
        //batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //batch.end();
        stage.act();
        stage.draw();
        device.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        Controllers.clearListeners();
        batch.dispose();
        playButtonSkin.dispose();
        exitButtonSkin.dispose();
        backgroundSkin.dispose();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}