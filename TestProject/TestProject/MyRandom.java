import java.util.ArrayList;
import java.util.List;

/**
 * In this project you will learn about writing classes in Java.
 * 
 * You will:
 * 1. Write a traditional class with a class (static) method
 * 
 * 2. Add instance variables, instance methods, and a constructor so that it can be instaniated.
 *  
 * @author (your name) 
 * @version Unit 7 - Writing Object Classes
 */
public class MyRandom
{
    private int from;
    private int to;

    public MyRandom(int fromVal, int toVal)
    {
        from = fromVal;  // initialize from instance variable
        to = toVal;      // initialize to instance variable
    }

    public MyRandom()
    {
        from = 1;  // initialize from instance variable
        to = 10;      // initialize to instance variable
    }
    
    public void setFrom( int newFrom )
    {
        from = newFrom;
    }

    public void setTo (int newTo )
    {
        to = newTo;
    }

    public int getFrom ()
    {
        return from;
    }

    /**
     * Return a random number 
     *
     * @return  the next random number
     */
    public int nextRandom() 
    {
        return randomInRange(from, to);	
    }

    
 
    /**
     * Simply return your name as a String, last name then first name, separated by a comma
     *
     * @return your name as a String
     */
    public static String yourName()
    {
        return "Solution Key"; 
    }

    /**
     * Given two int parameter values, from and to, you will return a random int that falls
     * in the range of from and to (inclusive).
     * 
     * For example:
     *  randomInRange(1,10) would return a number between 1 and 10 inclusive.
     *    Valid return values would be the numbers: 1...10
     *  
     *  randomInRange(10,1) would also return a number between 1 and 10 inclusive.
     *  
     *  randomInRange(-5,5) would return a number between -5 and 5 inclusive.
     *  
     *  NOTE: This method does not work as intended!
     *  
     * @param   from    one bound of the random int to be generated
     * @return  to      the second bound of the random int ot be genrated
     */
    public static int randomInRange(int from, int to)
    {
        if (from > to) {
            int temp = from;
            from = to;
            to = temp;
        }
        return (int)(Math.random() * (to - from + 1)) + from;
    }
}

