/*
 * Copyright (c) 2002-2006, Marc Prud'hommeaux. All rights reserved.
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 */
package workspace;

import java.util.concurrent.*;
import jline.*;
import java.math.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;
/************************************************************
 * This code wraps an ASCII game so that we can process individual
 * char inputs (otherwise, Java only likes to process whole lines
 * followed by "enter" key).
 ************************************************************/

public class ClockGame{

  static ASCIIScreen game;
  static ConsoleReader reader;
  public static void main(String[] args) throws IOException {
    Character mask = null;
    String trigger = null;
    int position = 0;
    int speed = 1000;
    int score = 0;
    final String ANSI_CLS = "\u001b[2J";
    final String ANSI_HOME = "\u001b[H";
    game = new ASCIIScreen();

    reader = new ConsoleReader(System.in, new PrintWriter(System.out));

    char[] allowed = {'i','j','k','l','q',' '};
    String[][] clock = new String[13][25];
    Scanner sc = new Scanner(new File("../clock.txt"));
    int i = 0; 
    int j = 0;
    int target=0;

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

    System.out.println("Ready to play? Press i!");
    int in = reader.readCharacter(allowed);
    System.out.println("Your score is " + score + " How sad..");

    if(in == (int) 'i'){
    	target =(int) Math.floor(Math.random() * 12);
	if(target == 0){
    		System.out.println("12" + " is the target time, stop the clock here with space");
	}
	else
		System.out.println(target + " is the target time, stop the clock with space");
	try{
    		TimeUnit.MILLISECONDS.sleep(3000);
    		game.init(clock);
	}
	catch(InterruptedException e){
		e.printStackTrace();
	}
    }
    else{
	    System.out.println("Fine, I didnt want to play anyway..");
	    System.exit(0);
    }
    try {
      WaitForCharThread wfct = new WaitForCharThread();
      wfct.start();

      int c = -1;
      while(c != (int)'q') {
        
	if(wfct.i !=0){
		c = wfct.i;
		game.processChar(c,position,target);
		game.updateScreen(position, clock);
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
		game.printScreen(score,target);
		target = (int) Math.floor(Math.random()*12);
		TimeUnit.MILLISECONDS.sleep(1000);
		speed -= 100;
        System.out.println("Speed is: " + speed);
		score += 1;
		wfct = new WaitForCharThread();
		wfct.start();
		
	}
       	else{
		game.processChar(0,0,0);
		game.updateScreen(position, clock);
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
		game.printScreen(score,target);
		TimeUnit.MILLISECONDS.sleep(speed);
	} 
        position = (position + 1) % 12;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}// end of class


class WaitForCharThread extends Thread{


	int i = 0;
	static int count = 0;
	char[] allowed = {'i','j','k','l','q',' '};

	public void run(){
		try{
			i = ClockGame.reader.readCharacter(allowed);
		} catch (IOException e){
			System.out.println(e);
		}
	}
}
