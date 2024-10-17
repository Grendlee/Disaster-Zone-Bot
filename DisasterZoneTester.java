import java.io.*;
import java.util.Scanner;
/**
 * Write a description of class DisasterZoneTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DisasterZoneTester
{

    
    public static void main(String[] args)
    {
        DisasterZone simZone = new DisasterZone();   // use the default/empty disaster zone
        //DisasterZone simZone = new DisasterZone(10);   // with density of 10% debris
        // DisasterZone simZone = new DisasterZone(25, 'B');   // with density of 25% debris with a Building
        
        System.out.println("Initial status = " + simZone.getBotStatus());       
        
        System.out.println("ping result = " + simZone.ping());
        System.out.println("move 10 forward");
        System.out.println(simZone.move(10));

        System.out.println("ping result = " + simZone.ping());
        System.out.println("turn left");        
        simZone.turnLeft();

        System.out.println("ping result = " + simZone.ping());
        System.out.println("turn left");        
        simZone.turnLeft();
        
        System.out.println("ping result = " + simZone.ping());
        System.out.println("turn left");        
        simZone.turnLeft();
        
        System.out.println("ping result = " + simZone.ping());
        System.out.println("turn left");        
        simZone.turnLeft();
        
        System.out.println("ping result = " + simZone.ping());
        System.out.println("turn left");        
        simZone.turnLeft();

        System.out.println("ping result = " + simZone.ping());
        System.out.println("turn left");        
        simZone.turnLeft();

        System.out.println("ping result = " + simZone.ping());        
        System.out.println("simZone has turned around");
        simZone.displayScore();
        
        System.out.println("move 10 forward");        
        System.out.println(simZone.move(10));
        System.out.println("ping result = " + simZone.ping());
        
        simZone.displayScore();
    }
}

