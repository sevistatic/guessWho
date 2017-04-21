package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class OptionsBar extends JPanel{

  private static JPanel featuresBar;
  private ArrayList<Feature> featuresSet;
	private int height;
	private int width;
  private final int BORDER_SIZE = 2;
  static ArrayList<JList> jListList;

  public static ButtonBar buttonBar;
	public static PayoutBar payoutBar;
	public static Background background;

	public OptionsBar(int h, int w){
		payoutBar = new PayoutBar();
    jListList = new ArrayList<JList>();
		height = h;
		width = w;
		setLayout(new BorderLayout());
    setPreferredSize(new Dimension(width / 3, height));
    setBorder(new LineBorder(Color.black, 3));
    add(payoutBar, BorderLayout.NORTH);

		buttonBar = new ButtonBar();
		buttonBar.addMouseListener(background.mHandler);
    featuresSet = new ArrayList<Feature>();

		buildFeatures();
    featuresBar = new JPanel();
    initFeaturesBar();
    add(buttonBar, BorderLayout.SOUTH);
	}

	public void resetPointsLabel(){
		int trialPoints = GuessWho.trial.getTrialPoints();
		payoutBar.setGuesses(GuessWho.trial.resetTrialPoints());
	}

	public void resetJListList(){
    for (int j = 0; j < jListList.size(); j++) {
      jListList.get(j).setSelectedIndex(0);
    }
    for (int i = 0; i < jListList.size(); i++) {
      jListList.get(i).clearSelection();
    }
	}

	public ArrayList<JList> getJListList(){
		return jListList;
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
			a.addMouseListener(background.mHandler);
			j.add(jListList.get(i));
			featuresBar.add(j);
		}
		add(featuresBar, BorderLayout.CENTER);
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

	public void setAnswer(int answer){
		buttonBar.setAnswer(answer);
	}
}
