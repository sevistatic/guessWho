package guesswho;

import java.awt.event.*;

/**
 *
 * @author Spencer
 */
class MouseHandler implements MouseListener {

    public MouseHandler() {
        super();

    }

    public void mouseClicked(MouseEvent e) {
      GuessWho.logger.logMouseClick(e);
        if (e.getClickCount() == 2 && e.getButton() == 1)//2x-click and right-click both flip
        {
            GuessWho.logger.log("Event Type: Double Click");
            ((GuessObject) e.getSource()).doStuff(e.getXOnScreen(), e.getYOnScreen(), 3);
        } else {
            GuessWho.logger.log("Event Type: Single Click");
            ((GuessObject) e.getSource()).doStuff(e.getXOnScreen(), e.getYOnScreen(), e.getButton());
        }
            GuessWho.logger.log("-------------------------");
            GuessWho.logger.flush();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
