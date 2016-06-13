import java.util.Scanner;
import javax.swing.JFrame;

public class Application {	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		JFrame window,first;
		boolean error = true;
		
		//Retrieve a suitable input
		while(error == true){
			try{
				Scanner scan = new Scanner(System.in);
				System.out.println("What difficulty would you like(1 - 4 where 4 is the hardest): ");
				int input = scan.nextInt();

				if(input<=0 || input>=5){
					System.out.println("Please enter a number between 1 - 4");
				}
				else{
					error = false;
					first  = new BouncyBallFrame(700,0,600,600,input);
					scan.close();
			}
		}
		catch(Exception e){
			System.out.println("Error: Please enter a number");	
		}
		}	
	}
}

