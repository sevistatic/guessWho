/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guesswho;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Spencer
 */
public class GuessWho {
static final int START_TIME = 99;
static int systemStartTime;
static final boolean PARTIAL_INFO = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame gameFrame = new JFrame("Spencer's Guess Who Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        gameFrame.setSize(width, height);
        try{
        Background background = new Background(gameFrame.getWidth(), gameFrame.getHeight());
        BorderLayout g = new BorderLayout();
        background.setLayout(g);
        background.init();
        gameFrame.add(background);
        
        
        gameFrame.setVisible(true);
        systemStartTime = (int)(System.currentTimeMillis() / 1000);
        //background.setup();
        boolean b = false;
        do{
            pause(40);
            //background.updateTimer(START_TIME - ((int)(System.currentTimeMillis() / 1000) - systemStartTime));
            background.repaint();
            if (!b){
                 JOptionPane.showMessageDialog (null, 
                "Guess the character the computer chose by asking questions about the character's features using the bar at the left and the \"ASK\" button."
                + "\nEach time you ask, 10 points will be deducted from your score and if you guess the correct character, your current score will be added to a running total."
                + "\nYou get bonus points for using clever strategies to come up with the answer in only a few guesses."
                + "\nYou can ask about more than one feature at a time, but you will only be given a \"YES\" response if ALL of the features match."
                + "\nClick the \"INFO\" button to learn which features a certain character has."
                + "\nLeft-click to select a character and right-click to turn them over."
                );
                 b = true;
            }
        } while(b);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        
    }//end method main
        
    /**
     * Pauses the game for a specific amount of time (in ms)
     * @param ms the number of milliseconds to pause the game
     */
    public static void pause (int ms) {
        try {
           Thread.sleep (ms);
        } catch (Exception e) {
            e.printStackTrace ();
        }
    } // end method pause
}//end class Main
//to do: log @mouse click: mouse location, object clicked, time
//to do: log @trial end: total trial time
//to do: have drop boxes color, but not disable to reflect a previous choice.
//to do: implement proper radio button use