package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class PlayArea extends JPanel{

  public static ArrayList<Card> deck;
  public static Card targetCard;
  static Card selectedCard;
  public MersenneTwister randomizer;
  public Background background;
  public MouseHandler mouseHandler;
  private final int CARDS_PER_ROW = 6;
  private final int CARDS_PER_COLUMN = 3;
  private int deckSize;

	public PlayArea(MersenneTwister randomizer, Background background){
		super();
    this.randomizer = randomizer;
    this.background = background;
    this.mouseHandler = new MouseHandler();
    this.deckSize = CARDS_PER_COLUMN * CARDS_PER_ROW;
    deck = new ArrayList<Card>();
    GridLayout grid = new GridLayout(CARDS_PER_COLUMN, CARDS_PER_ROW);
    setLayout(grid);

    resetPlayArea();
	}

  public void addMouseListener(MouseHandler mouseHandler){
    for (int card = 0; card < deck.size(); card++){
      deck.get(card).addMouseListener(mouseHandler);
    }
  }

  public void resetPlayArea(){
      selectedCard = null;
      removeAll();
      shuffle();
      newTarget();
      addMouseListener(mouseHandler);
  }

  public void newTarget() {
    targetCard = deck.get(randomizer.nextInt(deck.size()));
  }

  public void shuffle() {
    while (!deck.isEmpty()) {
        deck.remove(0);
    }
    for (int card = 0; card < deckSize; card++){
      deck.add(new Card(background));
      add(deck.get(card));
    }
  }
}
