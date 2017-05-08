package com.tda367.parallax.model.coreabstraction;

/**
 * Handles 3d models.
 */
public class Model {

    private String modelName;
    private String modelDirectory;

    public Model(String modelName){
        this.modelName = modelName;
        this.modelDirectory = "";
    }
    public Model(String modelName, String modelDirectory){
        this.modelName = modelName;
        this.modelDirectory = modelDirectory;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelDirectory() {
        return modelDirectory;
    }
}
