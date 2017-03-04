package musicPlayPackage;

// About 520 lines of code uses

// This program demonstrates playing audio files and the use of a timer to animate images.
// The timer is delayed to coincide with the music.  At each tick of the timer, the image 
// positions are updated creating animation.

// About 520 lines of Java code


import java.awt.event.*;
import java.awt.*;
//import java.awt.image.*;
import java.io.File;


//import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;



public class musicPlay extends JFrame {
		
		Timer timer;
		int colorNum = 0;	// global to change background color
		int counter = 0; 	// global to count and stop timer
		int soundNumber = 1;	// global to choose sound and draw
		
		
		JFrame mainFrame = new JFrame("Playing Audio Files with Animation in Java");

		  JButton playButton = new JButton("Click Here to Animate the Sounds!");
		
			
			public musicPlay() {
			    
		       	JPanel myPanel = new JPanel();
		    
		        Image myIcon = Toolkit.getDefaultToolkit().getImage("noteIcon.png");
		        
		        mainFrame.setIconImage(myIcon);
		        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        mainFrame.setLocationRelativeTo(null);
		        mainFrame.pack();
		        mainFrame.add(myPanel);
		        mainFrame.setSize(600,600);	// width, height
		        mainFrame.setResizable(false);
		        mainFrame.setVisible(true);
		        
		        mainFrame.setLocation(400,150);
		        mainFrame.setLayout(new BorderLayout());

		        myPanel.setLayout(new BorderLayout());
		        
		        myPanel.add(playButton, BorderLayout.SOUTH);

		        myPanel.setVisible(true);
		        
		        playButton.setForeground(Color.BLUE);
		        playButton.setBackground(Color.GREEN);
		        Font myFont = new Font("Arial", Font.BOLD, 20);
		        playButton.setFont(myFont);
		        
		        playButton.setPreferredSize(new Dimension(300,50));
	     
		        
		       
		        JComponent component1 = new JComponent()
		    	{
		    		public void paintComponent(Graphics graph)
		    		{
		    			super.paintComponent(graph);
		    			
		    			if(soundNumber > 4)	// there are 3 sounds and a description
		    				soundNumber = 1;
		    			
		    			// soundNumber = 4;	// testing
		    			
		    			if(soundNumber == 1)
		    			{
		    				System.out.println("In soundNumber == 1 of paint and soundNumber is " + soundNumber);
		    				myTimer.setDelay(160);
		    				drawSound1(graph,colorNum);   			
		    				colorNum++;
		    				counter++;
		    			
		    				if(counter == 38)		// once it plays through once, reset everything
		    				{						// and stop the timer
		    					colorNum = 0;
		    					myTimer.stop();
		    					counter = 0;
		    					soundNumber++;		// increment the sound number
		    					playButton.setEnabled(true);// re- enable the play button (disabled in playButton ActionListener
		    				}
		    			}
		    			
		    			
		    			if(soundNumber == 2)
		    			{
		    				myTimer.setDelay(220);
		    				drawSound2(graph,colorNum);   			
		    				colorNum++;
		    				counter++;
		    				if(colorNum == 25)		// resets at 25 since both intervals are the same
		    					colorNum = 0;
		    			
		    				if(counter == 50)		// once it plays through once, reset everything
		    				{						// and stop the timer
		    					colorNum = 0;
		    					myTimer.stop();
		    					counter = 0;
		    					soundNumber++;		// increment the sound number
		    					playButton.setEnabled(true);// re- enable the play button
		    				}
		    			}
		    			
		    			if(soundNumber == 3)		// third sound and drawSound3
		    			{
		    				myTimer.setDelay(176);		// 176 is right for the bass line
		    				drawSound3(graph,colorNum);   			
		    				colorNum++;
		    				counter++;
		    				if(colorNum == 106)		// resets at at end of music
		    					colorNum = 0;
		    			
		    				if(counter == 106)		// once it plays through once, reset everything
		    				{						// and stop the timer
		    					colorNum = 0;
		    					myTimer.stop();
		    					counter = 0;
		    					soundNumber++;		// increment the sound number
		    					playButton.setEnabled(true);// re- enable the play button
		    					System.out.println("Just finished number 3 and sound number is " + soundNumber);
		    					System.out.println(" ");
		    				}
		    			}
		    			
		    			
		    			if(soundNumber == 4)		// fourth sound number is the write-up
		    			{
		    				drawSound4(graph,colorNum);
		    				colorNum++;
		    				counter++;
		    				if(counter > 15)			// THIS NEEDS TO BE SET TO SOMETHING
		    				{						// and stop the timer
		    					colorNum = 0;
		    					myTimer.stop();
		    					counter = 0;
		    					soundNumber++;		// increment the sound number
		    					playButton.setEnabled(true);// re- enable the play button
		    					System.out.println("Just finished number 3 and sound number is " + soundNumber);
		    					System.out.println(" ");
		    				}
		    				
		    			}
		    			
		    		}
		    	};
		    	
		        myPanel.add(component1);
		        
		        // when the play button is clicked, play the sound and start the timer which fires the drawSound
		        playButton.addActionListener(new ActionListener()
		        {
		            public void actionPerformed(ActionEvent e)
		            {
		            	
		            	if(soundNumber > 4)
		            		soundNumber = 1;
		            	
		            	if(soundNumber == 1)
		            		playSound("Ring10.wav");
		            	
		            	
		            	if(soundNumber == 2)
		            		playSound("Ring08.wav");
		            	
		            	if(soundNumber == 3)
		            		playSound("brownTheme.wav");
		            	
		            	if(soundNumber == 4)	// background music
		            		playSound("Ring10.wav");

		                myTimer.start();
						playButton.setEnabled(false);		// disable the play button - re-enabled after sound
		            }
		        });		        
		        
			}
		        
		        public static void drawSound1(Graphics g, int colorNum)
		        {         
		        	
		        	System.out.println("In drawSound1 now and colorNum is " + colorNum);
		        	
		        	// Background image
		        	Image image = Toolkit.getDefaultToolkit().getImage("musicImage1.png");
		        	g.drawImage(image, 0, 0, null);	// image, x, y, observer
		        	
		        	if (colorNum == 1 || colorNum == 2)
		            {
		        	   g.setColor(Color.BLUE);
		        	   g.fillOval(90, 250, 50, 50);			// arguments x, y,upper left corner and width and height
		            }
		            if (colorNum == 3)
		            {
		        	   g.setColor(Color.GREEN);
		        	   g.fillOval(150, 250, 50, 50);		// move them IAW the notes
		            }
		            if (colorNum == 4)
		            {
		        	   g.setColor(Color.CYAN);
		        	   g.fillOval(210, 250, 50, 50);
		            }  
		        	if (colorNum == 5)
		        	{
		        	   g.setColor(Color.ORANGE);
		        	   g.fillOval(270, 175, 50, 50);
		        	}
		        	if (colorNum == 6)
		        	{
		        	   g.setColor(Color.YELLOW);
		        	   g.fillOval(330, 100, 50, 50);
		        	}
		        	
		        	if (colorNum == 7)
		        	{
		        	   g.setColor(Color.BLUE);
		        	   g.fillOval(330, 75, 50, 50);
		        	}
		        	
		        	if (colorNum == 8) // || colorNum == 9)
		        	{
		        	   g.setColor(Color.RED);
		        	   g.fillOval(410, 125, 50, 50);
		        	}
		        	   
		        	if (colorNum > 8 && colorNum < 20)
		            {
		        	   g.setColor(Color.MAGENTA);
		        	   g.fillOval(470, 250, 50, 50);    	   // arguments x, y, upper left corner, width, height
		            }
		        	
		        	
		            if (colorNum == 20 || colorNum == 21)
		            {
		        	   g.setColor(Color.BLUE);
		        	   g.fillOval(90, 50, 50, 50);			// arguments x,y,upper left corner and width and height
		            }
		            if (colorNum == 22)
		            {
		        	   g.setColor(Color.GREEN);
		        	   g.fillOval(150, 125, 50, 50);		// must move them IAW the notes
		            }
		            if (colorNum == 23)
		            {
		        	   g.setColor(Color.CYAN);
		        	   g.fillOval(210, 150, 50, 50);
		            }  
		        	if (colorNum == 24)
		        	{
		        	   g.setColor(Color.ORANGE);
		        	   g.fillOval(270, 175, 50, 50);
		        	}
		        	if (colorNum == 25 || colorNum == 26)
		        	{
		        	   g.setColor(Color.YELLOW);
		        	   g.fillOval(330, 200, 50, 50);
		        	}
		        	
		        	if (colorNum == 27 || colorNum == 28)
		        	{
		        	   g.setColor(Color.RED);
		        	   g.fillOval(410, 225, 50, 50);
		        	}
		        	   
		        	if (colorNum > 28 && colorNum < 37)
		            {
		        	   g.setColor(Color.MAGENTA);
		        	   g.fillOval(470, 250, 50, 50);    	   // arguments x, y, upper left corner, width, height
		            }
		        	
		           // dispose after display       
		           
		        }   

		        
		        public static void drawSound2(Graphics g, int colorNum)
		        {         
		        	
		        	//System.out.println("In drawSound2 now and colorNum is " + colorNum);
		        	
		        	// Background image
		        	Image image = Toolkit.getDefaultToolkit().getImage("musicImage2.png");
		        	g.drawImage(image, 0, 0, null);	// image, x, y, observer
		        		//g.finalize();
		        	
		        	g.setColor(Color.BLACK);
		        	
		        	if (colorNum > 0)					// draw down to the point, the highest is 150
		        		g.fillRect(75, 400, 20, 50);		// arguments x, y upper left corner and width and height
		            
		            if (colorNum > 1)
		            	g.fillRect(105, 350, 20, 100);		// adding 30 to x (20px each w/ 10 in between)
		     
		            if (colorNum > 2)
		        	   g.fillRect(135, 300, 20, 150);

		        	if (colorNum > 3)
		        	   g.fillRect(165, 400, 20, 50);
		        	
		        	if (colorNum > 4)
		        	   g.fillRect(195, 350, 20, 100);
		        	
		        	if (colorNum > 5)
		         	   g.fillRect(225, 300, 20, 150);
		        	
		        	if (colorNum > 6)
		         	   g.fillRect(255, 400, 20, 50);
		        	
		        	if (colorNum > 7)
		         	   g.fillRect(285, 350, 20, 100);
		        	
		        	if (colorNum > 8)
		         	   g.fillRect(315, 300, 20, 150);
		        	
		        	if (colorNum > 9)
		         	   g.fillRect(345, 400, 20, 50);
		        	
		        	if (colorNum > 10)
		         	   g.fillRect(375, 350, 20, 100);
		        	
		        	if (colorNum > 11)
		         	   g.fillRect(405, 300, 20, 150);
		        	
		        	if (colorNum > 12)
		         	   g.fillRect(435, 400, 20, 50);
		        	
		        	if (colorNum > 13)
		          	   g.fillRect(465, 350, 20, 100);
		        	
		        	if (colorNum > 14)		// == 15|| colorNum == 16 || colorNum == 17 || colorNum == 18)
		        		g.fillRect(495, 300, 20, 150);
		        	
		        	if (colorNum == 19|| colorNum == 20 || colorNum == 21 || colorNum == 22 || colorNum == 23 || colorNum == 24)
		        		g.fillRect(495, 300, 20, 150);
		        	
		           // dispose after display       
		           
		        }   
		        
		        
		        
		        
		        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<  Draw Sound three >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		        // --------------------------Bass is handled by itself below -------------------------
		        
		        public static void drawSound3(Graphics g, int colorNum)
		        {         
		        	
		        	int noteNumber = colorNum;
		        	    	
		        	int Xpos = 0;
		        	int Ypos = 0;
		        	
		        	//System.out.println("In drawSound3 now and colorNum is " + colorNum);
		        	
		        	// Background image
		        	Image image = Toolkit.getDefaultToolkit().getImage("musicImage3.png");
		        	g.drawImage(image, 0, 0, null);	// image, x, y, observer
		        		//g.finalize();
		        	
		        	Image iTwoNotes = Toolkit.getDefaultToolkit().getImage("twoNotesClear.png");
		        //	g.drawImage(iTwoNotes, 100, 200, null);	// image, x, y, observer
		        		//g.finalize();
		        	
		        	g.setColor(Color.BLACK);
		        	
		        	drawSound3BassLine(g, colorNum);
		        	
		        	
		        	   	
		        	if(colorNum < 49)					// at 48, Xpos needs to start over at the left margin
		        		Xpos = 50 + (10 * colorNum);	// needs margin for 1 and 2, then + 10 until 7 and 8 then reset, colorNum goes to 106
		        	else
		        		Xpos = 50 + (10 * (colorNum - 48));	// start over after getting to the end of X
		        	
		        	if(colorNum < 49)
		        		Ypos = 160;		// was 190
		        	else
		        		Ypos = 310;
		        	
		        	g.setColor(Color.BLUE);
		        	
		        	if(noteNumber > 54 && noteNumber < 82)
		        		noteNumber = (noteNumber - 54) + 20;	// only half the intro the second time
		        	
		        	
		        	
		        	if (noteNumber == 32)	// hold for two beats but "X" moves... set above
		        	{
		        		//g.drawString(Integer.toString(noteNumber), Xpos, Ypos);
		        		g.drawImage(iTwoNotes, Xpos, Ypos, null);	// g.fillOval(Xpos, Ypos, 20, 10);				// arguments x, y upper left corner and width and height
		        	}
		            if (noteNumber == 33)
		            	g.drawImage(iTwoNotes, Xpos, Ypos-20, null);	//g.fillOval(Xpos, Ypos-20, 20, 10);
		     
		            if (noteNumber == 34 || noteNumber == 35)
		            	g.drawImage(iTwoNotes, Xpos, Ypos-40, null);	//g.fillOval(Xpos, Ypos-40, 20, 10);
		            
		        	if (noteNumber == 36)
		        		g.drawImage(iTwoNotes, Xpos, Ypos-40, null);	//g.fillOval(Xpos, Ypos-40, 20, 10);
		        	
		        	if (noteNumber == 37)
		        		g.drawImage(iTwoNotes, Xpos, Ypos-20, null);	//g.fillOval(Xpos, Ypos-20, 20, 10);
		        	
		        	if (noteNumber == 38 || noteNumber == 39)
		        		g.drawImage(iTwoNotes, Xpos, Ypos, null);	//g.fillOval(Xpos, Ypos, 20, 10);
		        	
		        	
		        	if (noteNumber == 40 || noteNumber == 41 || noteNumber == 42)
		        		g.drawImage(iTwoNotes, Xpos, Ypos-20, null);	//g.fillOval(Xpos, Ypos-20, 20, 10);
		        	
		        	if (noteNumber == 43 || noteNumber == 44 || noteNumber == 45 || noteNumber == 46 || noteNumber == 47)
		        		g.drawImage(iTwoNotes, Xpos, Ypos, null);	//g.fillOval(Xpos, Ypos, 20, 10);
		        	
		        	if (noteNumber == 48 || noteNumber == 49)			// these 3 are skipped for last section
		        		g.drawImage(iTwoNotes, Xpos, Ypos, null);	//g.fillOval(Xpos, Ypos, 20, 10);
		        	
		        	if (noteNumber == 50 || noteNumber == 51)
		        		g.drawImage(iTwoNotes, Xpos, Ypos-20, null);	//g.fillOval(Xpos, Ypos-20, 20, 10);		// hold this through 3
		        	
		        	if (noteNumber == 52 || noteNumber == 53 || noteNumber == 54)
		        		g.drawImage(iTwoNotes, Xpos, Ypos-40, null);	//g.fillOval(Xpos, Ypos-40, 20, 10);		// hold this through 3
		        	
		        	

		        	
		        	if (noteNumber == 82 || noteNumber == 83 || noteNumber == 84)	// hold for two beats but "X" moves... set above
		        		g.drawImage(iTwoNotes, Xpos, Ypos, null);	//g.fillOval(Xpos, Ypos, 20, 10);
		        	
		        	
		        	if (noteNumber == 85 || noteNumber == 86 || noteNumber == 87 || noteNumber == 88)
		        		g.drawImage(iTwoNotes, Xpos, Ypos-20, null);	//g.fillOval(Xpos, Ypos-20, 20, 10);

		        	
		        	if (noteNumber == 97 || noteNumber == 98 || noteNumber == 99)	// hold for two beats but "X" moves... set above
		        		g.drawImage(iTwoNotes, Xpos, Ypos, null);	//g.fillOval(Xpos, Ypos, 20, 10);
		        	
		        	
		        	if (noteNumber == 100 || noteNumber == 101 || noteNumber == 102 || noteNumber == 103)
		        		g.drawImage(iTwoNotes, Xpos, Ypos-20, null);	//g.fillOval(Xpos, Ypos-20, 20, 10);
		        	
		        	
		        	
		           // dispose after display       
		           
		        }   
		        
		        
		        // the bass line
		        public static void drawSound3BassLine(Graphics g, int colorNum)
		        {
		        	int Xpos = 0;	// needed for changes
		        	int Ypos = 0;
		        	
		        	g.setColor(Color.BLACK);
		        	
		        	Image iNote = Toolkit.getDefaultToolkit().getImage("noteClear.png");
			        // g.drawImage(iNote, 100, 200, null);	// image, x, y, observer
		        	    	
		        	int noteNumber = colorNum;
		        	
		        	if(colorNum < 49)					// at 48, Xpos needs to start over at the left margin
		        		Xpos = 50 + (10 * colorNum);	// needs left margin for 1 and 2, then + 10 until 7 and 8, then reset, colorNum goes to 106
		        	else
		        		Xpos = 50 + (10 * (colorNum - 48));	// start over after getting to the end of X
		        	
		        	
		        	// Ypos for the bottom C Cleff is 400
		        	// at 48, Ypos needs to go down to the bottom C cleff
		        	
		        	if(colorNum < 49)
		        		Ypos = 230;		// bass line position was 250
		        	else
		        		Ypos = 385;		// was 405
		        	
		        	
		        	// Image that moves across the window
		        	// First, declare the variable
		        	
		        	Image imageSnoop;
		        	// First image is until upper part starts at 34
		       		imageSnoop = Toolkit.getDefaultToolkit().getImage("snoopy5.png");
		        	
		        	// Second image is 34 to second upper part
		        	if((noteNumber > 30) && (noteNumber < 64))
		        		imageSnoop = Toolkit.getDefaultToolkit().getImage("snoopy4.png");
		        	
		        	// Third image is second upper part until sustain 
		        	if(noteNumber > 63 && noteNumber < 100)
		        		imageSnoop = Toolkit.getDefaultToolkit().getImage("snoopy2.png");
		        	
		        	
		        	if (noteNumber > 8)		// the sequence is 1.. 45678
		        		noteNumber = noteNumber % 8;
		        	
		        //	if(noteNumber == 0 && colorNum != 0)	// keeps zero from painting before start and at end
		        //		noteNumber = 1;
		        	
		        	
		        	
		        	if(colorNum > 0 && colorNum < 103)
		        		g.drawImage(imageSnoop, Xpos-70, 20, null);	// image, x, y, observer


		        	
		        	if (noteNumber == 1 || noteNumber == 2)					// 2 beats on first note
		        		g.drawImage(iNote, Xpos, Ypos, null);	// g.fillOval(Xpos, Ypos, 20, 10);		// arguments x, y upper left corner and width and height
		        	
		        	if (noteNumber == 3)
		        		g.drawImage(iNote, Xpos, Ypos-20, null); // g.fillOval(Xpos, Ypos-20, 20, 10);		// Ypos starts at 400 then 380, 360, 400, 360, 380
		        	
		        	if (noteNumber == 4)
		        		g.drawImage(iNote, Xpos, Ypos-40, null); //g.fillOval(Xpos, Ypos-40, 20, 10);
		        	
		        	if (noteNumber == 5)
		        		g.drawImage(iNote, Xpos, Ypos, null); //g.fillOval(Xpos, Ypos, 20, 10);
		        	
		        	if (noteNumber == 6)
		        		g.drawImage(iNote, Xpos, Ypos-20, null); //g.fillOval(Xpos, Ypos-20, 20, 10);
		        	
		        	if (noteNumber == 7 || noteNumber ==8)
		        	{
		        		g.drawImage(iNote, Xpos, Ypos-40, null); //g.fillOval(Xpos, Ypos - 40, 20, 10);
		        		//g.drawString(Integer.toString(noteNumber), Xpos, Ypos-40);
		        	}
		        	
		        }
		        
		        
		        
		        public static void drawSound4(Graphics g, int colorNum)
		        {         
		        	// Background image
		        	Image image = Toolkit.getDefaultToolkit().getImage("musicImage4.png");
		        	g.drawImage(image, 0, 0, null);	// image, x, y, observer
		        		//g.finalize();
		        }
		        
		        
		        
		        
		    	private class TimerListener implements ActionListener
		        {
		        	public void actionPerformed(ActionEvent ae)
		        	{
		        		System.out.println ("In timer listener now and colorNum is " + colorNum);
		        		mainFrame.repaint();
		        		                       			
		        	}
		        };
		        	
		        	Timer myTimer = new Timer(175, new TimerListener());	// tried 180 is close, but
		        	
		        	
		        	
		        	public static void playSound(String soundName)
		            {
		              try 
		              {
		               AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
		               Clip clip = AudioSystem.getClip();
		               clip.open(audioInputStream);
		               clip.start();
		                        
		              }
		              catch(Exception ex)
		              {
		                System.out.println("Error with playing sound.");
		                ex.printStackTrace( );
		              }
		            }

		        	
		        		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			new musicPlay();
		}

	}
