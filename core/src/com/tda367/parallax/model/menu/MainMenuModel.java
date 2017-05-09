package com.tda367.parallax.model.menu;

import com.tda367.parallax.model.menu.buttons.Button;
import com.tda367.parallax.model.menu.buttons.ExitButton;
import com.tda367.parallax.model.menu.buttons.StartButton;

import java.util.LinkedList;

import javax.vecmath.Vector2f;

public class MainMenuModel {

    private StartButton startButton;
    private ExitButton exitButton;
    private LinkedList<Button> buttonSequence;

    public MainMenuModel(int screenWidth, int screenHeight) {
        this.startButton = new StartButton(
                calculateStartButtonPosition(screenWidth, screenHeight),
                calculateButtonWidth(screenWidth),
                calculateButtonHeight(screenHeight)
        );
        this.exitButton = new ExitButton(
                calculateExitButtonPosition(screenWidth, screenHeight),
                calculateButtonWidth(screenWidth),
                calculateButtonHeight(screenHeight)
        );
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

    public void resize(int screenWidth, int screenHeight) {
        startButton.setHeight(calculateButtonHeight(screenHeight));
        startButton.setWidth(calculateButtonWidth(screenWidth));
        startButton.setPos(calculateStartButtonPosition(screenWidth, screenHeight));
        exitButton.setHeight(calculateButtonHeight(screenHeight));
        exitButton.setWidth(calculateButtonWidth(screenWidth));
        exitButton.setPos(calculateExitButtonPosition(screenWidth, screenHeight));
    }

    private Vector2f calculateStartButtonPosition(int screenWidth, int screenHeight) {
        return new Vector2f(screenWidth/3,screenHeight * 3/5);
    }

    private Vector2f calculateExitButtonPosition(int screenWidth, int screenHeight) {
        return new Vector2f(screenWidth/3,screenHeight / 5);
    }

    private int calculateButtonWidth(int screenWidth) {
        return screenWidth/3;
    }

    private int calculateButtonHeight(int screenHeight) {
        return screenHeight/5;
    }
}
