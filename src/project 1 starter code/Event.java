final class Event
{
   private Action action;
   private long time;
   private Entity entity;

   // accessor
   public Action getAction() {
      return action;
   }
   public long getTime() {
      return time;
   }
   public Entity getEntity() {
      return entity;
   }

   // constructor
   public Event(Action action, long time, Entity entity)
   {
      this.action = action;
      this.time = time;
      this.entity = entity;
   }
}
