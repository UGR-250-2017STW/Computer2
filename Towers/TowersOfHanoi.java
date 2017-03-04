

import java.util.ArrayList;

public class TowersOfHanoi
{


   private int totalDisks;

   private ArrayList moves;

// stores the moves in the solution
//-----------------------------------------------------------------
//  Sets up the puzzle.
//-----------------------------------------------------------------


   public TowersOfHanoi(int numDisks)
   {
      totalDisks = numDisks;
      moves = new ArrayList();
   }


//-----------------------------------------------------------------
//  Performs the initial call to moveTower to solve the puzzle.
//  Moves the disks from tower 1 to tower 3 using tower 2.
//  Returns an ArrayList of the moves made.
//-----------------------------------------------------------------
   
public ArrayList getSolution()
   {
     moveTower(totalDisks, 0, 2, 1);
      return moves;
   }
 
  
//-----------------------------------------------------------------
//  Moves the specified number of disks from one tower to another
//  by moving a subtower of n-1 disks out of the way, moving one
//  disk, then moving the subtower back. Base case of 1 disk.
//-----------------------------------------------------------------
   

private void moveTower(int numDisks, int start, int end, int temp)
   {
      if (numDisks == 1)
         moveOneDisk (start, end);
      else
      {
         moveTower(numDisks-1, start, temp, end);
         moveOneDisk(start, end);
         moveTower(numDisks-1, temp, end, start);
      }
   }


//-----------------------------------------------------------------
//  Stores instructions to move one disk from the specified start
//  tower to the specified end tower.
//-----------------------------------------------------------------
   

private void moveOneDisk(int start, int end)
   {
      moves.add(new TowersMove(start, end));
   }

}

