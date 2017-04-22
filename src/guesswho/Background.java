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


  private final int BORDER_SIZE = 2;
  private final int NUM_OF_OPTIONS_BUTTONS_DOWN = 3;
  private final int NUM_OF_OPTIONS_BUTTONS_ACROSS = 2;

	private final Color FOREGROUND_TEXT_COLOR = new Color(0,150,0);

  public static MersenneTwister mt;

  public Background(int h, int w) throws IOException {
    mt = new MersenneTwister();
    mHandler = new MouseHandler();
    optionsBar = new OptionsBar(h, w, this);
    playArea = new PlayArea(mt, mHandler, this);

		optionsBar.addMouseListener(mHandler);
  }

  public void init() {
    playArea.initPlayArea();
		this.add(playArea, BorderLayout.CENTER);

    optionsBar.initOptionsBar();
    this.add(optionsBar, BorderLayout.WEST);
  }

  public void reset() {
    GuessWho.trial.newTrial();
    GuessWho.trial.resetTrialPoints();
		playArea.resetPlayArea();
		optionsBar.resetOptionsBar();
		GuessWho.logger.newTrial();
  }

}//end class Background

//TODO drastically cut size of class
	//TODO single responsibility - hold items of Background
	//TODO move all logic into GuessWho.java or into classes that make sense
	//(Deck should know how to shuffle)
	//Move initialization of optionsbar, etc into their respective classes
