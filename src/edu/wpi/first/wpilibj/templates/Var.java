/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

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
public class Var {
    
    // PWM
    static final int chnVicDrvLeft = 1;
    static final int chnVicDrvRight = 2;
    static final int chnVicRetrieve = 3;
	
    // Solenoids
    // SOLENOID BUMPER CHANNEL 7 OR 8 DOES NOT WORK!!!!!!
    static final int chnSolReleaseUp = 1;
    static final int chnSolReleaseDown = 2;
	
    // Digital Stuff ?
    static final int chnDigiCompressor = 1;
    static final int chnDigiSensor = 2;
    static final int chnRelay = 3;

    // Joysticks and buttons
    static final int btReleaseBall = 1;
    static final int btActRetrieve = 3;
    static final int btTurnOnCompressor = 4;
    static final int btPrintFile = 5;
    static final int btModifyAuto = 6;
    static final int btChangeDrive = 8;
    static final int btRecord = 9;
    static final int btAllowEdit = 10;
    static final int btChangeFile = 11;
    static final int btReplay = 12;
    
    // Other
    static final double dENDSIGNAL = -10.0;
    static final double dPrecision = 2;
    static final CPrintDriver drvStationPrinter = new CPrintDriver();
    static boolean bDrive = true;
    
    // DriverStation Channels
    static final int chnDigInReg = 0;
    static final int chnDigInAutoCtr = 1;
    static final int chnDigInAutoLft = 2;
    static final int chnDigInAutoRght = 3;
    static final int chnAngMin = 1;
    static final int chnAngMax = 2;
    static final int chnAngMtLeft = 3;
    static final int chnAngMtRight = 4;
    
    // Printing to Driverstation lines, 2-6 are available only
    static final int iDriveStatusLine = 2;
    static final int iEditFileStat = 4;
    static final int iEditAutoMode = 5;
    static final int iRecordStatusLine = 6;
    
    public static String setPrecision(double dDouble)
    {
        String sArg = Double.toString(dDouble);
        String sReturn = "";
        boolean bAfterDec = false;
        int iSpaceAfter = 0;
        
        for(int index = 0; index < sArg.length(); index++)
        {
            sReturn += sArg.charAt(index);
            
            if(sArg.charAt(index) == '.')
                bAfterDec = true;
            
            else if(bAfterDec)
                iSpaceAfter++;
                
            if(iSpaceAfter == dPrecision)
                break;
        }
        
        return sReturn;
    }
}
