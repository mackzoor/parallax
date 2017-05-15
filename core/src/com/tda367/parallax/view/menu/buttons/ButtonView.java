package com.tda367.parallax.view.menu.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tda367.parallax.model.menu.buttons.Button;

/**
 * Created by Markus on 2017-05-09.
 */

public abstract class ButtonView {

    private Button button;
    Texture unselected;
    Texture selected;

    ButtonView(Button button) {
        this.button = button;
    }

    public void render(SpriteBatch batch){
        if (button.isMarked()) {
            batch.draw(
                    selected,
                    button.getPos().getX(),
                    button.getPos().getY(),
                    button.getWidth(),
                    button.getHeight()
            );
        } else {
            batch.draw(
                    unselected,
                    button.getPos().getX(),
                    button.getPos().getY(),
                    button.getWidth(),
                    button.getHeight()
            );
        }
    }
}
