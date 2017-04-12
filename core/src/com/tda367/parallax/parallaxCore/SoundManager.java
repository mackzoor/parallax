package com.tda367.parallax.parallaxCore;

import com.badlogic.gdx.audio.Music;
import com.tda367.parallax.platform.Sound;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xoxLU on 2017-04-12.
 */
public class SoundManager {

    private List<SoundListener> listeners = new ArrayList<SoundListener>();
    private List<Pair<String,Float>> soundQueue = new ArrayList<Pair<String, Float>>();
    private List<Pair<String,Float>> musicQueue = new ArrayList<Pair<String, Float>>();

    private static SoundManager instance;
    public static SoundManager getInstance(){
        if (instance == null) instance = new SoundManager();
        return instance;
    }

    public void addListener(SoundListener listener){
        listeners.add(listener);
        playQueuedSoundAndMusic();
    }

    private void playQueuedSoundAndMusic(){
        for (int i = 0; i < soundQueue.size(); i++){
            if(soundQueue.get(i).getValue() == 1f){
                playMusic(soundQueue.get(i).getKey());
            } else{
                playMusic(soundQueue.get(i).getKey(), soundQueue.get(i).getValue());
            }
        }
        for (int i = 0; i < musicQueue.size(); i++){
            if(musicQueue.get(i).getValue() == 1f){
                playMusic(musicQueue.get(i).getKey());
            } else{
                playMusic(musicQueue.get(i).getKey(), musicQueue.get(i).getValue());
            }
        }
    }

    public void playSound(String sound){
        if(listeners.size() < 1){
            soundQueue.add(new Pair<String, Float>(sound, 1f));
        }
        // Notify listeners to play sound.
        for (SoundListener Sl : listeners){
            Sl.playSound(sound);
        }
    }

    public void playSound(String sound, float volume){
        if(listeners.size() < 1){
            soundQueue.add(new Pair<String, Float>(sound, volume));
        }
        // Notify listeners to play sound.
        for (SoundListener Sl : listeners){
            Sl.playSound(sound, volume);
        }
    }

    public void playMusic(String music){
        if(listeners.size() < 1){
            musicQueue.add(new Pair<String, Float>(music, 1f));
        }
        // Notify listeners to play music.
        for (SoundListener Sl : listeners){
            Sl.playMusic(music);
        }
    }

    public void playMusic(String music, float volume){
        if(listeners.size() < 1){
            musicQueue.add(new Pair(music,volume));
        }
        // Notify listeners to play music.
        for (SoundListener Sl : listeners){
            Sl.playMusic(music, volume);
        }
    }

}
