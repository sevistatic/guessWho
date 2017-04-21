package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

class PayoutBar extends JPanel{
	public JLabel totalPayoutLabel;
	public JLabel trialPayoutLabel;

  private final int BORDER_SIZE = 2;
	private final Color FOREGROUND_TEXT_COLOR = new Color(0,150,0);

	public PayoutBar(){
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.black, 3));
		initTotalPayoutLabel();
		initTrialPayoutLabel();
		add(totalPayoutLabel, BorderLayout.NORTH);
		add(trialPayoutLabel, BorderLayout.SOUTH);
	}

	public void setGuesses(int guesses){
		trialPayoutLabel.setText(String.format("%d Guesses", guesses));
	}

	public void setTotalPoints(int points){
		totalPayoutLabel.setText("Points: " + points);
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
}
