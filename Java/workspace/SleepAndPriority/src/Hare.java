
public class Hare implements Runnable {
	int napTime;
	int speed;
	
	
	public Hare(int napTime, int speed) {
		super();
		this.napTime = napTime;
		this.speed = speed;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i;
		for(i=0;i<Race.raceDistance;)
		{
			if(Race.raceOverFlag)
				break;
			if(i%100 == 0){
				try {
					Thread.sleep(napTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			i = i+speed;
		}
		if(!Race.raceOverFlag)
		{
			Race.raceOverFlag = true;
			System.out.println("\nAnd the winner is Hare\n");
		}
		System.out.printf("\t Hare: %d",i);
	}
	

}
