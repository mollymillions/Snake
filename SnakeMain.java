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
	 * be 4 spces, symbolized by - , and increases in length every time the snake 
	 * consumes an apple, symbolized by o . 
	 */
	
	public static void printBoard(int[][] board) {
		System.out.println(" xxxxxxxxxxxxxxxxxxxx");
		for (int i=0; i< 20; i++) {
			System.out.print("x");
			for (int j=0; j< 20; j++) {
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
			System.out.println("x");
		}
		System.out.println(" xxxxxxxxxxxxxxxxxxxx");
	}
	
	public static void turns(int[][] board, Snake snake) {
		
		//Create rule bases/builders
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("TurnRules.drl"), ResourceType.DRL);
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		
		//Create an input stream for the snake to get input
		System.out.print("Enter input: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
		char move = (char) br.read();
		ksession.insert(new Character(move));
		//Now process the move
		Unit[] snakebod = snake.getBody();
		Unit headUnit = snakebod[snake.getLength()-1];
		ksession.insert(headUnit);

		
		
		
		
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		int[][] board = new int[20][20];
		
		//Initialize the board to all zeros
		for (int i=0; i< 20; i++) {
			for (int j=0; j<20; j++) {
				board[i][j] = 0;
			}
		}
		
		//Initialize the snake in the top left corner
		for (int i=0; i<4; i++) {
			board[0][i] = 1;
		}
		Snake snake = new Snake();
				
		//Now take user input for where the snake is going to move (I used the WASD keys)
		//First, print out a statement for how to move the snake
		//printBoard(board);
		System.out.println("Instructions to move: W = up, A = left, S = down, and D = right");
		
		turns(board, snake);
		System.out.println("YOU DIED! Sorry.");
	
	}

}
