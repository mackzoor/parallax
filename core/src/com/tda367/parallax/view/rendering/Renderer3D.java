package com.tda367.parallax.view.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.CardboardCamera;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSystem;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.badlogic.gdx.math.Matrix4;
import com.google.vrtoolkit.cardboard.Eye;
import com.tda367.parallax.utilities.ResourceLoader;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for rendering 3d objects.
 */
public final class Renderer3D {

    @Getter private ParticleSystem particleSystem;


    private BillboardParticleBatch spriteBatch;
    private ModelBatch modelBatch;
    private Camera camera;
    private Environment environment;

    private List<Renderable3dObject> modelsToRender;
    private List<RenderableParticleEffect> particleEffectsToRender;

    //Singleton pattern
    private static Renderer3D renderer3D;

    public static Renderer3D initialize(float fov, int width, int height, boolean isVr) {
        if (isVr) {
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
     *
     * @param camera
     */
    private Renderer3D(Camera camera) {
        this.camera = camera;
        modelsToRender = new ArrayList<Renderable3dObject>();
        particleEffectsToRender = new ArrayList<RenderableParticleEffect>();
        modelBatch = new ModelBatch();

        particleSystem = ParticleSystem.get();
        spriteBatch = new BillboardParticleBatch();
        spriteBatch.setTexture(ResourceLoader.getInstance().getTexture("particles/pre_particle.png"));
        spriteBatch.setCamera(camera);
        particleSystem.add(spriteBatch);


        camera.near = 0.1f;
        camera.far = 50f;

        this.environment = new Environment();
        this.environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1.f));
        this.environment.set(new ColorAttribute(ColorAttribute.Fog, 0f, 0f, 0f, 1f));
        //environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
    }
    /**
     * Creates a new Renderer3D.
     *
     * @param fov    Field of view of the camera
     * @param width  Amount of pixels on the x axis to be rendered.
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
        this.modelsToRender.add(renderObject);
    }
    public void addParticleEffectToFrame(RenderableParticleEffect particleEffect){
        particleEffectsToRender.add(particleEffect);
    }

    public void renderFrame() {
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        modelBatch.begin(camera);

        renderOpaque3dModels(modelBatch, modelsToRender);
        renderTransparent3dModels(modelBatch, modelsToRender);
        renderParticles(modelBatch, particleEffectsToRender);

        modelBatch.end();


        modelsToRender.clear();
        particleEffectsToRender.clear();
    }

    private void renderOpaque3dModels(ModelBatch modelBatch, List<Renderable3dObject> modelsToRender){
        //Render high priority objects
        for (Renderable3dObject renderable3dObject : this.modelsToRender) {
            if (renderable3dObject.isHighPriority()) {
                this.modelBatch.render(renderable3dObject.getModelInstance(), this.environment);
            }
        }

        modelBatch.flush();
    }
    private void renderTransparent3dModels(ModelBatch modelBatch, List<Renderable3dObject> modelsToRender){
        //Render low priority objects
        for (Renderable3dObject renderable3dObject : this.modelsToRender) {
            if (!renderable3dObject.isHighPriority()) {
                this.modelBatch.render(renderable3dObject.getModelInstance(), this.environment);
            }
        }

        modelBatch.flush();
    }
    private void renderParticles(ModelBatch modelBatch, List<RenderableParticleEffect> particleEffectsToRender) {

        for (RenderableParticleEffect renderableParticleEffect : particleEffectsToRender) {
            particleSystem.add(renderableParticleEffect.getParticleEffect());
        }
        particleSystem.update();

        particleSystem.begin();
        particleSystem.draw();
        particleSystem.end();

        modelBatch.render(particleSystem);
        modelBatch.flush();
        particleSystem.removeAll();
    }

    /**
     * Draws eye for VR
     */
    public void onDrawEye(Eye eye) {
        ((CardboardCamera) this.camera).setEyeViewAdjustMatrix(new Matrix4(eye.getEyeView()));

        float[] perspective = eye.getPerspective(this.camera.near, this.camera.far);
        ((CardboardCamera) this.camera).setEyeProjection(new Matrix4(perspective));
        this.camera.update();

    }

    public void setCameraPosition(float x, float y, float z) {
        //Update camera position
        camera.position.set(
                x,
                z,
                y * -1
        );
        camera.update();
    }
    public void setHeight(int y) {
        this.camera.viewportHeight = y;
    }
    public void setWidth(int x) {
        camera.viewportWidth = x;
    }

}
