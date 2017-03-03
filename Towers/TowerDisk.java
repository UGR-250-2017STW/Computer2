//********************************************************************
//  TowerDisk.java  ********************

import java.awt.Color;
import java.awt.Graphics;

public class TowerDisk
{
   private int diskSize;
   
   public static final int HEIGHT = 20;
   public static final int WIDTH_STEP = 20;
   public static final int ARC_WIDTH = 5, ARC_HEIGHT = 5;
  
   public static final Color[] DISK_COLORS = {
      Color.black, Color.red, Color.blue, Color.yellow, Color.green,
      Color.orange, Color.magenta, Color.gray, Color.pink };

   //-----------------------------------------------------------------
   //  Creates a disk of specified size.
   //-----------------------------------------------------------------
   public TowerDisk(int size)
   {
      diskSize = size;
   }

   //-----------------------------------------------------------------
   //  Returns the size of the disk.
   //-----------------------------------------------------------------
   public int getSize()
   {
      return diskSize;
   }

   //-----------------------------------------------------------------
   //  Returns the width of the disk to be drawn on the screen.
   //-----------------------------------------------------------------
   public int getScreenWidth()
   {
       return diskSize * WIDTH_STEP;
   }
   
   //-----------------------------------------------------------------
   //  Returns the color of the disk as indexed in DISK_COLORS.
   //-----------------------------------------------------------------
   public Color getDiskColor()
   {
      return DISK_COLORS[diskSize % DISK_COLORS.length];
   }

   //-----------------------------------------------------------------
   //  Draws the disk using graphics context g.  Position x 
   //  represents the x coordinate of the center of the tower and
   //  position y represents the top position of the disk on the tower.
   //-----------------------------------------------------------------
   public void draw(Graphics page, int x, int y)
   {
      int width = getScreenWidth();
      int diskX = x - width / 2;
      page.setColor(getDiskColor());
      page.fillRoundRect(diskX, y, width, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
      page.setColor(Color.black);
      page.drawRoundRect(diskX, y, width, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
   }
}
