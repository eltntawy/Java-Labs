package standard.lab2;

import java.util.StringTokenizer;

public class IPCutter {
	



public static void main (String args []) {


	if(args.length  > 0) {


		String ip = args[0];

		String ipArray [] = ip.split("\\.");

		System.out.println("length is : "+ipArray.length);
		for( String s : ipArray) {
			if(!s.equals(""))
				System.out.println(s);
		}


		System.out.println("----------------------------------------");

		while (ip.length() > 0) {
			
			int posiotion = ip.indexOf(".");
			
			

			String s = ip.substring(0,posiotion == -1 ? ip.length() : posiotion );
			
			if(!s.equals(""))
				System.out.println(s);

			if(posiotion == -1) 
				break;

			ip = ip.substring(posiotion+1 , ip.length());
		}
		
		System.out.println("----------------------------------------");

		ip = args[0];
		StringTokenizer strTkn = new StringTokenizer(ip,"\\.");

		while(strTkn.hasMoreTokens())
			System.out.println(strTkn.nextToken());
	} else {
			System.out.println("Please enter valid IP");
	}
}

}