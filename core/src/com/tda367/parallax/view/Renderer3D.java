package com.tda367.parallax.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Quaternion;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for rendering 3d objects.
 */
public class Renderer3D {

    private ModelBatch modelBatch;
    private Camera camera;
    private Environment environment;
    private ResourceLoader rh;

    //Singleton pattern
    private static Renderer3D renderer3D;
    public static Renderer3D initialize(Camera camera){
        renderer3D = new Renderer3D(camera);
        return renderer3D;
    }
    public static Renderer3D getInstance(){
        return renderer3D;
    }


    private Renderer3D(Camera camera) {
        this.camera = camera;
        rh = ResourceLoader.getInstance();
        modelBatch = new ModelBatch();

        camera.near = 0.1f;
        camera.far = 50f;

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1.f));
        environment.set(new ColorAttribute(ColorAttribute.Fog, 0f, 0f, 0f, -1f));
        //environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

    }
    public Renderer3D(float fov, int width, int height) {
        this(
                new PerspectiveCamera(
                        fov,
                        width,
                        height
                )
        );
    }


    private List<ModelInstance> modelsToRender = new ArrayList<ModelInstance>();
    public void addObjectToFrame(Renderable renderObject) {
        // You've seen all this before, just be sure to clear the GL_DEPTH_BUFFER_BIT when working in 3D
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        //Start rendering for the camera.
        ModelInstance modelInstance = rh.getModel(renderObject.getModel().getModelName(), renderObject.getModel().getModelDirectory());

        modelInstance.transform.setToTranslation(
                renderObject.getPos().getX(),
                renderObject.getPos().getZ(),
                renderObject.getPos().getY() * -1
        );

        modelInstance.transform.rotate(
                new Quaternion(
                        renderObject.getRot().getX(),
                        renderObject.getRot().getZ(),
                        renderObject.getRot().getY() * -1,
                        renderObject.getRot().getW()
                )
        );
        modelsToRender.add(modelInstance);
    }

    public void setCameraPosition(float x, float y, float z){
        //Update camera position
        camera.position.set(
                x,
                z,
                y * -1
        );
        camera.update();
    }

    public void renderFrame(){
        modelBatch.begin(camera);
        for (ModelInstance modelInstance : modelsToRender) {
            modelBatch.render(modelInstance, environment);
        }
        modelBatch.end();
        modelsToRender.clear();
    }

    public void setHeight(int y) {
        camera.viewportHeight = y;
    }

    public void setWidth(int x) {
        camera.viewportWidth = x;
    }
}
