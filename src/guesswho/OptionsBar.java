package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class OptionsBar extends JPanel{
  public JPanel payoutBar;
	public JPanel featuresBar;
	public JLabel totalPayoutLabel;
  public JLabel trialPayoutLabel;

  private final int BORDER_SIZE = 2;
	private final Color FOREGROUND_TEXT_COLOR = new Color(0,150,0);
  private int height;
  private int width;

	public OptionsBar(int h, int w){
		super();

    height = h;
    width = w;
		payoutBar = new JPanel();
    featuresBar = new JPanel();
    initTotalPayoutLabel();
    initTrialPayoutLabel();

    payoutBar.setLayout(new BorderLayout());
    payoutBar.setBorder(new LineBorder(Color.black, 3));
    payoutBar.add(totalPayoutLabel, BorderLayout.NORTH);
    payoutBar.add(trialPayoutLabel, BorderLayout.SOUTH);
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(width / 3, height));
    setBorder(new LineBorder(Color.black, 3));
    add(payoutBar, BorderLayout.NORTH);
	}

	public void setTrialText(String s){
		trialPayoutLabel.setText(s);
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
