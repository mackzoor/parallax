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
import com.tda367.parallax.model.coreabstraction.RenderManager;
import com.tda367.parallax.model.coreabstraction.Renderable;

import java.util.List;

/**
 * Class that renders {@link Renderable} objects.
 */

public class Renderer {
    private ModelBatch modelBatch;
    private Camera camera;
    private Environment environment;
    private ResourceHandler rh;


    public Renderer(Camera camera){
        this.camera = camera;
        rh = ResourceHandler.getInstance();
        modelBatch = new ModelBatch();

        camera.near = 0.1f;
        camera.far = 50f;

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1.f));
        environment.set(new ColorAttribute(ColorAttribute.Fog, 0f, 0f, 0f, -1f));
        //environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

    }
    public Renderer(float fov, int width, int height) {
        this(
                new PerspectiveCamera(
                        fov,
                        width,
                        height
                )
        );
    }

    public void renderAll() {


        // You've seen all this before, just be sure to clear the GL_DEPTH_BUFFER_BIT when working in 3D
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        RenderManager rm = RenderManager.getInstance();

        //Update camera position
        camera.position.set(
                rm.getCamXCoord(),
                rm.getCamZCoord(),
                rm.getCamYCoord()*-1
        );
        camera.update();


        //Start rendering for the camera.
        modelBatch.begin(camera);
        List<Renderable> renderables = rm.getRenderables();

        for (Renderable renderable : renderables){
            ModelInstance modelInstance = rh.getModel(renderable.getModel().getModelName(),renderable.getModel().getModelDirectory());

            modelInstance.transform.setToTranslation(
                    renderable.getPos().getX(),
                    renderable.getPos().getZ(),
                    renderable.getPos().getY() * -1
            );

            modelInstance.transform.rotate(
                    new Quaternion(
                            renderable.getRot().getX(),
                            renderable.getRot().getZ(),
                            renderable.getRot().getY() * -1,
                            renderable.getRot().getW()
                    )
            );
            modelBatch.render(modelInstance, environment);
        }
        modelBatch.end();
    }

    public void setHeight(int y){
        camera.viewportHeight = y;
    }
    public void setWidth(int x){
        camera.viewportWidth = x;
    }

}
