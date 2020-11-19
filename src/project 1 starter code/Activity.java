public class Activity extends Action{

    private WorldModel world;
    private ImageStore imageStore;

    public Activity(Entity entity, WorldModel world, ImageStore imageStore) {
        super(entity);
        this.world = world;
        this.imageStore = imageStore;
    }

    public void executeAction(EventScheduler scheduler)
    {
        EntityLayerOne entity = (EntityLayerOne) this.getEntity();
        entity.executeActivity(this.world, this.imageStore, scheduler);
    }
}
