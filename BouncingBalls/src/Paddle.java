import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Paddle implements Runnable {
	private double x;
	private int y;
	private double speed_x;
	private int width;
	private int height;
	private JPanel panel;

	public Paddle(int x, int y, double speed_x, int width, int height, JPanel panel) {
		this.x = x;
		this.y = y;
		this.speed_x = speed_x;
		this.width = width;
		this.height = height;
		this.panel = panel;
	}
	
	//Draw the paddle
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)x, y,width, height);
		
	}
	public int getWidth(){
		return width;
	}
	public double getX(){
		return x;
	}
	public int getHeight(){
		return height;
	}
	public void setX(double num){
		x = num;
	}
	public void setSpeed(double num){
		speed_x = num;
	}
	public double getSpeed(){
		return speed_x;
	}
	
	//Implement the runnable method
	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(10);
				panel.repaint();
			}
		} catch (InterruptedException caught) {
			System.out.println("Ball stopped.");
		}
	}
}

	


