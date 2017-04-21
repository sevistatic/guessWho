/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guesswho;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Spencer Bryant
 */
public class Card extends JLabel implements GuessObject {

    private int tileHeight;
    private int tileWidth;
    private int rowNum;
    private int columnNum;
    private float alpha;
    private Image cardback;
    private Image cardfront;
    private Image showingFace;
    private Character character;
    // private final int GAP_WIDTH;
    //private final int GAP_HEIGHT;
    private Color rectColor;
    private boolean marked;
    private boolean faded;

    /*      public int getBorderHeight() {
    return GAP_HEIGHT;
    }
    public int getBorderWidth() {
    return GAP_WIDTH;
    }
     *
     */
    
    

    public Character getCharacter() {
        return character;
    }
    
    public Image getCardFace() {
        return showingFace;
    }

    public void setCardfront(Image cardfront) {
        this.cardfront = cardfront;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public int getRowNum() {
        return rowNum;
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

    Card(int row, int column, Character c) {
        super();
        character = c;
        faded = false;
        alpha = 1.0f;
        tileHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4;
        tileWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 10;
        cardfront = new ImageIcon(character.getImageName()).getImage();
        cardback = new ImageIcon("cardback.jpg").getImage();
        showingFace = cardfront;
        rowNum = row;
        columnNum = column;
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
                Background.info.setSelected(this.getCharacter());
                break;
        }
    }
    
    public void flip() {
        showingFace = (showingFace == cardfront) ? cardback : cardfront;
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
        Background.info.setSelected(this.getCharacter());
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
            g.setColor(Color.yellow);
        }
        g.fillRect(0, 0, getTileWidth() + 10, getTileHeight() + 10);
        g.drawImage(getCardFace(), 5, 5, getTileWidth(), getTileHeight(), null);
        if (isFaded()){
            g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
            g.fillRect(5, 5, getTileWidth(), getTileHeight());
        }
    } // end method paintComponent
}
