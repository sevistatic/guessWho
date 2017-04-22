package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class PlayArea extends JPanel{

  public static ArrayList<Card> deck;
  public static Card target;
  static Card selected;
  public MersenneTwister mt;
  public Background background;

	public PlayArea(MersenneTwister mt, Background background){
		super();
    this.mt = mt;
    this.background = background;
    deck = new ArrayList<Card>();
	}

  public void newTarget() {
    int r = mt.nextInt(deck.size());
    target = deck.get(r);
  }

  public void shuffle(ArrayList<Card> d) {
    ArrayList<Card> newDeck = new ArrayList<Card>();
    int sz = d.size();
    while (!d.isEmpty()) {
      d.remove(0);
    }
    for (int i = 0; i < sz; i++){
      newDeck.add(new Card(background));
    }
    deck = newDeck;
  }
}
