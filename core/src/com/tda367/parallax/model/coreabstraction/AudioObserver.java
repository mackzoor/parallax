package com.tda367.parallax.model.coreabstraction;

public interface AudioObserver {
    //TODO might want to remove method-heads using musicFile as parameter, they might never get used.

    void playSound(String sound);
    void playMusic(String music);
    void playSound(String sound, float volume);
    void playMusic(String music, float volume);
    void stopActiveMusic(String fileNameAndDirectory);
    void pauseActiveMusic(String fileNameAndDirectory);
    void unPauseActiveMusic(String fileNameAndDirectory);
    void clearAllActiveMusic();
}