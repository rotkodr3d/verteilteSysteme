package aufgabe2;

public class MatrixElement {
	
	int value;
	int row;
	int col;
	
	public MatrixElement(int value, int row, int col) {
		this.row = row;
		this.col = col;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;	
	}
}
