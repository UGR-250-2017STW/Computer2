
//********************************************************************
//  TowersAnimation.java      


import javax.swing.*;


public class TowersAnimation {

	
	//-----------------------------------------------------------------
	   //  Creates and presents the program frame.
	   //-----------------------------------------------------------------
	   public static void main(String[] args)
	   {
	      JFrame frame = new JFrame("Towers of Hanoi Animation");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.getContentPane().add(new TowersAnimationPanel());
	      frame.pack();
	      frame.setVisible(true);
	   }

}
