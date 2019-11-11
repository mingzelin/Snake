import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Top extends JPanel {
  private JLabel label2;//countdown
  private JLabel label4;//level
  private JLabel label6;//score
  public Top(){
    this.setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();
    gc.gridx=0;
    gc.gridy=0;
    JLabel label1 = new JLabel("  Time: ");
    label1.setFont(new Font("Verdana",2,23));
    add(label1, gc);
    gc.gridx=1;
    gc.gridy=0;
    label2 = new JLabel(" ");
    label2.setFont(new Font("Verdana",2,23));
    add(label2, gc);
    gc.gridx=2;
    gc.gridy=0;
    JLabel label3 = new JLabel("  Level: ");
    label3.setFont(new Font("Verdana",2,23));
    add(label3, gc);
    gc.gridx=3;
    gc.gridy=0;
    label4 = new JLabel(" ");
    label4.setFont(new Font("Verdana",2,23));
    add(label4, gc);
    gc.gridx=4;
    gc.gridy=0;
    JLabel label5 = new JLabel("  Score: ");
    label5.setFont(new Font("Verdana",2,23));
    add(label5, gc);
    gc.gridx=5;
    gc.gridy=0;
    label6 = new JLabel(" ");
    label6.setFont(new Font("Verdana",2,23));
    add(label6, gc);
  }

  public void updateTimer(int clock){
    if(clock!=-1){
      label2.setText(Integer.toString(clock)+"  ");
    }else{
      label2.setText("Infin.");
    }

  }

  public void updateLevel(int level){
    label4.setText(Integer.toString(level)+"  ");
  }

  public void updateScore(int score){
    label6.setText(Integer.toString(score)+"  ");
  }

}
