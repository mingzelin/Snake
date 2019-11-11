import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

public class Fruit extends JComponent{
  private ArrayList<Coordinates> collection;
  private Snake snake;

  public Fruit(Snake python){
    collection = new ArrayList<Coordinates>();
    snake = python;
  }

  public void addFruit(int xx, int yy){
    if(xx==-1){//random points
      Random rand = new Random();
      int x = rand.nextInt(40);
      int y = rand.nextInt(22);
      while(checkRepeat(x, y)){
        x = rand.nextInt(40);
        y = rand.nextInt(22);
      }
      Coordinates position = new Coordinates();
      position.setX(x*Board.BLOCK_SIZE);
      position.setY(y*Board.BLOCK_SIZE);
      collection.add(position);
    }else{//determined points
      Random rand = new Random();
      int x = xx;
      int y = yy;
      while(checkRepeat(x/Board.BLOCK_SIZE, y/Board.BLOCK_SIZE)){
        x = rand.nextInt(40)*Board.BLOCK_SIZE;
        y = rand.nextInt(22)*Board.BLOCK_SIZE;
      }
      Coordinates position = new Coordinates();
       position.setX(x);
       position.setY(y);
       collection.add(position);
    }

  }


  //1=collide, 0 = not
  public int checkCollision(int x, int y){
    int len = collection.size();

    for(int i = 0; i<len; i++){
      Coordinates item = collection.get(i);
      if((item.getX()==x)&&(item.getY()==y)){
        //collide
        collection.remove(i);
        addFruit(-1, -1);
        return 1;
      }
    }
    return 0;
  }

  public boolean checkRepeat(int x, int y){
    int length = collection.size();
    for(int i=0; i<length; i++){
      Coordinates item = collection.get(i);
      //no fruit should not repeat coordinates.
      if((item.getX()==x)&&(item.getY()==y)){
        return true;
      }
    }
    ArrayList<Coordinates> body = snake.getBody();
    length = body.size();
    for(int i=0; i<length; i++){
      Coordinates itemB = body.get(i);
      //no fruit should not be on the body of the snake.
      if((itemB.getX()==x)&&(itemB.getY()==y)){
          return true;
      }
    }
    return false;
  }


  public void initLots(){
    initMany();
    addFruit(330,540);
    addFruit(270,240);
    addFruit(1140,450);
    addFruit(660,90);
    addFruit(180,150);
  }

  public void initMany(){
    initSome();
    addFruit(750,30);
    addFruit(810,90);
    addFruit(1050,450);
    addFruit(150,120);
    addFruit(300,600);
    addFruit(1110,360);
    addFruit(900,180);
    addFruit(240,270);
    addFruit(1080,60);
    addFruit(420,510);
  }

  public void initSome(){
    collection.clear();
    addFruit(1110,270);
    addFruit(480,270);
    addFruit(390,300);
    addFruit(270,480);
    addFruit(420,480);
  }

  @Override
  public void paintComponent(Graphics graphics){
    super.paintComponent(graphics);
    int length = collection.size();
    for(int i=0; i<length; i++){
      Coordinates item = collection.get(i);
      graphics.setColor(Color.red);
      graphics.fillOval(item.getX(), item.getY(), Board.BLOCK_SIZE, Board.BLOCK_SIZE);
      graphics.setColor(Color.black);
      graphics.drawOval(item.getX(), item.getY(), Board.BLOCK_SIZE, Board.BLOCK_SIZE);
    }
  }

}
