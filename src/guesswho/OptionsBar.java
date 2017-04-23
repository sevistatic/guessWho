package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class OptionsBar extends JPanel{
  public PayoutBar payoutBar;
	public FeaturesBar featuresBar;
  public ButtonBar buttonBar;

	private static MouseHandler mouseHandler;

  private final int WIDTH_RATIO = 3;
  private final int BORDER_SIZE = 3;

	public OptionsBar(int height, int width, Background background){
		super();
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(width / WIDTH_RATIO, height));
    setBorder(new LineBorder(Color.black, BORDER_SIZE));
    mouseHandler = new MouseHandler();

		payoutBar = new PayoutBar();
    add(payoutBar, BorderLayout.NORTH);

    featuresBar = new FeaturesBar();
  	add(featuresBar, BorderLayout.CENTER);

		buttonBar = new ButtonBar(background);
    buttonBar.addMouseListener(mouseHandler);
		add(buttonBar, BorderLayout.SOUTH);
	}

  public void resetOptionsBar(){
		buttonBar.setAnswer("");
		payoutBar.resetPointsLabel();
    featuresBar.clearFeatureSelectors();
	}
}
