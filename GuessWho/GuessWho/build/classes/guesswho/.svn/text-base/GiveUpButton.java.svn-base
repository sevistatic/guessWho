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
public class GiveUpButton extends JButton implements GuessObject{
    
    GiveUpButton(String message){
        super(message);
        
    }
    
    public void doStuff(int x, int y, int Button){
        
                
                if (JOptionPane.showConfirmDialog(null, "Are you absolutely sure?") == JOptionPane.OK_OPTION){
                JOptionPane.showMessageDialog(null, "The answer was " + Background.target.getCharacter().getName());
                //Background.deductTrialPoints(Background.getTrialPoints());
                Background.reset();
                }
    }
    
    public String toString(){
        return "Button: Give Up";
    }
}
