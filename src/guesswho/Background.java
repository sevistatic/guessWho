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

  public static MersenneTwister mt;

  public Background(int h, int w) throws IOException {
    mt = new MersenneTwister();
    mHandler = new MouseHandler();
    optionsBar = new OptionsBar(h, w, this);
    playArea = new PlayArea(mt,this);
		BorderLayout g = new BorderLayout();
		setLayout(g);
		init();
  }

  public void init() {
    playArea.initPlayArea();
		this.add(playArea, BorderLayout.CENTER);
		playArea.addMouseListener(mHandler);

    optionsBar.initOptionsBar();
    this.add(optionsBar, BorderLayout.WEST);
		optionsBar.addMouseListener(mHandler);
  }

  public void reset() {
    GuessWho.trial.newTrial();
    GuessWho.trial.resetTrialPoints();
		playArea.resetPlayArea();
		optionsBar.resetOptionsBar();
		GuessWho.logger.newTrial();
  }
}//end class Background
