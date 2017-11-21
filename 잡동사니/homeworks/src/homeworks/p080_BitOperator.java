//만든이 : 이동규
package homeworks;

public class p080_BitOperator {
	public static void main(String[]args){
		int x=0x0fff, y=0xfff0;
		System.out.printf("%x\n",(x&y));
		System.out.printf("%x\n",(x|y));
		System.out.printf("%x\n",(x^y));
		System.out.printf("%x\n",(~x));
		System.out.printf("%x\n",(x<<4));
		System.out.printf("%x\n",(x>>4));
		System.out.printf("%x\n",(-1>>>4));
	}
}