package com.tda367.parallax.platform;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Quaternion;
import com.tda367.parallax.parallaxCore.RenderManager;
import com.tda367.parallax.parallaxCore.Renderable;
import com.tda367.parallax.parallaxCore.spaceCraft.ISpaceCraft;

import java.util.List;

/**
 * Created by Anthony on 11/04/2017.
 */
public class Renderer {
    private ModelBatch modelBatch;
    private Camera camera;
    private Environment environment;
    private ResourceHandler rh;
    private TextureRegion textureRegion;
    private Texture texture;

    Renderer(Camera camera) {
        rh = ResourceHandler.getInstance();
        modelBatch = new ModelBatch();
        this.camera = camera;

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
    }

    public FrameBuffer renderAll(FrameBuffer frameBuffer){

        camera.update();

        // You've seen all this before, just be sure to clear the GL_DEPTH_BUFFER_BIT when working in 3D
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Like spriteBatch, just with models!  pass in the box Instance and the environment
        modelBatch.begin(camera);

        List<Renderable> renderables = RenderManager.getInstance().getRenderables();

        for (Renderable renderable : renderables){
            ModelInstance modelInstance = rh.getModel(renderable.getModel().getModelName());

            modelInstance.transform.setToTranslation(
                    renderable.getPos().getX(),
                    renderable.getPos().getZ(),
                    renderable.getPos().getY()*-1
                    );

            modelInstance.transform.rotate(
                    new Quaternion(
                            renderable.getRot().getX(),
                            renderable.getRot().getZ(),
                            renderable.getRot().getY()*-1,
                            renderable.getRot().getW()
                    )
            );
            modelBatch.render(modelInstance,environment);
        }
        modelBatch.end();


        return frameBuffer;
    }

}
