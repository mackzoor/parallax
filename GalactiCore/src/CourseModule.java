import java.util.List;

/**
 * A single module of a course in the game. Will automatically create usables and obstacles.
 */
public interface CourseModule {
    List<Collidable> getObstacles();
    List<Collidable> getUsables();


    //TODO Get start point & get end point?
}
