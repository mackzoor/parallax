package com.tda367.parallax.model.menu.mainmenu;

import com.tda367.parallax.model.menu.buttons.Button;
import com.tda367.parallax.model.menu.buttons.ExitButton;
import com.tda367.parallax.model.menu.buttons.StartButton;

import java.util.LinkedList;

import javax.vecmath.Vector2f;

/**
 * Created by Markus on 2017-05-08.
 */

public class MainMenuModel {

    private StartButton startButton;
    private ExitButton exitButton;
    private LinkedList<Button> buttonSequence;

    public MainMenuModel(int screenWidth, int screenHeight) {
        this.startButton = new StartButton(new Vector2f(screenWidth/2,screenHeight/2)); //Should have better values
        this.exitButton = new ExitButton(new Vector2f(screenWidth/2,screenHeight/2)); //Should have better values
        buttonSequence = new LinkedList<Button>();
        buttonSequence.add(startButton);
        buttonSequence.add(exitButton);
    }

    public Button buttonPressed(int x, int y) {
        if (startButton.inludesPoint(x, y)) {
            return startButton;
        } else if (exitButton.inludesPoint(x, y)) {
            return exitButton;
        }
        return null;
    }

    public void iterateUp() {
        Button firstButton = buttonSequence.getFirst();
        firstButton.unMarkButton();
        buttonSequence.removeFirst();
        buttonSequence.addLast(firstButton);
        buttonSequence.getFirst().markButton();
    }

    public void iterateDown() {
        Button lastButton = buttonSequence.getLast();
        lastButton.markButton();
        buttonSequence.removeLast();
        buttonSequence.getFirst().unMarkButton();
        buttonSequence.addFirst(lastButton);
    }

    public StartButton getStartButton() {
        return startButton;
    }

    public ExitButton getExitButton() {
        return exitButton;
    }
}
