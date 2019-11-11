import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.*;

public class Board extends JPanel implements KeyListener {
    static final int BLOCK_SIZE = 30;
    private Snake snake;
    private Fruit fruit;
    private Splash splash;
    private Timer timer;
    private Timer clock;
    private int seconds;
    private boolean paused;
    private int gameOver; //0 not started, 1 started, 2 gg
    private int level;
    private Top top;
    private int score;
    private boolean splashreset;
    private ArrayList<Integer> scorelist;
    private Clip eat;
    private Clip applause;
    //board is 1200 = 40*30, 660 = 22*30
    public Board(Top topp){
      level = 1;
      this.top = topp;
      this.setLayout(new GridBagLayout());
      GridBagConstraints gc = new GridBagConstraints();
      splash = new Splash();
      gc.gridx=0;
      gc.gridy=0;
      add(splash, gc);
      snake = new Snake();
      fruit = new Fruit(snake);
      timer = new Timer();
      clock = new Timer();
      paused = false;
      splashreset = false;
      gameOver = 0;
      score = 0;
      scorelist = new ArrayList<Integer>();
      top.updateScore(score);
      top.updateLevel(1);
      this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
      this.setPreferredSize(new Dimension(1200, 660));
      loadData();
      loadClips();
    }


    public void countDown(){
      clock.schedule(new TimerTask(){
        @Override
        public void run(){
          top.updateTimer(seconds);
          seconds--;
          if(seconds<0){
            cancel();
            level += 1;
            top.updateLevel(level);
            openGame();
          }
        }
      }, 0, 1000);
    }


    public void openGame(){
      if(paused==false){
        if(level == 1){
          fruit.initSome();
          seconds = 30;
        }else if(level == 2){
          fruit.initMany();
          seconds = 30;
        }else if(level == 3){
          fruit.initLots();
          seconds = -1;
          top.updateTimer(seconds);
        }
      }

      if(level!=3){
        countDown();
      }

      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          snake.move();
          checkCollision();
          repaint();
        }
      }, 0, (500-120*level));
    }

    public void checkCollision(){
      Coordinates head = snake.getHead();
      int x = head.getX();
      int y = head.getY();
      //check fruit collision
      int status = fruit.checkCollision(x, y);
      if(status==1){
        eat.setFramePosition(0);
        eat.start();
        snake.grow();
        score+=1;
        top.updateScore(score);
      }

      //check border hit
      status = snake.checkHitWall();
      if(status == 1){
        endGame();
      }

      //todo
      status = snake.crash();
      if(status == 1){
        endGame();
      }
    }

    public void endGame(){
      applause.setFramePosition(0);
      applause.start();
      System.out.println("Game Over!");
      gameOver = 2;
      timer.cancel();
      clock.cancel();
      scorelist.add(score);
      Collections.sort(scorelist);
      setData();
      splash.updateSplash(scorelist);
      splash.showResult();
      splash.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics graphics){
      super.paintComponent(graphics);
      fruit.paintComponent(graphics);
      snake.paintComponent(graphics);
    }

    @Override
    public void keyPressed(KeyEvent e) {
      if(gameOver==0){
        //before the game starts, press any key to play
        gameOver=1;
        splash.setVisible(false);
        openGame();
      }else if((gameOver==1)&&(splashreset==true)){
        // press any key to hide splash screen and continue game
          splash.setVisible(false);
          splashreset=false;
          timer = new Timer();
          clock = new Timer();
          this.openGame();
          paused = false;
      }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
        //Right-arrow: turn the snake right
        if(gameOver==1){
          snake.turnRight();
          snake.move();
          checkCollision();
        }
      }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
        //Left-arrow: turn the snake left
        if(gameOver==1){
          snake.turnLeft();
          snake.move();
          checkCollision();
        }
      }else if(e.getKeyCode() == KeyEvent.VK_P){
        //P: pause and un-pause the game
        if(gameOver==1){
          if(paused == false){
            timer.cancel();
            clock.cancel();
            paused = true;
          }else{
            timer = new Timer();
            clock = new Timer();
            this.openGame();
            paused = false;
          }
        }
      }else if(e.getKeyCode() == KeyEvent.VK_1){
        //1: start level 1
        if(gameOver==1){
          if(paused == false){
            if(level != 1){
              level = 1;
              timer.cancel();
              clock.cancel();
              timer = new Timer();
              clock = new Timer();
              top.updateLevel(1);
              openGame();
            }
          }
        }
      }else if(e.getKeyCode() == KeyEvent.VK_2){
        //2: start level 2
        if(gameOver==1){
          if(paused == false){
            if(level != 2){
              level = 2;
              timer.cancel();
              clock.cancel();
              timer = new Timer();
              clock = new Timer();
              top.updateLevel(2);
              openGame();
            }
          }
        }
      }else if(e.getKeyCode() == KeyEvent.VK_3){
        //3: start level 3
        if(gameOver==1){
          if(paused == false){
            if(level != 3){
              level = 3;
              timer.cancel();
              clock.cancel();
              timer = new Timer();
              clock = new Timer();
              top.updateLevel(3);
              openGame();
            }
          }
        }
      }else if(e.getKeyCode() == KeyEvent.VK_R){
        //R: reset to the splash screen
        if(gameOver==1){
          if(splashreset==false){
            splash.setVisible(true);
            splashreset=true;
            timer.cancel();
            clock.cancel();
            paused = true;
          }
        }
      }else if(e.getKeyCode() == KeyEvent.VK_Q){
        //Q: quit and display the high score screen
        endGame();
      }else if(e.getKeyCode() == KeyEvent.VK_S){
        //S: gets shorter and easier to play
        if(gameOver==1){
          if(paused == false){
            snake.shorter();
          }
        }
      }else if(e.getKeyCode() == KeyEvent.VK_C){
        //C: power up to a colourful snake
        if(gameOver==1){
          if(paused == false){
            snake.setRandomColor();
          }
        }
      }

      repaint();

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

   @Override
   public void keyReleased(KeyEvent e) {
   }

   public void loadData(){
     try{
       String f = "./info";
       File file = new File(f);
       FileReader fr = new FileReader(file);
       BufferedReader br = new BufferedReader(fr);
       Integer score;
       String record;
       while((record = br.readLine()) != null){
          score = Integer.parseInt(record);
          scorelist.add(score);
       }
     }catch(IOException e){
       System.out.println("load file failed, either file does not exist or the file has corrupt data.");
     }
   }

   public void setData(){
     try{
       BufferedWriter writer=new BufferedWriter(new FileWriter("info"));
       for(Integer in : scorelist){
         writer.write("" + in);
         writer.newLine();
       }
       writer.flush();
       writer.close();
     }catch(IOException e){
       System.out.println("set file failed, either file does not exist or the file has corrupt data.");
     }
   }

   public void loadClips(){
     try {
         File f1 = new File("eat.wav");
         AudioInputStream audioinput1 = AudioSystem.getAudioInputStream(f1);
         eat = AudioSystem.getClip();
         eat.open(audioinput1);

         File f2 = new File("applause.wav");
         AudioInputStream audioinput2 = AudioSystem.getAudioInputStream(f2);
         applause = AudioSystem.getClip();
         applause.open(audioinput2);
     } catch (Exception e) {
         e.printStackTrace();
     }
   }

}
