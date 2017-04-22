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
  public static ArrayList<JList> jListList;
  public ArrayList<Feature> featuresSet;
	private static MouseHandler mHandler;

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
    featuresSet = new ArrayList<Feature>();

    jListList = new ArrayList<JList>();
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

	public void buildFeatures() {
		featuresSet.add(new Feature("Skin Color", new String[]{"light skin", "dark skin"}));
		featuresSet.add(new Feature("Eye Color", new String[]{"blue eyes", "black eyes", "brown eyes", "green eyes", "grey eyes"}));
		featuresSet.add(new Feature("Sex", new String[]{"boy", "girl"}));
		featuresSet.add(new Feature("Mouth", new String[]{"smiling", "frowning"}));
		featuresSet.add(new Feature("Lips", new String[]{"big lips", "thin lips"}));
		featuresSet.add(new Feature("Hair", new String[]{"blonde hair", "black hair", "brown hair", "red hair", "grey hair", "bald"}));
		featuresSet.add(new Feature("Beard", new String[]{"beard", "no beard"}));
		featuresSet.add(new Feature("Mustache", new String[]{"mustache", "no mustache"}));
		featuresSet.add(new Feature("Nose", new String[]{"big nose", "short nose", "thin nose"}));
		featuresSet.add(new Feature("Shirt", new String[]{"blue shirt", "black shirt", "red shirt", "green shirt", "orange shirt",
									"yellow shirt", "purple shirt"/*, "white shirt", "leopard shirt", "warning shirt"*/}));
		featuresSet.add(new Feature("Headwear", new String[]{"hat", "no hat"}));
		featuresSet.add(new Feature("Eyewear", new String[]{"glasses", "no glasses"}));
	}

	public void setTrialText(String s){
		trialPayoutLabel.setText(s);
	}

	public void initOptionsBar() {
    initFeaturesBar();
  }

	public void initFeaturesBar() {
		featuresBar.setLayout(new GridLayout(4, 2));
		featuresBar.setBorder(new LineBorder(Color.black, BORDER_SIZE));
		for (int i = 0; i < featuresSet.size(); i++) {
			JPanel j = new JPanel();
			j.setLayout(new GridLayout(1, 1));
			String[] data = featuresSet.get(i).getOptions();
			GuessList a = new GuessList(data, featuresSet.get(i).getName());
			a.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jListList.add(a);
			a.addMouseListener(mHandler);
			j.add(jListList.get(i));
			featuresBar.add(j);
		}
		add(featuresBar, BorderLayout.CENTER);
	}

	public void addMouseListener(MouseHandler mHandler){
		this.mHandler = mHandler;
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
