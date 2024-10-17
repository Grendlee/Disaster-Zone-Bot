import java.util.*;
import java.io.*;
/**
 * The Driver program to perform the mission
 *
 * @author Grantley 100260205
 * @version 2/9/22
 */
public class DisasterZoneDriver 
{
    public static String fileName;
    
    public static void main (String args[]) throws IOException
    {
    
        String terrainType = "";
        int numberOfMapsCreated = 1;
        boolean defaultZone = false;
        String choice;
        boolean choiceMade = false;
        char terrainTypeSymbol = 'E';
        int percentageDebris = -1;
        DisasterZone simZone;
        String status = "";
        
        while(!choiceMade)
        {
            System.out.println("Would You like to simulate a default zone? (y/n)");
            Scanner in = new Scanner(System.in);
        
            choice =  in.nextLine().toLowerCase();
            if(choice.equals("y") || choice.equals("n"))
            {
                choiceMade = true;
                if(choice.equals("y"))
                {
                    defaultZone = true;
                }
                else
                {
                    defaultZone = false;
                }
            }
        }
            
        
        if(defaultZone)
        {
            terrainType = "defaultZone";
            simZone = new DisasterZone();
        }
        else
        {
            choiceMade = false;
            while(!choiceMade)
            {
                System.out.println(
                "Type the corresponding number to select the terrain type:" +
                    "\n\t1. Random" +
                    "\n\t2. Building" +
                    "\n\t3. Structure");
                Scanner in = new Scanner(System.in);
            
                int number =  in.nextInt();
                
                switch(number)
                {
                    case 1:
                        terrainType += "RandomDebris";
                        terrainTypeSymbol = 'R';
                        choiceMade = true;
                        break;
                    case 2:
                        terrainType += "BuildingDebris";
                        terrainTypeSymbol = 'B';
                        choiceMade = true;
                        break;
                    case 3:
                        terrainType += "StructureDebris";
                        terrainTypeSymbol = 'S';
                        choiceMade = true;
                        break;
                }
            }
            
            choiceMade = false;
            while(!choiceMade)
            {
                System.out.print("Type type percentage of debris you would like to simulate (0 to 100): ");
                Scanner in = new Scanner(System.in);
            
                int number =  in.nextInt();
                
                if(number >= 0 && number <= 100)
                {
                    terrainType += number;
                    percentageDebris = number;
                    choiceMade = true;
                }
                else
                { 
                }
            }
            
            simZone = new DisasterZone(percentageDebris, terrainTypeSymbol);
        }
        
        
        
        //filename for the simulation
        fileName = "C:/Users/Grantley/Desktop/Cpsc_1181_BlueJay/Assig4/" +  terrainType + ".txt"; 
        
        //create file to upload map to
        PrintWriter summaryForDay = new PrintWriter("C:/Users/Grantley/Desktop/Cpsc_1181_BlueJay/Assig4/" +  terrainType + ".txt");
        
        BotController bot = new BotController(simZone);
    }
}
