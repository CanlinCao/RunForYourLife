import processing.core.PImage;
import java.util.List;

/* obstacle, fish, sgrass */

abstract class EntityLayerOne extends Entity{

    private int actionPeriod;

    public EntityLayerOne(String id, Point position, List<PImage> images, int actionPeriod)
    {
        super(id, position, images);
        this.actionPeriod = actionPeriod;
    }

    protected int getActionPeriod() {
        return this.actionPeriod;
    }

    public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this,
                this.createActivityAction(world, imageStore),
                this.getActionPeriod());
    }

    public Activity createActivityAction(WorldModel world, ImageStore imageStore)
    {
      return new Activity(this, world, imageStore);
    }
}
