//********************************************************************
//  TowersMove.java       
//
//  Represents one move in the Towers Of Hanoi puzzle.
//*********************************************************************

public class TowersMove
{
   private int from, to;
    
   //-----------------------------------------------------------------
   //  Sets up this move.
   //-----------------------------------------------------------------
   public TowersMove(int fromTower, int toTower) 
   {
      from = fromTower;
      to = toTower;
   }

   //-----------------------------------------------------------------
   //  Returns a string representing this move.
   //-----------------------------------------------------------------
   public String toString()
   {
      return "Move from tower " + from + " to tower " + to;
   }
    
   //-----------------------------------------------------------------
   //  Returns the originating post.
   //-----------------------------------------------------------------
   public int getFrom()
   {
      return from;
   }
    
   //-----------------------------------------------------------------
   //  Returns the destination post.
   //-----------------------------------------------------------------
   public int getTo()
   {
      return to;
   }   
}
