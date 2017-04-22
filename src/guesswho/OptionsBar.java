package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class OptionsBar extends JPanel{
  public JPanel payoutBar;
	public JLabel totalPayoutLabel;
  public JLabel trialPayoutLabel;

	public OptionsBar(){
		super();
		payoutBar = new JPanel();
	}
}
