import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class BouncyBallFrame extends JFrame implements KeyListener {

	BouncyBallsPanel panel;
	 
	
	public BouncyBallFrame (int x, int y, int width, int height, int difficulty) {
	
		JTextField typingArea = new JTextField(20);
		typingArea.addKeyListener(this);
		add(typingArea,BorderLayout.SOUTH);
		
		setSize(width, height);
		setLocation(x, y);
		
		//Set up all of the objects
		panel = new BouncyBallsPanel(width,height,difficulty);
		add(panel, BorderLayout.CENTER);
		setVisible(true);
		panel.makeObjects();
		panel.paddleCollision();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

