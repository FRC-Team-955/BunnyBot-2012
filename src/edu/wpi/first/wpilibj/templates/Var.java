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
    static final int chnVicDrvRight = 1;
    static final int chnVicDrvLeft = 2;
    static final int chnVicRetrieve = 3;
	
    // Solenoids
    // SOLENOID BUMPER CHANNEL 7 OR 8 DOES NOT WORK!!!!!!
    static final int chnSolReleaseUp = 1;
    static final int chnSolReleaseDown = 2;

    // Joysticks and buttons
    static final int btActRetrieve = 3;
    static final int btRecord = 9;
    static final int btReplay = 12;
    static final int btClearList = 10;
    static final int btSwitchLayout = 11;
    static final int btReleaseBall = 1;
    
    // Other
    static final double dMaxRecordingTime = 14.5;
    static final double dRcrdLimit = 14;
    static final int chnJoyDrive = 3;
    static final int iArrayRecrdSize = 12000;
    static boolean bAnotherIsPressed = false;
    static boolean bDrive = true;
    static String sOutput = "AutoVal";
    
    // Printing to Driverstation lines, 2-6 are available only
    static final int iRecordStatusLine = 5;
}
