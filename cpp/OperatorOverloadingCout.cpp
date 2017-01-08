#include<iostream>
using namespace std;
class  point {
 public:	
    int x,y;
     point()
	{
	   x=0;y=0;	
	}
     point operator+(point& p1)
	{
	  point sum;
	  sum.x= p1.x+this->x;
	  sum.y= p1.y+this->y;
	  return sum;	
	}	
};
ostream& operator<<(ostream& out, point& p1);
point operator+(point& p1);

ostream& operator<<(ostream& out, point& p1)
{
  out<<"\nX="<<p1.x<<","<<" Y="<<p1.y<<endl;
  return out;
}

int main()
{

 point a,b,c;
 a.x=1;b.x=2;
 a.y=6;b.y=9;	
 cout<<"a =("<<a.x<<","<<a.y<<")\nB=("<<b.x<<","<<b.y<<")\n";
 c=a+b;
 cout<<"sum ="<<c.x<<","<<c.y<<endl; 
 cout<<"Now ! Trying with stream overloading!!!";
 cout<<c;

 return 1;
}

