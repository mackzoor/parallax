package com.tda367.parallax.view;

import com.badlogic.gdx.audio.Music;
import com.tda367.parallax.model.coreabstraction.AudioObserver;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.util.ResourceLoader;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Sound implements AudioObserver {

    private ResourceLoader resources;

    private List<ActiveMusicCombination> activeMusic = new ArrayList<ActiveMusicCombination>();

    public Sound(){
        this.resources = ResourceLoader.getInstance();
        AudioQueue.getInstance().addListener(this);
    }


    //AudioObserver methods

    //Methods for playing music or sound using its location (directory\fileName).
    //Methods use either 1f or "volume" variable for deciding volume level.
    @Override
    public void playSound(String soundLocation) {
        resources.getSound(soundLocation).play();
    }
    @Override
    public void playMusic(String musicLocation) {
        Music playing = resources.getMusic(musicLocation);
        playing.play();
        activeMusic.add(new ActiveMusicCombination(musicLocation, playing));
    }
    @Override
    public void playSound(String soundLocation, float volume) {
        resources.getSound(soundLocation).play(volume);
    }
    @Override
    public void playMusic(String musicLocation, float volume) {
        Music playing = resources.getMusic(musicLocation);
        playing.setVolume(volume);
        playing.setLooping(true);
        playing.play();
        activeMusic.add(new ActiveMusicCombination(musicLocation, playing));
    }

    //Methods interacting with active music. Either Stop, Pause, Un-pause or clear it.
    @Override
    public void stopActiveMusic(String fileNameAndDirectory){
        for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileNameAndDirectory)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().stop();
                this.activeMusic.remove(this.activeMusic.get(i));
            }
        }
    }
    @Override
    public void pauseActiveMusic(String fileNameAndDirectory){
           for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileNameAndDirectory)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().pause();
            }
        }
    }
    @Override
    public void unPauseActiveMusic(String fileNameAndDirectory){
           for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileNameAndDirectory)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().play();
            }
        }
    }
    @Override
    public void clearAllActiveMusic(){
        while(!(activeMusic.size() == 0)){
            activeMusic.get(0).getMusicPlaying().stop();
            activeMusic.remove(0);
        }
    }



    //Methods for interacting with the music using it's musicFile, generally only used within this class
    //TODO, remove these methods if we never find a use for them.

    public void stopActiveMusic(Music musicFile){
        for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getMusicPlaying().equals(musicFile)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().stop();
                this.activeMusic.remove(this.activeMusic.get(i));
            }
        }
    }

    public void pauseActiveMusic(Music musicFile){
        for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getMusicPlaying().equals(musicFile)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().pause();
            }
        }
    }

    public void unPauseActiveMusic(Music musicFile){
        for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getMusicPlaying().equals(musicFile)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlaying().play();
            }
        }
    }



    //Inner class for combination class, directory of sound/music and file of playing sound/music.

    private final class ActiveMusicCombination {
        @Getter private final String fileName;
        @Getter private final Music musicPlaying;

        private ActiveMusicCombination(String fileName, Music playing) {
            this.fileName = fileName;
            this.musicPlaying = playing;
        }
    }

}
