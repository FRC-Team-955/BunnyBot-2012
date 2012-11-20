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

    // Joysticks and buttons
    static final int btReleaseBall = 1;
    static final int btReplay = 2;
    static final int btActRetrieve = 3;
    static final int btTurnOnCompressor = 4;
    static final int btChangeMode = 7;
    static final int btChangeDrive = 8;
    static final int btRecord = 9;
    static final int btAllowEdit = 10;
    
    // Other
    static CPrintDriver drvStationPrinter = new CPrintDriver();
    static boolean bDrive = true;
    static final String sAutoOutput = "file:///autoval.txt";
    
    // Printing to Driverstation lines, 2-6 are available only
    static final int iDriveStatusLine = 2;
    static final int iEditAutoMode = 5;
    static final int iRecordStatusLine = 6;
}
