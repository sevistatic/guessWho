package guesswho;

import java.awt.*;
import java.awt.Font;
import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Spencer
 */
abstract interface GuessObject {

    public abstract void doStuff(int x, int y, int button);

    public abstract String toString();
}

//------------------------------------------------------------------------------

class InfoButton extends JButton implements GuessObject {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

    InfoButton(String message) {
        super(message);
    }

    public void doStuff(int x, int y, int Button) {
      Character selected = Background.selected.getCharacter();
      if (selected != null) {
        //   System.out.println("selected = " + Background.selected.getCharacter().getName());
        GuessWho.logger.log("Request Info, Character: " + selected.getName());
        JFrame infoWindow = new JFrame("Info");
        JPanel infoBG = new JPanel();
        infoBG.add(new JLabel(selected.getName()));
        //makes a list of features for a specific card
        infoBG.setLayout(new GridLayout(selected.getFeatures().length + 1, 1));
        //adds the features to the list on the window
        for (int i = 0; i < selected.getFeatures().length; i++){
          infoBG.add(new JLabel(selected.getFeatures()[i]));
        }
        infoWindow.add(infoBG);
        infoWindow.setSize(200, 500);
        infoWindow.setVisible(true);
      }
    }

    public String toString() {
        return "Button: Info";
    }
}

//------------------------------------------------------------------------------
class InstructionButton extends JButton implements GuessObject{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	JFrame instructionsWindow;
    JPanel messageLabel;

    InstructionButton(String message){
        super(message);

    }

    public void doStuff(int x, int y, int button){

                instructionsWindow = new JFrame("Instructions");
                messageLabel = new JPanel();
                messageLabel.setLayout(new GridLayout(4, 1));
                //messageLabel.setBackground(Color.ORANGE);
                messageLabel.add(new JLabel("Right-click  or double-click a FEATURE to deselect it."), 0);
                messageLabel.add(new JLabel("Right-click  or double-click a CARD to turn it face-down."), 0);
                messageLabel.add(new JLabel("Left-click a CARD to select or deselect it."), 0);
                messageLabel.add(new JLabel("Left-click a FEATURE to select it."), 0);

                instructionsWindow.add(messageLabel);
                instructionsWindow.setLocation(200, 500);
                instructionsWindow.setSize(450, 150);
                //dookieWindow.setLocation(x, y);
                instructionsWindow.setVisible(true);
    }

    public String toString(){
        return "Button: Instructions";
    }
}

//------------------------------------------------------------------------------

class GiveUpButton extends JButton implements GuessObject{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
  public Background background;

	GiveUpButton(String message, Background background){
        super(message);
        this.background = background;
    }

    public void doStuff(int x, int y, int Button){
                if (JOptionPane.showConfirmDialog(null, "Are you absolutely sure?") == JOptionPane.OK_OPTION){
                JOptionPane.showMessageDialog(null, "The answer was " + Background.target.getCharacter().getName());
                background.reset();
                }
    }

    public String toString(){
        return "Button: Give Up - Answer: " + Background.target.getCharacter().getName();
    }
}

//------------------------------------------------------------------------------
class FinalGuessButton extends JButton implements GuessObject {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

  public Background background;

    FinalGuessButton(String message, Background background) {
        super(message);
        this.background = background;
    }

    public void doStuff(int x, int y, int Button) {
        if (JOptionPane.showConfirmDialog(null, "Are you absolutely sure?") == JOptionPane.YES_OPTION) {
            // System.out.println("target = " + person.getName());
            if (background.selected != null) {
                //   System.out.println("selected = " + Background.selected.getCharacter().getName());
                GuessWho.logger.log("Final Guess, Player Answer: " + background.selected.getCharacter().getName());

                if (background.selected.getCharacter() == background.target.getCharacter()) {
                    JOptionPane.showMessageDialog(null, "CORRECT!! The answer was " + background.target.getCharacter().getName());
                    GuessWho.logger.log("Correct Answer: " + background.target.getCharacter().getName());
                    GuessWho.trial.increaseTotalPoints(GuessWho.trial.getTrialPoints());

                    if (GuessWho.trial.getTrialPoints() >= 2) {
                        GuessWho.trial.increaseTotalPoints(GuessWho.trial.getTrialPoints());
                    }
                    background.optionsBar.totalPayoutLabel.setText("Points: " + GuessWho.trial.getTotalPoints());
                } else {
                    JOptionPane.showMessageDialog(null, "WRONG!! The answer was " + background.target.getCharacter().getName());
                    GuessWho.logger.log("Correct Answer: " + background.target.getCharacter().getName());
                }
            } else {

                GuessWho.logger.log("Final Guess, Player Answer: None Selected");
                JOptionPane.showMessageDialog(null, "WRONG!! The answer was " + background.target.getCharacter().getName());
                GuessWho.logger.log("Correct Answer: " + background.target.getCharacter().getName());
            }
        }
        background.reset();
    }

    public String toString() {
        return "Button: Final Guess";
    }
}

//------------------------------------------------------------------------------

class GuessList extends JList implements GuessObject{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int selectedIndex;
    private String name;
    GuessList(){
        super();
        selectedIndex = 10;
    }
    GuessList(String[] s, String n){
        super(s);
        name = n;
    }

    public String toString(){
    	String n = this.name;
    	String s = (String)this.getSelectedValue();
    	return n + " - " + s;
    }
    public void doStuff(int x, int y, int button){
        if (button == 3){
        if (this.getSelectedIndex() == selectedIndex){
           clearSelection();
        }else {
            selectedIndex = this.getSelectedIndex();
        }
        }

    }
}
//------------------------------------------------------------------------------

class Card extends JLabel implements GuessObject {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int tileHeight;
    private int tileWidth;
    private float alpha;
    private ImageIcon[] cardback;
    private ImageIcon[] cardfront;
    private ImageIcon[] showingFace;
    private Character character;
    private Color rectColor;
    private boolean marked;
    private boolean faded;
    private String folderName;

    public Character getCharacter() {
        return character;
    }

    public ImageIcon[] getCardFace() {
        return showingFace;
    }

    public void setCardfront(ImageIcon[] cardfront) {
        this.cardfront = cardfront;
    }

    public void setTileHeight(int height) {
        tileHeight = height;
    }

    public void setTileWidth(int width) {
        tileWidth = width;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public Color getBorderColor() {
        return rectColor;
    }

    Card() {
        super();
        character = new Character();

        //portraitLabel = new JLabel(character.getName(), 20);
        //add(portraitLabel);
        faded = false;
        alpha = 1.0f;
        tileHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 7/24;
        tileWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 10;
        folderName = "images/";
        cardfront = character.getPortrait();
        cardback = new ImageIcon[1];
        cardback[0] = new ImageIcon(folderName + "cardback.jpg");
        showingFace = cardfront;
        rectColor = Color.green;
        marked = false;
        AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    }

    public boolean isMarked() {
        return marked;
    }

    public boolean isFaded(){
        return faded;
    }

    public void doStuff(int x, int y, int button){
        switch(button){
            case -1:
                fade();
                break;
            case 3:
                flip();
                unmark();
                break;
            case 1:
                for (int i = 0; i < Background.deck.size(); i++) {//un-click others
                    if (Background.deck.get(i) != this) {
                            Background.deck.get(i).unfade();
                    }
                }
                fade();
                Background.selected = this;
                break;
        }
    }

    public void flip() {
        showingFace = (showingFace.equals(cardfront)) ? cardback : cardfront;
    }

    public void unflip(){
        showingFace = cardfront;
    }

    public void fade() {
        marked = false;
        alpha = (faded == false) ? 0.0f : 1.0f;
        faded = (faded == false) ? true : false;
        Background.selected = this;
    }
    public void mark() {
        marked = (marked == false) ? true : false;
        Background.selected = this;
    }

    public void unmark(){
        marked = false;
    }

    public void unfade() {
        alpha = 0.0f;
        faded = false;
    }
    public String toString(){
        return this.getCharacter().getName();
    }

    @Override
    public void paintComponent(Graphics g) {
        if (isMarked()) {
            g.setColor(Color.black);
        } else {
            g.setColor(new Color(200, 200, 0));
        }
        g.fillRect(0, 0, getTileWidth() + 10, getTileHeight() + 10);

        for (int i = 0; i < showingFace.length; i++) {
            g.drawImage(showingFace[i].getImage(), 5, 5, getTileWidth(), getTileHeight() * 7 / 8, null);
        }
        if (isFaded()){
            g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
            g.fillRect(5, 5, getTileWidth(), getTileHeight() * 7 / 8);
        }
        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString(character.getName(), 10, getTileHeight());
    } // end method paintComponent
}
//------------------------------------------------------------------------------

class AskButton extends JButton implements GuessObject{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
  public Background background;

    AskButton(String message, Background background) {
        super(message);
        this.background = background;
    }

    public void doStuff(int x, int y, int button) {
        //actually input the guess
        GuessWho.logger.log("Query submitted:");
        int correct = areAllCorrect();
        if (correct == 1) {
            GuessWho.trial.deductTrialPoints(1);
            background.buttonBar.setAnswer(1);
        } else if (correct == 0) {
            GuessWho.trial.deductTrialPoints(1);
            background.buttonBar.setAnswer(0);
        } else {
            background.buttonBar.setAnswer(-1);
        }
          background.optionsBar.trialPayoutLabel.setText(String.format("%d Guesses", GuessWho.trial.getTrialPoints()));
        for (int i = 0; i < background.optionsBar.jListList.size(); i++) {
            background.optionsBar.jListList.get(i).clearSelection();
        }
    }

    public int areAllCorrect() {
        int unchanged = 0;
        for (int i = 0; i < background.optionsBar.jListList.size(); i++) {
            if (!background.optionsBar.jListList.get(i).isSelectionEmpty()) {
                //if it was changed
                //if (!Background.jListList.get(i).getSelectedValue().toString().equals(Background.target.getCharacter().getFeatures()[i])) {
                if (!background.optionsBar.jListList.get(i).getSelectedValue().toString().equals(Background.target.getCharacter().getFeatures()[i])) {
                    //is it wrong?
                    GuessWho.logger.log("No Match for " + background.optionsBar.jListList.get(i).getSelectedValue().toString());
                    return 0;
                } else {
                    //must be a match for this one feature at least
                    GuessWho.logger.log("Match for " + background.optionsBar.jListList.get(i).getSelectedValue().toString());
                }
            } else {
                unchanged++;
            }
        }
        if (unchanged == background.optionsBar.jListList.size()) {
            GuessWho.logger.log("Nothing Selected");
            return -2;
        }
        //must be entirely correct
        GuessWho.logger.log("Selected Characteristics Match");
        return 1;
    }

    public String toString() {
        return "Button: Query";
    }
}
