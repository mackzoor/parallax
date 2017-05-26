package com.tda367.parallax.view;

import com.badlogic.gdx.audio.Music;
import com.tda367.parallax.model.coreabstraction.AudioObserver;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.utilities.ResourceLoader;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/*
*   Class that calls upon the resource loader to interact with the sound.
 */


public class Sound implements AudioObserver {

    private final ResourceLoader resources;

    private final List<ActiveMusicCombination> activeMusic = new ArrayList<ActiveMusicCombination>();

    public Sound() {
        this.resources = ResourceLoader.getInstance();
        AudioQueue.getInstance().addAudioListener(this);
    }


    //AudioObserver methods

    //Methods for playing music or sound using its data location (directory\fileName).
    @Override
    public void playSound(String soundLocation) {
        this.resources.getSound(soundLocation).play();
    }

    @Override
    public void playMusic(String musicLocation) {
        final Music playing = this.resources.getMusic(musicLocation);
        playing.play();
        this.activeMusic.add(new ActiveMusicCombination(musicLocation, playing));
    }

    @Override
    public void playSound(String soundLocation, float volume) {
        this.resources.getSound(soundLocation).play(volume);
    }

    @Override
    public void playMusic(String musicLocation, float volume) {
        final Music playing = this.resources.getMusic(musicLocation);
        playing.setVolume(volume);
        playing.setLooping(true);
        playing.play();
        this.activeMusic.add(new ActiveMusicCombination(musicLocation, playing));
    }

    //Methods interacting with active music. Either Stop, Pause, Un-pause or clear it.
    @Override
    public void stopActiveMusic(String fileNameAndDirectory) {
        for (int i = 0; i < this.activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileNameAndDirectory)) {
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().stop();
                this.activeMusic.remove(this.activeMusic.get(i));
            }
        }
    }

    @Override
    public void pauseActiveMusic(String fileNameAndDirectory) {
        for (int i = 0; i < this.activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileNameAndDirectory)) {
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().pause();
            }
        }
    }

    @Override
    public void unPauseActiveMusic(String fileNameAndDirectory) {
        for (int i = 0; i < this.activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileNameAndDirectory)) {
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().play();
            }
        }
    }

    @Override
    public void clearAllActiveMusic() {
        while (!(this.activeMusic.size() == 0)) {
            this.activeMusic.get(0).getMusicPlaying().stop();
            this.activeMusic.remove(0);
        }
    }


    //Methods for interacting with the music using it's musicFile, generally only used within this class
    //TODO, remove these methods if we never find a use for them.
    public void stopActiveMusic(Music musicFile) {
        for (int i = 0; i < this.activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getMusicPlaying().equals(musicFile)) {
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().stop();
                this.activeMusic.remove(this.activeMusic.get(i));
            }
        }
    }

    public void pauseActiveMusic(Music musicFile) {
        for (int i = 0; i < this.activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getMusicPlaying().equals(musicFile)) {
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().pause();
            }
        }
    }

    public void unPauseActiveMusic(Music musicFile) {
        for (int i = 0; i < this.activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getMusicPlaying().equals(musicFile)) {
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().play();
            }
        }
    }


    //Inner class for combination class, directory of sound/music and file of playing sound/music.
    private final class ActiveMusicCombination {
        @Getter
        private final String fileName;
        @Getter
        private final Music musicPlaying;

        ActiveMusicCombination(String fileName, Music playing) {
            this.fileName = fileName;
            this.musicPlaying = playing;
        }
    }

}
