package random_generic;

import java.util.ArrayList;
import java.util.Iterator;


public class temp { 	
    public static void main(String [] args){
    	RandomList<String> rl = new RandomList<String>();
    	rl.add("aa");
    	rl.add("bb");
    	rl.add("cc");
    	rl.add("dd");
    	rl.add("ee");
    	System.out.println(rl.select());
    	System.out.println(rl.select());
    	System.out.println(rl.select());
    	System.out.println(rl.select());
   }
}