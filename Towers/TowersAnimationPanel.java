//********************************************************************
//  TowersAnimationPanel.java 

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;
import java.awt.*;
import java.util.Hashtable;

public class TowersAnimationPanel extends JPanel
{
   private TowerPanel towerViewer;

   //-----------------------------------------------------------------
   //  Sets up the main GUI for the animation.
   //-----------------------------------------------------------------
   public TowersAnimationPanel()
   {
      setLayout(new BorderLayout(25,50));
    //  this.setSize(600,600);

      towerViewer = new TowerPanel(TowerPanel.DEFAULT_NUM_DISKS);
      add(towerViewer, BorderLayout.CENTER);
      
     // towerViewer.setSize(600,600);

      SliderListener sliderHandler = new SliderListener();
      ButtonListener buttonHandler = new ButtonListener();
        
      JPanel animationButtonPanel = new JPanel();
      animationButtonPanel.setLayout(new GridLayout(1,2));
        
      JButton pause = new JButton("Pause Animation");
      pause.setName("pause");
      pause.addActionListener(buttonHandler);
      animationButtonPanel.add(pause);
        
      JButton start = new JButton("Resume Animation");
      start.setName("resume");
      start.addActionListener(buttonHandler);
      animationButtonPanel.add(start);        
        
      JSlider speed = new JSlider();
      speed.setName("speed");
      speed.setMinimum(TowerPanel.MIN_PAUSE_TIME);
      speed.setMaximum(TowerPanel.MAX_PAUSE_TIME);
      speed.setValue(TowerPanel.DEFAULT_PAUSE_TIME);
      speed.setBorder(new TitledBorder("Animation Speed"));
       
      Hashtable labelTable = new Hashtable();
      labelTable.put(new Integer(TowerPanel.MIN_PAUSE_TIME), new JLabel("Faster"));
      labelTable.put(new Integer(TowerPanel.MAX_PAUSE_TIME), new JLabel("Slower"));
       
      speed.setLabelTable(labelTable);
      speed.setPaintLabels(true);
      speed.addMouseListener(sliderHandler);

      JPanel buttonPanel = new JPanel();
        
      JButton animate = new JButton("Animate");
      animate.setName("animate");
      animate.addActionListener(buttonHandler);
      buttonPanel.add(animate);
        
      JButton user = new JButton("Let Me Try");
      user.setName("user");
      user.addActionListener(buttonHandler);
      buttonPanel.add(user);
        
      JButton reset = new JButton("Reset");
      reset.setName("reset");
      reset.addActionListener(buttonHandler);        
      buttonPanel.add(reset);
        
      JPanel controls = new JPanel();
      controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
      controls.add(buttonPanel);
      controls.add(speed);
      controls.add(animationButtonPanel);
        
      add(controls, BorderLayout.SOUTH);
        
      JSlider diskNum = new JSlider();
      diskNum.setName("disks");
      diskNum.setMinimum(TowerPanel.MIN_DISKS);
      diskNum.setMaximum(TowerPanel.MAX_DISKS);
      diskNum.setValue(TowerPanel.DEFAULT_NUM_DISKS);
      diskNum.setBorder(new TitledBorder("Disks"));
      diskNum.setMajorTickSpacing(1);
      diskNum.setPaintLabels(true);
      diskNum.setPaintTicks(true);
      diskNum.setSnapToTicks(true);
      diskNum.addMouseListener(sliderHandler); 
        
      add(diskNum, BorderLayout.NORTH);
   }

   //********************************************************************
   //  Represents the mouse listener for the sliders.
   //********************************************************************
   private class SliderListener extends MouseAdapter
   {
      //-----------------------------------------------------------------
      //  Sets the animation speed or number of disks.
      //-----------------------------------------------------------------
      public void mouseReleased(MouseEvent event)
      {
         JSlider source = (JSlider)event.getSource();
         if (source.getName() == "speed")
            towerViewer.setPauseTime(source.getValue());
         else
            if (source.getName() == "disks")
               towerViewer.setNumDisks(source.getValue());
      }
   }
    
   //********************************************************************
   //  Represents the listener for all of the buttons.
   //********************************************************************    
   private class ButtonListener implements ActionListener
   {
      //-----------------------------------------------------------------
      //  Determines which button was pressed and processes accordingly.
      //-----------------------------------------------------------------
      public void actionPerformed(ActionEvent event)
      {
         JButton source = (JButton)event.getSource();
         String name = source.getName();
         if (name == "reset")
            towerViewer.resetTowers();
         else
            if (name == "animate")
               towerViewer.animate();
            else
               if (name == "user")
                  towerViewer.userSolve();
               else 
                  if (name == "pause")
                     towerViewer.stopTimer();
                  else 
                     if (name == "resume")
                        towerViewer.resumeTimer();
      }
   }
}
