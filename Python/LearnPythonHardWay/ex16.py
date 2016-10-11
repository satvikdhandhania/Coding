from sys import argv

script, filename = argv

print "We're going to erase %r" % filename
print "If you do not wish to proceed press Ctrl+C"
print "If you wish to proceed press Enter"

raw_input("?")

print "Opening the file..."
target = open(filename, 'w')

print "Truncating the file."
target.truncate()

print "Now I'm going to ask you for three lines."
line1 = raw_input("line 1: ")
line2 = raw_input("line 2: ")
line3 = raw_input("line 3: ")

print "I'm going to write these to the file."
target.write(line1)
target.write("\n")
target.write(line2)
target.write("\n")
target.write(line3)
target.write("\n")

print "And finally, we close it"
target.close()

target1 = open(filename, 'r')
print target1.read()
target1.close()
