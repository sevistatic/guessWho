/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guesswho;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author Spencer
 */
public class InfoButton extends JButton implements GuessObject {

    Character selected;

    InfoButton(String message, Character c) {

        super(message);
        selected = c;

    }

    public void setSelected(Character ch) {
        selected = ch;
    }

    public void doStuff(int x, int y, int Button) {
        if (selected != null) {
            //   System.out.println("selected = " + Background.selected.getCharacter().getName());
            Background.outFile.println("Request Info, Character: " + selected.getName());
            JFrame infoWindow = new JFrame("Info");
            JPanel infoBG = new JPanel();
            infoBG.add(new JLabel(selected.getName()));
            infoBG.setLayout(new GridLayout(selected.getFeatures().size() + 1, 1));
            for (int i = 0; i < selected.getFeatures().size(); i++){
                infoBG.add(new JLabel(selected.getFeatures().get(i)));
            }
            infoWindow.add(infoBG);
            infoWindow.setSize(200, 500);
            infoWindow.setVisible(true);
        }
    }

    public String toString() {
        return "Button: Info";
    }
}
