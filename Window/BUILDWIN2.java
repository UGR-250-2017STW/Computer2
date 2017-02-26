package WIN2Package;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class BUILDWIN2 extends JFrame{

			
			public static String userTitle;
			
			public static JFrame userFrame = new JFrame();
			
			public static int runFlag = 1;

			
			public static void main(String[] args) {

				startup();

			}// end of main
			
			
			// program start, and is called by tempConverter when the close button is clicked
			public static void startup(){
				
				if(runFlag == 1)	// the run flag keeps it from running until temp converter resets it
				{
					runFlag = 0;
					
				// Announce the program, the arguments are frame, text inside, Title above, message type, icon 
				JOptionPane.showMessageDialog(null, "Lets generate some windows in Java.", 
						"STEMFEST 2017", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(BUILDWIN2.class.getResource("yay_32x40.png")));
				
			//	JFrame userFrame = new JFrame();
				userFrame.setSize(600,500);
				
				// ask for the window title, the arguments are frame, text inside, Title above, message type
				userTitle = JOptionPane.showInputDialog(userFrame, "This is a show input dialog box.\n"
						+ "Java has components like this that can be \n"
						+ "used to gather input.\n\n" + "Enter your first name ", "STEMFEST 2017", JOptionPane.QUESTION_MESSAGE);
				

				userFrame.setTitle(userTitle + "'s Custom Window in Java");
				userFrame.setLocationRelativeTo(null);	// center the window
				userFrame.setBackground(Color .WHITE);
				userFrame.setVisible(true);	

				JComponent component = new JComponent()
				{
					public void paintComponent(Graphics graph)
					{
						draw(graph);					
					}
				};


				userFrame.add(component);
				
				try{
					Thread.sleep(1000);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}

				JOptionPane.showMessageDialog(null,"That window is a frame with some text. \n "
						+ "\n" + "Let's create another window that we can draw on.", 
						"STEMFEST 2017",JOptionPane.INFORMATION_MESSAGE);

				userFrame.setVisible(false);	// HIDE THE MAIN
				


				secondWindow();	// call the second window function
				
				
				userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			
			}
			
			
			
			
			public static void draw(Graphics g)
			{
			
				Font textFont = new Font("Serif", Font.BOLD, 18);
				
				g.setFont(textFont);
				
				g.drawString("Notice the title at the top of window.", 60, 40);	// string, x, y
				g.drawString("It includes the name that you entered in the last window.", 60, 60);
				g.drawString("This is one way that Java captures and uses input.", 60, 80);	// string, x, y
				
				Font myFont = new Font("SERIF", Font.BOLD, 16);	// create a font
				g.setFont(myFont);
				
				g.drawString("The window size is 600 x 500, and is centered on the desktop.", 60, 110);	// string, x, y
				g.drawString("and notice that we can change the font.", 60, 130);	// string, x, y
				
				
			}	
			
			
			public static void secondWindow(){
				
				JFrame userFrame2 = new JFrame();
				userFrame2.setSize(600,600);	// width then height
				
				//JPanel myPanel = new JPanel();
				//userFrame2.add(myPanel);
				//myPanel.setBackground(Color .RED);
				

				userFrame2.setTitle("Drawing on a Frame in Java");
				userFrame2.setLocationRelativeTo(null);	// center the window

				userFrame2.setVisible(true);
				
				JComponent component2 = new JComponent()
				{
					public void paintComponent(Graphics graph)
					{
						draw2(graph);					
					}
				};
				
				
				userFrame2.add(component2);
				
				
				try{
					Thread.sleep(1000);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}

				JOptionPane.showMessageDialog(null,"Now let's look at a window with some interaction built in...", 
						"STEMFEST 2017",JOptionPane.INFORMATION_MESSAGE);

				userFrame2.dispose();
				
				new tempConverter();	// call the converter class window function
				
				
				userFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
			}
			
			
			// The second window which has the diamond or the sine wave depending on the random number
			
				public static void draw2(Graphics g2)	// for second window
				{
				
					// generate a random number, one or two
					// if a one, call drawDiamond, if a two call drawSine
					
					int randNum;
					Random randomNumbers = new Random();
					randNum = randomNumbers.nextInt(2);
					System.out.println("The random number is : " + randNum);
					
					if (randNum == 0)
						drawSine(g2);
					else
					{
						int barHeight = 0; 
						g2.setColor(Color.BLUE);        // for the bar color
						// g.fillRect arguments are x, y, width, height
						
						for (int i = 130; i <= 280; i = i + 15)
						{
							barHeight = barHeight + 20;
							g2.fillRect(i, 225 - barHeight/2, 8, barHeight);					
						}
						
						for (int i = 295; i <= 430; i = i + 15)
						{
							barHeight = barHeight - 20;
							g2.fillRect(i, 225 - barHeight/2, 8, barHeight);					
						}
						
						g2.setColor(Color.BLACK);
								//  x    y   width, Height
						g2.drawRect(110, 75, 350, 300);
					
						// fonts on page 519	
						Font titleFont = new Font("Serif", Font.BOLD, 24);
						
						g2.setFont(titleFont);			// string, x, y
						g2.drawString("Drawing a Diamond with some Text.", 105, 425);
						// g2.drawString(userTitle, 105, 450);
					}
					
				}	
			
				
				
				public static void drawSine(Graphics g2)	// for second window
				{
				
					// Called by draw2 if random number is a two			
								
						final int HEIGHT = 300;
						
						for(int c = 0; c < 360; c= c+5)
						{
							double y = Math.sin(Math.toRadians(c));
							int height = (int)(y*HEIGHT/2);
							if(height > 0)
							{
								g2.fillRect(c+105, (HEIGHT/2 - height) + 50, 3, height);	// args - top left as x,y, width, height
							}
							else
							{
								g2.fillRect(c+105, (HEIGHT/2) + 50,  3,  -height);		// c+105, and height+50 centers the sine wave
								
							}
						}
							
						Font titleFont = new Font("Serif", Font.BOLD, 24);
						
						g2.setFont(titleFont);			// string, x, y
						g2.drawString("Drawing a 360 Degree Sine Wave.", 105, 425);
						// g2.drawString(userTitle, 105, 450);
					
				}	
		}


		
		


