package class_test;

public class Box {
	public static int tessss;
	private int row;
	private int column;
	private int height;
	public Box(int row, int column, int height){
		super();
		this.row=row;
		this.column=column;
		this.height=height;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row=row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column=column;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height=height;
	}
	public int cal_cube() {
		return row*column*height;
	}
}
