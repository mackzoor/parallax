package com.tda367.parallax.model.menu.buttons;

import javax.vecmath.Vector2f;

/**
 * Created by Markus on 2017-05-06.
 */

public abstract class Button {

    Vector2f pos;
    int width;
    int height;
    boolean isMarked;

    Button(Vector2f pos) {
        this.pos = pos;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Vector2f getPos() {
        return pos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isMarked() {
        return isMarked;
    }
}
