
public class demoSample {
	
	public static void main(String[] args){
	
	}
	class Solution {
	    public int solution(int[] A) {
	    	int sum=0,sum2=0;
	    	for(int i=0;i<A.length;i++)
	    	{
	    		sum+=A[i];
	    	}
	    	for(int i=0;i<A.length;i++)
	    	{
	    		if(i>0)
	    		sum2+=A[i-1];
	    		sum-=A[i];
	    		if(sum2==sum)
	    		{
	    			return i;
	    		}	
	    	}
	    	
	    	return -1;
	    }
	}
}
