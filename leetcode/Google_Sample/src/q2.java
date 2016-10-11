import java.util.ArrayDeque;
import java.util.Deque;

public class q2 {
	public static void main(String[] args){
		String S = "dir1\n\tdir11\n\tdir12\n\t\tpicture.jpeg\n\t\tdir121\n\t\tfile1.txt\n\tdir2\n\tfile2.gif";
        // write your code in Java SE 8
        int maxLen =0,currentLength=0;
        if((S.length()==0) || (S == null))
        	return;
        Deque<String> stack = new ArrayDeque<String>();
        String[] records = S.split("\n");
        for(int i=0;i<records.length;i++)
        {
        	String record = records[i];
        	System.out.println(record);
        	int countTabs = 0;
        	while(record.charAt(countTabs)=='\t')
        		countTabs++;
        	while(countTabs < stack.size()) {
                String s = stack.pop();
                currentLength -= s.length()+1;
            }
        	String value = record.substring(countTabs);
        	
        	if(!value.contains("."))
        	{
        	    stack.push(value.trim());
        	    currentLength+=value.trim().length()+1;
        	}	
        	System.out.println(stack);
        	System.out.println(currentLength);
   
        	if((value.endsWith(".png"))||(value.endsWith(".gif"))||(value.endsWith(".jpeg")))
        	{
        		maxLen = Math.max(maxLen, currentLength);
        	}
        }
        System.out.println(maxLen);
		
		
		
	}

}
