/*
John has discovered various rocks. Each rock is composed of various elements, and each element is represented by a lowercase latin letter from 'a' to 'z'. An element can be present multiple times in a rock. An element is called a 'gem-element' if it occurs at least once in each of the rocks.

Given the list of N rocks with their compositions, display the number of gem-elements that exist in those rocks.
Input Format
The first line consists of N, the number of rocks.
Each of the next N lines contain rocks' composition. Each composition consists of lowercase letters of English alphabet.
Output Format
Print the number of gem-elements that are common in these rocks.
Constraints
1 ≤ N ≤ 100
Each composition consists of only small latin letters ('a'-'z').
1 ≤ Length of each composition ≤ 100
Sample Input
3
abcdde
baccd
eeabg
Sample Output
2
Explanation
Only "a", "b" are the two kind of gem-elements, since these are the only characters that occur in each of the rocks' composition.
*/
#include<iostream>
#include<string.h>
using namespace std;
int main()
{
    long long int N;
    int i;
    char a[100][100],set[100];
    cin>>N;
    for(i=0;i<N;i++)
    {
      cin>>a[i];        
    }
    /*
    cout<<"HI";
    for(int i=0;i<N;i++)
    {
      cout<<a[i]<<endl;   
    }
    */
    int j,z,n;
    strcpy(set,a[0]);
    n=strlen(set);
    for(i=0;i<n;i++)
      for(j=i+1;j<n;j++)
       {
        if(set[i]==set[j])
        {
         z=j;   
         while(z<n-1)
         {
            set[z]=set[z+1];
            z++;
         }      
            j--;
            n--;  
         set[n]='\0';
        } 
    }
    int flag;
    //cout<<set<<endl;    
    for(i=1;i<N;i++)
    {
     for(j=0;j<n;j++)   
     {
       flag=0;  
       z=0;  
       while(a[i][z]!='\0')  
       {
           if(a[i][z]==set[j])
               flag=1;
           z++; 
       }  
       if(flag==0)
       { 
         z=j;   
         while(z<n-1)
         {
            set[z]=set[z+1];
            z++;
         }      
            j--;
            n--;  
         set[n]='\0';
        }     
       }      
     }       
        cout<<n<<endl;    

}
