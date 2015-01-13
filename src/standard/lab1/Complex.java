package standard.lab1;


public class Complex {
	

	private float real, img;

	public Complex() {
		real=img =0;
	}

	public Complex (float r,float i) {
		this.real = real; 
		this.img = img;
	}
	
	public void setReal (float real) {
		this.real =real;
	}
	public float getReal () {
		return real;
	}

	public void setImg(float img) {
		this.img = img;
	}
	public float getImg() {
		return img;
	}

	
	public String toString() {
		return real + " "+((img>0) ?" +" : "" ) +""+img+ "i";
	}

	public static void main (String args []) {

		Complex c = new Complex();

		c.setReal(10);

		c.setImg(-20);

		System.out.println(c);

	}
}