package weekthree2;
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;

public class MarkovZero extends AbstractMarkovModel {
	public MarkovZero() {
		myRandom = new Random();
	}
	
//	public void setTraining(String s){
//		myText = s.trim();
//	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int k=0; k < numChars; k++){
			int index = myRandom.nextInt(myText.length());
			sb.append(myText.charAt(index));
		}
		
		return sb.toString();
	}

	@Override
	public String toString() {
		return "MarkovModel of order 0";
	}
}
