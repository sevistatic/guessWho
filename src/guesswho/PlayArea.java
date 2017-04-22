package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class PlayArea extends JPanel{

  public static ArrayList<Card> deck;
  public static Card target;
  public MersenneTwister mt;

	public PlayArea(MersenneTwister mt){
		super();
    this.mt = mt;
    deck = new ArrayList<Card>();
	}

  public void newTarget() {
    int r = mt.nextInt(deck.size());
    target = deck.get(r);
  }
}
