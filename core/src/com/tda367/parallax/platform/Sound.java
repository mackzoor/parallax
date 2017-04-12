package com.tda367.parallax.platform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.tda367.parallax.parallaxCore.SoundListener;
import com.tda367.parallax.parallaxCore.SoundManager;

/**
 * Created by xoxLU on 2017-04-11.
 */

public class Sound implements SoundListener {

    ResourceHandler resources;

    public Sound(){
        this.resources = ResourceHandler.getInstance();
        SoundManager.getInstance().addListener(this);
    }

    @Override
    public void playSound(String sound) {
        resources.getSound(sound).play();
    }

    @Override
    public void playMusic(String music) {
        resources.getMusic(music).play();
    }

    @Override
    public void playSound(String sound, float volume) {
        resources.getSound(sound).play(volume);
    }

    @Override
    public void playMusic(String music, float volume) {
        Music playing = resources.getMusic(music);
        playing.play();
        playing.setVolume(volume);
        playing.setLooping(true);
    }
}
