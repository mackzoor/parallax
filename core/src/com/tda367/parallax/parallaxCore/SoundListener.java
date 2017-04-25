package com.tda367.parallax.parallaxCore;

import com.badlogic.gdx.audio.Music;

/**
 * Created by xoxLU on 2017-04-12.
 */
public interface SoundListener {
    //TODO might want to remove method-heads using musicFile as parameter, they might never get used.

    void playSound(String sound);
    void playMusic(String music);
    void playSound(String sound, float volume);
    void playMusic(String music, float volume);
    void stopActiveMusic(Music musicFile);
    void pauseActiveMusic(Music musicFile);
    void unPauseActiveMusic(Music musicFile);
    void stopActiveMusic(String fileNameAndDirectory);
    void pauseActiveMusic(String fileNameAndDirectory);
    void unPauseActiveMusic(String fileNameAndDirectory);
}
