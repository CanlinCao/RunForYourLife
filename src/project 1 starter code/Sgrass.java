import processing.core.PImage;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Sgrass extends EntityLayerOne{

    private static final String FISH_KEY = "fish";
    private static final String FISH_ID_PREFIX = "fish -- ";
    private static final int FISH_CORRUPT_MIN = 20000;
    private static final int FISH_CORRUPT_MAX = 30000;

    private static final Random rand = new Random();

    // constructor
    public Sgrass(String id, Point position,
                  List<PImage> images,
                  int actionPeriod)
    {
        super(id, position, images, actionPeriod);
    }

    // methods
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Point> openPt = world.findOpenAround(this.getPosition());

        if (openPt.isPresent())
        {
            Fish fish = new Fish(FISH_ID_PREFIX + this.getId(), openPt.get(), imageStore.getImageList(FISH_KEY),
                    FISH_CORRUPT_MIN + this.rand.nextInt(FISH_CORRUPT_MAX - FISH_CORRUPT_MIN));
            world.addEntity(fish);
            fish.scheduleActions(scheduler, world, imageStore);
        }

        scheduler.scheduleEvent(this,
                this.createActivityAction(world, imageStore),
                this.getActionPeriod());
    }
}
