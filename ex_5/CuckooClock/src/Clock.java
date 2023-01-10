import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/*
 * @author Efraim Vishnevetsky and Shon Dayan
 * 
 * Simple animation of an abstract Cuckoo clock
 **/
public class Clock extends JPanel implements ActionListener
{
	private Timer timer; 
	private Image bird; // JPEG image of a bird
	private int seconds; // counter representing seconds passed since start of program
	private Clip koo = null; // audio clip of clock vocalization
	
	/*Constructor*/
	public Clock()
	{
		try
		{
			this.bird = ImageIO.read(new File("Bird.jpg"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			this.koo = AudioSystem.getClip();
		}
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}
		try
		{
			this.koo.open(AudioSystem.getAudioInputStream(new File("CuckooCall.wav")));
		}
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		this.bird = this.bird.getScaledInstance(50, 50, 0);
		this.timer = new Timer(1000,this);
		this.seconds = 0;
		this.timer.start();
	}
	
	/*Draws red triangle representing clock roof
	 * 
	 * @param g - graphics object*/
	public void drawRoof(Graphics2D g)
	{
		int[] xCoords = {0,150,300};
		int[] yCoords = {100,0,100};
		g.setColor(Color.red);
		g.fillPolygon(xCoords, yCoords, 3);
	}
	
	/*Draws green rectangle representing clock body
	 * 
	 * @param g - graphics object*/
	public void drawBody(Graphics2D g)
	{
		g.setColor(Color.green);
		g.fillRect(50, 100, 200, 150);
	}
	
	/*Draws pendulum in left position
	 * 
	 * @param g - graphics object*/
	public void drawPendulumLeft(Graphics2D g)
	{
		g.setColor(Color.black);
		g.drawLine(100, 250, 50, 375);
		g.fillOval(30, 375, 30, 30);
	}
	
	/*Draws pendulum in right position
	 * 
	 * @param g - graphics object*/
	public void drawPendulumRight(Graphics2D g)
	{
		g.setColor(Color.black);
		g.drawLine(200, 250, 250, 375);
		g.fillOval(240, 375, 30, 30);
	}
	
	/*Draws an image representing cuckoo
	 * 
	 * @param g - graphics object*/
	public void drawBird(Graphics2D g)
	{
		g.drawImage(this.bird, 125, 120, this);
	}
	
	/*Handles painting events
	 * 
	 * @param g - graphics object*/
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.white);
	    this.drawRoof((Graphics2D)g);
	    this.drawBody((Graphics2D)g);
	    if(this.seconds %2 == 1) drawPendulumLeft((Graphics2D)g);
	    else drawPendulumRight((Graphics2D)g);
	    if(this.seconds %8 == 0 && this.seconds != 0)
	    {
	    	drawBird((Graphics2D)g);
	    	this.koo.stop();
	    	this.koo.setFramePosition(0);
	    	this.koo.start();
	    }
	}
	
	/*Main loop for program
	 * 
	 * @param e - ActionEvent*/
	@Override
	public void actionPerformed(ActionEvent e)
	{	
		this.seconds++;
		System.out.println(this.seconds);
		this.repaint();
	}
	
}
