package com.tda367.parallax.parallaxCore;

import com.badlogic.gdx.audio.Music;
import com.tda367.parallax.platform.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xoxLU on 2017-04-12.
 */
public class SoundManager {

    private List<SoundListener> listeners = new ArrayList<SoundListener>();
    private List<String> soundQueue = new ArrayList<String>();
    private List<String> musicQueue = new ArrayList<String>();

    private static SoundManager instance;
    public static SoundManager getInstance(){
        if (instance == null) instance = new SoundManager();
        return instance;
    }

    public void addListener(SoundListener listener){
        listeners.add(listener);
        playQueuedSound();
    }

    private void playQueuedSound(){
        for (String sound: soundQueue){
            playSound(sound);
        }
        for (String music : musicQueue){
            playMusic(music);
        }
    }

    public void playSound(String sound){
        if(listeners.size() < 1){
            soundQueue.add(sound);
        }
        // Notify listeners to play sound.
        for (SoundListener Sl : listeners){
            Sl.playSound(sound);
        }
    }

    public void playMusic(String music){
        if(listeners.size() < 1){
            musicQueue.add(music);
        }
        // Notify listeners to play music.
        for (SoundListener Sl : listeners){
            Sl.playMusic(music);
        }
    }


}
