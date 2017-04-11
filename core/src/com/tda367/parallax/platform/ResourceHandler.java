package com.tda367.parallax.platform;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.utils.UBJsonReader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anthony on 11/04/2017.
 */
public class ResourceHandler {
    private ResourceHandler instance;
    private Map<String,Model> loadedModels;

    private G3dModelLoader modelLoader;

    private ResourceHandler(){
        UBJsonReader jsonReader = new UBJsonReader();
        modelLoader = new G3dModelLoader(jsonReader);

        loadedModels = new HashMap<String, Model>();
    }

    public ResourceHandler getInstance(){
        if (instance == null) instance = new ResourceHandler();
        return instance;
    }

    private ModelInstance loadModel(String modelName, String modelDirectory){
        Model modelNew;

        if (modelDirectory.length() > 0){
            modelNew = modelLoader.loadModel(Gdx.files.getFileHandle(modelDirectory+"\\"+modelName, Files.FileType.Internal));
        } else {
            modelNew = modelLoader.loadModel(Gdx.files.getFileHandle(modelName, Files.FileType.Internal));
        }

        loadedModels.put(modelName,modelNew);
        return new ModelInstance(modelNew);
    }

    public ModelInstance getModel(String modelName){
        Model entry = loadedModels.get(modelName);
        ModelInstance modelInstance;
        if (entry == null){
            modelInstance = loadModel(modelName,"");
        } else {
            modelInstance = new ModelInstance(entry);
        }
        return modelInstance;
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
}
