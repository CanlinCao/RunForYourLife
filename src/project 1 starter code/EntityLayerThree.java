import processing.core.PImage;
import java.util.List;

/* obstacle, atlantis, fish, Sgrass, crab, quake, octo */

abstract class EntityLayerThree extends EntityLayerTwo {

    private int resourceLimit;
    private int resourceCount;

    public EntityLayerThree(String id, Point position, List<PImage> images,
                            int actionPeriod, int animationPeriod,
                            int resourceLimit, int resourceCount) {
        super(id, position, images, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;
    }

    protected int getResourceLimit() {
        return this.resourceLimit;
    }

    protected int getResourceCount() {
        return this.resourceCount;
    }

    protected void setResourceCount(int resourceCount) {
        this.resourceCount = resourceCount;
    }

   protected Point nextPositionOcto(WorldModel world, Point destPos)
   {
      int horiz = Integer.signum(destPos.getX() - this.getPosition().getX());
      Point newPos = new Point(this.getPosition().getX() + horiz,
         this.getPosition().getY());

      if (horiz == 0 || world.isOccupied(newPos))
      {
         int vert = Integer.signum(destPos.getY() - this.getPosition().getY());
         newPos = new Point(this.getPosition().getX(),
            this.getPosition().getY() + vert);

         if (vert == 0 || world.isOccupied(newPos))
         {
            newPos = this.getPosition();
         }
      }

      return newPos;
   }
}
