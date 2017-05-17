package com.tda367.parallax.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.tda367.parallax.view.util.Renderable3dObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for rendering 3d objects.
 */
public class Renderer3D {

    private ModelBatch modelBatch;
    private Camera camera;
    private Environment environment;
    private List<Renderable3dObject> modelsToRender;

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
        modelsToRender = new ArrayList<Renderable3dObject>();
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

    /**
     * Adds {@link Renderable3dObject} to be rendered in next frame.
     * @param renderObject object to be rendered.
     */
    public void addObjectToFrame(Renderable3dObject renderObject) {
        //Add objects to render queue for current frame
        modelsToRender.add(renderObject);
    }

    /**
     * Sets the render camera position in the format; X+ = Right, Y+ = forward, Z+ = up
     * @param x x-value.
     * @param y y-value.
     * @param z z-value.
     */
    public void setCameraPosition(float x, float y, float z){
        //Update camera position
        camera.position.set(
                x,
                z,
                y * -1
        );
        camera.update();
    }

    /**
     * Renders frame.
     */
    public void renderFrame(){
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        //Start rendering
        modelBatch.begin(camera);

        //Render high priority objects
        for (Renderable3dObject renderable3dObject : modelsToRender) {
            if (renderable3dObject.isHighPriority()){
                modelBatch.render(renderable3dObject.getModelInstance(), environment);
            }
        }

        //Render low priority objects
        for (Renderable3dObject renderable3dObject : modelsToRender) {
            if (!renderable3dObject.isHighPriority()){
                modelBatch.render(renderable3dObject.getModelInstance(), environment);
            }
        }

        //End rendering
        modelBatch.end();

        //Clear models to render.
        modelsToRender.clear();
    }

    /**
     * Sets render y-resolution.
     * @param y new y-resolution.
     */
    public void setHeight(int y) {
        camera.viewportHeight = y;
    }

    /**
     * Sets render x-resolution.
     * @param x new x-resolution.
     */
    public void setWidth(int x) {
        camera.viewportWidth = x;
    }
}
