package com.tda367.parallax.platform;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.UBJsonReader;

public class SpaceCraftDisplay implements ApplicationListener, InputProcessor {
    private PerspectiveCamera camera;
    private ModelBatch modelBatch;
    private Model model;
    private ModelInstance modelInstance;
    private Environment environment;
    private boolean screenShot = false;
    private FrameBuffer frameBuffer;
    private Texture texture = null;
    private TextureRegion textureRegion;
    private SpriteBatch spriteBatch;
    private CameraInputController controller;

    @Override
    public void create() {
        // Create camera sized to screens width/height with Field of View of 75 degrees
        camera = new PerspectiveCamera(
                90,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());

        // Move the camera 5 units back along the z-axis and look at the origin
        camera.position.set(0f,0f,1f);
        camera.lookAt(0f,0f,0f);

        // Near and Far (plane) represent the minimum and maximum ranges of the camera in, um, units
        camera.near = 0.1f;
        camera.far = 300.0f;

        controller = new CameraInputController(camera);
        Gdx.input.setInputProcessor(controller);

        // A ModelBatch is like a SpriteBatch, just for models.  Use it to batch up geometry for OpenGL
        modelBatch = new ModelBatch();

        // Model loader needs a binary json reader to decode
        UBJsonReader jsonReader = new UBJsonReader();
        // Create a model loader passing in our json reader
        G3dModelLoader modelLoader = new G3dModelLoader(jsonReader);
        // Now load the model by name
        // Note, the model (g3db file ) and textures need to be added to the assets folder of the Android proj
        model = modelLoader.loadModel(Gdx.files.getFileHandle("ship.g3db", Files.FileType.Internal));


        // Now create an instance.  Instance holds the positioning data, etc of an instance of your model
        modelInstance = new ModelInstance(model);

        //move the model down a bit on the screen ( in a z-up world, down is -z ).
        modelInstance.transform.translate(0, 0, 0);

        // Finally we want some light, or we wont see our color.  The environment gets passed in during
        // the rendering process.  Create one, then create an Ambient ( non-positioned, non-directional ) light.
        environment = new Environment();
//        Texture texture1 = new Texture("bg.jpg");
//        environment.set(new TextureAttribute(TextureAttribute.createAmbient(texture1)));
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1.0f));


        frameBuffer = new FrameBuffer(Pixmap.Format.RGB888,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),false);
//        Gdx.input.setInputProcessor(this);

        spriteBatch = new SpriteBatch();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        model.dispose();
    }

    @Override
    public void render() {

        // Update camera controller
        controller.update();

        // You've seen all this before, just be sure to clear the GL_DEPTH_BUFFER_BIT when working in 3D
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);



        // When you change the camera details, you need to call update();
        // Also note, you need to call update() at least once.
        camera.update();


        // If the user requested a screenshot, we need to call begin on our framebuffer
        // This redirects output to the framebuffer instead of the screen.
        if(screenShot)
            frameBuffer.begin();

        // Like spriteBatch, just with models!  pass in the box Instance and the environment
        modelBatch.begin(camera);
        modelBatch.render(modelInstance, environment);
        modelBatch.end();

        // Now tell OpenGL that we are done sending graphics to the framebuffer
        if(screenShot)
        {
            frameBuffer.end();
            // get the graphics rendered to the framebuffer as a texture
            texture = frameBuffer.getColorBufferTexture();
            // welcome to the wonderful world of different coordinate systems!
            // simply put, the framebuffer is upside down to normal textures, so we have to flip it
            // Use a TextureRegion to do so
            textureRegion = new TextureRegion(texture);
            // and.... FLIP!  V (vertical) only
            textureRegion.flip(false, true);
        }

        // In the case that we have a texture object to actually draw, we do so
        // using the old familiar SpriteBatch to do so.
        if(texture != null)
        {
            spriteBatch.begin();
            spriteBatch.draw(textureRegion,0,0);
            spriteBatch.end();
            screenShot = false;
        }
    }

    public void resume () {
    }

    public void resize (int width, int height) {
    }

    public void pause () {
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
