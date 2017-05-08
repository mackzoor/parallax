package com.tda367.parallax.parallaxCore.menuModels;


import com.tda367.parallax.model.parallaxcore.Parallax;
import com.tda367.parallax.model.parallaxcore.Player;

public class MainMenu {

    Player player;
    Parallax parallax;

    public MainMenu(Player player){
        this.player = player;
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


}
