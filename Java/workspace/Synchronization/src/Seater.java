
public class Seater {
	
	public static void main(String[] args) {
	
		Seat s = new Seat();
		
		Thread t1 = new Thread(s);
		Thread t2 = new Thread(s);
		
		t1.setName("Alpha");
		t2.setName("Beta");
		
		t1.start();
		t2.start();
	}
}
