import processing.core.PImage;
import java.util.List;
import java.util.Optional;

public class Octo_Full extends EntityLayerThree {

    // constructor
    public Octo_Full(String id, Point position, List<PImage> images,
                     int actionPeriod, int animationPeriod,
                     int resourceLimit, int resourceCount)
    {
        super(id, position, images,
                actionPeriod, animationPeriod,
                resourceLimit, resourceCount);
    }

    // methods
    public void executeActivity(WorldModel world,
                                        ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Entity> fullTarget = world.findNearest(this.getPosition(),
                Atlantis.class);

        if (fullTarget.isPresent() &&
                moveToFull(world, fullTarget.get(), scheduler))
        {
            //at atlantis trigger animation
            ((Atlantis)fullTarget.get()).scheduleActions(scheduler, world, imageStore);

            //transform to unfull
            transformFull(this, world, scheduler, imageStore);
        }
        else
        {
            scheduler.scheduleEvent(this,
                    createActivityAction(world, imageStore),
                    this.getActionPeriod());
        }
    }

    private boolean moveToFull(WorldModel world,
                               Entity target, EventScheduler scheduler)
    {
        if (this.getPosition().adjacent(target.getPosition()))
        {
            return true;
        }
        else
        {
            Point nextPos = this.nextPositionOcto(world, target.getPosition());

            if (!this.getPosition().equals(nextPos))
            {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent())
                {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    private void transformFull(Entity entity, WorldModel world,
                               EventScheduler scheduler, ImageStore imageStore)
    {
        Octo_Not_Full octo = new Octo_Not_Full(this.getId(), this.getPosition(), this.getImages(),
                this.getActionPeriod(), this.getAnimationPeriod(),
                this.getResourceLimit(), 0);

        world.removeEntity(entity);
        scheduler.unscheduleAllEvents(entity);

        world.addEntity(octo);
        octo.scheduleActions(scheduler, world, imageStore);
    }
}
