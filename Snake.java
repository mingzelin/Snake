import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Random;

public class Snake extends JComponent{
  private ArrayList<Coordinates> collection;//head is [0]
  private int direction; //N=0,E=1,S=2,W=3
  //private int size;
  private Coordinates shadow;
  private boolean randomColor;
  public Snake(){
    collection = new ArrayList<Coordinates>();
    shadow = new Coordinates();
    direction = 1;
    randomColor = false;
    // size =3;
    int initX = 10*Board.BLOCK_SIZE;
    int initY = 5*Board.BLOCK_SIZE;
    for(int i=0; i<3; i++){
      Coordinates position = new Coordinates();
      position.setX(initX-i*Board.BLOCK_SIZE);
      position.setY(initY);
      collection.add(position);
    }
  }

  public void setRandomColor(){
    if(!randomColor){
      randomColor = true;
    }else{
      randomColor = false;
    }
  }

  public Coordinates getHead(){
    return collection.get(0);
  }

  public ArrayList<Coordinates> getBody(){
    return collection;
  }

  public void grow(){
    Coordinates additional = new Coordinates();
    int length = collection.size();
    additional.setX(shadow.getX());
    additional.setY(shadow.getY());
    collection.add(length, additional);
  }

  public void shorter(){
    int length = collection.size();
    if(length>3){
      collection.remove(length-1);
    }
  }

  public void move(){
    int length = collection.size();
    //shadow keeps track of the last visited block
    shadow.setX(collection.get(length-1).getX());
    shadow.setY(collection.get(length-1).getY());
    //remove the last
    collection.remove(length-1);
    //add a new head depend on current direction
    //N=0,E=1,S=2,W=3
    Coordinates pos = new Coordinates();
    if(direction==0){
      int newX = collection.get(0).getX();
      int newY = collection.get(0).getY()-1*Board.BLOCK_SIZE;
      pos.setX(newX);
      pos.setY(newY);
    }else if(direction==1){
      int newX = collection.get(0).getX()+1*Board.BLOCK_SIZE;
      int newY = collection.get(0).getY();
      pos.setX(newX);
      pos.setY(newY);
    }else if(direction==2){
      int newX = collection.get(0).getX();
      int newY = collection.get(0).getY()+1*Board.BLOCK_SIZE;
      pos.setX(newX);
      pos.setY(newY);
    }else if(direction==3){
      int newX = collection.get(0).getX()-1*Board.BLOCK_SIZE;
      int newY = collection.get(0).getY();
      pos.setX(newX);
      pos.setY(newY);
    }
    collection.add(0, pos);
  }

  // 1=hit wall, 0= fine
  public int checkHitWall(){
    //board is 1200 = 40*30, 660 = 22*30
    int x = getHead().getX();
    int y = getHead().getY();

    if((x<0)||(x>=1200)||(y<0)||(y>=660)){
      return 1;
    }
    return 0;

  }

  public void turnRight(){
    //N=0,E=1,S=2,W=3
    if(direction==0){
      direction=1;
    }else if(direction==1){
      direction=2;
    }else if(direction==2){
      direction=3;
    }else if(direction==3){
      direction=0;
    }
  }

  public void turnLeft(){
    //N=0,E=1,S=2,W=3
    if(direction==0){
      direction=3;
    }else if(direction==1){
      direction=0;
    }else if(direction==2){
      direction=1;
    }else if(direction==3){
      direction=2;
    }
  }
  //snake hit itself  1=hit, 0=fine
  public int crash(){
    //count the num of diff block for snake, it should eqaul size of snake
    int length = collection.size();
    Dictionary<String, Integer> unique = new Hashtable<String, Integer>();
    for(int i=0; i<length; i++){
      int x = collection.get(i).getX();
      int y = collection.get(i).getY();

      String combine = String.valueOf(x) + "00" +String.valueOf(y);
      if(unique.get(combine)!=null){
        //if not null -> crashed
        return 1;
      }
      unique.put(combine, 1);
    }

    return 0;
  }

  @Override
  public void paintComponent(Graphics graphics){
    super.paintComponent(graphics);
    int length = collection.size();
    for(int i=0; i<length; i++){
      Coordinates item = collection.get(i);
      if(i==0){
        Color c = new Color(204, 204, 204);
        graphics.setColor(c);
      }else{
        if(randomColor){
          Random rand = new Random();
          Color r = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
          graphics.setColor(r);
        }else{
          graphics.setColor(Color.white);
        }

      }
      graphics.fillRect(item.getX(), item.getY(), Board.BLOCK_SIZE, Board.BLOCK_SIZE);
      graphics.setColor(Color.black);
      graphics.drawRect(item.getX(), item.getY(), Board.BLOCK_SIZE, Board.BLOCK_SIZE);


    }
  }

}
