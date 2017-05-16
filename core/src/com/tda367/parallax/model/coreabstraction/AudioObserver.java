package com.tda367.parallax.model.coreabstraction;

/**
 * An interface for classes handling music playing
 */

public interface AudioObserver {

    void playSound(String sound);
    void playMusic(String music);
    void playSound(String sound, float volume);
    void playMusic(String music, float volume);
    void stopActiveMusic(String fileNameAndDirectory);
    void pauseActiveMusic(String fileNameAndDirectory);
    void unPauseActiveMusic(String fileNameAndDirectory);
    void clearAllActiveMusic();
}
