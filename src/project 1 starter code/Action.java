/*
Action: ideally what our various entities might do in our virutal world
 */

abstract class Action
{
   private Entity entity;

   public Action(Entity entity)
   {
      this.entity = entity;
   }

   protected Entity getEntity() {
      return this.entity;
   }

   public abstract void executeAction(EventScheduler scheduler);

   public void setEntity(Entity entity) {
      this.entity = entity;
   }
}
