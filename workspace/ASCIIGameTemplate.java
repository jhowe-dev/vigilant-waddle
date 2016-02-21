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
    game = new ASCIIScreen();

    ConsoleReader reader = new ConsoleReader(System.in, new PrintWriter(System.out));

    char[] allowed = {'i','j','k','l','q'};
    String[][] clock = new String[13][25];
    Scanner sc = new Scanner(new File("../clock.txt")).useDelimiter("p\\s+");
    int i = 0; 
    int j = 0;
    boolean nextRow = false;
    boolean reading = false;
    while(sc.hasNext()){
      if(sc.next() == "^" && !reading){ //got to the first ^ character
        clock[i][j] = " ";
        j++;
        reading = true;
          
    System.out.println("HAPPENING!!!!");
      }
      else if(sc.next() == "^" && reading){//got to last ^ character
        clock[i][j] = " ";
        i++;
        j = 0;
        reading = false;
      }
      if(reading){
        clock[i][j] = sc.next();
        j++;
      }

    }
    System.out.println(clock[0][0]);
    game.init(clock);

    try {
      int c = 1;
      while(c != (int)'q') {
        //c = reader.readCharacter(allowed);
        //System.out.println(c);

        game.processChar(c);
        game.printScreen();
        TimeUnit.MILLISECONDS.sleep(100);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
