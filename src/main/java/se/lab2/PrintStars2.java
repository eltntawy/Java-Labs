package main.java.se.lab2;

public class PrintStars2 {
	

	public static void main (String args [] ) {

		int n = 10 ;
		int spaceCount = 20;
		
		int counter = 0;

		for (int i = 1 ; i < n ; i ++) {


			
			System.out.print("*");

			counter++;

			if (i == counter)
				System.out.println();

		}
	}
}