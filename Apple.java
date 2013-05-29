package com.snake;

import java.util.Random;

public class Apple {
	private int appleX;
	private int appleY;
	private Random generator;
		
	public Apple() {
		this.generator = new Random();
		this.appleX = generator.nextInt(10);
		this.appleY = generator.nextInt(10);
	}
	
	public int getAppleX() {
		return this.appleX;
	}
		
	public int getAppleY() {
		return this.appleY;
	}
		
	public void setAppleX(int appleX) {
		this.appleX = appleX;
	}
		
	public void setAppleY(int appleY) {
		this.appleY = appleY;
	}
	
	public void newRandCoords() {
		this.appleX = generator.nextInt(10);
		this.appleY = generator.nextInt(10);
	}
}
