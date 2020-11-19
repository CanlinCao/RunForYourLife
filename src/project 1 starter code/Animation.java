public class Animation extends Action {

    private int repeatCount;

    public Animation(EntityLayerOne entity, int repeatCount) {
        super(entity);
        this.repeatCount = repeatCount;
    }

    public void executeAction(EventScheduler scheduler)
    {
        EntityLayerTwo newentity = (EntityLayerTwo) this.getEntity();
        newentity.nextImage();
        this.setEntity(newentity);

        if (this.repeatCount != 1) {
            if (this.getEntity() instanceof EntityLayerTwo) {
                EntityLayerTwo entity = (EntityLayerTwo) this.getEntity();
                scheduler.scheduleEvent(entity,
                        entity.createAnimationAction(Math.max(this.repeatCount - 1, 0)),
                        entity.getAnimationPeriod());
            }
        }
    }
}
