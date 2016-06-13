import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


//This class sets up the ball
public class BouncyBall implements Runnable {
	private double x;
	private double y;
	private double speed_x;
	private double speed_y;
	private int diameter;
	private JPanel panel;

	public BouncyBall(double x, double y, double speed_x, double speed_y, int radius, JPanel panel) {
		this.x = x;
		this.y = y;
		this.speed_x = speed_x;
		this.speed_y = speed_y;
		this.diameter = radius;
		this.panel = panel;
	}

	//Draws the ball with the given dimensions
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillOval((int) x, (int) y, diameter, diameter);
	}
	
	//Creates the bouncing off of the side walls
	private void collision() {
		
		  if (0 > y || y > panel.getHeight() - diameter) {
			speed_y = -speed_y;
		}
		
		 
		if (0 > x || x > panel.getWidth() - diameter) {
			speed_x = -speed_x;
		}
		
	}
	//Stops the ball
	public void stop(){
		speed_x = 0;
		speed_y = 0;
	}
	//Getters and Setters
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getSpeedY(){
		return speed_y;
	}
	public void setSpeedY(double speedY){
		speed_y = speedY;
	}
	public void setY(double integer){
		y=integer;
	}
	public double getSpeedX(){
		return speed_x;
	}
	public void setSpeedX(double number){
		speed_x = number;
	}
	
	//Starts and runs the ball
	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(10);
				collision();
				x = x + speed_x;
				y = y + speed_y;
				panel.repaint();
			}
		} catch (InterruptedException caught) {
			System.out.println("Ball stopped.");
		}
	}
}
