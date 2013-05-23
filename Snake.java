package com.snake;

public class Snake {
	private
		Unit[] body;
		int length;
		boolean isDead;
		
	public
		Snake() {
			this.length = 4;
			this.isDead = false;
			this.body = new Unit[this.length];
			for (int i=0; i < length; i++) {
				body[i] = new Unit(0,i);
			}
		}
		
		Unit[] getBody() {
			return this.body;
		}
	
		int getLength() {
			return this.length;
		}
		
		boolean getIsDead() {
			return this.isDead;
		}
				
		void setLength(int length) {
			this.length = length;
		}
		
		void setIsDead(boolean isDead) {
			this.isDead = isDead;
		}
}
