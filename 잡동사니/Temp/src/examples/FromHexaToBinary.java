package examples;
import java.util.Arrays;
import java.util.Scanner;

public class FromHexaToBinary {
    public static void main(String[] args){
    	String[] hexa2bin = {"0000", "0001", "0010", "0011",
                "0100", "0101", "0110", "0111",
                "1000", "1001", "1010", "1011",
                "1100", "1101", "1110", "1111"};
    	int[] check = new int[16];
    	System.out.print("16진수 문자열을 입력하시오 : ");
    	Scanner scan = new Scanner(System.in);
    	String input = scan.nextLine();
    	System.out.println(input+"에 대한 2진수 문자열");
    	
    	for(int i=0;i<input.length();i++) {
    		System.out.print(input.charAt(i));
    		check[i]=(int)input.charAt(i);
    		System.out.println(check[i]);
    		if(check[i]>95) {
    			check[i]-=86;
    			System.out.println(hexa2bin[check[i]]);
    		}else {
    		System.out.println(hexa2bin[check[i]-48]);
    		}
    	}
    }
}