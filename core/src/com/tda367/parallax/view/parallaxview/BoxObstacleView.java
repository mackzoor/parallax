package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.world.BoxObstacle;
import com.tda367.parallax.view.util.Model;
import com.tda367.parallax.view.Renderer3D;

/**
 * View class for {@link com.tda367.parallax.model.parallaxcore.world.BoxObstacle}.
 */
public class BoxObstacleView implements View{

    private final String model3dInternalPath = "3dModels/boxObstacle/boxObstacle.g3db";
    private final BoxObstacle obstacle;
    private com.tda367.parallax.view.util.Renderable3dObject renderable3dObject;

    public BoxObstacleView(Collidable collidable){
        obstacle = null;
        renderable3dObject = new com.tda367.parallax.view.util.Renderable3dObject(
                collidable.getPos(),
                collidable.getRot(),
                new Model("3dModels/boxObstacle/boxObstacle.g3db"),
                0.5f
        );
    }

    public BoxObstacleView(BoxObstacle obstacle) {
        this.obstacle = obstacle;
        renderable3dObject = new com.tda367.parallax.view.util.Renderable3dObject(
                obstacle.getPos(),
                obstacle.getRot(),
                new Model("3dModels/boxObstacle/boxObstacle.g3db"),
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
