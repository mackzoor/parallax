package com.tda367.parallax.view.menu.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.tda367.parallax.model.menu.buttons.Button;

/**
 * Created by Markus on 2017-05-09.
 */

public final class ExitButtonView extends ButtonView {
    public ExitButtonView(Button button) {
        super(button);
        unselected = new Texture("exitWhite.png");
        selected = new Texture("exitButtonFocus.png");
    }
}
