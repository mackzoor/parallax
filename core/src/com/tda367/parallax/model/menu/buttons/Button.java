package com.tda367.parallax.model.menu.buttons;

import javax.vecmath.Vector2f;

import lombok.Getter;
import lombok.Setter;

public abstract class Button {

    @Getter @Setter private Vector2f pos;
    @Getter @Setter private int width;
    @Getter @Setter private int height;
    @Getter private boolean isMarked;

    public Button(Vector2f pos, int width, int height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
    }

    public void markButton() {
        isMarked = true;
    }

    public void unMarkButton() {
        isMarked = false;
    }

    public boolean inludesPoint(int x, int y) {
        boolean inX = (pos.getX() < x && x < (pos.getX() + width));
        boolean inY = (pos.getY() < y && y < (pos.getY() + width));
        return (inX && inY);
    }
}
