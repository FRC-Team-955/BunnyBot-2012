/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

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
    
    // PWM
    public static final int chnVicDrvLeft = 1;
    public static final int chnVicDrvRight = 2;
    public static final int chnVicRetrieve = 3;
	
    // Solenoids
    // SOLENOID BUMPER CHANNEL 7 OR 8 DOES NOT WORK!!!!!!
    public static final int chnSolReleaseUp = 1;
    public static final int chnSolReleaseDown = 2;
	
    // Digital Stuff 
    public static final int chnCompressor = 3;
    public static final int chnDigiSensor = 14;

    // Joysticks and buttons
    public static final int btReleaseBall = 1;
    public static final int btActRetrieve = 3;
    public static final int btRecord = 9;
    public static final int btAllowEdit = 10;
    public static final int btReplay = 12;
    
    // Other
    public static boolean bDrive = true;
    public static final double dENDSIGNAL = -10.0;
    public static final PrintDriver drvStationPrinter = new PrintDriver();
    public static final String sRegOutput = "file:///regVal.txt";
    
    // DriverStation Channels
    public static final int chnDigInReg = 0;
    public static final int chnDigInAutoCtr = 1;
    public static final int chnDigInAutoLft = 2;
    public static final int chnDigInAutoRght = 3;
    
    // Printing to Driverstation lines, 2-6 are available only
    public static final int iDriveStatusLine = 2;
    public static final int iEditAutoMode = 5;
    public static final int iRecordStatusLine = 6;
    
    public static Double setPrecision(double dDouble)
    {
        return Double.valueOf(Math.floor(dDouble * 10 + 0.5) / 10);
    }
}
