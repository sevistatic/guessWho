/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guesswho;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Spencer
 */
public class InstructionButton extends JButton implements GuessObject{
    
    JFrame instructionsWindow;
    JPanel messageLabel;
    
    InstructionButton(String message){
        super(message);
        
    }
    
    public void doStuff(int x, int y, int button){
        
                instructionsWindow = new JFrame("Instructions");
                messageLabel = new JPanel();
                messageLabel.setLayout(new GridLayout(4, 1));
                //messageLabel.setBackground(Color.ORANGE);
                messageLabel.add(new JLabel("Right-click  or double-click a FEATURE to deselect it."), 0);
                messageLabel.add(new JLabel("Right-click  or double-click a CARD to turn it face-down."), 0);
                messageLabel.add(new JLabel("Left-click a CARD to select or deselect it."), 0);
                messageLabel.add(new JLabel("Left-click a FEATURE to select it."), 0);
                
                instructionsWindow.add(messageLabel);
                instructionsWindow.setLocation(200, 500);
                instructionsWindow.setSize(450, 150);
                //dookieWindow.setLocation(x, y);
                instructionsWindow.setVisible(true);
    }
    
    public String toString(){
        return "Button: Instructions";
    }
}
