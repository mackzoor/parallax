package com.tda367.parallax.utilities;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btConvexHullShape;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.UBJsonReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class for loading libgdx compatible file types from harddrive into memory.
 */
public final class ResourceLoader {
    private static ResourceLoader instance;
    private G3dModelLoader modelLoader;
    ParticleEffectLoader particleEffectLoader;

    private AssetManager assetManager;
    private Array<ParticleBatch<?>> array = new Array<ParticleBatch<?>>();

    private Map<String,Model> loadedModels;
    private Map<String,Sound> loadedSounds;
    private Map<String,Music> loadedMusic;
    private Map<String,Texture> loadedTextures;
    private Map<String, btCollisionShape> loadedCollisionShapes;
    private Map<String, ParticleEffect> loadedParticleEffects;

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
        particleEffectLoader = new ParticleEffectLoader(new InternalFileHandleResolver());
        assetManager = new AssetManager(new InternalFileHandleResolver());

        loadedModels = new HashMap<String, Model>();
        loadedSounds = new HashMap<String, Sound>();
        loadedMusic = new HashMap<String, Music>();
        loadedTextures = new HashMap<String, Texture>();
        loadedCollisionShapes = new HashMap<String, btCollisionShape>();
        loadedParticleEffects = new HashMap<String, ParticleEffect>();
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
     * Loads a {@link Texture} from the filepath into memory and stores it in the loadedTextures Map.
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

    /**
     * Loads a {@link btCollisionShape} from the filepath into memory and stores it in the loadedTextures Map.
     * @param pathToObjFile path to the .obj file. Has to be .obj file type.
     * @return the loaded collision shape.
     */
    private btCollisionShape loadCollisionShape(String pathToObjFile){
        btConvexHullShape collisionShape = new btConvexHullShape();

        List<Vector3> vertices = get3dModelVertices(pathToObjFile);

        for (Vector3 vertex : vertices) {
            collisionShape.addPoint(vertex);
        }

        loadedCollisionShapes.put(pathToObjFile,collisionShape);
        return collisionShape;
    }
    /**
     * Creates a set of {@link Vector3}'s from an .obj files vectors. Only works with .obj file type.
     * @param pathToFile path to .obj file.
     * @return list of vectors.
     */
    private List<Vector3> get3dModelVertices(String pathToFile){
        FileHandle file = Gdx.files.internal(pathToFile);
        String fullString = file.readString();

        String[] lines = fullString.split("\n");

        List<Vector3> vectors = new ArrayList<Vector3>();
        for (String line : lines) {
            if (line.length() > 0 && line.substring(0,2).contains("v ")){
                vectors.add(stringToVector3(line));
            }
        }

        return vectors;
    }
    /**
     * Converts a string from a vertex line in an .obj 3d file. Has to be a line that starts with a "v".
     * @param str line to create vector with.
     * @return Created vector.
     */
    private Vector3 stringToVector3(String str){
        String[] splitted = str.split("\\s+");

        Vector3 vector = new Vector3(
                Float.valueOf(splitted[1]),
                Float.valueOf(splitted[2]),
                Float.valueOf(splitted[3])
        );

        return vector;
    }

    /**
     * Creates a {@link btCollisionShape} from the specified .obj file.
     * @param pathToObjFile path to .obj file.
     * @return the created collision shape
     */
    public btCollisionShape getCollisionShape(String pathToObjFile){
        btCollisionShape shape = loadedCollisionShapes.get(pathToObjFile);

        if (shape == null) {
            shape = loadCollisionShape(pathToObjFile);
        }

        return shape;
    }

    private ParticleEffect loadParticleEffect(String filePath){
        ParticleEffect effect;

        assetManager.setLoader(ParticleEffect.class,new ParticleEffectLoader(new InternalFileHandleResolver()));
        assetManager.load(filePath,ParticleEffect.class);

        assetManager.finishLoading();

        effect = assetManager.get(filePath, ParticleEffect.class);


        return effect;
    }

    public ParticleEffect getParticleEffect(String filePath) {
        ParticleEffect effect = loadedParticleEffects.get(filePath);

        if (effect == null) {
            effect = loadParticleEffect(filePath);
        }

        return effect.copy();
    }
}
