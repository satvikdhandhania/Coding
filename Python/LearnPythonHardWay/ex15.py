from sys import argv
screipt, filename = argv
txt = open(filename)
print "Here's you file : %s" % filename
print txt.read()
print "Type your filename again :"
file_again = raw_input("> ")
txt_again = open(file_again)
print txt_again.read()
txt.close()
txt_again.close()
