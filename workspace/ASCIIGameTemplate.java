/*
 * Copyright (c) 2002-2006, Marc Prud'hommeaux. All rights reserved.
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 */
package workspace;

import java.util.concurrent.*;
import jline.*;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/************************************************************
 * This code wraps an ASCII game so that we can process individual
 * char inputs (otherwise, Java only likes to process whole lines
 * followed by "enter" key).
 ************************************************************/

public class ASCIIGameTemplate {

  static ASCIIScreen game;

  public static void main(String[] args) throws IOException {
    Character mask = null;
    String trigger = null;
    int position = 0;
    int speed = 1000;
    game = new ASCIIScreen();

    ConsoleReader reader = new ConsoleReader(System.in, new PrintWriter(System.out));

    char[] allowed = {'i','j','k','l','q'};
    String[][] clock = new String[13][25];
    Scanner sc = new Scanner(new File("../clock.txt"));
    int i = 0; 
    int j = 0;
    while(sc.hasNext()){
      String str = sc.next();
      for(int x = 0; x < str.length(); x++){
        if(str.charAt(x) == 'p'){ //insert whitespace
          clock[i][j] = " ";
          j++;
        }
        else if(str.charAt(x) == 'n'){//increment i to next row
          i++;
          j = 0;
        }
        else {//regular character
          clock[i][j] = str.charAt(x) + "";
          j++;
        }
      }
    }
    sc.close();
    game.init(clock);

    try {
      int c = 1;
      while(c != (int)'q') {
        //c = reader.readCharacter(allowed);
        System.out.println(c);
        game.processChar(c);
        game.updateScreen(position, clock);
        game.printScreen();
        TimeUnit.MILLISECONDS.sleep(speed);
        
        position = (position + 1) % 12;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}// end of class
