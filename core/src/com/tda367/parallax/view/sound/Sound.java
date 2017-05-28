package com.tda367.parallax.view.sound;

import com.badlogic.gdx.audio.Music;
import com.tda367.parallax.utilities.ResourceLoader;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that calls upon the resource loader to interact with the sound.
 */
public final class Sound {

    private static Sound instance;
    private final ResourceLoader resources;
    private final List<ActiveMusicCombination> activeMusic = new ArrayList<ActiveMusicCombination>();

    private Sound() {
        this.resources = ResourceLoader.getInstance();
    }

    public static Sound getInstance() {
        if (instance == null) {
            instance = new Sound();
        }
        return instance;
    }

    public void playSound(String soundLocation) {
        this.resources.getSound(soundLocation).play();
    }

    public void playMusic(String musicLocation) {
        final Music playing = this.resources.getMusic(musicLocation);
        playing.play();
        this.activeMusic.add(new ActiveMusicCombination(musicLocation, playing));
    }

    public void playSound(String soundLocation, float volume) {
        this.resources.getSound(soundLocation).play(volume);
    }

    public void playMusic(String musicLocation, float volume) {
        final Music playing = this.resources.getMusic(musicLocation);
        playing.setVolume(volume);
        playing.setLooping(true);
        playing.play();
        this.activeMusic.add(new ActiveMusicCombination(musicLocation, playing));
    }


    public void stopActiveMusic(String fileNameAndDirectory) {
        for (int i = 0; i < this.activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileNameAndDirectory)) {
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().stop();
                this.activeMusic.remove(this.activeMusic.get(i));
            }
        }
    }

    public void pauseActiveMusic(String fileNameAndDirectory) {
        for (int i = 0; i < this.activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileNameAndDirectory)) {
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().pause();
            }
        }
    }

    public void unPauseActiveMusic(String fileNameAndDirectory) {
        for (int i = 0; i < this.activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileNameAndDirectory)) {
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().play();
            }
        }
    }

    public void clearAllActiveMusic() {
        while (this.activeMusic.size() != 0) {
            this.activeMusic.get(0).getMusicPlaying().stop();
            this.activeMusic.remove(0);
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
