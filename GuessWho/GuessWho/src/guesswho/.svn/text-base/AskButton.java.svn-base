/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guesswho;

import java.awt.Color;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author Spencer
 */
public class AskButton extends JButton implements GuessObject {

    ArrayList<JList> listList;

    AskButton(String message, ArrayList<JList> ll) {
        super(message);
        listList = ll;
    }

    public void doStuff(int x, int y, int button) {
        //actually input the guess 
        Background.outFile.println("Query submitted:");
        int correct = areAllCorrect();
        if (correct == 1) {
            Background.deductTrialPoints(10);
            Background.setAnswer(1);
        } else if (correct == 0) {
            Background.deductTrialPoints(10);
            Background.setAnswer(0);
        } else {
            Background.setAnswer(-1);
        }
        for (int i = 0; i < Background.jListList.size(); i++) {
            Background.jListList.get(i).clearSelection();
        }
    }

    public int areAllCorrect() {
        int unchanged = 0;
        for (int i = 0; i < Background.jListList.size(); i++) {
            if (!Background.jListList.get(i).isSelectionEmpty()) {
                //if it was changed
                if (!Background.jListList.get(i).getSelectedValue().toString().equals(Background.target.getCharacter().getFeatures().get(i))) {
                    //is it wrong?
                    Background.outFile.println("No Match for " + Background.jListList.get(i).getSelectedValue().toString());
                    return 0;
                } else {
                    //must be a match for this one feature at least
                    Background.outFile.println("Match for " + Background.jListList.get(i).getSelectedValue().toString());
                }
            } else {
                unchanged++;
            }
        }
        if (unchanged == Background.jListList.size()) {
            Background.outFile.println("Nothing Selected");
            return -2;
        }
        //must be entirely correct
        Background.outFile.println("Selected Characteristics Match");
        return 1;
    }

    public String toString() {
        return "Button: Query";
    }
}
