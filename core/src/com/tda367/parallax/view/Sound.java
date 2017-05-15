package com.tda367.parallax.view;

import com.badlogic.gdx.audio.Music;
import com.tda367.parallax.model.coreabstraction.AudioObserver;
import com.tda367.parallax.model.coreabstraction.AudioQueue;

import java.util.ArrayList;
import java.util.List;

public class Sound implements AudioObserver {

    private com.tda367.parallax.view.util.ResourceLoader resources;

    private List<ActiveMusicCombination> activeMusic = new ArrayList<ActiveMusicCombination>();

    public Sound(){
        this.resources = com.tda367.parallax.view.util.ResourceLoader.getInstance();
        AudioQueue.getInstance().addListener(this);
    }

    @Override
    public void playSound(String sound) {
        resources.getSound(sound).play();
    }

    @Override
    public void playMusic(String music) {
        Music playing = resources.getMusic(music);
        playing.play();
        activeMusic.add(new ActiveMusicCombination(music, playing));
    }

    @Override
    public void playSound(String sound, float volume) {
        resources.getSound(sound).play(volume);
    }

    @Override
    public void playMusic(String music, float volume) {
        Music playing = resources.getMusic(music);
        playing.setVolume(volume);
        playing.setLooping(true);
        playing.play();
        activeMusic.add(new ActiveMusicCombination(music, playing));
    }

    public void stopActiveMusic(Music musicFile){
    for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getMusicPlying().equals(musicFile)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlying().stop();
                this.activeMusic.remove(this.activeMusic.get(i));
            }
        }
    }

    public void pauseActiveMusic(Music musicFile){
        for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getMusicPlying().equals(musicFile)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlying().pause();
            }
        }
    }

    public void unPauseActiveMusic(Music musicFile){
        for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getMusicPlying().equals(musicFile)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlying().play();
            }
        }
    }

    public void stopActiveMusic(String fileNameAndDirectory){
        for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileNameAndDirectory)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlying().stop();
                this.activeMusic.remove(this.activeMusic.get(i));
            }
        }
    }

    public void pauseActiveMusic(String fileNameAndDirectory){
           for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileNameAndDirectory)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlying().pause();
            }
        }
    }

    public void unPauseActiveMusic(String fileNameAndDirectory){
           for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileNameAndDirectory)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlying().play();
            }
        }
    }

    public void clearAllActiveMusic(){
        while(!(activeMusic.size() == 0)){
            activeMusic.remove(0);
        }
    }


    //Inner class for combination class, soundname and file of playing sound.

    private final class ActiveMusicCombination {
        private final String fileName;
        private final Music playing;

        private ActiveMusicCombination(String fileName, Music playing) {
            this.fileName = fileName;
            this.playing = playing;
        }

        String getFileName() {
            return fileName;
        }

        Music getMusicPlying() {
            return playing;
        }
    }

}
