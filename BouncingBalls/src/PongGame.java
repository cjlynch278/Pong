import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

@SuppressWarnings("serial")

/*
 * PongGame is the panel that hosts the game of pong. It will call create all of the necessary threads
 *  and paint all of the components.
 * 
 */
public class PongGame extends JPanel {


	private static final int diameter;
	private static final int paddleH;
	private static final int paddleW;
	private static final int ballSpeedX;
	private static int change;
	
	
	int count = 0;
	PaddleMechs paddleMechs;
	Paddle paddle;
	Paddle AIPaddle;
	BouncyBall ball;
	int offset =-10;
	int width;
	int height;
	BouncyBallFrame bbf;
	double difficulty;
	static {
		diameter = 10;
		paddleW = 140;
		paddleH = 15;
		ballSpeedX = 3;
		change = 40;
	}
	
	public PongGame(int w, int h, double d, BouncyBallFrame b) {
		width = w;
		height = h;
		difficulty = d;
		bbf = b;
	}
	
	public void makeObjects() {
		Thread thread;
		Thread pThread;
		Thread mThread;
		Thread pmThread;
		
		//Create all of the objects and their threads
		paddle = new Paddle(0,0,ballSpeedX,paddleW, paddleH, this);
		ball =new BouncyBall(getWidth()/2, 40,0, 5, diameter, this);
		AIPaddle= new Paddle(getWidth()/2 - (paddleW/2), getHeight()-paddleH,3, paddleW,paddleH,this);
		paddleMechs = new PaddleMechs(this, ball, paddle,AIPaddle, paddleH, paddleW, diameter, getHeight(), difficulty, width, bbf);

		
		// Create a new thread for this bouncing ball
		thread = new Thread(ball);
		pThread = new Thread(paddle);
		mThread = new Thread(AIPaddle);
		pmThread = new Thread(paddleMechs);
		
		// Start the bouncing thread
		thread.start();
		pThread.start();
		mThread.start();
		pmThread.start();
	
	}
	//Paint all of the components of the screen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ball.draw(g);
		paddle.draw(g);
		AIPaddle.draw(g);
		
	}
	
	//Creates boundaries for the players paddle, so it doesn't go beyond the frame's sides
	public void boundaries(Paddle paddle){
		if(paddle.getX()+paddleW>= width){
			paddle.setX(width- paddleW);
		}
		if(paddle.getX() <= 0){
			paddle.setX(0);
		}
	}
	
	//Moves Player Paddle
	public void moveMyPaddle(char key){
		if(key == 'd'){
			paddle.setX(paddle.getX()+change);
		}
		else if(key == 'a'){
			paddle.setX(paddle.getX() -change);
		}
		boundaries(paddle);
	}

}
	
