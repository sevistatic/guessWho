package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class PlayArea extends JPanel{
	static ArrayList<Card> deck;
	private final int CARDS_PER_ROW = 6;
	private final int CARDS_PER_COLUMN = 3;
	private final int BORDER_SIZE = 2;
	private final int NUM_OF_OPTIONS_BUTTONS_DOWN = 3;
	private final int NUM_OF_OPTIONS_BUTTONS_ACROSS = 2;
	private final Color FOREGROUND_TEXT_COLOR = new Color(0,150,0);

  static Card target;
  static Card selected;
	public static Background background;

	public PlayArea(){
    deck = new ArrayList<Card>();
		GridLayout grid = new GridLayout(CARDS_PER_COLUMN, CARDS_PER_ROW);
		setLayout(grid);

		//prints CARDS_PER ROW cards in a single row * CARDS_PER_COLUMN columns
		for (int a = 0; a < CARDS_PER_ROW; a++) {
			for (int b = 0; b < CARDS_PER_COLUMN; b++) {
				deck.add(new Card());
			}
		}
		set();
	}

	public void reset(){
		removeAll();
    shuffle();
    newTarget();
	}

  public void shuffle() {
    ArrayList<Card> newDeck = new ArrayList<Card>();
    int sz = deck.size();
    while (!deck.isEmpty()) {
    	deck.remove(0);
    }
    for (int i = 0; i < sz; i++){
    	newDeck.add(new Card());
    }
    deck = newDeck;
		for (int i = 0; i < deck.size(); i++){
			deck.get(i).addMouseListener(background.mHandler);
			add(deck.get(i));
		}
  }

  public void newTarget() {
    int r = GuessWho.mt.nextInt(deck.size());
    target = deck.get(r);
		selected = null;
  }

  public void set() {
    reset();
  }

}
