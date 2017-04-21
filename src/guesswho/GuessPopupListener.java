/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guesswho;
import javax.swing.event.*;

/**
 *
 * @author Spencer
 */
public class GuessPopupListener implements PopupMenuListener{

    GuessPopupListener(){
        super();
    }


    @Override
    public void popupMenuCanceled(PopupMenuEvent e) {
        //Background.outFile.println(((FeaturesComboBox)e.getSource()).getSelectedItem());
        //Background.outFile.flush();
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        GuessWho.logger.log("Event: PopupMenu Closed");
        GuessWho.logger.logf("Time: %.3f\n", GuessWho.logger.currentTime());
        //Background.outFile.println("Cursor Location: (" + e.getXOnScreen() + ", " + e.getYOnScreen() + ")");
        GuessWho.logger.log("Object Clicked: " + e.getSource().toString());
        GuessWho.logger.log(((FeaturesComboBox)e.getSource()).getSelectedItem() + " selected from menu");
        //Background.outFile.println("Button Used: " + e.getButton());
        GuessWho.logger.log("Current Trial: " + GuessWho.trial.getTrialNum());
        GuessWho.logger.log("Current Trial Score: " + GuessWho.trial.getTrialPoints());
        GuessWho.logger.log("Current Total Score: " + GuessWho.trial.getTotalPoints());
        GuessWho.logger.log("-------------------------");
        GuessWho.logger.flush();
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        GuessWho.logger.log("Event: PopupMenu Opened");
        GuessWho.logger.logf("Time: %.3f\n", GuessWho.logger.currentTime());
        //Background.outFile.println("Cursor Location: (" + e.getXOnScreen() + ", " + e.getYOnScreen() + ")");
        GuessWho.logger.log("Object Clicked: " + e.getSource().toString());
        //Background.outFile.println("Button Used: " + e.getButton());
        GuessWho.logger.log("Current Trial: " + GuessWho.trial.getTrialNum());
        GuessWho.logger.log("Current Trial Score: " + GuessWho.trial.getTrialPoints());
        GuessWho.logger.log("Current Total Score: " + GuessWho.trial.getTotalPoints());
        GuessWho.logger.log("-------------------------");
        GuessWho.logger.flush();
    }

}
