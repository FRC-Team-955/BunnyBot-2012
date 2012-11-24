/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    Joystick ps3Joy = new Joystick(1);
    CDrive drive = new CDrive(ps3Joy);
    CRetrieve retrieve = new CRetrieve(ps3Joy);
    CRelease releaser = new CRelease(ps3Joy);
    CCompressor compressor = new CCompressor(ps3Joy);
    CRecord recorder = new CRecord(ps3Joy, drive, retrieve);
	CSpecialButton btTest = new CSpecialButton();
	CButton btMoo = new CButton();
	boolean bTest = false;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
 
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        
        recorder.replay(Var.sAutoOutput);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
       
		btTest.run(ps3Joy.getRawButton(5), bTest);
		btMoo.run((ps3Joy.getRawButton(5)));
		
		System.out.println(bTest);
		
//		if(btTest.gotPressed())
//			System.out.println("Test got pressed!");		
		
        drive.run();
        retrieve.run();
        releaser.run();
        compressor.run();
        recorder.run();
    }
}
