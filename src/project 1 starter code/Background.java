import java.util.List;
import processing.core.PImage;

final class Background
{
   private String id;
   private List<PImage> images;
   private int imageIndex;

   //getter
   public List<PImage> getImages() {
      return images;
   }
   public int getImageIndex() {
      return imageIndex;
   }

   public Background(String id, List<PImage> images)
   {
      this.id = id;
      this.images = images;
   }

   public PImage getCurrentImage(Object entity)
   {
      return ((Background)entity).getImages()
              .get(((Background)entity).getImageIndex());
   }

}
