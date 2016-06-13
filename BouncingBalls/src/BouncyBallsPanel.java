import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BouncyBallsPanel extends JPanel {

	private static Random oracle;
	private static final int diameter;
	private static final int max_speed;
	private static final int paddleH;
	private static final int paddleW;
	private static final int ballSpeedX;
	private static  double difficulty;
	private static int change;
	
	int count = 0;
	Paddle paddle;
	Paddle AIPaddle;
	BouncyBall ball;
	int offset =-10;
	int width;
	int height;


	static {
		max_speed = 5;
		diameter = 10;
		oracle = new Random();
		paddleW = 140;
		paddleH = 15;
		ballSpeedX = 3;
		difficulty =  .6;
		change = 40;
		
	}
	
	public BouncyBallsPanel(int w, int h, int d) {
		width = w;
		height = h;
		
		//Determines how responsive the AI will be
		switch(d){
			case 1:
				difficulty = .5;
				break;
			case 2:
				difficulty = .6;
				break;
			case 3:
				difficulty = .7;
				break;
			case 4:
				difficulty = .8;
				break;
			default:
				System.out.println("Error: Wrong difficulty entry, you entered " + d);
		}
	}
	
	public void makeObjects() {
		Thread thread;
		Thread pThread;
		Thread mThread;
		
		//Create all of the objects and their threads
		paddle = new Paddle(0,0,ballSpeedX,paddleW, paddleH, this);
		ball =new BouncyBall(getWidth()/2, 40,0, 5, diameter, this);
		AIPaddle= new Paddle(getWidth()/2 - (paddleW/2), getHeight()-paddleH,3, paddleW,paddleH,this);
		
			// Create a new thread for this bouncing ball
			thread = new Thread(ball);
			pThread = new Thread(paddle);
			mThread = new Thread(AIPaddle);
			// Start the bouncing thread
			thread.start();
			pThread.start();
			mThread.start();
			try{
				//Create a delay, so that the user can click on the correct window in time
				double previousSpeedY = ball.getSpeedY();
				ball.setSpeedY(0);
				thread.sleep(2500);
				ball.setSpeedY(previousSpeedY);
			}
			 catch (InterruptedException caught) {
					System.out.println("Ball stopped.");
				}
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ball.draw(g);
		paddle.draw(g);
		AIPaddle.draw(g);
		
	}
	
	//Determines the mechanics of the AI
	public void trackBall(){
		
		AIPaddle.setSpeed((double)(ball.getSpeedX()));
		//This if statement caps the max speed of the AI paddle. This is what gives the game "Difficulty"
		if(AIPaddle.getSpeed()> difficulty)
			AIPaddle.setSpeed(difficulty);
		else if (AIPaddle.getSpeed() < -difficulty)
			AIPaddle.setSpeed(-difficulty);
		
		
		//Stops the AI paddle when it is under the ball
		if((ball.getX())>(AIPaddle.getX() - offset) && ball.getX()< (AIPaddle.getX()+paddleW + offset)){
			AIPaddle.setSpeed(0);	
		}
		else if((ball.getX()<AIPaddle.getX() - offset) && ball.getSpeedX()>0){
			AIPaddle.setSpeed(0);
			}
		else if((ball.getX() > AIPaddle.getX() + paddleW + offset) && ball.getSpeedX() <0){
			AIPaddle.setSpeed(0);
		}
		else
			AIPaddle.setX((AIPaddle.getX() + AIPaddle.getSpeed()));
			
		//Stops the paddle from travelling beyond the sides
		boundaries(AIPaddle);
	}

	public void moveMyPaddle(char key){
		if(key == 'd'){
			paddle.setX(paddle.getX()+change);
			
		}
		else if(key == 'a'){
			paddle.setX(paddle.getX() -change);
		}
		boundaries(paddle);
		}

	public void paddleCollision(){
	  try{
		while(true){
			Thread.sleep(1);
			trackBall();
		
			//AIPaddle
			if(ball.getY()<= paddleH && paddle.getX()<=ball.getX()&& ball.getX()<=(paddle.getX()+ paddle.getWidth())){
				
				ball.setSpeedX(ballHit(paddleW, paddle.getX(), ball.getX()));
			    ball.setSpeedY(-ball.getSpeedY());
				
				Thread.sleep(25);
			}
			if(ball.getY()>=(getHeight()-paddleH-diameter)&& AIPaddle.getX()<=ball.getX()&& ball.getX()<=(AIPaddle.getX()+ AIPaddle.getWidth()) ){
			
				ball.setSpeedX(ballHit(paddleW, AIPaddle.getX(), ball.getX()));
				ball.setSpeedY(-ball.getSpeedY());
				Thread.sleep(25);
				
				}
			if(ball.getY()>=getHeight()-paddleH){
				System.out.println("YOU WIN!");
				ball.stop();
				break;
				
			}
			else if( ball.getY()<0){
				System.out.println("YOU LOSE!");
				ball.stop();
				break;
				
			}
		
			}
	
	  } catch (InterruptedException caught) {
		System.out.println("Ball stopped.");
	}
	
}
	public void boundaries(Paddle paddle){
		if(paddle.getX()+paddleW>= width){
			paddle.setX(width- paddleW);
		}
		if(paddle.getX() <= 0){
			paddle.setX(0);
		}
	}
	public double ballHit( double paddleW, double paddleX , double ballXCoord ){
		double center = paddleX + paddleW/2;
		double placement = ballXCoord - center;
		double ballAngle= placement/10;
		
		return ballAngle;
		 
	 }
	}
	