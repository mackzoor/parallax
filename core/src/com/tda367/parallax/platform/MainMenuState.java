package com.tda367.parallax.platform;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;


/**
    Class that renders and starts the Main Menu
 */
//TODO Create GUI for the menu
//TODO Get TrueTypeFontFactory

public class MainMenuState implements ApplicationListener {

    private Stage stage;
    private Skin playButtonSkin;
    private Skin exitButtonSkin;
    private Skin backgroundSkin;
    private SpriteBatch batch;
    GameStateManager gameStateManager;
    private Table table;
    ImageButton playButton;
    private Drawable playButtonDrawable;
    private Drawable backgroundDrawable;
    private Drawable exitButtonDrawable;
    ImageButton exitButton;


    float w = Gdx.graphics.getWidth(); //the width of the window
    float h = Gdx.graphics.getHeight();//the height of the window

    public MainMenuState(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;

        stage = new Stage(new FitViewport(w,h));
        batch = new SpriteBatch();
        table = new Table();
        playButtonSkin = new Skin();
        backgroundSkin = new Skin();
        exitButtonSkin = new Skin();

        backgroundSkin.add("background",new Texture("gridBg.jpg"));
        backgroundDrawable = backgroundSkin.getDrawable("background");

        playButtonSkin.add("playButtonBackground", new Texture("playWhite.png"));
        playButtonDrawable = playButtonSkin.getDrawable("playButtonBackground");

        exitButtonSkin.add("exitButtonBackground", new Texture("exitWhite.png"));
        exitButtonDrawable = exitButtonSkin.getDrawable("exitButtonBackground");

        playButton = new ImageButton(playButtonDrawable);
        exitButton = new ImageButton(exitButtonDrawable);

        table.setFillParent(true);
        table.add(playButton).size(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/3);
        table.row();
        table.add(exitButton).size(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/3);
        table.setBackground(backgroundDrawable);

        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameStateManager.setState(GameStateManager.State.PLAY);
            }
        });

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });


        Gdx.input.setInputProcessor(stage);

        stage.addActor(table);
        stage.addListener(new InputListener());


    }


    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {
        table.setFillParent(true);
        stage.setViewport(new FitViewport(width,height));
        stage.getViewport().update(width,height,true);
        table.getCell(playButton).size(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/3);
        table.getCell(exitButton).size(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/3);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //batch.begin();
        //batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //batch.end();
        stage.act();
        stage.draw();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


    @Override
    public void dispose() {
        batch.dispose();
        playButtonSkin.dispose();
        exitButtonSkin.dispose();
        backgroundSkin.dispose();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }
}