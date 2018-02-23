import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * The test class MyRandomTest
 *
 * @version 1
 */

public class MyRandomTest
{
    static int version = 1;

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
            
            System.out.println("RESULT: " + (runCount-failCount) + " of  " + runCount + " tests were successful.  All tests passed = " + result.wasSuccessful());
            System.out.println("RESULT: " + points + " of  " + pointsPossible + " points achieved.");            

        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Failed to find runner class : " + args[0]);
        }

    }

    private static boolean outputRunToConsole = false;

    /**
     * Default constructor for test class ExamMethodsTest
     */
    public MyRandomTest()
    {
    }

    private static boolean outputSummary(String parameters, String expected, String run, String declaration)
    {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        String methodName = e.getMethodName();
        //summary += methodName + parameters + "\n";
        if (outputRunToConsole && declaration != null)
        {
            if (captureRun == null)
            {
                captureRun = declaration + "{\n";
            }
            captureRun += run + ","; 
        }
        //summary += "Expected:\t" + expected+"\nRun:\t" + run + "\n";
        boolean result = expected.equals(run);
        if (!result) 
        {
            summary += methodName + parameters + "\n";
            summary += "Expected:\t" + expected+"\nRun:\t" + run + "\n";

            allPassed = false;
            if (firstFailExpected == null)
            {
                firstFailExpected = expected;
                firstFailRun = run;
            }
        }
        return result;
    }

    private static void awardPoints(double ptsPossible)
    {
        pointsPossible += ptsPossible;

        if (allPassed)
        {
            points += ptsPossible;
        }

    }

    private static String summary = "";
    private static boolean allPassed = true;
    private static String firstFailExpected = null;
    private static String firstFailRun = null;
    private static String captureSummary = "";
    private static String captureRun = null;
    private static double points = 0;
    private static double pointsPossible = 0;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        summary = "";
        allPassed = true;
        firstFailExpected = null;
        firstFailRun = null;
        captureSummary = "";
        captureRun = null;
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        if (captureRun != null)
        {
            int crLen = captureRun.length() - 1;
            System.out.println(captureRun.substring(0, crLen) + "\n" + "};");
        }
    }

    @Test
    public void yourName()
    {
        String studentName = MyRandom.yourName();
        if (studentName == null || studentName.equals(""))
        {
            System.out.println("Student did not complete the yourName method!");
            allPassed = false;            
        }
        else
        {
            System.out.println("RESULT Student: " + studentName);
        }
        awardPoints(1);
        assertNotNull(studentName);
    }

    @Test
    public void test_00_instantiationAndNextRandom()
    {
        int numSamples = 1000;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        MyRandom mr = new MyRandom();
        for (int i = 0; i < numSamples; i++)
        {
            int r = mr.nextRandom();
            if (r < min) min = r;
            if (r > max) max = r;
        }
        String callParams = "()";

        String runResult ="(" + min + ", " + max + ")";
        String expect =  "(1, 10)";
        outputSummary(callParams, expect, runResult, "");
        awardPoints(1);        
        if (!allPassed) 
            assertEquals(summary, firstFailExpected, firstFailRun);
    }   

    @Test
    public void test_01_setter_from()
    {
        int numSamples = 1000;
        int[] params = {
                1,
                -10,
                10,
                20
            };
        int[][] expected = {
                {1,10},
                {-10,10},
                {10,10},
                {10,20}
            };

        for (int testIndex = 0; testIndex < params.length; testIndex++)
        {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            MyRandom mr = new MyRandom();
            mr.setFrom(params[testIndex]);
            for (int i = 0; i < numSamples; i++)
            {
                int r = mr.nextRandom();
                if (r < min) min = r;
                if (r > max) max = r;
            }
            String callParams = ":instance method call->setFrom("+params[testIndex]+")";

            String runResult ="(" + min + ", " + max + ")";
            String expect =  "(" + expected[testIndex][0] + ", " + expected[testIndex][1] + ")";
            outputSummary(callParams, expect, runResult, "");
        }
        awardPoints(1);        
        if (!allPassed) 
            assertEquals(summary, firstFailExpected, firstFailRun);
    }   
    @Test
    public void test_02_setter_to()
    {
        int numSamples = 1000;
        int[] params = {
                1,
                -10,
                10,
                20
            };
        int[][] expected = {
                {1,1},
                {-10,1},
                {1,10},
                {1,20}
            };

        for (int testIndex = 0; testIndex < params.length; testIndex++)
        {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            MyRandom mr = new MyRandom();
            mr.setTo(params[testIndex]);
            for (int i = 0; i < numSamples; i++)
            {
                int r = mr.nextRandom();
                if (r < min) min = r;
                if (r > max) max = r;
            }
            String callParams = ":instance method call->setTo("+params[testIndex]+")";

            String runResult ="(" + min + ", " + max + ")";
            String expect =  "(" + expected[testIndex][0] + ", " + expected[testIndex][1] + ")";
            outputSummary(callParams, expect, runResult, "");
        }
        awardPoints(1);        
        if (!allPassed) 
            assertEquals(summary, firstFailExpected, firstFailRun);
    }   
    @Test
    public void test_03_setters_from_and_to()
    {
        int numSamples = 1000;
        int[][] params = {
                {1,10},
                {-10,10},
                {10,1},
                {10,-10}
            };
        int[][] expected = {
                {1,10},
                {-10,10},
                {1,10},
                {-10,10}
            };

        for (int testIndex = 0; testIndex < params.length; testIndex++)
        {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            MyRandom mr = new MyRandom();
            mr.setFrom(params[testIndex][0]);
            mr.setTo(params[testIndex][1]);
            for (int i = 0; i < numSamples; i++)
            {
                int r = mr.nextRandom();
                if (r < min) min = r;
                if (r > max) max = r;
            }
            String callParams = "\n*instance method call->setFrom("+params[testIndex][0]+")";
            callParams += "\n*instance method call->setTo("+params[testIndex][1]+")";

            String runResult ="(" + min + ", " + max + ")";
            String expect =  "(" + expected[testIndex][0] + ", " + expected[testIndex][1] + ")";
            outputSummary(callParams, expect, runResult, "");
        }
        awardPoints(1);        
        if (!allPassed) 
            assertEquals(summary, firstFailExpected, firstFailRun);
    }   

}