import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JTextField;


@SuppressWarnings("serial")

/*
 * BouncyBallFrame creates the frame for the game. This class will hold all of the panels, and 
 * 		organizes them all. It will determine which panel to display.
 */
public class BouncyBallFrame extends JFrame implements KeyListener {

	PongGame panel;
	int width;
	int height;
	int difficulty;
	EndGamePanel endGamePanel;
	Instructions instructions;
	
	
	public BouncyBallFrame (int x, int y, int w, int h, int d) {

		//Creates all of the properties of the frame
		
		//Set the dimensions of the frame
		width = w;
		height = h;
		difficulty = d;
		setLocation(x, y);
		setSize(width, height);
		
		//The typing area is needed to move the players paddle
		JTextField typingArea = new JTextField(20);
		typingArea.addKeyListener(this);
		add(typingArea,BorderLayout.SOUTH);
		
		//Create the home panel and add that to the frame, this will be the 
		// first panel
		Home home = new Home(this);
		setVisible(true);
		add(home);
		home.setVisible(true);

		//Make the player unable to change the dimensions of the window
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	//Calls the necessary methods to start the game of pong.
	public void startPong(double difficulty) {
		panel = new PongGame(width,height,difficulty, this);
		add(panel, BorderLayout.CENTER);
		setVisible(true);
		panel.makeObjects();
	}
	
	//Creates the end game panel and displays it
	public void endGame(char result,double difficulty) {
		endGamePanel = new EndGamePanel(difficulty,result, this);
		add(endGamePanel, BorderLayout.CENTER);
		setVisible(true);
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//Move the paddle in a certain direction
		panel.moveMyPaddle(e.getKeyChar());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}

