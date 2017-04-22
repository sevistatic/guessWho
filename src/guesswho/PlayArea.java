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

  private final int CARDS_PER_ROW = 6;
  private final int CARDS_PER_COLUMN = 3;

	public PlayArea(MersenneTwister mt, MouseHandler mHandler, Background background){
		super();
    this.mt = mt;
    this.background = background;
    this.mHandler = mHandler;
    deck = new ArrayList<Card>();
	}

  public void resetPlayArea(){
    selected = null;
    removeAll();
    shuffle(deck);
    newTarget();
    for (int i = 0; i < deck.size(); i++){
      deck.get(i).addMouseListener(mHandler);
    }
    for (int i = 0; i < deck.size(); i++) {
      add(deck.get(i));
    }
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

  public void initPlayArea() {
    GridLayout grid = new GridLayout(CARDS_PER_COLUMN, CARDS_PER_ROW);
    setLayout(grid);
    //prints CARDS_PER ROW cards in a single row * CARDS_PER_COLUMN columns
    for (int a = 0; a < CARDS_PER_ROW; a++) {
      for (int b = 0; b < CARDS_PER_COLUMN; b++) {
        deck.add(new Card(background));
      }
    }
    set();
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
