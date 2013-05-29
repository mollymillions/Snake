package com.snake;

public class Snake {
	private Unit[] body;
	private int length;
	private boolean isDead;
		
	public Snake() {			
		this.length = 4;
		this.isDead = false;
		this.body = new Unit[this.length];
		for (int i=0; i < length; i++) {
			body[i] = new Unit(i,0);
		}
	}
		
	public Unit[] getBody() {
		return this.body;
	}
	
	public int getLength() {
		return this.length;
	}
		
	public boolean getIsDead() {
		return this.isDead;
	}
				
	public void setLength(int length) {
		this.length = length;
	}
		
	public void setIsDead(boolean isDead) {
		this.isDead = isDead;
	}
}
