package com.tda367.parallax.model.menu;

import com.tda367.parallax.model.menu.buttons.Button;
import com.tda367.parallax.model.menu.buttons.ExitButton;
import com.tda367.parallax.model.menu.buttons.StartButton;

import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Vector2f;

import lombok.Getter;

public class MainMenuModel {

    @Getter private StartButton startButton;
    @Getter private ExitButton exitButton;
    private List<Button> buttonSequence;

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
        Button firstButton = ((LinkedList<Button>)buttonSequence).getFirst();
        firstButton.unMarkButton();
        ((LinkedList<Button>)buttonSequence).removeFirst();
        ((LinkedList<Button>)buttonSequence).addLast(firstButton);
        ((LinkedList<Button>)buttonSequence).getFirst().markButton();
    }

    public void iterateDown() {
        Button lastButton = ((LinkedList<Button>) buttonSequence).getLast();
        lastButton.markButton();
        ((LinkedList<Button>) buttonSequence).removeLast();
        ((LinkedList<Button>) buttonSequence).getFirst().unMarkButton();
        ((LinkedList<Button>) buttonSequence).addFirst(lastButton);
    }

    public void resize(int screenWidth, int screenHeight) {
        startButton.setHeight(calculateButtonHeight(screenHeight));
        startButton.setWidth(calculateButtonWidth(screenWidth));
        startButton.setPos(calculateStartButtonPosition(screenWidth, screenHeight));
        exitButton.setHeight(calculateButtonHeight(screenHeight));
        exitButton.setWidth(calculateButtonWidth(screenWidth));
        exitButton.setPos(calculateExitButtonPosition(screenWidth, screenHeight));
    }


    //TODO, make sure that the float casting doesn't negatively impact the program.
    private Vector2f calculateStartButtonPosition(int screenWidth, int screenHeight) {
        return new Vector2f((float) screenWidth/3,(float) screenHeight * 3/5);
    }

    private Vector2f calculateExitButtonPosition(int screenWidth, int screenHeight) {
        return new Vector2f((float) screenWidth/3,(float) screenHeight / 5);
    }

    private int calculateButtonWidth(int screenWidth) {
        return screenWidth/3;
    }

    private int calculateButtonHeight(int screenHeight) {
        return screenHeight/5;
    }
}
