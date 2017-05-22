package com.tda367.parallax.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.CardboardCamera;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.google.vrtoolkit.cardboard.Eye;
import com.tda367.parallax.view.util.Renderable3dObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for rendering 3d objects.
 */
public final class Renderer3D {

    private ModelBatch modelBatch;
    private Camera camera;
    private Environment environment;
    private List<Renderable3dObject> modelsToRender;

    //Singleton pattern
    private static Renderer3D renderer3D;
    public static Renderer3D initialize(float fov, int width, int height, boolean isVr){
        if (isVr){
            // Setup of special camera for VR
            Camera cardboardCamera = new CardboardCamera();
            cardboardCamera.lookAt(0, 0, -1);
            renderer3D = new Renderer3D(cardboardCamera);
        } else {
            renderer3D = new Renderer3D(fov, width, height);
        }

        return renderer3D;
    }

    public static Renderer3D getInstance() {
        return renderer3D;
    }

    /**
     * Creates a new Renderer3D from a {@link Camera}
     * @param camera
     */
    private Renderer3D(Camera camera) {
        this.camera = camera;
        modelsToRender = new ArrayList<Renderable3dObject>();
        modelBatch = new ModelBatch();

        camera.near = 0.1f;
        camera.far = 50f;

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1.f));
        environment.set(new ColorAttribute(ColorAttribute.Fog, 0f, 0f, 0f,1f));
        //environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

    }

    /**
     * Creates a new Renderer3D.
     * @param fov Field of view of the camera
     * @param width Amount of pixels on the x axis to be rendered.
     * @param height Amount of pixels on the y axis to be rendered.
     */
    private Renderer3D(float fov, int width, int height) {
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
     *
     * @param renderObject object to be rendered.
     */
    public void addObjectToFrame(Renderable3dObject renderObject) {
        //Add objects to render queue for current frame
        modelsToRender.add(renderObject);
    }

    /**
     * Sets the render camera position in the format; X+ = Right, Y+ = forward, Z+ = up
     *
     * @param x x-value.
     * @param y y-value.
     * @param z z-value.
     */
    public void setCameraPosition(float x, float y, float z) {
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
    public void renderFrame() {
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        //Start rendering
        modelBatch.begin(camera);

        //Render high priority objects
        for (Renderable3dObject renderable3dObject : modelsToRender) {
            if (renderable3dObject.isHighPriority()){
                modelBatch.render(renderable3dObject.getModelInstance(), environment);
            }
        }
        modelBatch.flush();

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
     * Draws eye for VR
     */
    public void onDrawEye(Eye eye){
        ((CardboardCamera)camera).setEyeViewAdjustMatrix(new Matrix4(eye.getEyeView()));

        float[] perspective = eye.getPerspective(camera.near, camera.far);
        ((CardboardCamera)camera).setEyeProjection(new Matrix4(perspective));
        camera.update();

    }

    /**
     * Sets render y-resolution.
     *
     * @param y new y-resolution.
     */
    public void setHeight(int y) {
        camera.viewportHeight = y;
    }

    /**
     * Sets render x-resolution.
     *
     * @param x new x-resolution.
     */
    public void setWidth(int x) {
        camera.viewportWidth = x;
    }

}
