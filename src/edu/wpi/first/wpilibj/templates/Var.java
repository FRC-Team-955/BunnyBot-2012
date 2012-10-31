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

    // Joysticks and buttons
    static final int btActivateRetrieve = 3;
    static final int buttonRecord = 9;
    static final int buttonReplay = 12;
    static final int buttonClearList = 10;
    
    // Other
    //static boolean bRecord = false;
    static boolean bDrive = true;
    static final int chanJoyDrive = 3;
    
    // Printing to Driverstation lines, 2-6 are available only

    static final int iRecordStatusLine = 5;
}
