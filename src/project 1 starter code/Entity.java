import java.util.List;
import processing.core.PImage;

/* obstacle */

abstract class Entity
{
   private String id;
   private Point position;
   private List<PImage> images;
   private int imageIndex;

   public Entity(String id, Point position,
                 List<PImage> images)
   {
      this.id = id;
      this.position = position;
      this.images = images;
      this.imageIndex = 0;
   }

   public int getImageIndex()
   {
      return this.imageIndex;
   }

   protected String getId() {
      return this.id;
   }

   protected Point getPosition() {
      return this.position;
   }

   protected List<PImage> getImages() {
      return this.images;
   }

   public void setPosition(Point position) {
      this.position = position;
   }

   public void setImageIndex(int imageIndex) {
      this.imageIndex = imageIndex;
   }

   protected PImage getCurrentImage()
   {
      return this.images.get(this.imageIndex);
   }

//   public void nextImage()
//   {
//      this.imageIndex = (this.imageIndex + 1 % this.images.size());
//   }
}
