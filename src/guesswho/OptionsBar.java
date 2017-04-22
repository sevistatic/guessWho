package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class OptionsBar extends JPanel{
  public PayoutBar payoutBar;
	public JPanel featuresBar;
  public ButtonBar buttonBar;

  public static ArrayList<JList> jListList;
  public ArrayList<Feature> featuresSet;
	private static MouseHandler mHandler;

  private final int BORDER_SIZE = 2;
	private final Color FOREGROUND_TEXT_COLOR = new Color(0,150,0);
  private int height;
  private int width;

	public OptionsBar(int h, int w, Background background){
		super();

    height = h;
    width = w;
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(width / 3, height));
    setBorder(new LineBorder(Color.black, 3));

		payoutBar = new PayoutBar();
    featuresBar = new JPanel();
		buttonBar = new ButtonBar(background);

    featuresSet = new ArrayList<Feature>();
    jListList = new ArrayList<JList>();

    payoutBar.add(initTotalPayoutLabel(), BorderLayout.NORTH);
    payoutBar.add(initTrialPayoutLabel(), BorderLayout.SOUTH);
    add(payoutBar, BorderLayout.NORTH);
	}

  public void resetOptionsBar(){
		buttonBar.setAnswer(-1);
  //  GuessWho.trial.resetTrialPoints();
		resetPointsLabel();
		setTrialText(String.format("%d Guesses", GuessWho.trial.getTrialPoints()));
    for (int j = 0; j < jListList.size(); j++) {
      jListList.get(j).setSelectedIndex(0);
    }
    for (int i = 0; i < jListList.size(); i++) {
      jListList.get(i).clearSelection();
    }
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
		payoutBar.trialPayoutLabel.setText(s);
	}

	public void initOptionsBar() {
    buildFeatures();
    initFeaturesBar();
		add(buttonBar, BorderLayout.SOUTH);
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
    buttonBar.addMouseListener(mHandler);
	}

	public JLabel initTotalPayoutLabel() {
		payoutBar.totalPayoutLabel = new JLabel();
		payoutBar.totalPayoutLabel.setBorder(new LineBorder(Color.black, BORDER_SIZE));
		payoutBar.totalPayoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		payoutBar.totalPayoutLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		payoutBar.totalPayoutLabel.setForeground(FOREGROUND_TEXT_COLOR);
		payoutBar.totalPayoutLabel.setText("Points: " + GuessWho.trial.getTotalPoints());
    return payoutBar.totalPayoutLabel;
	}

	public JLabel initTrialPayoutLabel() {
		payoutBar.trialPayoutLabel = new JLabel();
		payoutBar.trialPayoutLabel.setBorder(new LineBorder(Color.black, 2));
		payoutBar.trialPayoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		payoutBar.trialPayoutLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		payoutBar.trialPayoutLabel.setForeground(FOREGROUND_TEXT_COLOR);
		payoutBar.trialPayoutLabel.setText(String.format("%d Guesses", GuessWho.trial.getTrialPoints()));
    return payoutBar.trialPayoutLabel;
	}

	public void resetPointsLabel(){
		int trialPoints = GuessWho.trial.getTrialPoints();
		payoutBar.trialPayoutLabel.setText(String.format("%d Guesses", trialPoints));
	}
}
