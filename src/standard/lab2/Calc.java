package standard.lab2;

public class Calc {


	public static void main (String args []) {

		int res = 0 ;

		for(String s : args )
			System.out.println(s);

		if(args.length == 3) {

			int x = Integer.parseInt(args[0]);
			int y = Integer.parseInt(args[2]);

			
				if (args [1].length() >= 2 ) {
					System.out.println("You must enter 3 paramter like 5 + 6 ");
				} else {

					char oper = args[1] .charAt(0);
					
					
					if(oper == '+') {
						res = x + y ;

					} else if (oper == '-') {
						res = x - y ;

					} else if (oper == '*') {
						res = x * y ;
					} else if (oper == '/' && y != 0) {
						res = x / y ;
					} else {

						
						
						if ( y == 0 && oper == '/' ) {
							System.out.println(" can not divided by 0");
						} else {
							System.out.println(" please use currect operator ");
						}
							
					}

					System.out.println ("the resualt is : "+res);
				}
			

		 } else {
			
			System.out.println("You must enter 3 paramter like 5 + 6 ");
		}
		
	}
}