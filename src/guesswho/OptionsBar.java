package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class OptionsBar extends JPanel{
  public JPanel payoutBar;
	public JLabel totalPayoutLabel;
  public JLabel trialPayoutLabel;

  private final int BORDER_SIZE = 2;
	private final Color FOREGROUND_TEXT_COLOR = new Color(0,150,0);

	public OptionsBar(){
		super();
		payoutBar = new JPanel();
    initTotalPayoutLabel();
    initTrialPayoutLabel();
	}

	public void initTotalPayoutLabel() {
		totalPayoutLabel = new JLabel();
		totalPayoutLabel.setBorder(new LineBorder(Color.black, BORDER_SIZE));
		totalPayoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalPayoutLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		totalPayoutLabel.setForeground(FOREGROUND_TEXT_COLOR);
		totalPayoutLabel.setText("Points: " + GuessWho.trial.getTotalPoints());
	}

	public void initTrialPayoutLabel() {
		trialPayoutLabel = new JLabel();
		trialPayoutLabel.setBorder(new LineBorder(Color.black, 2));
		trialPayoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		trialPayoutLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		trialPayoutLabel.setForeground(FOREGROUND_TEXT_COLOR);
		trialPayoutLabel.setText(String.format("%d Guesses", GuessWho.trial.getTrialPoints()));
	}

	public void resetPointsLabel(){
		int trialPoints = GuessWho.trial.getTrialPoints();
		trialPayoutLabel.setText(String.format("%d Guesses", trialPoints));
	}
}
