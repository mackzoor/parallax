/**
 * An enemy minion. Will try to destroy its target.
 */
public class MinionEnemy implements EnemyAI {

    private SpaceCraft spaceCraft;
    private Collidable target;


    //TODO Update?

    MinionEnemy(SpaceCraft spaceCraft){

    }

    @Override
    public void setTarget(Collidable collidableObject) {
        target = collidableObject;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {
        //TODO Update ai to target enemy and fire if targeted
    }
}
