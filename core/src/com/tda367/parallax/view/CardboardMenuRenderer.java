package com.tda367.parallax.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.CardboardCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Quaternion;

import java.util.List;


public class CardboardMenuRenderer {
    private ModelBatch modelBatch;
    private CardboardCamera camera;
    private Environment environment;
    private com.tda367.parallax.view.util.ResourceLoader rh;

    public CardboardMenuRenderer(CardboardCamera camera) {
        rh = com.tda367.parallax.view.util.ResourceLoader.getInstance();
        modelBatch = new ModelBatch();
        this.camera = camera;

        camera.near = 0.1f;
        camera.far = 50f;

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1.f));
        environment.set(new ColorAttribute(ColorAttribute.Fog, 0f, 0f, 0f, -1f));
//        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

    }

    public void renderAll() {

        camera.update();
//TODO Fix to work with new render structure
/*
        RenderQueue rq = RenderQueue.getInstance();
        Quaternion quat = camera.combined.getRotation(new Quaternion());
        rq.setCamXQuat(quat.x);
        rq.setCamYQuat(quat.z);
        rq.setCamZQuat(quat.y*-1);
        rq.setCamWQuat(quat.w);
*/


        // You've seen all this before, just be sure to clear the GL_DEPTH_BUFFER_BIT when working in 3D
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Like spriteBatch, just with models!  pass in the box Instance and the environment
        modelBatch.begin(camera);

        //TODO Fix to work with new render structure
        List<com.tda367.parallax.view.util.Renderable> renderables = null;// = RenderQueue.getInstance().getRenderables();

        for (com.tda367.parallax.view.util.Renderable renderable : renderables) {
            ModelInstance modelInstance = rh.getModel(renderable.getModel().getModelName(), renderable.getModel().getModelDirectory());

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

}




