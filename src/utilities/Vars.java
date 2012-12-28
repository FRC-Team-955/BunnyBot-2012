package utilities;

import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 *
 * @author Fauzi
 */

/*
 * This class has all the variables that are used the set the 
 * channels, slots or speed parameters for any object in the t-shirt cannon.
 * We have a seperate class just so we can change the channel more easily rather
 * than seaching through the class itself.
 */
public class Vars {
    
    // Victors
    public static final int chnVicDrvLeft = 1;
    public static final int chnVicDrvRight = 2;
    public static final int chnVicRetrieve = 3;
	
    // Solenoids
    // SOLENOID BUMPER CHANNEL 7 OR 8 DOES NOT WORK!!!!!!
    public static final int chnSolReleaseUp = 1;
    public static final int chnSolReleaseDown = 2;
	
    // Digital Sidecard 
    public static final int chnCompressor = 3;
    public static final int chnDigiSensor = 14;

    // Button channel on the joystick
    public static final int btReleaseBall = 1;
    public static final int btActRetrieve = 3;
    public static final int btRecord = 9;
    public static final int btAllowEdit = 10;
    public static final int btReplay = 12;
    
    // DriverStation Autonomous Button Channels
    public static final int chnDigInReg = 0;
    public static final int chnDigInAutoCtr = 1;
    public static final int chnDigInAutoLft = 2;
    public static final int chnDigInAutoRght = 3;
    
    // Printing to Driverstation lines, 2-6 are available only
    public static final int iDriveStatusLine = 2;
    public static final int iEditAutoModeLine = 5;
    public static final int iAutonomousStatLine = 6;
    
    // Other
    public static final int iPs3Buttons = 13;
    private static boolean bDrive = true;
    
    /**
     * Sets the double to be only at the hundreth's place, ex. 12.34
     */
    public static double fnSetPrecision(double dDouble)
    {
        return (Double.valueOf(Math.floor(dDouble * 10 + 0.5) / 10)).doubleValue();
    }
    
    /**
     * Disables ability for user to drive robot
     */
    public static void fnDisableDrive()
    {
        bDrive = false;
    }
    
    /**
     * Enables ability for the user to drive the robot
     */
    public static void fnEnableDrive()
    {
        bDrive = true;
    }
    
    /**
     * Checks if the user has as the ability to drive the robot
     */
    public static boolean fnCanDrive()
    {
        return bDrive;
    }
    
    /**
     * Prints specified message to the driver station on the corresponding line
     */
    public static void fnPrintToDriverstation(int iLine, String sMessage)
    {
        switch(iLine)
        {
            case 2:
            {
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, "");
                DriverStationLCD.getInstance().updateLCD(); 
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, sMessage);
                break;
            }
                
            case 3:
            {
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "");
                DriverStationLCD.getInstance().updateLCD(); 
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, sMessage);
                break;
            }
                
            case 4:
            {
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser4, 1, "");
                DriverStationLCD.getInstance().updateLCD(); 
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser4, 1, sMessage);
                break;
            }
                
            case 5:
            {
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser5, 1, "");
                DriverStationLCD.getInstance().updateLCD(); 
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser5, 1, sMessage);
                break;
            }
                
            case 6:
            {
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser6, 1, sMessage);
                DriverStationLCD.getInstance().updateLCD(); 
                DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser6, 1, sMessage);
                break;
            }
        }
        
        DriverStationLCD.getInstance().updateLCD(); 
    }
}
