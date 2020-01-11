package HW2;

import java.util.ArrayList;

/*
Name:Alexander Skarlatov
Date: 2/8/18
assignment Hw2
class CSC: 364
Description: an example of dynamic programming , this program will take any given string from the user
then output the longest increasing subsequence
the program does this by using arrays to keep track of where to find the longest increasing subsequence
the program outputs the longest icnreasing subsequence and it does so by referencing
the corresponding arrays
 */


import java.util.Scanner;


public class MaxIncreasinSubseq
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		//acquire the pattern
		System.out.println("input something");
		String pattern = input.next();
		System.out.println("your input was: " + pattern);
		//initialize score array and previous array
		int[] score = new int[pattern.length()];
		int[] previous = new int[pattern.length()];
		char[] patternArrChar = new char[pattern.length()];
		//convert from string to char array to int array
		for(int i = 0; i < pattern.length(); i++)
		{
			patternArrChar[i] = pattern.charAt(i);
		}
		int previouslyEvaluatedValue = -1;
		for(int i = 0; i<patternArrChar.length;i++)
		{
			previouslyEvaluatedValue = -1;
			for(int j = 0 ; j < i ; j++)
			{
				if(patternArrChar[j] < patternArrChar[i] && patternArrChar[j] > previouslyEvaluatedValue)
				{
					score[i] += 1;
					previouslyEvaluatedValue = patternArrChar[j];//log this as the previously evaluate value
					//update previous index
					previous[i] = j;
				}
			}
			score[i] += 1;//implicit plus one this is for the [i] index that counts itself
			if(previouslyEvaluatedValue == -1 && previous[i] == 0)
			{
				previous[i] = -1;
			}
		}//end of outer loop
		int highScore = 0;
		int highScoreIndex =0;
		//in this loop i will first be finding the largest score i can find and its corresponding index
		for(int i = 0; i <  score.length ; i++)
		{
			if(highScore < score[i] )
			{
				highScore = score[i];
				highScoreIndex = i;
			}
		}
		//now that i have the high score. and its index i will trace my way backwards
		boolean flag = true;
		MyDoublyLinkedList<String> answer = new MyDoublyLinkedList<>();
		while(flag == true)
		{
			String s = pattern.substring(highScoreIndex, highScoreIndex+1);
			answer.addFirst(s);
			//update the highScore index using previous array
			highScoreIndex = previous[highScoreIndex];
			if(highScoreIndex == -1)
			{
				//exit out of loop
				flag = false;
			}
		}
		System.out.println("the longest increasing subsequence: ");
		System.out.println(answer.toString());
	}//end of main
}

