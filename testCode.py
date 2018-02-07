#!/usr/bin/env python

import sys
import argparse
import glob
import os
import re


def buildAndRun( args ):

    userSource = ""   # save the filename that containing the user's code
    
    # build a list of java files
    javaFiles = ""
    foundOne = 0
    listing = glob.glob(args.projectLocation + '/*.java')
    for filename in listing:
        if ("Test.java" not in filename):
            userSource = filename
        javaFiles = javaFiles + filename + " "
        foundOne = 1

    # Test that we found some files to compile
    if foundOne == 0:
        print "No java files found in " + args.projectLocation
        sys.exit(2)

    os.system("grep @author " + userSource)

    #remove the compiled file
    listing = glob.glob(args.projectLocation + '/*.class')
    for filename in listing:
        command = "rm " + filename
        os.system(command)

    #compile it
    command = "javac -d " + args.projectLocation + " -cp " + args.blueJApp + "/Contents/Resources/Java/junit-4.11.jar:" + args.projectLocation + "/+libs/UTSLib.jar " + javaFiles
    #print "COMPILE:"
    #print command
    os.system(command)

    # get the list of compiled java files
    numClasses = 0
    classFiles = ""
    listing = glob.glob(args.projectLocation + '/*.class')
    for filename in listing:
        if numClasses > 0:
            classFiles = classFiles + ":"
        classFiles = classFiles + filename
        numClasses = numClasses + 1

    if numClasses == 0:
        print "RESULT: Java compile failed\n"
        return

    #execute it
    command = "java -cp " + args.projectLocation + ":" + args.blueJApp + "/Contents/Resources/Java/junit-4.11.jar:" + args.blueJApp + "/Contents/Resources/Java/hamcrest-core-1.3.jar:" + args.projectLocation + "/+libs/UTSLib.jar:" + classFiles + " " + args.mainClass + " " + args.mainClass
    #print "RUN:"
    #print command
    os.system(command)
    print "\n"



parser = argparse.ArgumentParser(description='Run unit tests.')

parser.add_argument('projectLocation', metavar='projectLocation', type=str, nargs='?',
                    help='The directory containing the BlueJ project')

parser.add_argument('studentFilesLocation', metavar='studentFilesLocation', type=str, nargs='?',
                    help='The directory containing the student source files')

parser.add_argument('mainClass', metavar='mainClass', type=str, nargs='?',
                    help='The class name that contains main(), usually somethingTest.java')


parser.add_argument('-blueJApp', metavar='BlueJ Application directory', type=str, default="/Applications/BlueJ.app", nargs='?',
                    help='The path to the BlueJ.app directory (including BlueJ.app)')

# print help if no args
if len(sys.argv)==1:
    parser.print_help()
    sys.exit(1)

args = parser.parse_args()
# print args
# print args.studentFilesLocation

#generate the name of the user's source file
sourceFileName = re.sub('Test', '', args.mainClass) + ".java"
#print "Source : " + sourceFileName
#javaFiles = ""
#foundOne = 0
copyCommnd = ""
listing = glob.glob(args.studentFilesLocation + '/*.java')
for filename in listing:
    if ("Test.java" not in filename):
        filename = '"' + filename + '"'
        #print "RESULT For : " + filename;
        sys.stdout.flush()
        copyCommnd = "cp " + filename + " " + args.projectLocation + "/" + sourceFileName
        print copyCommnd
#copyCommnd = copyCommnd.replace("(","\\(");
        #copyCommnd = copyCommnd.replace("(","\\(");
        #copyCommnd = '"' + copyCommnd + '"'
        #print "NOW :" + copyCommnd
        #print "\n\n\n\n\n"
        os.system(copyCommnd)
        buildAndRun(args)















