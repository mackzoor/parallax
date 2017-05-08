package com.tda367.parallax.model.coreabstraction;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton bus class that holds the current audio that need to be played.
 */

public class SoundManager {

    private final class SoundCombiantion {
        private final String fileName;
        private final Float volume;

        private SoundCombiantion(String fileName, Float volume) {
            this.fileName = fileName;
            this.volume = volume;
        }

        public String getFileName() {
            return fileName;
        }

        public Float getVolume() {
            return volume;
        }
    }

    private List<SoundListener> listeners = new ArrayList<SoundListener>();
    private List<SoundCombiantion> soundQueue = new ArrayList<SoundCombiantion>();
    private List<SoundCombiantion> musicQueue = new ArrayList<SoundCombiantion>();

    private static SoundManager instance;

    public static SoundManager getInstance() {
        if (instance == null) instance = new SoundManager();
        return instance;
    }

    public void addListener(SoundListener listener) {
        listeners.add(listener);
        playQueuedSoundAndMusic();
    }

    //TODO might want to remove methods using musicFile as parameter, they might never get used.

    //Stops a playing music file. Uses directory "slash" filename for correct input.
    public void stopActiveMusic(String fileNameAndDirectory){
        for (SoundListener Sl : listeners) {
            Sl.stopActiveMusic(fileNameAndDirectory);
        }
    }

    //Pauses a music file that is running. Uses filename "slash" the directory for correct input.
    public void pauseActiveMusic(String fileNameAndDirectory){
        for (SoundListener Sl : listeners) {
            Sl.pauseActiveMusic(fileNameAndDirectory);
        }
    }

    //Un pauses a music file that has previously been paused. Uses filename "slash" the directory for correct input.
    public void unPauseActiveMusic(String fileNameAndDirectory){
        for (SoundListener Sl : listeners) {
            Sl.unPauseActiveMusic(fileNameAndDirectory);
        }
    }

    public void clearAllActiveMusic(){
        for(SoundListener Sl: listeners){
            Sl.clearAllActiveMusic();
        }
    }

    private void playQueuedSoundAndMusic() {
        for (SoundCombiantion soundQueued : soundQueue) {
            if (soundQueued.getVolume() == 1f) {
                playSound(soundQueued.getFileName());
            } else {
                playSound(soundQueued.getFileName(), soundQueued.getVolume());
            }
        }
        for (SoundCombiantion musicQueued : musicQueue) {
            if (musicQueued.getVolume() == 1f) {
                playMusic(musicQueued.getFileName());
            } else {
                playMusic(musicQueued.getFileName(), musicQueued.getVolume());
            }
        }
    }

    private void playSound(String sound) {
        if (listeners.size() < 1) {
            soundQueue.add(new SoundCombiantion(sound,1f));
        }
        // Notify listeners to play sound.
        for (SoundListener Sl : listeners) {
            Sl.playSound(sound);
        }
    }
    public void playSound(String sound, Float volume) {
        if (listeners.size() < 1) {
            soundQueue.add(new SoundCombiantion(sound, volume));
        }
        // Notify listeners to play sound.
        for (SoundListener Sl : listeners) {
            Sl.playSound(sound, volume);
        }
    }
    public void playSound(String sound, String directory) {
        playSound(directory + "/" + sound);
    }
    public void playSound(String sound, String directory, Float volume) {
        playSound(directory + "/" + sound, volume);
    }

    private void playMusic(String music) {
        if (listeners.size() < 1) {
            musicQueue.add(new SoundCombiantion(music, 1f));
        }
        // Notify listeners to play music.
        for (SoundListener Sl : listeners) {
            Sl.playMusic(music);
        }
    }
    public void playMusic(String music, Float volume) {
        if (listeners.size() < 1) {
            musicQueue.add(new SoundCombiantion(music, volume));
        }
        // Notify listeners to play music.
        for (SoundListener Sl : listeners) {
            Sl.playMusic(music, volume);
        }
    }
    public void playMusic(String music, String directory) {
        playMusic(directory + "/" + music);
    }
    public void playMusic(String music, String directory, Float volume){
        playMusic(directory+"/"+music,volume);
    }
}
