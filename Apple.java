package com.snake;

import java.util.Random;

public class Apple {
	private
		int appleX;
		int appleY;
		Random generator;
		
	public
		Apple() {
			this.generator = new Random();
			this.appleX = generator.nextInt(20);
			this.appleY = generator.nextInt(20);
		}
	
		int getAppleX() {
			return this.appleX;
		}
		
		int getAppleY() {
			return this.appleY;
		}
		
		void setAppleX(int appleX) {
			this.appleX = appleX;
		}
		
		void setAppleY(int appleY) {
			this.appleY = appleY;
		}
}
