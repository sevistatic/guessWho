package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class PlayArea extends JPanel{

  public static ArrayList<Card> deck;

	public PlayArea(){
		super();
    deck = new ArrayList<Card>();
	}
}
