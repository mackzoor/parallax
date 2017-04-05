package ParallaxCore;

/**
 * Interface for ai that will try to destroy its target.
 */
public interface EnemyAI extends Updatable{
    void setTarget(Collidable collidableObject);
    void start();
    void stop();
}
