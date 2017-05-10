package com.tda367.parallax.model.cardboardmenu;

import com.tda367.parallax.model.coreabstraction.RenderQueue;
import com.tda367.parallax.model.coreabstraction.Updatable;
import com.tda367.parallax.model.parallaxcore.Camera;
import com.tda367.parallax.model.parallaxcore.Parallax;
import com.tda367.parallax.model.parallaxcore.Player;


/**
 * Created by Rasmus on 2017-05-08.
 */
public class MainMenu implements Updatable {


    Player player;
    Parallax parallax;
    CardboardMenu cbMenu;
    Camera camera;
    RenderQueue renderQueue;

    public MainMenu(Player player){
        this.player = player;
        cbMenu = new CardboardMenu();
        this.camera = new Camera();

    }

    public void startGame(){
        parallax = new Parallax(player);
    }

    public void exitGame(){
        //Ends the game
    }

    public void settingsPressed(){
        //enters settings menu
    }


    @Override
    public void update(int milliSinceLastUpdate) {
        camera.update(milliSinceLastUpdate);
        cbMenu.update(milliSinceLastUpdate);
    }

    public Camera getCamera(){
        return camera;
    }
}
