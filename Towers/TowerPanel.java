//********************************************************************
//  TowerPanel.java      

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TowerPanel extends JPanel
{
   public static final int MIN_PAUSE_TIME = 50;
   public static final int MAX_PAUSE_TIME = 1500;
   public static final int DEFAULT_PAUSE_TIME = 900;
   public static final int MIN_DISKS = 1;
   public static final int MAX_DISKS = 8;
   public static final int DEFAULT_NUM_DISKS = 4;
   public static final int NUM_TOWERS = 3;
    
   public final String TRY_AGAIN_MESSAGE = "Incorrect move\n Try Again?";
   public final String SOLVED_MESSAGE = "Congratulations!\nYou solved the puzzle!";

   private TowersOfHanoi towerEngine;
   private Tower[] towers;
    
   private int numDisks, pauseTime, firstTower;
   private boolean userSolve, firstTowerSelected;

   private ArrayList solution;
   private Timer animationTimer;

   //-----------------------------------------------------------------
   //  Sets up this tower panel to diplay in the animation.
   //-----------------------------------------------------------------
   public TowerPanel(int disks) 
   {
      numDisks = disks;
      pauseTime = DEFAULT_PAUSE_TIME;
      solution = new ArrayList();
      userSolve = false;
      firstTowerSelected = false;
      
      towers = new Tower[NUM_TOWERS];
      for (int i=0; i<NUM_TOWERS; i++)
          towers[i] = new Tower(numDisks);
      
      //add sized disks to first tower
      for (int i=numDisks; i > 0; i--)
         towers[0].addDisk(new TowerDisk(i));

      towerEngine = new TowersOfHanoi(numDisks);

      setBackground(Color.white);
      
      addMouseListener(new TowerSelectListener());
    }

   //-----------------------------------------------------------------
   //  Overrides JPanels getPreferredSize method.
   //----------------------------------------------------------------- 
   public Dimension getPreferredSize() 
   {
      // panel width is length of largest disk * num towers + 2 width step between towers
      int panelWidth = MAX_DISKS * TowerDisk.WIDTH_STEP * NUM_TOWERS +
                       2 * TowerDisk.WIDTH_STEP + NUM_TOWERS;

      // panel height 1.2 as tall as largest towers
      int panelHeight = TowerDisk.HEIGHT * MAX_DISKS; 
      panelHeight = (int) Math.round(1.2 * panelHeight);
      
      return new Dimension(panelWidth, panelHeight);
   }

   //-----------------------------------------------------------------
   //  Draws this Towers of Hanoi tower.
   //-----------------------------------------------------------------
   public void paintComponent(Graphics page)
   {
      super.paintComponent(page);
      int towerSpacing = getSize().width /  NUM_TOWERS;
        
      int towerX = towerSpacing/2 - Tower.WIDTH/2;
      int towerY = getSize().height;
        
      for (int i=0; i < NUM_TOWERS; i++)
      {
          towers[i].draw(page, towerX, towerY);
          towerX += towerSpacing;
      }
   }

   //-----------------------------------------------------------------
   //  Sets the amount of time in milliseconds to pause between each
   //  animation step. If in animation mode, resets the animation
   //  timer.
   //-----------------------------------------------------------------
   public void setPauseTime(int time)
   {
      pauseTime = time;

      if (animationTimer == null)
         return;

      if (userSolve)  // no animation
         return;

      // update animation speed
      if (!solution.isEmpty() && animationTimer.isRunning()) 
      {
         stopTimer();
         animationTimer = new Timer(pauseTime, new UpdateAnimation());
         animationTimer.start();
      }
   }
    
   //-----------------------------------------------------------------
   //  Sets the number of disks and resets the puzzle.
   //-----------------------------------------------------------------   
   public void setNumDisks(int num)
   {
      numDisks = num;
      stopTimer();        // stop any animation
      userSolve = false;  // stop any user solution
      resetTowers();
   }
   
   //-----------------------------------------------------------------
   //  Animates the current solution. Stops any user solution.
   //  If the solution is empty, resets the towers.
   //-----------------------------------------------------------------
   public void animate()
   {
      userSolve = false;  // stop user solution
      stopTimer();        // temporarily stop timer

      if (solution.isEmpty())
         resetTowers();

      animationTimer = new Timer(pauseTime, new UpdateAnimation());
      animationTimer.start();
   }

   //-----------------------------------------------------------------
   //  Allows a user to step-by-step solve the puzzle. Stops any
   //  animation and starts user solution mode. If solution is empty,
   //  resets the towers.
   //-----------------------------------------------------------------
   public void userSolve()
   {
      stopTimer();

      if (solution.isEmpty())
         resetTowers();

      userSolve = true;
   }

   //-----------------------------------------------------------------
   //  Resets the towers to current settings. Creates new towers,
   //  tower engine, and solution.
   //-----------------------------------------------------------------
   public void resetTowers() 
   {
      towers = new Tower[NUM_TOWERS];

      for (int i=0; i < NUM_TOWERS; i++)
         towers[i] = new Tower(numDisks);
      
      for (int i=numDisks; i > 0; i--)
         towers[0].addDisk(new TowerDisk(i));
      
      towerEngine = new TowersOfHanoi(numDisks);
      solution = towerEngine.getSolution();
      repaint();
   }
    
   //-----------------------------------------------------------------
   //  Stops the animation timer.
   //-----------------------------------------------------------------
   public void stopTimer()
   {
      if (animationTimer != null)
         if (animationTimer.isRunning())
            animationTimer.stop();
   }
    
   //-----------------------------------------------------------------
   //  Resumes the animation timer and stops any user solutions.
   //-----------------------------------------------------------------
   public void resumeTimer()
   {
      if (animationTimer != null)
         if (!animationTimer.isRunning())  // get new settings and restart
         {
            userSolve = false;
            animationTimer = new Timer(pauseTime, new UpdateAnimation());
            animationTimer.start();
         }
   }
    
   //********************************************************************
   //  Represents the listener for the animation timer.
   //********************************************************************
   private class UpdateAnimation implements ActionListener
   {
      //-----------------------------------------------------------------
      //  Gets the next move in the solution and "executes" it.
      //-----------------------------------------------------------------
      public void actionPerformed(ActionEvent event)
      {
         if (!solution.isEmpty()) 
         {
            TowersMove nextMove = (TowersMove) solution.remove(0);
            TowerDisk d = towers[nextMove.getFrom()].removeDisk();
            towers[nextMove.getTo()].addDisk(d);
            repaint();
         }
      }
   }
    
   //********************************************************************
   //  Represents the mouse listener class for the panel.  Used in
   //  the user solve mode.
   //********************************************************************    
   private class TowerSelectListener extends MouseAdapter
   {
      //-----------------------------------------------------------------
      //  Processes a user move.
      //-----------------------------------------------------------------
      public void mousePressed(MouseEvent e)
      {
         if (!userSolve)
            return;
         if (solution.isEmpty())
            return;

         int towerSpacing = getSize().width /  NUM_TOWERS;                

         // determine tower choosen
         int selectedTower;
         int xValue = e.getX();
         if (xValue < towerSpacing)
            selectedTower = 0;
         else
            if (xValue < 2 * towerSpacing)
               selectedTower = 1;
            else
               selectedTower = 2;
         if (!firstTowerSelected)
         {
            firstTower = selectedTower;
            firstTowerSelected = true;
         }
         else
         {
            // determine if valid move was selected
            firstTowerSelected = false;
                
            TowersMove currentMove = (TowersMove) solution.get(0);
            // if currentMove correct  . . .
            if (currentMove.getFrom() == firstTower &&
                currentMove.getTo() == selectedTower)
            {
               solution.remove(0);
               TowerDisk d = towers[currentMove.getFrom()].removeDisk();
               towers[currentMove.getTo()].addDisk(d);
               repaint();
            }
            else // incorrect move
            {
               int result = JOptionPane.showConfirmDialog(null, TRY_AGAIN_MESSAGE, 
                   "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
               // show solution anyway?
               if (result != JOptionPane.YES_OPTION) 
               {
                  solution.remove(0);
                  TowerDisk d = towers[currentMove.getFrom()].removeDisk();
                  towers[currentMove.getTo()].addDisk(d);
                  repaint();
               }
                        
            }
         }
         // display solved message
         if (solution.isEmpty())
            JOptionPane.showMessageDialog(null, SOLVED_MESSAGE, "",
                                          JOptionPane.INFORMATION_MESSAGE);
      }
   }
}
