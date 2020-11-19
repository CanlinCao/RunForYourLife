import processing.core.PImage;
import java.util.List;
import java.util.Optional;

public class Octo_Not_Full extends EntityLayerThree {

    // constructor
    public Octo_Not_Full(String id, Point position, List<PImage> images,
                         int actionPeriod, int animationPeriod,
                         int resourceLimit, int resourceCount) {
        super(id, position, images,
                actionPeriod, animationPeriod,
                resourceLimit, resourceCount);
    }

    // methods
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> notFullTarget = world.findNearest(this.getPosition(),
                Fish.class);

        if (!notFullTarget.isPresent() ||
                !this.moveToNotFull(world, notFullTarget.get(), scheduler) ||
                !this.transformNotFull(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this,
                    this.createActivityAction(world, imageStore),
                    this.getActionPeriod());
        }
    }

    private boolean moveToNotFull(WorldModel world,
                                  Entity target, EventScheduler scheduler) {
        if (this.getPosition().adjacent(target.getPosition())) {
            int resourceCount = this.getResourceCount();
            this.setResourceCount(resourceCount += 1);
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);

            return true;
        } else {
            Point nextPos = this.nextPositionOcto(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    private boolean transformNotFull(WorldModel world,
                                     EventScheduler scheduler, ImageStore imageStore) {
        if (this.getResourceCount() >= this.getResourceLimit()) {
            Octo_Full octo = new Octo_Full(this.getId(), this.getPosition(), this.getImages(),
                    this.getActionPeriod(), this.getAnimationPeriod(),
                    this.getResourceLimit(), this.getResourceLimit());

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(octo);
            octo.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }
}
