package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class PayoutBar extends JPanel{
	public JLabel totalPayoutLabel;
  public JLabel trialPayoutLabel;

	public PayoutBar(){
		super();
    setLayout(new BorderLayout());
    setBorder(new LineBorder(Color.black, 3));
	}
}
