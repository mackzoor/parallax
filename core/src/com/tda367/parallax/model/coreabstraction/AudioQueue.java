package com.tda367.parallax.model.coreabstraction;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton bus class that holds the current audio that needs to be played.
 */

public class AudioQueue {

    private static AudioQueue instance;

    private final List<AudioObserver> listeners = new ArrayList<AudioObserver>();

    //Sound and music stored to be played when a listener is added.
    private final List<SoundCombiantion> soundQueue = new ArrayList<SoundCombiantion>();
    private final List<SoundCombiantion> musicQueue = new ArrayList<SoundCombiantion>();

    AudioQueue() {

    }

    public static synchronized AudioQueue getInstance() {
        if (instance == null) {
            instance = new AudioQueue();
        }
        return instance;
    }

    public void addAudioListener(AudioObserver listener) {
        this.listeners.add(listener);
        //Plays music that might have been called before the listener class had been added.
        playQueuedSoundAndMusic();
    }

    //Methods connected with the audioObserver class. Methods connected or calling upon listeners
    public void stopActiveMusic(String fileNameAndDirectory) {
        for (final AudioObserver soundLis : this.listeners) {
            soundLis.stopActiveMusic(fileNameAndDirectory);
        }
    }

    public void pauseActiveMusic(String fileNameAndDirectory) {
        for (final AudioObserver soundLis : this.listeners) {
            soundLis.pauseActiveMusic(fileNameAndDirectory);
        }
    }

    public void unPauseActiveMusic(String fileNameAndDirectory) {
        for (final AudioObserver soundLis : this.listeners) {
            soundLis.unPauseActiveMusic(fileNameAndDirectory);
        }
    }

    public void clearAllActiveMusic() {
        for (final AudioObserver soundLis : this.listeners) {
            soundLis.clearAllActiveMusic();
        }
    }

    private void playQueuedSoundAndMusic() {
        for (final SoundCombiantion soundQueued : this.soundQueue) {
            if (soundQueued.getVolume() == 1f) {
                playSound(soundQueued.getFileName());
            } else {
                playSound(soundQueued.getFileName(), soundQueued.getVolume());
            }
        }
        for (final SoundCombiantion musicQueued : this.musicQueue) {
            if (musicQueued.getVolume() == 1f) {
                playMusic(musicQueued.getFileName());
            } else {
                playMusic(musicQueued.getFileName(), musicQueued.getVolume());
            }
        }
    }

    private void playSound(String soundLocation) {
        //Methods to call upon AudioObservers to play the desired sound,
        //Using the right volume. Chained with methods below.

        if (this.listeners.size() < 1) {
            this.soundQueue.add(new SoundCombiantion(soundLocation, 1f));
        }

        for (final AudioObserver soundLis : this.listeners) {
            soundLis.playSound(soundLocation);
        }
    }

    public void playSound(String soundLocation, Float volume) {
        if (this.listeners.size() < 1) {
            this.soundQueue.add(new SoundCombiantion(soundLocation, volume));
        }

        for (final AudioObserver soundLis : this.listeners) {
            soundLis.playSound(soundLocation, volume);
        }
    }

    public void playSound(String soundName, String soundDirectory) {
        this.playSound(soundDirectory + "/" + soundName);
    }

    public void playSound(String sound, String directory, Float volume) {
        this.playSound(directory + "/" + sound, volume);
    }

    private void playMusic(String musicLocation) {
        //Methods to call upon AudioObservers to play the desired music, with the right volume.
        // Chained with methods below

        if (this.listeners.size() < 1) {
            this.musicQueue.add(new SoundCombiantion(musicLocation, 1f));
        }
        for (final AudioObserver soundLis : this.listeners) {
            soundLis.playMusic(musicLocation);
        }
    }

    public void playMusic(String music, Float volume) {
        if (this.listeners.size() < 1) {
            this.musicQueue.add(new SoundCombiantion(music, volume));
        }
        for (final AudioObserver soundLis : this.listeners) {
            soundLis.playMusic(music, volume);
        }
    }

    public void playMusic(String music, String directory) {
        this.playMusic(directory + "/" + music);
    }

    public void playMusic(String music, String directory, Float volume) {
        this.playMusic(directory + "/" + music, volume);
    }


    //Inner class for combination of sound and name
    private final class SoundCombiantion {
        @Getter
        private final String fileName;
        @Getter
        private final Float volume;

        SoundCombiantion(String fileName, Float volume) {
            this.fileName = fileName;
            this.volume = volume;
        }
    }

}
