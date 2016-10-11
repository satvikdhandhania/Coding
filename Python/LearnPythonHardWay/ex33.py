numbers = []

def while_loop(z, inc):
    print "WHILE"  
    i = 0
    while i < z:
        print "At the top i  is", i  
        numbers.append(i)
       
        i = i + inc
        print "Numbers now: ", numbers
        print "At the bottom i  is ", i  

def for_loop(z,inc):
    print "FOR"
    for i in range(0,z,inc):
        print "At the top i  is", i  
        numbers.append(i)
        print "Numbers now: ", numbers
        print "At the bottom i  is ", i  

print "How big do you want your list to be..And what increments do you want. "
z = int(raw_input("> "))
inc = int(raw_input("> "))
#while_loop(z,inc)
for_loop(z,inc)
print "The Numbers : "
for num in numbers:
    print num
