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

	private int height;
	private int width;

  public static MersenneTwister randomizer;

  public Background(int height, int width) throws IOException {
		this.height = height;
		this.width = width;
    randomizer = new MersenneTwister();
    mouseHandler = new MouseHandler();
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		playArea = new PlayArea(randomizer,this);
		this.add(playArea, BorderLayout.CENTER);
		playArea.addMouseListener(mouseHandler);

		optionsBar = new OptionsBar(height, width, this);
		this.add(optionsBar, BorderLayout.WEST);
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
