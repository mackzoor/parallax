package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.world.BoxObstacle;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.util.Renderable3dObject;
import com.tda367.parallax.view.util.ResourceLoader;

/**
 * View class for {@link com.tda367.parallax.model.parallaxcore.world.BoxObstacle}.
 */
public class BoxObstacleView implements View{

    private final String model3dInternalPath = "3dModels/boxObstacle/boxObstacle.g3db";
    private final BoxObstacle obstacle;
    private com.tda367.parallax.view.util.Renderable3dObject renderable3dObject;

    public BoxObstacleView(Collidable collidable){
        obstacle = null;
        renderable3dObject = new Renderable3dObject(
                collidable.getPos(),
                collidable.getRot(),
                ResourceLoader.getInstance().getModel(model3dInternalPath),
                0.5f
        );
    }

    public BoxObstacleView(BoxObstacle obstacle) {
        this.obstacle = obstacle;
        renderable3dObject = new Renderable3dObject(
                obstacle.getPos(),
                obstacle.getRot(),
                ResourceLoader.getInstance().getModel(model3dInternalPath),
                0.5f
        );
    }

    @Override
    public void render() {
        Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
    }

    @Override
    public boolean isObsolete() {
        return obstacle.collisionActivated();
    }

}
