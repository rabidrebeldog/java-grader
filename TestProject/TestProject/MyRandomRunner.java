
/**
 * Our first class in this class about classes
 *
 * @author Main Test Runner
 * @version v. 1
 */
public class MyRandomRunner {
    

    
    public static void main() {
        
    
        
        int from = 10;
        int to = 1;
        int iterations = 40;
        
        MyRandom randObject = new MyRandom(50, 100);

        
        
        int min = randObject.nextRandom();
        int max = randObject.nextRandom();
        for (int i = 0; i < iterations; i++) {
            int rand = randObject.nextRandom();
            if (rand > max) max = rand;
            if (rand < min) min = rand;
            //System.out.println(rand);
        }
        
        System.out.println("After " + iterations + 
                            " iterations max = " + max +
                            " min = " + min +
                              " From value was " + 
                              randObject.getFrom()  );
    }
}

