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
public class MyRandom //Class declaration
{
    private int from = -10; // instance variable
    private int to = 10; // instance variable
    
    public MyRandom(int f, int t)
    {
        from = f;
        to = t;
    }
    
    public MyRandom()
    {
        from = 1;
        to = 10;
    }
    
    /**
     * Return a random number 
     *
     * @return  the next random number //
     */
    public int nextRandom()  // Instance Method(s)

    {
        return randomInRange(from, to); 
    }

    public void setBoth(int setFrom,int setTo)
    {
        from = setFrom;
        to = setTo;
    }
    
    public void setFrom(int setFrom) //Setter(s)
    {
        from = setFrom;
    }

    public void setTo(int setTo) //Setter(s)
    {
        to = setTo;
    }

    public int getFrom() //Getter(s)
    {
        return from;
    }

    public int getTo()  //Getter(s)
    {
        return to;
    }

    /**
     * Simply return your name as a String, last name then first name, separated by a comma
     *
     * @return your name as a String
     */
    public static String yourName() // class static method
    {
        return "Student 4";
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
    public static int randomInRange(int from, int to) // class static method
    {
        int max = Math.max(from,to)+1;
        int min = Math.min(from,to);
        return (int)(Math.random() * (max - min )  ) + min;
    }
}

