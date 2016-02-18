/*
 * Copyright (c) 2002-2006, Marc Prud'hommeaux. All rights reserved.
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 */
package "vigilant-waddle";

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

	game.init();
	
	try {
	    int i = 0;
	    while(i != (int)'q') {
		i = reader.readCharacter(allowed);
		System.out.println(i);
		
		game.processChar(i);
		game.printScreen();
		TimeUnit.MILLISECONDS.sleep(100);
	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }
}
