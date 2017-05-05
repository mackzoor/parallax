package com.tda367.parallax.model.CoreAbstraction;


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


    private void playQueuedSoundAndMusic() {
        for (int i = 0; i < soundQueue.size(); i++) {
            if (soundQueue.get(i).getVolume() == 1f) {
                playSound(soundQueue.get(i).getFileName());
            } else {
                playSound(soundQueue.get(i).getFileName(), soundQueue.get(i).getVolume());
            }
        }
        for (int i = 0; i < musicQueue.size(); i++) {
            if (musicQueue.get(i).getVolume() == 1f) {
                playMusic(musicQueue.get(i).getFileName());
            } else {
                playMusic(musicQueue.get(i).getFileName(), musicQueue.get(i).getVolume());
            }
        }
    }

    private void playSound(String sound) {
        if (listeners.size() < 1) {
            soundQueue.add(new SoundCombiantion(sound, new Float(1f)));
        }
        // Notify listeners to play sound.
        for (SoundListener Sl : listeners) {
            Sl.playSound(sound);
        }
    }

    public void playSound(String sound, String directory) {
        playSound(directory + "/" + sound);
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

    public void playSound(String sound, String directory, Float volume) {
        playSound(directory + "/" + sound, volume);
    }

    private void playMusic(String music) {
        if (listeners.size() < 1) {
            musicQueue.add(new SoundCombiantion(music, new Float(1f)));
        }
        // Notify listeners to play music.
        for (SoundListener Sl : listeners) {
            Sl.playMusic(music);
        }
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

    public void playMusic(String music, String directory) {
        playMusic(directory + "/" + music);
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

    public void playMusic(String music, String directory, Float volume){
        playMusic(directory+"/"+music,volume);
    }

    public void clearAllActiveMusic(){
        for(SoundListener Sl: listeners){
            Sl.clearAllActiveMusic();
        }
    }
}
