import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Splash extends JPanel{
  ArrayList<Integer> scorelist;
  public Splash(){
    this.setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();
    gc.gridx=0;
    gc.gridy=0;
    JLabel label1 = new JLabel("Welcome to the SNAKE!");
    label1.setFont(new Font("Verdana",2,40));
    add(label1, gc);
    gc.gridx=0;
    gc.gridy=2;
    JLabel author = new JLabel("author: Mingze Lin, userid: m59lin");
    author.setFont(new Font("Verdana",2,15));
    add(author, gc);
    gc.gridx=0;
    gc.gridy=3;
    JLabel label2 = new JLabel("Control the snake with left or right keys.");
    label2.setFont(new Font("Verdana",2,20));
    add(label2, gc);
    gc.gridx=0;
    gc.gridy=4;
    JLabel label3 = new JLabel("The snake grows when eats the fruit.");
    label3.setFont(new Font("Verdana",2,20));
    add(label3, gc);
    gc.gridx=0;
    gc.gridy=5;
    JLabel label4 = new JLabel("The snake dies by eating itself or by hit the bounds.");
    label4.setFont(new Font("Verdana",2,20));
    add(label4, gc);
    gc.gridx=0;
    gc.gridy=6;
    JLabel label5 = new JLabel("Left-arrow: turn the snake left");
    label5.setFont(new Font("Verdana",2,20));
    add(label5, gc);

    gc.gridx=0;
    gc.gridy=11;
    JLabel label6 = new JLabel("Right-arrow: turn the snake right");
    label6.setFont(new Font("Verdana",2,20));
    add(label6, gc);
    gc.gridx=0;
    gc.gridy=12;
    JLabel label7 = new JLabel("P: pause and un-pause the game");
    label7.setFont(new Font("Verdana",2,20));
    add(label7, gc);
    gc.gridx=0;
    gc.gridy=13;
    JLabel label8 = new JLabel("R: reset to the splash screen");
    label8.setFont(new Font("Verdana",2,20));
    add(label8, gc);
    gc.gridx=0;
    gc.gridy=14;
    JLabel label9 = new JLabel("1: start level 1");
    label9.setFont(new Font("Verdana",2,20));
    add(label9, gc);
    gc.gridx=0;
    gc.gridy=15;
    JLabel label10 = new JLabel("2: start level 2");
    label10.setFont(new Font("Verdana",2,20));
    add(label10, gc);
    gc.gridx=0;
    gc.gridy=16;
    JLabel label11 = new JLabel("3: start level 3");
    label11.setFont(new Font("Verdana",2,20));
    add(label11, gc);
    gc.gridx=0;
    gc.gridy=17;
    JLabel label12 = new JLabel("Q: quit and display the high score screen");
    label12.setFont(new Font("Verdana",2,20));
    add(label12, gc);

    gc.gridx=0;
    gc.gridy=18;
    JLabel label13 = new JLabel("------------> Press ANY KEY to play the game! ");
    label13.setFont(new Font("Verdana",1,20));
    add(label13, gc);

    setVisible(true);
    setPreferredSize(new Dimension(750,500));
    setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
  }

  public void showResult(){
    removeAll();
    GridBagConstraints gc = new GridBagConstraints();
    gc.gridx=0;
    gc.gridy=0;
    JLabel label1 = new JLabel("Top 10 Score:");
    label1.setFont(new Font("Verdana",2,40));
    add(label1, gc);

    int length = 10;
    int size = scorelist.size();
    if(size<length){
      length=size;
    }
    for(int i=0; i<length; i++){
      gc.gridx=0;
      gc.gridy= 1+i;
      JLabel label = new JLabel("No."+ Integer.toString(i+1) + " : "+Integer.toString(scorelist.get(size-1-i)));
      label.setFont(new Font("Verdana",2,20));
      add(label, gc);
    }

  }

  public void updateSplash(ArrayList<Integer> list){
    scorelist = list;
  }
}
