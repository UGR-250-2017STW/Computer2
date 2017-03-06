package bounceBallPackage;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;



public class bounceBall extends JApplet{
	
	
	private final int X = 109;
	private final int WIDTH = 40;
	private final int HEIGHT = 40;
	private final int TIME_DELAY = 50;
	private final int MOVE = 20;
	private final int MINIMUM_Y = 50;
	private final int MAXIMUM_Y = 400;
	private int y = 400;
	private boolean goingUp = true;
	private Timer timer;
	
	
	public void init(){
		timer = new Timer(TIME_DELAY, new TimerListener());
		timer.start();
	}
	
	public void paint(Graphics g){
		super.paint(g);;
		
		g.setColor(Color.black);
		g.fillOval(X,  y,  WIDTH,  HEIGHT);
		
		g.setColor(Color.blue);
		g.fillOval(X+50,  y,  WIDTH,  HEIGHT);
		
		g.setColor(Color.cyan);
		g.fillOval(X+100,  y,  WIDTH,  HEIGHT);
		
		g.setColor(Color.darkGray);
		g.fillOval(X+150,  y,  WIDTH,  HEIGHT);
		
		g.setColor(Color.gray);
		g.fillOval(X+200,  y,  WIDTH,  HEIGHT);
		
		g.setColor(Color.green);
		g.fillOval(X+250,  y,  WIDTH,  HEIGHT);
		
		g.setColor(Color.lightGray);
		g.fillOval(X+300,  y,  WIDTH,  HEIGHT);
		
		g.setColor(Color.magenta);
		g.fillOval(X+350,  y,  WIDTH,  HEIGHT);
		
		g.setColor(Color.orange);
		g.fillOval(X+400,  y,  WIDTH,  HEIGHT);
		
		g.setColor(Color.pink);
		g.fillOval(X+450,  y,  WIDTH,  HEIGHT);
		
		g.setColor(Color.red);
		g.fillOval(X+500,  y,  WIDTH,  HEIGHT);
		
		g.setColor(Color.white);
		g.fillOval(X+550,  y,  WIDTH,  HEIGHT);
		
		g.setColor(Color.yellow);
		g.fillOval(X+600,  y,  WIDTH,  HEIGHT);
		
		
	}
	
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(goingUp)
			{
				if(y > MINIMUM_Y)
					y = y - MOVE;
				else
					goingUp = false;
			}
			else
			{
				if(y < MAXIMUM_Y)
					y = y + MOVE;
				else
					goingUp = true;
			}
			repaint();
		}
	}
	

}
