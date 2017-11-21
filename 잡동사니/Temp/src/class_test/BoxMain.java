package class_test;

public class BoxMain {
	public final static int SIZE=5;
	public static void main(String[]args) {
		int result=0;
		Box[] box = new Box[SIZE];
		
//		for(int i=0;i<box.length;i++) {
//			box[i] = new Box((int)(Math.random()*(50-10+1)+10), (int)(Math.random()*(50-10+1)+10), (int)(Math.random()*(50-10+1)+10));
//		}
//		//box[i].set방식으로는 값을 받지 못함
//		int i=0;
//		for(Box value:box) {
//			i++;
//			System.out.println(i+"번째  정육면체 가로 :"+value.getRow()+", 세로 : "+value.getColumn()+", 높이 : "+value.getHeight()+", 부피 : "+value.cal_cube(value.getRow(), value.getColumn(), value.getHeight()));
//		}
		input(box);
		print(box);
	}
	public static void input(Box[] box) {
		for(int i=0;i<box.length;i++) {
			box[i] = new Box((int)(Math.random()*(50-10+1)+10), (int)(Math.random()*(50-10+1)+10), (int)(Math.random()*(50-10+1)+10));
		}
	}
	public static void print(Box[] box) {
		int i=0;
		for(Box value:box) {
			i++;
			System.out.println(i+"번째  정육면체 가로 :"+value.getRow()+", 세로 : "+value.getColumn()+", 높이 : "+value.getHeight()+", 부피 : "+value.cal_cube());
		}
	}
}