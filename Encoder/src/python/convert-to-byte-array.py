#!/usr/bin/env python

__author__ = 'timmattison'

import sys

if(len(sys.argv) != 2):
    print "Specify the input file as the one, and only, command line argument"
    sys.exit(1)

filename = sys.argv[1]

java_output = []
java_output.append("private static final byte[] output = { ")
separator = ""

f = open(filename, "rb")

try:
    byte = f.read(1)

    while byte != "":
        # Do stuff with byte.
        java_output.append(separator)
        java_output.append("(byte) 0x")
        java_output.append(byte.encode("hex"))
        separator = ", "
        byte = f.read(1)

finally:
    f.close()

java_output.append(" };\n")

print ''.join(java_output)