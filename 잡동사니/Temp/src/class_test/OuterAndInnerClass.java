package class_test;
import java.util.Scanner;
class Outer 
{
    class Inner { }
}
class Test 
{
    public static void main (String [] args) 
    {
        Outer f = new Outer();
        Outer.Inner inner = f.new Inner();
    }
}