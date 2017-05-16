package com.tda367.parallax.model.coreabstraction;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

/**
 * A singleton bus class that holds the current audio that needs to be played.
 */

public class AudioQueue {

    //List of listeners, classes that handle sound resources.
    private List<AudioObserver> listeners = new ArrayList<AudioObserver>();

    //Sound and music stored to be played when a listener is added.
    private List<SoundCombiantion> soundQueue = new ArrayList<SoundCombiantion>();
    private List<SoundCombiantion> musicQueue = new ArrayList<SoundCombiantion>();

    //Single instance of the AudioQueue class
    private static AudioQueue instance;

    //Constructor(Singleton) of audioQueue class
    public static AudioQueue getInstance() {
        if (instance == null) {
            instance = new AudioQueue();
        }
        return instance;
    }

    public void addListener(AudioObserver listener){
        //Adding a class to become a listener for sound and music. Adding audioObservers to listener list.

        listeners.add(listener);
        playQueuedSoundAndMusic();
    }



    //Methods connected with the audioObserver class. Methods connected or calling upon listeners

    public void stopActiveMusic(String fileNameAndDirectory){
        //Calls upon listeners to play a music file. Uses directory "slash" filename for correct input.

        for (AudioObserver sl : listeners) {
            sl.stopActiveMusic(fileNameAndDirectory);
        }
    }

    public void pauseActiveMusic(String fileNameAndDirectory){
        //Calls upon listeners to pause a music file that is running.
        //Uses filename "slash" the directory for correct input.

        for (AudioObserver sl : listeners) {
            sl.pauseActiveMusic(fileNameAndDirectory);
        }
    }

    public void unPauseActiveMusic(String fileNameAndDirectory){
        //Calls upon listeners to un pause a music file that has previously been paused.
        //Uses filename "slash" the directory for correct input.
        for (AudioObserver sl : listeners) {
            sl.unPauseActiveMusic(fileNameAndDirectory);
        }
    }

    public void clearAllActiveMusic(){
        //Calls upon the listeners to remove all the activeMusic music. (music loaded and stored, both paused and playing)

        for(AudioObserver sl: listeners){
            sl.clearAllActiveMusic();
        }
    }

    private void playQueuedSoundAndMusic() {
        //Method that is called when there is listeners to handle the song requests.
        //Calls the playSound and playMusic methods with the stored information from Queues

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

    //Methods to call upon AudioObservers to play the desired sound, with the right volume.
    //Method chaining for use of different settings (with or without volume, different sound calls)
    private void playSound(String soundLocation) {
        //Checks if there are any listeners, if there is not, add the sound to a queue with stored sounds.
        if (listeners.size() < 1) {
            soundQueue.add(new SoundCombiantion(soundLocation,1f));
        }
        // Notify listeners to play sound.
        for (AudioObserver sl : listeners) {
            sl.playSound(soundLocation);
        }
    }
    public void playSound(String soundLocation, Float volume) {
        //Checks if there are any listeners, if there is not, add the sound to a queue with stored sounds.
        if (listeners.size() < 1) {
            soundQueue.add(new SoundCombiantion(soundLocation, volume));
        }
        // Notify listeners to play sound with the desired volume.
        for (AudioObserver sl : listeners) {
            sl.playSound(soundLocation, volume);
        }
    }
    public void playSound(String soundName, String soundDirectory) {
        //Adds the directory and the name together and calls upon head of method cain.
        playSound(soundDirectory + "/" + soundName);
    }
    public void playSound(String sound, String directory, Float volume) {
        //Adds the directory and the name together and calls upon head of method cain, with a volume variable.
        playSound(directory + "/" + sound, volume);
    }

    //Methods to call upon AudioObservers to play the desired music, with the right volume.
    //Method chaining for use of different settings (with or without volume, different sound calls)
    private void playMusic(String musicLocation) {
        //Checks if there are any listeners, if there is not, add the music to a queue with stored songs.
        if (listeners.size() < 1) {
            musicQueue.add(new SoundCombiantion(musicLocation, 1f));
        }
        // Notify listeners to play music.
        for (AudioObserver sl : listeners) {
            sl.playMusic(musicLocation);
        }
    }
    public void playMusic(String music, Float volume) {
        //Checks if there are any listeners, if there is not, add the music to a queue with stored songs.
        if (listeners.size() < 1) {
            musicQueue.add(new SoundCombiantion(music, volume));
        }
        // Notify listeners to play music with the desired volume.
        for (AudioObserver sl : listeners) {
            sl.playMusic(music, volume);
        }
    }
    public void playMusic(String music, String directory) {
        //Adds the directory and the name together and calls upon head of method cain.
        playMusic(directory + "/" + music);
    }
    public void playMusic(String music, String directory, Float volume){
        //Adds the directory and the name together and calls upon head of method cain, with the correct volume
        playMusic(directory+"/"+music,volume);
    }




    //Inner class for combination of sound and name
    private final class SoundCombiantion {
        @Getter private final String fileName;
        @Getter private final Float volume;

        private SoundCombiantion(String fileName, Float volume) {
            this.fileName = fileName;
            this.volume = volume;
        }
    }

}
