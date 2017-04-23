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

	private static MouseHandler mouseHandler;
  public OptionsBar optionsBar;
  public PlayArea playArea;

  public static MersenneTwister randomizer;

  public Background(int height, int width) throws IOException {
    randomizer = new MersenneTwister();
    mouseHandler = new MouseHandler();
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		playArea = new PlayArea(randomizer,this);
		add(playArea, BorderLayout.CENTER);
		playArea.addMouseListener(mouseHandler);

		optionsBar = new OptionsBar(height, width, this);
		add(optionsBar, BorderLayout.WEST);
		optionsBar.addMouseListener(mouseHandler);
  }

  public void reset() {
    GuessWho.trial.newTrial();
    GuessWho.trial.resetTrialPoints();
		playArea.resetPlayArea();
		optionsBar.resetOptionsBar();
		GuessWho.logger.newTrial();
  }
}
