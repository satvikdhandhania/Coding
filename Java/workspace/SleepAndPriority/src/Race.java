import java.util.Scanner;

public class Race {

	public static int raceDistance;
	public static boolean raceOverFlag;
	
	
	
	public static void main(String args[]){
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter race distance: ");
		int n = reader.nextInt(); // Scans the next token of the input as an int.
		raceDistance = n;
		raceOverFlag = false;
		System.out.println("Enter hare's priorirty: ");
		int harePrior = reader.nextInt(); // Scans the next token of the input as an int.
		System.out.println("Enter hare's nap time: ");
		int hareNap = reader.nextInt(); // Scans the next token of the input as an int.
		
		Hare hare = new Hare(hareNap, harePrior);
		Thread hareThread = new Thread(hare);
		hareThread.setPriority(harePrior);
		Tortoise tortoise = new Tortoise();
		Thread tortoiseThread = new Thread(tortoise);
		tortoiseThread.setPriority(Thread.MIN_PRIORITY);
		
		tortoiseThread.start();
		hareThread.start();
		reader.close();
		
	}
	
}
