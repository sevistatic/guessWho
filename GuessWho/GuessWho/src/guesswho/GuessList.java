/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guesswho;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
/**
 *
 * @author Spencer
 */
public class GuessList extends JList implements GuessObject{
    private int selectedIndex;
    GuessList(){
        super();
        selectedIndex = 10;
    }
    GuessList(String[] s){
        super(s);
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
