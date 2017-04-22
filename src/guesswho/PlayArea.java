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
  public MouseHandler mHandler;

	public PlayArea(MersenneTwister mt, MouseHandler mHandler, Background background){
		super();
    this.mt = mt;
    this.background = background;
    this.mHandler = mHandler;
    deck = new ArrayList<Card>();
	}

  public void set() {
    removeAll();
    shuffle(deck);
    newTarget();
    for (int i = 0; i < deck.size(); i++) {
      deck.get(i).addMouseListener(mHandler);
        add(deck.get(i));
    }
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
