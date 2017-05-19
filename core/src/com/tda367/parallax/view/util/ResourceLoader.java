package com.tda367.parallax.view.util;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.utils.UBJsonReader;

import java.util.HashMap;
import java.util.Map;

/**
 * A class for loading libgdx compatible file types from harddrive into memory.
 */
public final class ResourceLoader {
    private Map<String,Model> loadedModels;
    private Map<String,Sound> loadedSounds;
    private Map<String,Music> loadedMusic;
    private G3dModelLoader modelLoader;
    private static ResourceLoader instance;
    private Map<String,Texture> loadedTextures;

    //Singleton pattern
    public static ResourceLoader getInstance(){
        if (instance == null){
            instance = new ResourceLoader();
        }
        return instance;
    }
    private ResourceLoader(){
        UBJsonReader jsonReader = new UBJsonReader();
        modelLoader = new G3dModelLoader(jsonReader);

        loadedModels = new HashMap<String, Model>();
        loadedSounds = new HashMap<String, Sound>();
        loadedMusic = new HashMap<String, Music>();
        loadedTextures = new HashMap<String, Texture>();
    }

    /**
     * Loads a new {@link Model} into memory and puts it into the hash map; loadedModels.
     * @param modelName Name of the 3d model. Has to be a .g3db file type.
     * @param modelDirectory Relative path to directory of the model.
     * @return {@link ModelInstance} of the loaded Model.
     */
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

    /**
     * Sends back a {@link ModelInstance} from the specified model name.
     * If the 3d model is not found in hash map then it will be loaded into it from the hard drive.
     * @param modelName Name of the 3d model.
     * @param modelDirectory Relative path of the directory containing the 3d model.
     * @return {@link ModelInstance} of the {@link Model}.
     */
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
    public ModelInstance getModel(String modelName){
        return getModel(modelName,"");
    }

    /**
     * Loads a {@link Sound} into the memory from the specified file.
     * @param soundName Name of the file to be loaded. Has to be .mp3 file type.
     * @param soundDirectory Relative path to the directory containing the sound file.
     * @return The sound object.
     */
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

    /**
     * Returns the {@link Sound} from the specified file if already in hash, otherwise loads in from harddrive.
     * @param soundName Name of the sound file. Has to .mp3 file type.
     * @param soundDirectory Relative path to the directory containing the sound file.
     * @return The Sound object.
     */
    public Sound getSound(String soundName, String soundDirectory){
        Sound sound = loadedSounds.get(soundName);
        if (sound == null){
            sound = loadSound(soundName, soundDirectory);
        }
        return sound;
    }
    public Sound getSound(String soundName) {
        Sound sound = loadedSounds.get(soundName);
        if (sound == null) {
            sound = loadSound(soundName, "");
        }
        return sound;
    }


    /**
     * Loads a {@link Music} into the memory from the specified file.
     * @param musicName Name of the file to be loaded. Has to be .mp3 file type.
     * @param musicDirectory Relative path to the directory containing the sound file.
     * @return The sound object.
     */
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

    /**
     * Returns the {@link Music} from the specified file if already in hash, otherwise loads in from harddrive.
     * @param musicName Name of the sound file. Has to .mp3 file type.
     * @param musicDirectory Relative path to the directory containing the sound file.
     * @return the {@link Music} object.
     */
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

    /**
     * Loads a texture from the filepath into memory and stores it in the loadedTextures Map.
     * @param filePath path to the texture file. Has to be .png or .jpg file type.
     * @return the loaded texture.
     */
    private Texture loadTexture(String filePath){
        Texture texture = new Texture(filePath);

        loadedTextures.put(filePath,texture);
        return texture;
    }
    /**
     * If the texture has already been loaded a reference to it is sent back.
     * Otherwise it is loaded in and then sent back.
     * @param filePath path to the texture file. Has to be .png or .jpg.
     * @return the texture.
     */
    public Texture getTexture(String filePath){
        Texture texture = loadedTextures.get(filePath);
        if (texture == null){
            texture = loadTexture(filePath);
        }
        return texture;
    }
}
