package com.tda367.parallax.controller.gamestates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.controller.devicestates.DeviceManager;

/**
 * Created by Markus on 2017-05-04.
 */

public class MainMenuScreen implements Screen {

    Game game;
    Device device;

    //Almost everything is copied straight of from MainMenuState. Should be split up in the future
    private ImageButton.ImageButtonStyle playButtonStyle;
    private ImageButton.ImageButtonStyle exitButtonStyle;
    private Stage stage;
    private Skin playButtonSkin;
    private Skin playButtonFocusSkin;
    private Skin exitButtonSkin;
    private Skin exitButtonFocusSkin;
    private Skin backgroundSkin;
    private SpriteBatch batch;
    private Table table;
    public ImageButton playButton;
    private Drawable playButtonDrawable;
    private Drawable playButtonFocusDrawable;
    private Drawable backgroundDrawable;
    private Drawable exitButtonDrawable;
    private Drawable exitButtonFocusDrawable;
    public ImageButton exitButton;

    public MainMenuScreen(final Game game) {
        this.game = game;
        this.device = DeviceManager.getDevice();

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
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

        Gdx.input.setInputProcessor(stage);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameStateManager.setGameScreen(game);
            }
        });
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        table.setFillParent(true);
        stage.setViewport(new FitViewport(width, height));
        stage.getViewport().update(width, height, true);
        table.getCell(playButton).size(width / 3, height / 3);
        table.getCell(exitButton).size(width / 3, height / 3);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
