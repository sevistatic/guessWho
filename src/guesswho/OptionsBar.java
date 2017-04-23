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
	private final Color FOREGROUND_TEXT_COLOR = new Color(0,150,0);
  private final int BORDER_SIZE = 3;
  private int height;
  private int width;

	public OptionsBar(int height, int width, Background background){
		super();
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(width / WIDTH_RATIO, height));
    setBorder(new LineBorder(Color.black, BORDER_SIZE));

		payoutBar = new PayoutBar();
    featuresBar = new FeaturesBar();
		buttonBar = new ButtonBar(background);

    add(payoutBar, BorderLayout.NORTH);
  	add(featuresBar, BorderLayout.CENTER);
		add(buttonBar, BorderLayout.SOUTH);
	}

	public void addMouseListener(MouseHandler mouseHandler){
		this.mouseHandler = mouseHandler;
    buttonBar.addMouseListener(mouseHandler);
	}

  public void resetOptionsBar(){
		buttonBar.setAnswer(-1);
		payoutBar.resetPointsLabel();
    featuresBar.clearFeatures();
	}
}
