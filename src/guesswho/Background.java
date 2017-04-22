package guesswho;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.border.LineBorder;

/**
 * @author Spencer
 */
class Background extends JLayeredPane {
	private static final long serialVersionUID = 1L;

	private static MouseHandler mHandler;

  public OptionsBar optionsBar;

  public ButtonBar buttonBar;

  private JPanel featuresBar;
  private JPanel playArea;
  public JLabel trialPayoutLabel;

  private final int CARDS_PER_ROW = 6;
  private final int CARDS_PER_COLUMN = 3;
  private final int BORDER_SIZE = 2;
  private final int NUM_OF_OPTIONS_BUTTONS_DOWN = 3;
  private final int NUM_OF_OPTIONS_BUTTONS_ACROSS = 2;

  private ArrayList<Feature> featuresSet;
  static ArrayList<Card> deck;
  static ArrayList<JList> jListList;

	private final Color FOREGROUND_TEXT_COLOR = new Color(0,150,0);

  static Card target;
  static Card selected;

  private int height;
  private int width;

  public static MersenneTwister mt;

  public Background(int h, int w) throws IOException {
    height = h;
    width = w;
    mHandler = new MouseHandler();
    deck = new ArrayList<Card>();
    featuresBar = new JPanel();
    optionsBar = new OptionsBar();
    playArea = new JPanel();
		buttonBar = new ButtonBar(this);
		buttonBar.addMouseListener(mHandler);
    featuresSet = new ArrayList<Feature>();
    jListList = new ArrayList<JList>();
    mt = new MersenneTwister();
  }

	public void resetPointsLabel(){
		int trialPoints = GuessWho.trial.getTrialPoints();
		trialPayoutLabel.setText(String.format("%d Guesses", trialPoints));
	}

  public void init() {
    initPlayArea();
    buildFeatures();
    initOptionsBar();
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

  public void initOptionsBar() {
    optionsBar.setLayout(new BorderLayout());
    optionsBar.setPreferredSize(new Dimension(width / 3, height));
    optionsBar.setBorder(new LineBorder(Color.black, 3));
    optionsBar.payoutBar.setLayout(new BorderLayout());
    optionsBar.payoutBar.setBorder(new LineBorder(Color.black, 3));
    initTotalPayoutLabel();
    initTrialPayoutLabel();
    optionsBar.payoutBar.add(optionsBar.totalPayoutLabel, BorderLayout.NORTH);
    optionsBar.payoutBar.add(trialPayoutLabel, BorderLayout.SOUTH);
    optionsBar.add(optionsBar.payoutBar, BorderLayout.NORTH);
    initFeaturesBar();
    optionsBar.add(buttonBar, BorderLayout.SOUTH);
    this.add(optionsBar, BorderLayout.WEST);
  }

  public void set() {
    playArea.removeAll();
    shuffle(deck);
    newTarget();
    for (int i = 0; i < deck.size(); i++) {
    	deck.get(i).addMouseListener(mHandler);
        playArea.add(deck.get(i));
    }
  }

  public void reset() {
    GuessWho.trial.newTrial();
		GuessWho.logger.newTrial();

    selected = null;
    playArea.removeAll();
    shuffle(deck);
    newTarget();
    for (int i = 0; i < deck.size(); i++){
    	deck.get(i).addMouseListener(mHandler);
    }

    buttonBar.setAnswer(-1);
  //  GuessWho.trial.resetTrialPoints();
		resetPointsLabel();
    for (int i = 0; i < deck.size(); i++) {
      playArea.add(deck.get(i));
    }
    GuessWho.trial.resetTrialPoints();
    trialPayoutLabel.setText(String.format("%d Guesses", GuessWho.trial.getTrialPoints()));
    for (int j = 0; j < jListList.size(); j++) {
      jListList.get(j).setSelectedIndex(0);
    }
    for (int i = 0; i < jListList.size(); i++) {
      jListList.get(i).clearSelection();
    }
  }

  public static void shuffle(ArrayList<Card> d) {
    ArrayList<Card> newDeck = new ArrayList<Card>();
    int sz = d.size();
    while (!d.isEmpty()) {
    	d.remove(0);
    }
    for (int i = 0; i < sz; i++){
    	newDeck.add(new Card());
    }
    deck = newDeck;
  }

  public static void newTarget() {
    int r = mt.nextInt(deck.size());
    target = deck.get(r);
  }

  public void initTotalPayoutLabel() {
    optionsBar.totalPayoutLabel = new JLabel();
    optionsBar.totalPayoutLabel.setBorder(new LineBorder(Color.black, BORDER_SIZE));
    optionsBar.totalPayoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
    optionsBar.totalPayoutLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
    optionsBar.totalPayoutLabel.setForeground(FOREGROUND_TEXT_COLOR);
    optionsBar.totalPayoutLabel.setText("Points: " + GuessWho.trial.getTotalPoints());
  }

  public void initTrialPayoutLabel() {
    trialPayoutLabel = new JLabel();
    trialPayoutLabel.setBorder(new LineBorder(Color.black, 2));
    trialPayoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
    trialPayoutLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
    trialPayoutLabel.setForeground(FOREGROUND_TEXT_COLOR);
    trialPayoutLabel.setText(String.format("%d Guesses", GuessWho.trial.getTrialPoints()));
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
    optionsBar.add(featuresBar, BorderLayout.CENTER);
  }

  public void initPlayArea() {
    GridLayout grid = new GridLayout(CARDS_PER_COLUMN, CARDS_PER_ROW);
    playArea.setLayout(grid);
    this.add(playArea, BorderLayout.CENTER);
    //prints CARDS_PER ROW cards in a single row * CARDS_PER_COLUMN columns
    for (int a = 0; a < CARDS_PER_ROW; a++) {
      for (int b = 0; b < CARDS_PER_COLUMN; b++) {
        deck.add(new Card());
      }
    }
    set();
  }
}//end class Background

//TODO drastically cut size of class
	//TODO single responsibility - hold items of Background
	//TODO move all logic into GuessWho.java or into classes that make sense
	//(Deck should know how to shuffle)
	//Move initialization of optionsbar, etc into their respective classes
