package main.java.se.lab2;

public class PrintStars {
	

	public static void printSpaces(int n) {
		for(int i = 0 ; i < n ; i ++) 
			System.out.print(" ");
	}

	public static void main (String args [] ) {

		int n = 10 ;
		int spaceCount = 20;
		

		for (int i = 1 ; i < n ; i ++) {

			for (int j = 0 ; j < i ; j++) {
				System.out.print("*");
			}

			printSpaces( 2*n - 2*i );
			for(int j = 0 ; j < i ; j ++) {
				
				System.out.print("* ");
			}

			
			System.out.println();
		}
	}
}