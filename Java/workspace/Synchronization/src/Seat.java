

public class Seat implements Runnable{

	
	static  synchronized void sit(String name, int count){
		
		
		System.out.println("Occupied by "+name + "\tCount :"+count); 
		
		System.out.println("Released by "+name + "\tCount :"+count);

	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String name = Thread.currentThread().getName(); //who is running

		for(int i=1;i<=5;i++)
		{
			sit(name,i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
