package com.tda367.parallax.model.cardboardmenu;

/*
    An interface for the CardboardMenuScreen which observes if something has happened
    in the main menu logic.
 */
public interface CardboardMenuObserver {
    void cardboardStartButtonAction();
    void cardboardExitButtonAction();
}
