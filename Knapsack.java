package HW2;

/*
 * Name: Alexander Skarlatov
Date: 2/8/18
assignment Hw2
class CSC: 364

Description: this program is the knapsack problem it will first read from a file and parse the lines
into a bunch of project objects(a custom class i made). then store all these project objects in an array list
then it will construct a two dimensional array and fill it with values gradually by using the information
derived from the items stored in the projects arrayList. after the table has been filled up the
greatest value has been deteremined to be at the bottom right index of the two-d array matrix.

the program then proceeds to gather information for the ouptut of a report which is written to a text
file.

the program determines the best value  and it also determines all of the projects that will
be selected for the best value, it does this by iterating over every column in the matrix and determining
if the corresponding project should be included or excluded. upon including this project, the project
is then stored in a MyDoublyLinkedList data structure for use in writing the report.
 */

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.print.Printer;
import javafx.scene.image.Image;


public class Knapsack {
	//1    read from the file into the arrayList
	 public static final int MAX_WEIGHT = 55;
	public static void main(String[] args) throws IOException
	{
		Scanner input = new Scanner(System.in);
		System.out.println("what is the max weigth?(or weeks): ");
		int maxW = input.nextInt();
		ArrayList<Project> projects = new ArrayList<Project>();
		Scanner dataFile = new Scanner(new File("projects 500"));
		while (dataFile.hasNextLine())
		{
			String line = dataFile.nextLine();//take entire line
			String[] split = line.split(" ");//split into three tokens store into split string array
			projects.add(new Project(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2])));
		}
		dataFile.close();
		//2 here define matrix to fill up using above info
		int[][] matrix = new int[maxW + 1][projects.size() + 1 ];
		//iterate over matrix initialize to zero
		//unnecessary because all values in matrix start as zero by default
		//INITIALIZE FIRST COLUMN TO ALL ZERO
		for(int i = 0; i < matrix.length; i++){
			matrix[i][0] = 0;
		}
		//INITIALIZE FIRST ROW TO ALL ZERO
		for(int i = 0; i < matrix[0].length; i++){
			matrix[0][i] = 0;
		}
		for(int col = 1 ; col < matrix[0].length ; col++)//column is the slow mutatey one so it makes sense for it to be on the outside
		{
			for(int row = 1 ; row < matrix.length ; row++)//y axis//row is fast mutatey one so it change quickly and crawl down the column as a result filling up the column
			{
				if(projects.get(col - 1).getWeeks() > row)
				{
					matrix[row][col] = matrix[row][col-1];//it will be the thing on the left
				}
				//my include considered here, weighed against the exclude option
				else
				{
					matrix[row][col] = Math.max( matrix[ row - projects.get(col - 1).getWeeks() ][ col - 1 ]
							+  projects.get(col - 1).getValue() , matrix[row][col -1] );
				}
			}
		}
		//THIS IS THE END OF MY NESTED FOR LOOP
		//3   linked list
		MyDoublyLinkedList<Project> selectedProjects = new MyDoublyLinkedList<>();
		int valBook;
		//val book starts off at the bottom right of our table
		valBook = matrix[matrix.length-1][matrix[0].length-1];
		int row,col;
		// i will have to update these two vals as i  go along
		row = matrix.length - 1;
		col = matrix[0].length - 1;
		for(int i = matrix[0].length - 1; i >= 1 ; i-- )
		{
			//if case that current weight minus current row then do not do
			if(row - projects.get(i-1).getWeeks() >= 0)
			{
				if( matrix[row][col -1] < matrix[row - projects.get(i - 1).getWeeks()][i - 1]
					+ projects.get(i-1).getValue())
				{
					//include case
					//code to add the current item into my list
					selectedProjects.addFirst(projects.get(i - 1));
					//update row col here
					row -= projects.get(i-1).getWeeks();
					col -= 1;
				}
				else
				{
					//exclude case
					//update row and col here
					col -= 1;
				}
			}
		}
		//formatting output stage
		//WRITE TO FILE STAGE
		System.out.println("number of projects available: " + projects.size());
		System.out.println("available employee work weeks: " + maxW);
		System.out.println("number of projects chosen: " + selectedProjects.size());
		//the x variable should be renamed to sum, because it is keeping track of the sum total
		//value of all projects
		int x  = 0;
		for (Project p : selectedProjects)
		{
			x += p.getValue();
		}
		System.out.println("Total Profit: " + x	 );
		System.out.println( selectedProjects.toString());
		try(
		DataOutputStream output = new DataOutputStream(new FileOutputStream("outputfile"));
		){
			output.writeUTF("number of projects available: " + projects.size()+ "\n");
			output.writeUTF("available employee work weeks: " + maxW + "\n");
			output.writeUTF("number of projects chosen: " + selectedProjects.size() + "\n");
			output.writeUTF("Total Profit: " + x + "\n");
			output.writeUTF(selectedProjects.toString()+ "\n");
			output.writeUTF("----------------------------------\n");
		}
		input.close();
	}
	//END OF MAIN
}


