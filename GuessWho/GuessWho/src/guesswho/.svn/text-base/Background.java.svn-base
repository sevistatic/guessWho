/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guesswho;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Spencer
 */
public class Background extends JLayeredPane {
    MouseTrap mLKing;
    InstructionButton instructions;
    FinalGuessButton finalGuess;
    AskButton ask;
    GiveUpButton giveUp;
    JPanel payoutBar;
    JPanel optionsBar;
    JPanel buttonBar1;
    JPanel buttonBar;
    JPanel featuresBar;
    Image image;
    File file;
    Scanner reader;
    private final int CARDS_PER_ROW = 6;
    private final int CARDS_PER_COLUMN = 3;
    ArrayList<Feature> featuresSet;
    
    private static JLabel totalPayoutLabel;
    private static JLabel trialPayoutLabel;
    private static JPanel playArea;
    private static int totalPoints;
    private static int trialPoints;
    private static int trialNumber;
    public static ArrayList<Card> deck;
    public static JLabel answer;
    public static ArrayList<JList> jListList;
    public static Card target;
    public static Card selected;
    public static PrintWriter outFile;
    public static InfoButton info;
    public int height;
    public int width;
    public static MersenneTwister mt;

    public Background(int h, int w) throws IOException{
        height = h;
        width = w;
        outFile = new PrintWriter(new FileWriter("log.txt"));
        trialNumber = 0;
        totalPoints = 0;
        trialPoints = 50;
        mLKing = new MouseTrap();
        deck = new ArrayList<Card>();
        featuresBar = new JPanel();
        optionsBar = new JPanel();
        playArea = new JPanel();
        featuresSet = new ArrayList<Feature>();
        jListList = new ArrayList<JList>();
        file = new File("characterFeatures.txt");
        mt = new MersenneTwister();
    } //end constructor

    public static void deductTrialPoints(int points){
        if ((trialPoints-= points) >=0 ){
        trialPayoutLabel.setText(String.format("%d Current", trialPoints));
        } else {
            trialPoints = 0;
            trialPayoutLabel.setText(String.format("%d Current", trialPoints));
        }
    }
    
    public static int getTrialPoints(){
        return trialPoints;
    }
    
    public static int getTrialNum(){
        return trialNumber;
    }
    
    public static int getTotalPoints(){
        return totalPoints;
    }
    
    public static void increaseTotalPoints(double points){
        totalPoints += points;
        totalPayoutLabel.setText(String.format("%d Total", totalPoints));
    }
    
    public void init() {
        initPlayArea();
        buildFeatures();
        initOptionsBar();
    }//end method init

    public void buildFeatures() {
        featuresSet.add(new Feature("Sex", new String[]{"male", "female"}));
        featuresSet.add(new Feature("Eyewear", new String[]{"glasses", "no glasses"}));
        featuresSet.add(new Feature("Mouth", new String[]{"smile", "no smile"}));
        featuresSet.add(new Feature("Headwear", new String[]{"hat", "no hat"}));
        featuresSet.add(new Feature("Skin Color", new String[]{"white skin", "dark skin"}));
        featuresSet.add(new Feature("Hair", new String[]{"black hair", "brown hair", "blonde hair", "bald"}));
        featuresSet.add(new Feature("Eye Color", new String[]{"blue eyes", "green eyes", "brown eyes"}));
        featuresSet.add(new Feature("Facial Hair", new String[]{"facial hair", "no facial hair"}));
    }

    public void initOptionsBar() {
        info = new InfoButton("Card Info", null);
        info.addMouseListener(mLKing);
        instructions = new InstructionButton("Instructions");
        instructions.addMouseListener(mLKing);
        ask = new AskButton("Ask", jListList);
        ask.addMouseListener(mLKing);
        finalGuess = new FinalGuessButton("Final Guess", target.getCharacter());
        finalGuess.addMouseListener(mLKing);
        giveUp = new GiveUpButton("Give Up?");
        giveUp.addMouseListener(mLKing);
        
        optionsBar.setLayout(new BorderLayout());
        optionsBar.setPreferredSize(new Dimension(width / 3, height));
        optionsBar.setBorder(new LineBorder(Color.black, 3));
        payoutBar = new JPanel();
        payoutBar.setLayout(new BorderLayout());
        payoutBar.setBorder(new LineBorder(Color.black, 3));
        initTotalPayoutLabel();
        initTrialPayoutLabel();
        payoutBar.add(totalPayoutLabel, BorderLayout.NORTH);
        payoutBar.add(trialPayoutLabel, BorderLayout.SOUTH);
        optionsBar.add(payoutBar, BorderLayout.NORTH);
        initFeaturesBar();
        initButtons();
        this.add(optionsBar, BorderLayout.WEST);
    }
    
    public void set(){
        newTarget();
        playArea.removeAll();
        shuffle(deck);
        for (int i = 0; i < deck.size(); i++){
            playArea.add(deck.get(i));
        }
    }
    
    public static void reset(){
        trialNumber++;
        newTarget();
        selected = null;
        playArea.removeAll();
        shuffle(deck);
        
        Background.setAnswer(-1);
        Background.deductTrialPoints(0);
        for (int i = 0; i < deck.size(); i++){
            playArea.add(deck.get(i));
        }
        trialPoints = 50;
        trialPayoutLabel.setText(String.format("%d Current", trialPoints));
        for (int j = 0; j < jListList.size(); j++){
            jListList.get(j).setSelectedIndex(0);
        }
        for (int i = 0; i < Background.jListList.size(); i++) {
            Background.jListList.get(i).clearSelection();
        }
    }
    
    public static void setAnswer(int i){
        answer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        if (i == 1){
        answer.setForeground(new Color(0, 100, 0));
        answer.setText("Yes");
        } else if (i == 0){    
        answer.setForeground(new Color(100, 0, 0));
        answer.setText("No");
        } else {
            answer.setText("");
        }
    }
    
    public static void shuffle(ArrayList<Card> d){
        ArrayList<Card> newDeck = new ArrayList<Card>();
        while (!d.isEmpty()){
            Card c = d.remove(mt.nextInt(d.size()));
           // Card c = d.remove((int)(Math.random() * d.size()));
            c.unflip();
            c.unmark();
            c.unfade();
            newDeck.add(c);
        }
        deck = newDeck;
    }
    
    public static void newTarget(){
        //int r = (int) (Math.random() * deck.size());
        int r = mt.nextInt(deck.size());
            target = deck.get(r);
    }
    
    public void initTotalPayoutLabel() {
        totalPayoutLabel = new JLabel();
        totalPayoutLabel.setBorder(new LineBorder(Color.black, 2));
        totalPayoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalPayoutLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        totalPayoutLabel.setForeground(new Color(0, 100, 0));
        totalPayoutLabel.setText(String.format("%d Total", totalPoints));
    }
    
    public void initTrialPayoutLabel() {
        trialPayoutLabel = new JLabel();
        trialPayoutLabel.setBorder(new LineBorder(Color.black, 2));
        trialPayoutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        trialPayoutLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        trialPayoutLabel.setForeground(new Color(0, 100, 0));
        trialPayoutLabel.setText(String.format("%d Current", trialPoints));
    }

    public void initFeaturesBar() {
        answer = new JLabel("");
        answer.setBorder(new LineBorder(Color.BLACK, 2));
        featuresBar.setLayout(new GridLayout(4, 2));
        featuresBar.setBorder(new LineBorder(Color.black, 2));
        for (int i = 0; i < featuresSet.size(); i++) {
            JPanel j = new JPanel();
            j.setLayout(new GridLayout(1, 1));
            String[] data = featuresSet.get(i).getOptions();
            GuessList a = new GuessList(data);
            a.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jListList.add(a);
            a.addMouseListener(mLKing);
            j.add(jListList.get(i));
            featuresBar.add(j);
        }
        answer.setHorizontalAlignment(SwingConstants.CENTER);
        answer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        answer.setForeground(new Color(0, 100, 0));
        optionsBar.add(featuresBar, BorderLayout.CENTER);
    }

    public void initButtons() {
        buttonBar = new JPanel();
        buttonBar.setBorder(new LineBorder(Color.black, 2));
        buttonBar.setLayout(new GridLayout(3, 2));
        buttonBar.add(answer);
        buttonBar.add(info);
        buttonBar.add(instructions);
        buttonBar.add(ask);
        buttonBar.add(finalGuess);
        buttonBar.add(giveUp);
        optionsBar.add(buttonBar, BorderLayout.SOUTH);
    }

    public void initPlayArea() {
        GridLayout grid = new GridLayout(3, 6);
        playArea.setLayout(grid);
        this.add(playArea, BorderLayout.CENTER);
        try {
            reader = new Scanner(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        while (reader.hasNextLine()) {
            for (int a = 0; a < CARDS_PER_ROW; a++) {
                for (int b = 0; b < CARDS_PER_COLUMN; b++) {
                    int index = (a * CARDS_PER_COLUMN) + b;
                    createCharacterCard(a, b, index);
                }
            }
        }
        set();
    }
    
    public static double currentTime() {
        return (System.currentTimeMillis() / 1000.0) - GuessWho.systemStartTime;
    }

    public void createCharacterCard(int r, int c, int index) {
        ArrayList<String> feat = new ArrayList<String>();
        String name = reader.nextLine();
        String fn = reader.nextLine();
        String male = reader.nextLine();
        feat.add(male);
        String glasses = reader.nextLine();
        feat.add(glasses);
        String smiling = reader.nextLine();
        feat.add(smiling);
        String hat = reader.nextLine();
        feat.add(hat);
        String whiteSkin = reader.nextLine();
        feat.add(whiteSkin);
        String hairColor = reader.nextLine();
        feat.add(hairColor);
        String eyeColor = reader.nextLine();
        feat.add(eyeColor);
        String facialHair = reader.nextLine();
        feat.add(facialHair);
        deck.add(new Card(r, c, new Character(r, c, name, fn, feat)));
        reader.nextLine();
        deck.get(index).addMouseListener(mLKing);
    }

    @Override
    public void paintComponent(Graphics g) {
    } // end method paintComponent
}//end class Background

/*
 * ***change to % of screen size
 * 
 * automatic flipdown on yes *****not doing it
 */