package com.tda367.parallax.view.gameovermenu;

import com.tda367.parallax.model.gameover.GameOver;
import com.tda367.parallax.model.gameover.GameOverText;
import com.tda367.parallax.view.Renderer3D;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for creating and rendering all {@link GameOverTextView} from {@link GameOver}
 */


public class GameOverView {

    private GameOver model;

    private List<GameOverTextView> gameOverTextViews;

    public GameOverView(GameOver model){
        this.model = model;
        Renderer3D.getInstance().setCameraPosition(0, 0, 0);
        generateGameOverTextViews();
    }

    private void generateGameOverTextViews() {
        List<GameOverText> gameOverTexts = model.getGameOverTexts();
        gameOverTextViews = new ArrayList<GameOverTextView>();
        for (GameOverText gameOverText : gameOverTexts) {
            gameOverTextViews.add(new GameOverTextView(gameOverText));
        }
    }

    public void render(){
        for (GameOverTextView gameOverTextView : gameOverTextViews) {
            gameOverTextView.render();
        }
        Renderer3D.getInstance().renderFrame();
    }

    public void dispose(){

    }
}
