package com.tda367.parallax.view;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.utils.UBJsonReader;

import java.util.HashMap;
import java.util.Map;

public final class ResourceLoader {
    private static ResourceLoader instance;
    private Map<String,Model> loadedModels;
    private Map<String,Sound> loadedSounds;
    private Map<String, Music> loadedMusic;

    private G3dModelLoader modelLoader;

    private ResourceLoader(){
        UBJsonReader jsonReader = new UBJsonReader();
        modelLoader = new G3dModelLoader(jsonReader);

        loadedModels = new HashMap<String, Model>();
        loadedSounds = new HashMap<String, Sound>();
        loadedMusic = new HashMap<String, Music>();
    }

    public static ResourceLoader getInstance(){
        if (instance == null){
            instance = new ResourceLoader();
        }
        return instance;
    }

    private ModelInstance loadModel(String modelName, String modelDirectory){
        Model modelNew;

        if (modelDirectory.length() > 0){
            modelNew = modelLoader.loadModel(Gdx.files.getFileHandle(modelDirectory+"/"+modelName, Files.FileType.Internal));
        } else {
            modelNew = modelLoader.loadModel(Gdx.files.getFileHandle(modelName, Files.FileType.Internal));
        }

        loadedModels.put(modelName,modelNew);
        return new ModelInstance(modelNew);
    }

    public ModelInstance getModel(String modelName){
        return loadModel(modelName,"");
    }

    public ModelInstance getModel(String modelName, String modelDirectory){
        Model entry = loadedModels.get(modelName);
        ModelInstance modelInstance;
        if (entry == null){
            modelInstance = loadModel(modelName,modelDirectory);
        } else {
            modelInstance = new ModelInstance(entry);
        }
        return modelInstance;
    }

    private Sound loadSound(String soundName, String soundDirectory){
        Sound newSound;

        if (soundDirectory.length() > 0){
            newSound = Gdx.audio.newSound(Gdx.files.internal((soundDirectory+"/"+soundName)));
        } else {
            newSound = Gdx.audio.newSound(Gdx.files.internal((soundName)));
        }

        loadedSounds.put(soundName,newSound);
        return newSound;
    }

    public com.badlogic.gdx.audio.Sound getSound(String soundName, String soundDirectory){
        Sound sound = loadedSounds.get(soundName);
        if (sound == null){
            sound = loadSound(soundName, soundDirectory);
        }
        return sound;
    }

    public com.badlogic.gdx.audio.Sound getSound(String soundName) {
        Sound sound = loadedSounds.get(soundName);
        if (sound == null) {
            sound = loadSound(soundName, "");
        }
        return sound;
    }


    private Music loadMusic(String musicName, String musicDirectory){
        Music newMusic;

        if (musicDirectory.length() > 0){
            newMusic = Gdx.audio.newMusic(Gdx.files.internal((musicDirectory+"/"+musicName)));
        } else {
            newMusic = Gdx.audio.newMusic(Gdx.files.internal((musicName)));
        }

        loadedMusic.put(musicName,newMusic);
        return newMusic;
    }

    public Music getMusic(String musicName, String musicDirectory){
        Music music = loadedMusic.get(musicName);
        if (music == null){
            music = loadMusic(musicName, musicDirectory);
        }
        return music;
    }

    public Music getMusic(String musicName){
        Music music = loadedMusic.get(musicName);
        if (music == null){
            music = loadMusic(musicName, "");
        }
        return music;
    }

}
