package guesswho;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.border.LineBorder;

/**
 * @author Spencer
 */
class Background extends JLayeredPane {
	private static final long serialVersionUID = 1L;

	private static MouseHandler mHandler;

  public OptionsBar optionsBar;

  public PlayArea playArea;

  private final int CARDS_PER_ROW = 6;
  private final int CARDS_PER_COLUMN = 3;
  private final int BORDER_SIZE = 2;
  private final int NUM_OF_OPTIONS_BUTTONS_DOWN = 3;
  private final int NUM_OF_OPTIONS_BUTTONS_ACROSS = 2;

	private final Color FOREGROUND_TEXT_COLOR = new Color(0,150,0);

  public static MersenneTwister mt;

  public Background(int h, int w) throws IOException {
    mt = new MersenneTwister();
    mHandler = new MouseHandler();
    optionsBar = new OptionsBar(h, w, this);
		optionsBar.addMouseListener(mHandler);
    playArea = new PlayArea(mt, this);
		optionsBar.buttonBar.addMouseListener(mHandler);
  }

  public void init() {
    initPlayArea();
    optionsBar.buildFeatures();
    optionsBar.initOptionsBar();
		optionsBar.add(optionsBar.buttonBar, BorderLayout.SOUTH);
    this.add(optionsBar, BorderLayout.WEST);
  }

  public void set() {
    playArea.removeAll();
    playArea.shuffle(playArea.deck);
    playArea.newTarget();
    for (int i = 0; i < playArea.deck.size(); i++) {
    	playArea.deck.get(i).addMouseListener(mHandler);
        playArea.add(playArea.deck.get(i));
    }
  }

  public void reset() {
    GuessWho.trial.newTrial();
		GuessWho.logger.newTrial();

    playArea.selected = null;
    playArea.removeAll();
    playArea.shuffle(playArea.deck);
    playArea.newTarget();
    for (int i = 0; i < playArea.deck.size(); i++){
    	playArea.deck.get(i).addMouseListener(mHandler);
    }

    optionsBar.buttonBar.setAnswer(-1);
  //  GuessWho.trial.resetTrialPoints();
		optionsBar.resetPointsLabel();
    for (int i = 0; i < playArea.deck.size(); i++) {
      playArea.add(playArea.deck.get(i));
    }
    GuessWho.trial.resetTrialPoints();
    optionsBar.setTrialText(String.format("%d Guesses", GuessWho.trial.getTrialPoints()));
    for (int j = 0; j < optionsBar.jListList.size(); j++) {
      optionsBar.jListList.get(j).setSelectedIndex(0);
    }
    for (int i = 0; i < optionsBar.jListList.size(); i++) {
      optionsBar.jListList.get(i).clearSelection();
    }
  }

  public void initPlayArea() {
    GridLayout grid = new GridLayout(CARDS_PER_COLUMN, CARDS_PER_ROW);
    playArea.setLayout(grid);
    this.add(playArea, BorderLayout.CENTER);
    //prints CARDS_PER ROW cards in a single row * CARDS_PER_COLUMN columns
    for (int a = 0; a < CARDS_PER_ROW; a++) {
      for (int b = 0; b < CARDS_PER_COLUMN; b++) {
        playArea.deck.add(new Card(this));
      }
    }
    set();
  }
}//end class Background

//TODO drastically cut size of class
	//TODO single responsibility - hold items of Background
	//TODO move all logic into GuessWho.java or into classes that make sense
	//(Deck should know how to shuffle)
	//Move initialization of optionsbar, etc into their respective classes
