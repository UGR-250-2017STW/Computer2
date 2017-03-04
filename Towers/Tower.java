//********************************************************************
//  Tower.java      

import java.util.Stack;
import java.awt.Color;
import java.awt.Graphics;

public class Tower
{
   public static final int WIDTH = 10;

   private Stack disks;
   private int towerHeight;
   
   //-----------------------------------------------------------------
   //  Sets up this tower, storing the max number of disks the tower
   //  will hold.
   //-----------------------------------------------------------------
   public Tower(int height) 
   {
      disks = new Stack();
      towerHeight = height;
   }
   
   //-----------------------------------------------------------------
   //  Adds disk to the top of the tower.
   //----------------------------------------------------------------- 
   public void addDisk(TowerDisk d)
   {
      disks.push(d);
   }
   
   //-----------------------------------------------------------------
   //  Removes the top disk from the tower.
   //-----------------------------------------------------------------
   public TowerDisk removeDisk()
   {
      if (disks.empty())
         return null;
      else
         return (TowerDisk) disks.pop();
   }
   
   //-----------------------------------------------------------------
   //  Draws the tower using graphics context g. The coordinates (x,y)
   //  represent the bottom left corner of the tower
   //-----------------------------------------------------------------   
   public void draw(Graphics g, int x, int y)
   {
      // draw tower
      int height = TowerDisk.HEIGHT * towerHeight;
      g.setColor(Color.black);
      g.fillRect(x,y-height, WIDTH, height);
      
      // draw disks
      if (!disks.isEmpty())
      {
         // create a stack of disks in reverse order
         Stack temp = new Stack();
         while (!disks.empty())
            temp.push(disks.pop());
           
         // draw the disks
         int location = 1;
         int diskX = x + WIDTH / 2;
         while (!temp.isEmpty()) 
         {
            TowerDisk d = (TowerDisk)temp.pop();
               
            int diskY = y - TowerDisk.HEIGHT * location;
            d.draw(g, diskX, diskY);

            // put disk back on stack
            disks.push(d);
            location ++;
         }
      }
   }
}
