
public class Tortoise implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i;
		for(i=0;i<Race.raceDistance;i++)
		{
			if(Race.raceOverFlag)
				break;
			
		}
		if(!Race.raceOverFlag)
		{
			Race.raceOverFlag = true;
			System.out.println("\nAnd the winner is Tortoise\n");
		}
		System.out.printf("\t Tortoise: %d",i);
	}

}
