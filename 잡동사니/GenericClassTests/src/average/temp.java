package average;

import java.util.ArrayList;
import java.util.Iterator;


public class temp { 	
    public static void main(String [] args){
    	MyMath<Double> mm = new MyMath<Double>(10.0, 3.0);
    	System.out.println(mm.getAverage());
    	MyMath<Integer> dd = new MyMath<Integer>(10, 3);
    	System.out.println(dd.getAverage());
   }
}