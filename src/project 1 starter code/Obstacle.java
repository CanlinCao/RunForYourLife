import processing.core.PImage;
import java.util.List;

public class Obstacle extends Entity{

    // constructor
    public Obstacle(String id, Point position,
                  List<PImage> images)
    {
        super(id, position, images);
    }
}
