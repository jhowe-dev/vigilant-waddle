package workspace;

import java.util.concurrent.*;
import static java.lang.Math.*;

/**
 * Code for this class currently runs a local main() for testing (shows
 * a ball moving across the screen) or operates with ASCIIGameTemplate
 * main() to allow keyboard input (see allowed chars below) to move the ball
 * right.
 **/

class ASCIIScreen {

  static StringBuilder[] screen;
  static StringBuilder line;
  static StringBuilder blank;
  final static int WIDTH = 74;
  final static int HEIGHT = 20;
  int clockX;
  int clockY;

  /*******************************************************************
   * Constructor - initializes screen and line
   *******************************************************************/
  ASCIIScreen(){

    screen = new StringBuilder[HEIGHT];

    blank = new StringBuilder("||");
    for (int i = 0; i < WIDTH-2; i++) blank.insert(1, ' ');

    for(int i = 0; i < HEIGHT; i++)
      screen[i] = new StringBuilder(blank); //WHY?

    line = new StringBuilder("");
    for (int i = 0; i < WIDTH; i++) line.append('_');
  }

  /*******************************************************************
   * Print the current state.
   *******************************************************************/
  void printScreen(int score,int target){
    if(target == 0){
      System.out.println("Score: " + score + " Target: 12");
    }
    else{
      System.out.println("Score: " + score + " Target: " + target);
    }
    //System.out.println("Score: " + score + " Target: " + (target-1));
    System.out.println(line);
    for (int j = 0; j < HEIGHT; j++)
      System.out.println(screen[j]);
    System.out.println(line);
  }

  /******************************************************************
   * Assume row 0 is in the game, but first and last cols are not
   * (they are pipes '|'). x,y are coordinates of center.
   ******************************************************************/
  void putClockInScreen(int x, int y, String[][] clock){
    int i = 0;
    int j = 0;
    for(int row = y; row < y + 13; row++){
      for(int col = x; col < x + 25; col++){
        screen[row].deleteCharAt(col);
        screen[row].insert(col, clock[i][j].charAt(0));
        j++;
      }
      i++; 
      j = 0;
    }

    clockX = x;
    clockY = y;
  }

  void clearScreen(){
    for(int i = 0; i < HEIGHT; i++){
      for(int j = 1; j < WIDTH - 2; j++){
        screen[i].deleteCharAt(j);
        screen[i].insert(j, ' ');
      }
    } 
  }

  // delete the previous spots of the hour hand 
  // and update where they should be for this time
  void noon(String[][] clock){
    clearScreen();
    clock[4][11] = " ";
    clock[3][10] = " ";
    clock[4][12] = "|";
    clock[3][12] = "|";
    clock[2][12] = "^";
  }

  void one(String[][] clock){
    clearScreen();
    clock[4][12] = " ";
    clock[3][12] = " ";
    clock[2][12] = " ";
    clock[4][13] = "/";
    clock[3][14] = "/";
  }

  void two(String[][] clock){
    clearScreen();
    clock[4][13] = " ";
    clock[3][14] = " ";
    clock[5][14] = "/";
    clock[4][16] = "/";
  }

  void three(String[][] clock){
    clearScreen();
    clock[5][14] = " ";
    clock[4][16] = " ";
    clock[5][14] = "-";
    clock[5][16] = "-";
    clock[5][17] = ">";
  }

  void four(String[][] clock){
    clearScreen();
    clock[5][14] = " ";
    clock[5][16] = " ";
    clock[5][17] = " ";
    clock[5][13] = "\\";
    clock[6][15] = "\\";
  }

  void five(String[][] clock){
    clearScreen();
    clock[5][13] = " ";
    clock[6][15] = " ";
    clock[6][13] = "\\";
    clock[7][15] = "\\";
  }

  void six(String[][] clock){
    clearScreen();
    clock[6][13] = " ";
    clock[7][15] = " ";
    clock[6][12] = "|";
    clock[7][12] = "|";
    clock[8][12] = ".";
  }

  void seven(String[][] clock){
    clearScreen();
    clock[6][12] = " ";
    clock[7][12] = " ";
    clock[8][12] = " ";
    clock[6][11] = "/";
    clock[7][10] = "/";
  }

  void eight(String[][] clock){
    clearScreen();
    clock[6][11] = " ";
    clock[7][10] = " ";
    clock[5][11] = "/";
    clock[6][9] = "/";
  }

  void nine(String[][] clock){
    clearScreen();
    clock[5][11] = " ";
    clock[6][9] = " ";
    clock[5][10] = "-";
    clock[5][8] = "-";
    clock[5][6] = "<";
  }

  void ten(String[][] clock){
    clearScreen();
    clock[5][10] = " ";
    clock[5][8] = " ";
    clock[5][6] = " ";
    clock[5][11] = "\\";
    clock[4][9] = "\\";
  }

  void eleven(String[][] clock){
    clearScreen();
    clock[5][11] = " ";
    clock[4][9] = " ";
    clock[4][11] = "\\";
    clock[3][10] = "\\";
  }

  void updateScreen(int position, String[][] clock){
    if (position == 0){//noon
      noon(clock); 
    } 
    else if(position == 1){
      one(clock); 
    }

    else if(position == 2){
      two(clock);
    }  

    else if(position == 3){
      three(clock);
    }

    else if(position == 4){
      four(clock);
    }

    else if(position == 5){
      five(clock);
    }

    else if(position == 6){
      six(clock);
    }

    else if(position == 7){
      seven(clock);
    }

    else if(position == 8){
      eight(clock);
    }

    else if(position == 9){
      nine(clock);
    }

    else if(position == 10){
      ten(clock);
    }

    else {//11'oclock
      eleven(clock);
    }

    putClockInScreen(clockX, clockY, clock);

  }
  /* Initialize game pieces.
   ********************************************************************/
  void init(String[][] clock){
    putClockInScreen(20,5, clock);
  }

  /********************************************************************
   * Have game respond to a single character input.
   ********************************************************************/
  void processChar(int i, int pos, int target){
    switch(i){
      case ' ':
        if(target == 0){
          if(pos - 1 > 0){
            System.out.println("You stopped on " + (pos - 1) + " The target was 12");
            pos -= 1;
          }
          else{
            System.out.println("You stopped on 12" + " The target was 12");
            pos = 0;
          }
        }
        else {
          if(pos - 1 > 0){
            System.out.println("You stopped on " + (pos - 1) + " The target was " + target);
            pos -= 1;
          }
          else {
            System.out.println("You stopped on 11" + " The target was " + target);
            pos = 11;
          }
        }
        if(pos == target)
        {
          System.out.println("You scored!");
          try{
            TimeUnit.MILLISECONDS.sleep(2000);
          }
          catch(InterruptedException e){
            e.printStackTrace();
          }

        }
        else
        {
          System.out.println("That wasn't the right time!");
          System.exit(0);
        }	  
    }
  }
}
