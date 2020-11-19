import processing.core.PImage;
import java.util.List;

/* obstacle, fish, Sgrass, atlantis, crab, quake */

abstract class EntityLayerTwo extends EntityLayerOne {

    private int animationPeriod;

    public EntityLayerTwo(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod);
        this.animationPeriod = animationPeriod;
    }

    public void nextImage()
    {
        this.setImageIndex((this.getImageIndex() + 1) % this.getImages().size());
    }

    public void scheduleActions(EventScheduler scheduler,
                                WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                this.createActivityAction(world, imageStore),
                this.getActionPeriod());
        scheduler.scheduleEvent(this, this.createAnimationAction(0),
                this.getAnimationPeriod());
    }

    protected int getAnimationPeriod(){
        return this.animationPeriod;
    }

    public Animation createAnimationAction(int repeatCount)
    {
        return new Animation(this, repeatCount);
    }

}
