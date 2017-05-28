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
 * Class for rendering 3d objects by using libgdx rendering system.
 */
public final class Renderer3D {

    private static Renderer3D rend3D;

    @Getter
    private ParticleSystem particleSystem;


    private BillboardParticleBatch spriteBatch;
    private ModelBatch modelBatch;
    private Camera camera;
    private Environment environment;
    private boolean particlesEnabled;

    private List<Renderable3dObject> modelsToRender;
    private List<RenderableParticleEffect> particleEffectsToRender;

    /**
     * Creates a new Renderer3D.
     *
     * @param fov    Field of view of the camera
     * @param width  Amount of pixels on the x axis to be rendered.
     * @param height Amount of pixels on the y axis to be rendered.
     */
    private Renderer3D(float fov, int width, int height, boolean particlesEnabled) {
        this(
                new PerspectiveCamera(
                        fov,
                        width,
                        height),
                particlesEnabled
        );
    }

    private Renderer3D(Camera camera, boolean enableParticleEffects) {
        this.camera = camera;
        this.modelsToRender = new ArrayList<Renderable3dObject>();
        this.particleEffectsToRender = new ArrayList<RenderableParticleEffect>();
        this.modelBatch = new ModelBatch();

        this.particlesEnabled = enableParticleEffects;
        this.particleSystem = ParticleSystem.get();
        this.spriteBatch = new BillboardParticleBatch();
        this.spriteBatch.setTexture(ResourceLoader.getInstance().getTexture("particles/pre_particle.png"));
        this.spriteBatch.setCamera(camera);
        this.particleSystem.add(this.spriteBatch);


        camera.near = 0.1f;
        camera.far = 50f;

        this.environment = new Environment();
        this.environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1.f));
        this.environment.set(new ColorAttribute(ColorAttribute.Fog, 0f, 0f, 0f, 1f));
    }

    public static Renderer3D initialize(float fov, int width, int height, boolean isVr, boolean particlesEnabled) {
        if (isVr) {
            final Camera cardboardCamera = new CardboardCamera();
            cardboardCamera.lookAt(0, 0, -1);
            rend3D = new Renderer3D(cardboardCamera, particlesEnabled);
        } else {
            rend3D = new Renderer3D(fov, width, height, particlesEnabled);
        }

        return rend3D;
    }

    public static Renderer3D getInstance() {
        return rend3D;
    }


    /**
     * Adds {@link Renderable3dObject} to be rendered in next frame.
     *
     * @param renderObject object to be rendered.
     */
    public void addObjectToFrame(Renderable3dObject renderObject) {
        this.modelsToRender.add(renderObject);
    }

    /**
     * Adds {@link RenderableParticleEffect} to be rendered in next frame.
     * @param particleEffect object to be rendered.
     */
    public void addParticleEffectToFrame(RenderableParticleEffect particleEffect) {
        this.particleEffectsToRender.add(particleEffect);
    }

    public void renderFrame() {
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        this.modelBatch.begin(this.camera);

        renderOpaque3dModels(this.modelBatch, this.modelsToRender);
        renderTransparent3dModels(this.modelBatch, this.modelsToRender);
        if (this.particlesEnabled) {
            renderParticles(this.modelBatch, this.particleEffectsToRender);
        }

        this.modelBatch.end();


        this.modelsToRender.clear();
        this.particleEffectsToRender.clear();
    }

    private void renderTransparent3dModels(ModelBatch modelBatch, List<Renderable3dObject> modelsToRender) {
        for (final Renderable3dObject renderable3dObject : this.modelsToRender) {
            if (renderable3dObject.isTransparency()) {
                this.modelBatch.render(renderable3dObject.getModelInstance(), this.environment);
            }
        }

        modelBatch.flush();
    }

    private void renderOpaque3dModels(ModelBatch modelBatch, List<Renderable3dObject> modelsToRender) {
        for (final Renderable3dObject renderable3dObject : this.modelsToRender) {
            if (!renderable3dObject.isTransparency()) {
                this.modelBatch.render(renderable3dObject.getModelInstance(), this.environment);
            }
        }

        modelBatch.flush();
    }

    private void renderParticles(ModelBatch modelBatch, List<RenderableParticleEffect> particleEffectsToRender) {

        for (final RenderableParticleEffect renderableParticleEffect : particleEffectsToRender) {
            this.particleSystem.add(renderableParticleEffect.getParticleEffect());
            renderableParticleEffect.getParticleEffect().setBatch(this.particleSystem.getBatches());
        }
        this.particleSystem.update();

        this.particleSystem.begin();
        this.particleSystem.draw();
        this.particleSystem.end();

        modelBatch.render(this.particleSystem);
        modelBatch.flush();
        this.particleSystem.removeAll();
    }

    /**
     * Sets eye view matrix for VR.
     */
    public void onDrawEye(Eye eye) {
        ((CardboardCamera) this.camera).setEyeViewAdjustMatrix(new Matrix4(eye.getEyeView()));

        final float[] perspective = eye.getPerspective(this.camera.near, this.camera.far);
        ((CardboardCamera) this.camera).setEyeProjection(new Matrix4(perspective));
        this.camera.update();

    }

    public void setCameraPosition(float x, float y, float z) {
        this.camera.position.set(
                x,
                z,
                y * -1
        );
        this.camera.update();
    }

    public void setHeight(int y) {
        this.camera.viewportHeight = y;
    }

    public void setWidth(int x) {
        this.camera.viewportWidth = x;
    }

}
