package guesswho;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

class ButtonBar extends JPanel{

  private InstructionButton instructions;
  private FinalGuessButton finalGuess;
  private AskButton ask;
  private GiveUpButton giveUp;
  private InfoButton info;
  public JLabel answer;

  private static MouseHandler mHandler;

  private final int NUM_OF_OPTIONS_BUTTONS_DOWN = 3;
  private final int NUM_OF_OPTIONS_BUTTONS_ACROSS = 2;

  private final int BORDER_SIZE = 2;
  private final Color FOREGROUND_TEXT_COLOR = new Color(0,150,0);

  public ButtonBar(Background background){
    super();
    setBorder(new LineBorder(Color.black, BORDER_SIZE));
    setLayout(new GridLayout(NUM_OF_OPTIONS_BUTTONS_DOWN, NUM_OF_OPTIONS_BUTTONS_ACROSS));

    ask = new AskButton("Ask", background);
    answer = new JLabel("");
    info = new InfoButton("Card Info");
    instructions = new InstructionButton("Instructions");
    finalGuess = new FinalGuessButton("Final Guess", background);
    giveUp = new GiveUpButton("Give Up?", background);

    answer.setBorder(new LineBorder(Color.BLACK, BORDER_SIZE));
    answer.setHorizontalAlignment(SwingConstants.CENTER);
    answer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
    answer.setForeground(FOREGROUND_TEXT_COLOR);

    add(ask);
    add(answer);
    add(info);
    add(instructions);
    add(finalGuess);
    add(giveUp);
  }

  public void addMouseListener(MouseHandler handler){
    ask.addMouseListener(handler);
    info.addMouseListener(handler);
    instructions.addMouseListener(handler);
    finalGuess.addMouseListener(handler);
    giveUp.addMouseListener(handler);
  }

  public void setAnswer(int i) {
    answer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
    if (i == 1) {
      answer.setForeground(new Color(0, 100, 0));
      answer.setText("Yes");
    } else if (i == 0) {
      answer.setForeground(new Color(100, 0, 0));
      answer.setText("No");
    } else {
      answer.setText("");
    }
  }
}
