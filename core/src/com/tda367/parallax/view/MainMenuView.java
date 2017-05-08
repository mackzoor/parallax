package com.tda367.parallax.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tda367.parallax.model.menu.MainMenuModel;

/**
 * Created by Markus on 2017-05-08.
 */

public class MainMenuView {

    private MainMenuModel mainMenuModel;

    private SpriteBatch batch;
    private Texture background;
    private Texture unSelectedPlayButton;
    private Texture selectedPlayButton;
    private Texture unSelectedExitButton;
    private Texture selectedExitButton;

    public MainMenuView(MainMenuModel mainMenuModel) {

        this.mainMenuModel = mainMenuModel;

        batch = new SpriteBatch();
        background = new Texture("gridBg.jpg");
        unSelectedPlayButton = new Texture("playWhite.png");
        selectedPlayButton = new Texture("playButtonFocus.png");
        unSelectedExitButton = new Texture("exitWhite.png");
        selectedExitButton = new Texture("exitButtonFocus.png");
    }

    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        if (mainMenuModel.getStartButton().isMarked()) {
            batch.draw(
                    selectedPlayButton,
                    mainMenuModel.getStartButton().getPos().getX(),
                    mainMenuModel.getStartButton().getPos().getY(),
                    mainMenuModel.getStartButton().getWidth(),
                    mainMenuModel.getStartButton().getHeight()
            );
        } else {
            batch.draw(
                    unSelectedPlayButton,
                    mainMenuModel.getStartButton().getPos().getX(),
                    mainMenuModel.getStartButton().getPos().getY(),
                    mainMenuModel.getStartButton().getWidth(),
                    mainMenuModel.getStartButton().getHeight()
            );
        }

        if (mainMenuModel.getExitButton().isMarked()) {
            batch.draw(
                    selectedExitButton,
                    mainMenuModel.getExitButton().getPos().getX(),
                    mainMenuModel.getExitButton().getPos().getY(),
                    mainMenuModel.getExitButton().getWidth(),
                    mainMenuModel.getExitButton().getHeight()
            );
        } else {
            batch.draw(
                    unSelectedExitButton,
                    mainMenuModel.getExitButton().getPos().getX(),
                    mainMenuModel.getExitButton().getPos().getY(),
                    mainMenuModel.getExitButton().getWidth(),
                    mainMenuModel.getExitButton().getHeight()
            );
        }

        batch.end();

    }

    public void dispose() {
        batch.dispose();
    }
}
