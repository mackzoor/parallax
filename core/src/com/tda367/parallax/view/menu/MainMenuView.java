package com.tda367.parallax.view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tda367.parallax.model.menu.MainMenuModel;
import com.tda367.parallax.view.menu.buttons.ButtonView;
import com.tda367.parallax.view.menu.buttons.ExitButtonView;
import com.tda367.parallax.view.menu.buttons.StartButtonView;

import java.util.ArrayList;
import java.util.List;

public class MainMenuView {

    private List<ButtonView> buttonViews;

    private SpriteBatch batch;
    private Texture background;

    public MainMenuView(MainMenuModel mainMenuModel) {

        this.batch = new SpriteBatch();
        this.buttonViews = new ArrayList<ButtonView>();
        this.buttonViews.add(new StartButtonView(mainMenuModel.getStartButton()));
        this.buttonViews.add(new ExitButtonView(mainMenuModel.getExitButton()));


        background = new Texture("gridBg.jpg");
    }

    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        for (ButtonView buttonview : buttonViews) {
            buttonview.render(batch);
        }

        batch.end();

    }

    public void dispose() {
        batch.dispose();
    }
}
