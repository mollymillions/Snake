package com.snake;

public class Unit {
	private
		int xCoord;
		int yCoord;
		
	public
		Unit() {
			xCoord = 0;
			yCoord = 0;
		}
	
		Unit(int xCoord, int yCoord) {
			this.xCoord = xCoord;
			this.yCoord = yCoord;
		}
	
		int getXCoord() {
			return this.xCoord;
		}
	
		int getYCoord() {
			return this.yCoord;
		}
		
		void setXCoord(int xCoord) {
			this.xCoord = xCoord;
		}
		
		void setYCoord(int yCoord) {
			this.yCoord = yCoord;
		}
}
