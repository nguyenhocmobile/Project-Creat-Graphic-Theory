package Main;

public class Point {
	private int xPosition;
	private int yPosition;
	private boolean isChoose;
	
	public Point(int xPosition, int yPosition) {
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.isChoose = false;
	}

	@Override
	public String toString() {
		return "Point [xPosition=" + xPosition + ", yPosition=" + yPosition + "]";
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	public boolean isChoose() {
		return isChoose;
	}

	public void setChoose(boolean isChoose) {
		this.isChoose = isChoose;
	}

	public static void main(String[] args) {

	}

}
