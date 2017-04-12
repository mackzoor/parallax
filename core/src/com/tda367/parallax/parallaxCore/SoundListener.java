package com.tda367.parallax.parallaxCore;

/**
 * Created by xoxLU on 2017-04-12.
 */
public interface SoundListener {
    void playSound(String sound);
    void playMusic(String music);
    void playSound(String sound, float volume);
    void playMusic(String music, float volume);
}
