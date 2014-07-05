package com.malffej.decimak;

import java.util.Random;

public class DecisionMaker {
	
	//Declaring member variables
	public String[] mAnswers = {
		"Yes",
		"No",
		"Maybe"
		
	};
	
	//Creating a method
	public String getAnswer(){
		String answer = "";
		
		//Select a random answer using Random class
		
		Random numberGenerator = new Random(); //Creates new Random object
		int randomNumber = numberGenerator.nextInt(mAnswers.length);
		
		//Assigns a random number based on a random number selected
		answer = mAnswers[randomNumber];
		
		//Returns the answer
		return answer;
	}
}
