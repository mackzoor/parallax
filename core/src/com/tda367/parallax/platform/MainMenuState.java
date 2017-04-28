package com.tda367.parallax.platform;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


/*
    Class that renders and starts the Main Menu
 */
//TODO Create GUI for the menu
//TODO Get TrueTypeFontFactory

public class MainMenuState implements ApplicationListener {

    Texture texture;
    Stage stage;
    Skin skin;
    SpriteBatch batch;
    TextButton.TextButtonStyle textButtonStyle;
    GameStateManager gameStateManager;
    Table table;
    TextButton playButton;
    BitmapFont font;
    Drawable drawable;
    TextButton exitButton;

    float w = Gdx.graphics.getWidth(); //the width of the window
    float h = Gdx.graphics.getHeight();//the height of the window

    public MainMenuState(final GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;

        texture = new Texture("mainMenuBackground.jpg");
        font = new BitmapFont();
        stage = new Stage();
        batch = new SpriteBatch();
        table = new Table();
        skin = new Skin();


        //skin.add("playButtonBackground", new Texture("badlogic.jpg"));
        //drawable = skin.getDrawable("playButtonBackground");


        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;

        playButton = new TextButton("Play", textButtonStyle);
        //playButton.setBackground(drawable);
        playButton.setSize(100, 100);
        playButton.getLabel().setFontScale(3, 3);

        exitButton = new TextButton("Exit",textButtonStyle);
        exitButton.setSize(100,100);
        exitButton.getLabel().setFontScale(3,3);


        table.setFillParent(true);
        table.bottom();
        table.add(playButton);
        table.add();
        table.add(exitButton);

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

    }



    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(texture, 0, 0, w, h);
        batch.end();
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
        texture.dispose();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

}
