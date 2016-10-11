#include<stdio.h>
#include<string.h>
#include<stdlib.h>

char* reverseString(char* s) 
{
    int len = strlen(s);
    printf("%d",len);
    if(len<=0)
        return s;
    int  mid = len/2-1;
    char temp;
    for(int i=0;i<mid;i++){
        temp = s[i];
        s[i] = s[len-i];
        s[len-i] = temp;
    }
    return s;
}


int main(int argc, const char *argv[])
{
    char s[] = "hello\0";
    
    printf("%s",s);
    //exit(0);
    printf("%s\n",reverseString(s));
    return 0;
}
