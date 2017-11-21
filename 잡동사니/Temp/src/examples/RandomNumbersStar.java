package examples;

public class RandomNumbersStar {

    public static void main(String[] args){
    	int[] array= new int[100];
    	int[] check= new int[10];
    	int temp=0;
    	for(int i=0;i<array.length;i++) {
    		array[i] = (int)(Math.random()*100);
    		//System.out.println(array[i]);
    		temp=array[i]/10;
    		//System.out.println(temp);
    		check[temp]++;
    	}
    	for(int i=0;i<check.length;i++) {
    		System.out.print(i+"1"+" ~ "+(i+1)+"0 : ");
    		for(int k=0;k<check[i];k++) {
    			System.out.print("*");
    		}
    		System.out.println("\t\t"+check[i]+"°³");
    	}
    }

}
