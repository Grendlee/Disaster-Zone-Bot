

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class DisasterZoneTest.
 *
 * @author  Grantley
 * @version 2/7/22
 */
public class DisasterZoneTest
{
    
    @Test
    public void testPing()
    {
        DisasterZone simZone = new DisasterZone();
        //DisasterZone simZone = new DisasterZone(10);   // with density of 10% debris
        // DisasterZone simZone = new DisasterZone(25, 'B');
        
        assertEquals(150, simZone.ping(), 40, "Expected value of 150 - and minus 40");
    }
    
    @Test
    public void testRetrieveTarget()
    {
        DisasterZone simZone = new DisasterZone();
        //DisasterZone simZone = new DisasterZone(10);   // with density of 10% debris
        // DisasterZone simZone = new DisasterZone(25, 'B');
        
        assertFalse(simZone.retrieveTarget(), "should be false");
    }
    
    @Test
    public void testGetBotStatus()
    {
        DisasterZone simZone = new DisasterZone();
        // DisasterZone simZone = new DisasterZone(10);   // with density of 10% debris
        // DisasterZone simZone = new DisasterZone(25, 'B');
        
        assertEquals("Expected result is some string with direction, row and col, sourounding space, and battery", simZone.getBotStatus(), "Expected error") ;
    }
}
