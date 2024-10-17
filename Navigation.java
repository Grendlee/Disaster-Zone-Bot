import java.util.*;
import java.io.*;
/**
 * Program to build and store the map as the bot traverses the environment
 *
 * @author Grantley 100260205
 * @version 2/9/22
 */
public class Navigation
{
    
    //most difficult part was trying to print to a 2d array
    //solved by asked questions online and to the prof
    
    //general strategy for finding the target is to retrieve the bot after each move
    //general strategy to navigate the map is to use random number gen to move and turn
    private static PrintWriter out;
    private char nWSEOrientation;
    private int rowNumber;
    private int columnNumber;
    private int lastKnownRowNumber = -1;
    private int lastKnownColumnNumber = -1;
    private String charToStringConversion = "";
    private int batteryLevel;
    private boolean rowNumberFound = false;
    private boolean columnNumberFound = false;
    private boolean botSurroundingsFound = false;
    private boolean batteryLevelFound = false;
    private static char [][] disasterZoneMap = new char[104][104];
    private int movement;
    
    /**  
    * 
    * Navigation constructer used to initialize the navigation capability of the Bot
    */
    public Navigation()
    {
        
            
    } 
    
    /**  
    * reads the string recieved that is returneds from the .getBotStatus to section of data like orientation, coordinates, etc.
    *
    * @param  message that the bot sends after a call of the method .getBotStatus
    */
    public void readPosition(String message)
    {
    
        System.out.println("Current position details: " + message);
        
        for(int x = 0; x < message.length(); x++)
        {
            
            if(x == 0)
            {
                nWSEOrientation = message.charAt(x);
                //System.out.println(nWSEOrientation + " orien");
            }
            
            if(!rowNumberFound)
            {
                
                if(message.charAt(x) == '[')
                {
                    
                    if(message.charAt(x + 2) == ']')
                    {
                        
                        charToStringConversion += message.charAt(x + 1);
                        rowNumber = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";
                        rowNumberFound = true;
                    }
                    
                    if(message.charAt(x + 3) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        charToStringConversion += message.charAt(x + 2);
                        rowNumber = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";
                        rowNumberFound = true;
                    }
                    
                    if(message.charAt(x + 4) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        charToStringConversion += message.charAt(x + 2);
                        charToStringConversion += message.charAt(x + 3);
                        rowNumber = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";
                        rowNumberFound = true;
                    }
                    
                    //solves bug
                    x++;
                    //System.out.println(rowNumber + " = row number at NAV");
                }
            }
            
            if((!columnNumberFound) && (rowNumberFound))
            {
                
                if(message.charAt(x) == '[')
                {
        
                    if(message.charAt(x + 2) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        columnNumber = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";
                        columnNumberFound = true;
                    }
                    
                    if(message.charAt(x + 3) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        charToStringConversion += message.charAt(x + 2);
                        columnNumber = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";
                        columnNumberFound = true;
                    }
                    
                    if(message.charAt(x + 4) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        charToStringConversion += message.charAt(x + 2);
                        charToStringConversion += message.charAt(x + 3);
                        columnNumber = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";
                        columnNumberFound = true;
                    }
                    
                    //System.out.println(columnNumber + " = columnNumber NAV");
                }
            }
            
            
            if(!botSurroundingsFound)
            {
                
                if(message.charAt(x) == ']')
                {
                    
                    if(message.charAt(x + 9) == '[')
                    {
                        
                        String position = message.substring(x + 1, x + 9);
                        
                        //System.out.println(position + " position");
                        if(nWSEOrientation == 'S')
                        {
                            Navigation.updateMap(rowNumber, columnNumber, '.');
                            
                            int rowNumberForPrintingSurroundings = rowNumber;
                            int columnNumberForPrintingSurroundings = columnNumber;
                            
                            Navigation.updateMap(rowNumberForPrintingSurroundings + 1, columnNumberForPrintingSurroundings, position.charAt(0));
                            Navigation.updateMap(rowNumberForPrintingSurroundings + 1, columnNumberForPrintingSurroundings - 1, position.charAt(1));
                            Navigation.updateMap(rowNumberForPrintingSurroundings, columnNumberForPrintingSurroundings - 1, position.charAt(2));
                            Navigation.updateMap(rowNumberForPrintingSurroundings - 1, columnNumberForPrintingSurroundings - 1, position.charAt(3));
                            Navigation.updateMap(rowNumberForPrintingSurroundings - 1, columnNumberForPrintingSurroundings, position.charAt(4));
                            Navigation.updateMap(rowNumberForPrintingSurroundings - 1, columnNumberForPrintingSurroundings + 1, position.charAt(5)); 
                            Navigation.updateMap(rowNumberForPrintingSurroundings, columnNumberForPrintingSurroundings + 1, position.charAt(6));
                            Navigation.updateMap(rowNumberForPrintingSurroundings + 1, columnNumberForPrintingSurroundings + 1, position.charAt(7));
                            
                            botSurroundingsFound = true;
                        }
                        
                        if(nWSEOrientation == 'N')
                        {
                            Navigation.updateMap(rowNumber, columnNumber, '.');
                            
                            int rowNumberForPrintingSurroundings = rowNumber;
                            int columnNumberForPrintingSurroundings = columnNumber;
                            
                            Navigation.updateMap(rowNumberForPrintingSurroundings - 1, columnNumberForPrintingSurroundings, position.charAt(0));
                            Navigation.updateMap(rowNumberForPrintingSurroundings - 1, columnNumberForPrintingSurroundings + 1, position.charAt(1));
                            Navigation.updateMap(rowNumberForPrintingSurroundings, columnNumberForPrintingSurroundings + 1, position.charAt(2));
                            Navigation.updateMap(rowNumberForPrintingSurroundings + 1, columnNumberForPrintingSurroundings + 1, position.charAt(3));
                            Navigation.updateMap(rowNumberForPrintingSurroundings + 1, columnNumberForPrintingSurroundings, position.charAt(4));
                            Navigation.updateMap(rowNumberForPrintingSurroundings + 1, columnNumberForPrintingSurroundings - 1, position.charAt(5));
                            Navigation.updateMap(rowNumberForPrintingSurroundings, columnNumberForPrintingSurroundings - 1, position.charAt(6));
                            Navigation.updateMap(rowNumberForPrintingSurroundings - 1, columnNumberForPrintingSurroundings - 1, position.charAt(7));
                            
                            botSurroundingsFound = true;
                        }
                        
                        if(nWSEOrientation == 'E')
                        {
                            Navigation.updateMap(rowNumber, columnNumber, '.');
                            
                            int rowNumberForPrintingSurroundings = rowNumber;
                            int columnNumberForPrintingSurroundings = columnNumber;
                            
                            Navigation.updateMap(rowNumberForPrintingSurroundings, columnNumberForPrintingSurroundings + 1, position.charAt(0));
                            Navigation.updateMap(rowNumberForPrintingSurroundings + 1, columnNumberForPrintingSurroundings + 1, position.charAt(1));
                            Navigation.updateMap(rowNumberForPrintingSurroundings + 1, columnNumberForPrintingSurroundings, position.charAt(2));
                            Navigation.updateMap(rowNumberForPrintingSurroundings + 1, columnNumberForPrintingSurroundings - 1, position.charAt(3));
                            Navigation.updateMap(rowNumberForPrintingSurroundings, columnNumberForPrintingSurroundings - 1, position.charAt(4));
                            Navigation.updateMap(rowNumberForPrintingSurroundings - 1, columnNumberForPrintingSurroundings - 1, position.charAt(5));
                            Navigation.updateMap(rowNumberForPrintingSurroundings - 1, columnNumberForPrintingSurroundings, position.charAt(6));
                            Navigation.updateMap(rowNumberForPrintingSurroundings - 1, columnNumberForPrintingSurroundings + 1, position.charAt(7));
                            
                            botSurroundingsFound = true;
                        }
                        
                        if(nWSEOrientation == 'W')
                        {
                            Navigation.updateMap(rowNumber, columnNumber, '.');
                            
                            int rowNumberForPrintingSurroundings = rowNumber;
                            int columnNumberForPrintingSurroundings = columnNumber;
                            
                            Navigation.updateMap(rowNumberForPrintingSurroundings, columnNumberForPrintingSurroundings - 1, position.charAt(0));
                            Navigation.updateMap(rowNumberForPrintingSurroundings - 1, columnNumberForPrintingSurroundings - 1, position.charAt(1));
                            Navigation.updateMap(rowNumberForPrintingSurroundings - 1, columnNumberForPrintingSurroundings, position.charAt(2));
                            Navigation.updateMap(rowNumberForPrintingSurroundings - 1, columnNumberForPrintingSurroundings + 1, position.charAt(3));
                            Navigation.updateMap(rowNumberForPrintingSurroundings, columnNumberForPrintingSurroundings + 1, position.charAt(4));
                            Navigation.updateMap(rowNumberForPrintingSurroundings + 1, columnNumberForPrintingSurroundings + 1, position.charAt(5));
                            Navigation.updateMap(rowNumberForPrintingSurroundings + 1, columnNumberForPrintingSurroundings, position.charAt(6));
                            Navigation.updateMap(rowNumberForPrintingSurroundings + 1, columnNumberForPrintingSurroundings - 1, position.charAt(7));
                            
                            botSurroundingsFound = true;
                        }
                    }
                }
            }
            
            if(!batteryLevelFound && botSurroundingsFound)
            {
                if(message.charAt(x) == '[')
                {
                    
                    if(message.charAt(x + 2) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        batteryLevel = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";
                        batteryLevelFound = true;
                    }
                    
                    if(message.charAt(x + 3) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        charToStringConversion += message.charAt(x + 2);
                        batteryLevel = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";
                        batteryLevelFound = true;
                    }
                    
                    if(message.charAt(x + 4) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        charToStringConversion += message.charAt(x + 2);
                        charToStringConversion += message.charAt(x + 3);
                        batteryLevel = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";
                        batteryLevelFound = true;
                    }
                    
                    if(message.charAt(x + 5) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        charToStringConversion += message.charAt(x + 2);
                        charToStringConversion += message.charAt(x + 3);
                        charToStringConversion += message.charAt(x + 4);
                        batteryLevel = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";
                        batteryLevelFound = true;
                    }
                    //System.out.println(batteryLevel + " BATTERY LEVEL at NAV");
                }
            }
        }
        
        //reset values for next ping
        rowNumberFound = false;
        columnNumberFound = false;
        botSurroundingsFound = false;
        batteryLevelFound = false;
        
        //logic used to calculate how far the bot has moved
        //row number not changed
        if(lastKnownRowNumber == rowNumber)
        {
            if(nWSEOrientation == 'N' || nWSEOrientation == 'S')
            {
                movement = 0;
            }
        }
        else //row number changed or never changed
        {
            
            if(lastKnownRowNumber == -1)
            {
                lastKnownRowNumber = rowNumber;
            }
            else
            {
                movement = rowNumber - lastKnownRowNumber;
                //System.out.print(movement + " movement int NAV FOR ROW");
                if(movement > 0)
                {
                    for(int i = 1; i <= movement; i++)
                    {
                       Navigation.updateMap(lastKnownRowNumber + i, columnNumber, '.'); 
                    }
                }
                
                if(movement < 0)
                {
                    
                    movement = movement * -1;
                    for(int i = 1; i <= movement; i++)
                    {
                       Navigation.updateMap(lastKnownRowNumber - i, columnNumber, '.'); 
                    }
                }
                
                lastKnownRowNumber = rowNumber;
            }
        }
        
        if(lastKnownColumnNumber == columnNumber)
        {
            if(nWSEOrientation == 'W' || nWSEOrientation == 'E')
            {
                movement = 0;
            }
        }
        else //row number changed or never changed
        {
            
            if(lastKnownColumnNumber == -1)
            {
                lastKnownColumnNumber = columnNumber;
            }
            else
            {
                movement = columnNumber - lastKnownColumnNumber;
                //System.out.print(movement + " movement int FOR NAV FOR COL");
                if(movement > 0)
                {
                    for(int i = 1; i <= movement; i++)
                    {
                       Navigation.updateMap(rowNumber, lastKnownColumnNumber + i, '.'); 
                    }
                }
                
                if(movement < 0)
                {
                    
                    movement = movement * -1;
                    for(int i = 1; i <= movement; i++)
                    {
                       Navigation.updateMap(rowNumber, lastKnownColumnNumber - i, '.'); 
                    }
                }
                
                lastKnownColumnNumber = columnNumber;
            }
        }
    } 
    
    /**  
    * Calculates to see if exit is found
    *
    * @param  message
    * @param  row to compare with current row of this object initialization
    * @param  col to compare with current column of this object initialization
    * @param  orientation to compare with current orientation of this object initialization
    * @return if exit is found
    */
    public boolean isExitFound (String message, int row, int col, char orientation)
    {
        
        //System.out.println(message + " = bot status for NAV IS EXIT FOUND METHOD");
        //string
        rowNumberFound = false;
        columnNumberFound = false;
        for(int x = 0; x < message.length(); x++)
        {
            
            if(x == 0)
            {
                nWSEOrientation = message.charAt(x);
            }
            
            if(!rowNumberFound)
            {
                
                if(message.charAt(x) == '[')
                {
                    
                    if(message.charAt(x + 2) == ']')
                    {
                        
                        
                        charToStringConversion += message.charAt(x + 1);
                        rowNumber = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";
                        rowNumberFound = true;
                    }
                    
                    if(message.charAt(x + 3) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        charToStringConversion += message.charAt(x + 2);
                        rowNumber = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";
                        rowNumberFound = true;
                    }
                    
                    if(message.charAt(x + 4) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        charToStringConversion += message.charAt(x + 2);
                        charToStringConversion += message.charAt(x + 3);
                        rowNumber = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";
                        rowNumberFound = true;
                    }
                    x++;
                    //System.out.println(rowNumber + " = row number at NAV EXIT METHOD");
                }
            }
            
            if((!columnNumberFound) && (rowNumberFound))
            {
                
                if(message.charAt(x) == '[')
                {
        
                    if(message.charAt(x + 2) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        columnNumber = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";

                        columnNumberFound = true;
                    }
                    
                    if(message.charAt(x + 3) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        charToStringConversion += message.charAt(x + 2);
                        columnNumber = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";

                        columnNumberFound = true;
                    }
                    
                    if(message.charAt(x + 4) == ']')
                    {
                        charToStringConversion += message.charAt(x + 1);
                        charToStringConversion += message.charAt(x + 2);
                        charToStringConversion += message.charAt(x + 3);
                        columnNumber = Integer.parseInt(charToStringConversion);
                        charToStringConversion = "";

                        columnNumberFound = true;
                    }
                    
                    //System.out.println(columnNumber + " = columnNumber EXIT METHOD NAV");
                }
            }
            
        }
        
        if(rowNumber == row && columnNumber == col && nWSEOrientation == orientation)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**  
    * updates the map with a character
    *
    * @param  row of the map
    * @param  col of the map
    * @param  character to place in the map
    */
    public static void updateMap(int row, int column, char character)
    {
        disasterZoneMap[row][column] = character;
    }
    
    /**  
    * prints the map
    */
    public void printMap() throws IOException
    {

        out = new PrintWriter(DisasterZoneDriver.fileName);
        
        //row
        for(int r = 0; r < 104; r++)
        {
            
            //column
            for(int c = 0; c < 104; c++)
            {
                if(disasterZoneMap[r][c] == 0 && r == 0 || r == 103)
                {
                   out.print('*');
                }
                else if(disasterZoneMap[r][c] == 0 && c == 0 || c == 103)
                {
                    out.print('*');
                }
                else if(disasterZoneMap[r][c] == 0)
                {
                    out.print('?');
                }
                else
                {
                    out.print(disasterZoneMap[r][c]);
                }
            }
            out.println();
        }
        out.close();
    }
    
    /**  
    * returns the battery level
    *
    * @return the battery level
    */
    public int getBatteryLevel()
    {
        return this.batteryLevel;
    }
    
    /**  
    * returns the positive movement number
    *
    * @return returns the positive movement number
    */
    public int getMovementPositive()
    {
        
        if(this.movement >= 0)
        {
            return this.movement;
        }
        else
        {
           return this.movement * -1; 
        }
    }
    
    /**  
    * returns the row number
    *
    * @return returns the row number
    */
    public int getRowNumber()
    {
        return this.rowNumber;
    }
    
    /**  
    * returns the column number
    *
    * @int returns the column number
    */
    public int getColumnNumber()
    {
        return this.columnNumber;
    }
    
    /**  
    * returns the Orientation of the bot
    *
    * @returns returns the Orientation of the bot
    */
    public char getOrientation()
    {
        return this.nWSEOrientation;
    }
    
    /**  
    * returns opposite direction of the orientation
    *
    * @return returns opposite direction of the orientation
    */
    public char startingNWSEOrientationFlipper(char startingNWSEOrientation)
    {
        if(startingNWSEOrientation == 'N')
        {
            return 'S';
        }
        
        if(startingNWSEOrientation == 'S')
        {
            return 'N';
        }
        
        if(startingNWSEOrientation == 'W')
        {
            return 'E';
        }
        
        if(startingNWSEOrientation == 'E')
        {
            return 'W';
        }
        
        return 'o';
    }
}

