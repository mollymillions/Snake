package com.snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class SnakeMain {
	
	/*Basic format: 25x25 grid where the snake can move around. The original length will
	 * be 4 spaces, symbolized by x , and increases in length every time the snake 
	 * consumes an apple, symbolized by o . 
	 */
	
	public static void printBoard(int[][] board) {
		
		System.out.println("------------");
		for (int i=0; i< board.length; i++) {
			System.out.print("-");
			for (int j=0; j< board.length; j++) {
				Number numInt = new Integer(board[i][j]);
				//Insert into rule base
				KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
				kbuilder.add(ResourceFactory.newClassPathResource("PrintRules.drl"), ResourceType.DRL);
				KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
				kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
				
				StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
				ksession.insert(numInt);
				ksession.fireAllRules();
			}
			System.out.println("-");
		}
		System.out.println("------------");
		
	}
	
	public static void turns(int[][] board, Snake snake, Apple apple) {
		//Create rule bases/builders
		//For the snake
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("TurnRules.drl"), ResourceType.DRL);
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		
		//For the apple
		KnowledgeBuilder kbuilder2 = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder2.add(ResourceFactory.newClassPathResource("AppleRules.drl"), ResourceType.DRL);
		KnowledgeBase kbase2 = KnowledgeBaseFactory.newKnowledgeBase();
		kbase2.addKnowledgePackages(kbuilder2.getKnowledgePackages());
		
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		StatefulKnowledgeSession ksession2 = kbase2.newStatefulKnowledgeSession();
		ksession.insert(snake);
		ksession2.insert(snake);
		ksession2.insert(apple);
		//Create an input stream for the snake to get input
		System.out.print("Enter input: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
		Integer xmax = new Integer(board.length);
		Integer ymax = new Integer(board.length);
		ksession.insert(xmax);
		ksession.insert(ymax);
		char move = (char) br.read();
		ksession.insert(new Character(move));
		//Now process the move
		Unit[] snakebod = snake.getBody();
		Unit headUnit = snakebod[snake.getLength()-1];
		ksession.insert(headUnit);
		ksession2.insert(headUnit);
		ksession.insert(snakebod);
		
		//Updating the rest of the snake
		for (int i=0; i<snake.getLength()-1; i++) {
			snakebod[i].setXCoord(snakebod[i+1].getXCoord());
			snakebod[i].setYCoord(snakebod[i+1].getYCoord());
		}
		
		/*
		//Insert the snake body into the knowledge base
		for (int i=0; i<snake.getLength()-1; i++) {
			Object fact = snakebod[i];
			ksession.insert(fact);
		}
		*/
		
		//create an apple and see where it is
		//ksession.insert(apple);

		ksession.fireAllRules();
		ksession2.fireAllRules();

		if (!snake.getIsDead()) {
		//Updating the board
		//Clear the board
		for (int i =0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
					board[i][j] = 0;	
				}
		}
		
		//Add snake
		for (int i=0; i< snake.getLength(); i++) {
			int xcoord = snakebod[i].getXCoord();
			int ycoord = snakebod[i].getYCoord();		
			board[ycoord][xcoord] = 1;
		}
		
		//Add apple
		board[apple.getAppleX()][apple.getAppleY()] = 2;
		
		//print out the new board
		printBoard(board);

		}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		int[][] board = new int[10][10];
		
		//Initialize the board to all zeros
		for (int i=0; i< board.length; i++) {
			for (int j=0; j<board.length; j++) {
				board[i][j] = 0;
			}
		}
		
		//Initialize the snake in the top left corner
		for (int i=0; i<4; i++) {
			board[0][i] = 1;
		}
		Snake snake = new Snake();
		
		Apple apple = new Apple();
		//apple.setAppleX(0);
		//apple.setAppleY(0);
		board[apple.getAppleX()][apple.getAppleY()] = 2;
				
		//Now take user input for where the snake is going to move (I used the WASD keys)
		//First, print out a statement for how to move the snake
		printBoard(board);
		System.out.println("Instructions to move: W = up, A = left, S = down, and D = right");
		
		while (!snake.getIsDead()) {
		turns(board, snake, apple);
		}
		
		System.out.println("YOU DIED! Sorry.");
	
	}

}
