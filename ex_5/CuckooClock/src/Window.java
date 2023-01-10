import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;

/*
 * @author Efraim Vishnevetsky and Shon Dayan
 * 
 * Main JFrame for program GUI
 **/
public class Window extends JFrame
{
	/*Constructor*/
	public Window()
	{
		Dimension d = new Dimension(400,500);
		Clock clock = new Clock();
		this.setPreferredSize(d);
		clock.setSize(300, 405);
		this.add(clock);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
}
