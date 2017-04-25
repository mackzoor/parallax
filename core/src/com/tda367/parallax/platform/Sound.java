package com.tda367.parallax.platform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.tda367.parallax.parallaxCore.SoundListener;
import com.tda367.parallax.parallaxCore.SoundManager;
import org.omg.PortableInterceptor.ACTIVE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xoxLU on 2017-04-11.
 */

public class Sound implements SoundListener {

    private final class ActiveMusicCombination {
        private final String fileName;
        private final Music playing;

        private ActiveMusicCombination(String fileName, Music playing) {
            this.fileName = fileName;
            this.playing = playing;
        }

        public String getFileName() {
            return fileName;
        }

        public Music getMusicPlying() {
            return playing;
        }
    }

    ResourceHandler resources;

    List<ActiveMusicCombination> activeMusic = new ArrayList<ActiveMusicCombination>();

    public Sound(){
        this.resources = ResourceHandler.getInstance();
        SoundManager.getInstance().addListener(this);
    }

    @Override
    public void playSound(String sound) {
        resources.getSound(sound).play();
    }

    @Override
    public void playMusic(String music) {
        resources.getMusic(music).play();
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



    private void stopActiveMusic(Music musicFile){
    for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getMusicPlying().equals(musicFile)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlying().stop();
                this.activeMusic.remove(this.activeMusic.get(i));
            }
        }
    }

    private void pauseActiveMusic(Music musicFile){
        for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getMusicPlying().equals(musicFile)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlying().pause();
            }
        }
    }

    private void unPauseActiveMusic(Music musicFile){
        for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getMusicPlying().equals(musicFile)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlying().play();
            }
        }
    }

    private void stopActiveMusic(String fileName){
        for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileName)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlying().stop();
                this.activeMusic.remove(this.activeMusic.get(i));
            }
        }
    }

    private void pauseActiveMusic(String fileName){
           for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileName)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlying().pause();
            }
        }
    }

    private void unPauseActiveMusic(String fileName){
           for (int i = 0; i < activeMusic.size(); i++) {
            if (this.activeMusic.get(i).getFileName().equals(fileName)){
                this.activeMusic.get(this.activeMusic.indexOf(this.activeMusic.get(i))).getMusicPlying().play();
            }
        }
    }





}
