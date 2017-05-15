package com.tda367.parallax.view;

import lombok.Getter;

/**
 * Handles 3d models.
 */

public class Model {

    @Getter private String modelName;
    @Getter private String modelDirectory;

    public Model(String modelName){
        this.modelName = modelName;
        this.modelDirectory = "";
    }
    public Model(String modelName, String modelDirectory){
        this.modelName = modelName;
        this.modelDirectory = modelDirectory;
    }
}
