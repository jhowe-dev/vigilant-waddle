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
  int ballX;
  int ballY;

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
  void printScreen(){

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

    // for (int row = max(y-1, 0); row < min(HEIGHT, y+2); row++){

    //   //Find where to start printing ball in row. Width depends on which row.
    //   int widthOffset = row == y ? 2 : 1;

    //   for (int col = max(1, x - widthOffset); col < min(WIDTH-1, x + widthOffset + 1); col++){
    //     screen[row].deleteCharAt(col);
    //     screen[row].insert(col, '*');
    //   }
    //   ballX = x;
    //   ballY = y;
    // }	
    int i = 0;
    int j = 0;
    //for(int row = max(y-6, 0); row < min(HEIGHT, y+5); row++){
    //  for(int col = max(1, x - 13); col < min(WIDTH - 1, x + 10); col++){
    //    screen[row].deleteCharAt(col);
    //    screen[row].insert(col, clock[i][j].charAt(0));
    //    i++;
    //  }
    //  
    //}
    
    for(int row = y; row < y + 13; row++){
      for(int col = x; col < x + 25; col++){
        screen[row].deleteCharAt(col);
        screen[row].insert(col, clock[i][j].charAt(0));
        j++;
      }
     i++; 
     j = 0;
    }
    
    ballX = x;
    ballY = y;
  }

  /******************************************************************
   * x,y is current center of ball
   ******************************************************************/
  void moveBallRight(){
    int x = ballX;
    int y = ballY;
    System.out.println("moving ball right");
    for (int row = max(y-1, 0); row < min(HEIGHT, y+2); row++){

      //Find where to start printing ball in row. Width depends on which row.
      int widthOffset = row == y ? 2 : 1;

      if ((x - widthOffset) > 0) {
        screen[row].replace(x - widthOffset, x - widthOffset + 1, " ");
      }
      if ((x + widthOffset) < WIDTH) {
        screen[row].replace(x + widthOffset, x + widthOffset + 1, "*");
      }
    }
    ballX++;
  }

  /********************************************************************
   * Initialize game pieces.
   ********************************************************************/
  void init(String[][] clock){
    putClockInScreen(20,5, clock);
  }

  /********************************************************************
   * Have game respond to a single character input.
   ********************************************************************/
  void processChar(int i){
    switch(i){
      case 'l':
        //moveBallRight();
    }
  }

  /********************************************************************
   * For testing purposes only.
   ********************************************************************/
  //  public static void main(String[] a){
  //
  //    ASCIIScreen game = new ASCIIScreen();
  //
  //    try {
  //      game.putClockInScreen(0,8);
  //      game.printScreen();
  //      TimeUnit.MILLISECONDS.sleep(100);
  //
  //      for(int i = 0; i < 25; i++){
  //        game.moveBallRight();
  //        game.printScreen();
  //        TimeUnit.MILLISECONDS.sleep(100);
  //      }
  //    } catch (InterruptedException e) {
  //      e.printStackTrace();
  //    }
  //  }
}

