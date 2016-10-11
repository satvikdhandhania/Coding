import java.util.ArrayDeque;
import java.util.Deque;

public class q1 {


	public static void main(String[] args){

		int X = 623315;


		int digitCount = countDigits(X);
		int max = 0;
		int dig;
		Deque<Integer> stack = new ArrayDeque<Integer>();
		// Counting possible combinations based on number of digits

		// Since two digits are combined to one we run till count -1 
		for(int c=0;c<digitCount-1;c++)
		{
			int number = 0;
			int temp = X;
			// Repopulating stack
			while(temp>0)
			{
				stack.push(temp%10);
				temp = temp/10;
			}

			for(int i=0;i<digitCount-1;i++)
			{
				dig = stack.pop();
				if(i==c)
				{
					dig = (int) Math.round(((double)stack.pop() + (double)dig)/2.0);
				}
				number = number*10 + dig;
			}
			if(number>max)
				max = number;
		}


		System.out.println(max);
	}

	static int countDigits(int X)
	{
		int digitCount =0;
		while(X>0)
		{
			X = X/10;			
			digitCount++;
		}
		return digitCount;
	}

}
