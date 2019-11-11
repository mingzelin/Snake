import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main{
  public static void main(String[] args){
      JFrame frame = new JFrame("Snake");
      frame.setLayout(new GridBagLayout());
      GridBagConstraints gc = new GridBagConstraints();
      Top top = new Top();
      gc.gridx=0;
      gc.gridy=0;
      frame.add(top, gc);
      Board board = new Board(top);
      gc.gridx=0;
      gc.gridy=1;
      frame.add(board, gc);
      //frame.add(splash, gc);
      frame.addKeyListener(board);
      frame.setPreferredSize(new Dimension(1280,800));
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setResizable(false);
  }
}
