package guesswho;
import java.awt.*;
import javax.swing.*;

/**
 * @author Spencer Bryant
 */
public class GuessWho {
private static final String startInfo = "Guess the character the computer chose by "
+ "\nasking questions about the character's features using the bar at the left "
+ "\nand the \"ASK\" button."
+ "\n"
+ "\nEach time you ask, one point will be deducted from your score and if you "
+ "\nguess the correct character, your current score will be added to a running total."
+ "\nYou get bonus points for using clever strategies to come up with the answer "
+ "\nin only a few guesses."
+ "\n"
+ "\nYou can ask about more than one feature at a time, but you will only be "
+ "\ngiven a \"YES\" response if ALL of the features match."
+ "\n"
+ "\nClick the \"CARD INFO\" button to learn which features a certain character has."
+ "\nLeft-click to select a character and right-click to turn them over.";

public static Logger logger;
public static Trial trial;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame gameFrame = new JFrame("Spencer's Guess Who Game");
        trial = new Trial();
        logger = new Logger();

        //Set up game window
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        gameFrame.setSize(width, height);

        try{
        //adds game objects to game window
        Background background = new Background(gameFrame.getWidth(), gameFrame.getHeight());
        gameFrame.add(background);
        gameFrame.setVisible(true);

        boolean dialogOpen = true;
        //main game loop
        do{
            pause(40);
            background.repaint();
            if (dialogOpen){
                JOptionPane.showMessageDialog (null, startInfo);
                dialogOpen = false;
            }
        } while(true);//exit flag
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }//end method main

    /**
     * Pauses the game for a specific amount of time (in ms)
     * @param ms the number of milliseconds to pause the game
     */
    public static void pause (int milliseconds) {
        try {
           Thread.sleep (milliseconds);
        } catch (Exception e) {
            e.printStackTrace ();
        }
    } // end method pause
}//end class Main
