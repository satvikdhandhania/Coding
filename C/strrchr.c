#include<string.h>
#include<stdio.h>
int main ()
{
  char str[] = "This is a sample string";
  char *pch;
  pch=strrchr(str,'s');
  printf ("Last occurence of 's' found at %s \n",pch);
  return 0;
}
