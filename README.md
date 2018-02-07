This Python script will test student submitted Java code against a unit test suite.

TODO:
 - get rid of user console spew.  Just want to set the automated testing results.  For workaround, see below.

INSTRUCTIONS FOR USE:

1)

Before running this, you must configure your JUnit runner in the following way:

To the top of the file add:

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

To the top of your test class implementation add:

    public static void main(String[] args)
    {
        points = 0;
        pointsPossible = 0;

        try
        {
            Class runnerClass = Class.forName(args[0]);

            Result result = JUnitCore.runClasses(runnerClass);

            for (Failure failure : result.getFailures())
            {
                System.out.println(failure.toString());
            }

            int runCount = result.getRunCount();
            int failCount = result.getFailureCount();
            //System.out.println("RESULT Student: " + ExamMethods.yourName());
            //System.out.println("RESULT: Version :" + Sem1FinalTest.version);
            System.out.println("RESULT: " + (runCount-failCount) + " of  " + runCount + " tests were successful.  All tests passed = " + result.wasSuccessful());
            System.out.println("RESULT: " + points + " of  " + pointsPossible + " points achieved.");            

        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Failed to find runner class : " + args[0]);
        }

    }

2)
Download Student Java files to a folder on your system


3)
Here is the format for running this script:

usage: testCode.py [-h] [-blueJApp [BlueJ Application directory]]
                   [projectLocation] [studentFilesLocation] [mainClass]

Run unit tests.

positional arguments:
  projectLocation       The directory containing the BlueJ project
  studentFilesLocation  The directory containing the student source files
  mainClass             The class name that contains main(), usually
                        somethingTest.java

optional arguments:
  -h, --help            show this help message and exit
  -blueJApp [BlueJ Application directory]
                        The path to the BlueJ.app directory (including
                        BlueJ.app)

4)  Run the script redirecting the output to a text file.  Use "grep RESULT" to see the useful information.
Example:

./testCode.py ~/Downloads/Arrays1D_EnhancedForProject ~/Downloads/ Arrays1D_EnhancedForTest &> results.txt
grep RESULT results.txt

