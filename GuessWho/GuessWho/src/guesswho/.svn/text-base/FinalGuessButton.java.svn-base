/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guesswho;

import javax.swing.*;

/**
 *
 * @author Spencer
 */
public class FinalGuessButton extends JButton implements GuessObject {

    Character person;

    FinalGuessButton(String message, Character c) {
        super(message);
        person = c;

    }

    public void doStuff(int x, int y, int Button) {
        if (JOptionPane.showConfirmDialog(null, "Are you absolutely sure?") == JOptionPane.YES_OPTION) {
            // System.out.println("target = " + person.getName());
            if (Background.selected != null) {
                //   System.out.println("selected = " + Background.selected.getCharacter().getName());
                Background.outFile.println("Final Guess, Player Answer: " + Background.selected.getCharacter().getName());

                if (Background.selected.getCharacter() == person) {
                    JOptionPane.showMessageDialog(null, "CORRECT!! The answer was " + person.getName());
                    Background.outFile.println("Correct Answer: " + Background.target.getCharacter().getName());
                    Background.increaseTotalPoints(Background.getTrialPoints());

                    if (Background.getTrialPoints() >= 20) {
                        Background.increaseTotalPoints(Background.getTrialPoints() * .5 );
                    }
                    Background.reset();
                } else {
                    JOptionPane.showMessageDialog(null, "WRONG!! The answer was " + person.getName());
                    Background.outFile.println("Correct Answer: " + Background.target.getCharacter().getName());
                    //Background.deductTrialPoints(Background.getTrialPoints());
                    Background.reset();
                }
            } else {

                Background.outFile.println("Final Guess, Player Answer: None Selected");
                JOptionPane.showMessageDialog(null, "WRONG!! The answer was " + person.getName());
                Background.outFile.println("Correct Answer: " + Background.target.getCharacter().getName());
                //Background.deductTrialPoints(Background.getTrialPoints());
                Background.reset();
            }
            person = Background.target.getCharacter();
        }
    }

    public String toString() {
        return "Button: Final Guess";
    }
}
