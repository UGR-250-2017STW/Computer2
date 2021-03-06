package WIN2Package;



import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;



public class tempConverter extends JFrame{

	private JLabel label0, label1, label2;
	private JTextField fahrenheitTemp;
	private JTextField celsiusTemp;
	private JPanel zeroPanel;
	private JPanel fpanel;
	private JPanel cpanel;
	private JPanel sliderPanel;
	private JSlider slider; 
	
	public tempConverter()
	{
		setTitle("Temperatures");		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		label0 = new JLabel(" ");
		label1 = new JLabel("Fahrenheit: ");
		label2 = new JLabel("Celsius: ");
		
		
		fahrenheitTemp = new JTextField("32.0", 10);
		fahrenheitTemp.setEditable(false);
		celsiusTemp = new JTextField("0.0", 10);
		celsiusTemp.setEditable(false);
		
		slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(new SliderListener());
		
		zeroPanel = new JPanel();
		fpanel = new JPanel();
		fpanel.add(label1);
		fpanel.add(fahrenheitTemp);
		
		fpanel.setPreferredSize(new Dimension(300,60));
		
		cpanel = new JPanel();
		cpanel.add(label2);
		cpanel.add(celsiusTemp);
		
		
		
		sliderPanel = new JPanel();
		sliderPanel.add(slider);
		
		setLayout(new GridLayout(4,1));
		
		
		add(zeroPanel);
		add(fpanel);
		add(cpanel);
		add(sliderPanel);
		
		pack();
		
		this.setLocation(600, 300);
		
		setVisible(true);
	}
	
	
	private class SliderListener implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
			double fahrenheit, celsius;
			
			celsius = slider.getValue();
			
			fahrenheit = (9.0 / 5.0) * celsius + 32.0;
			
			celsiusTemp.setText(Double.toString(celsius));
			
			fahrenheitTemp.setText(String.format("%.1f", fahrenheit));
			
			
			
		}
		
		
	}
	
	
}
