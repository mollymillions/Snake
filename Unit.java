package com.snake;

public class Unit {
	private int xCoord;
	private int yCoord;
		
	
	public Unit() {
		xCoord = 0;
		yCoord = 0;
	}
	
	public Unit(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	public Integer getXCoord() {
		return new Integer(this.xCoord);
	}
	
	public Integer getYCoord() {
		return new Integer(this.yCoord);
	}
		
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}
		
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}
}
