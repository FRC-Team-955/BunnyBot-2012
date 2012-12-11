/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package core;
import autonomous.Autonomous;
import utilities.Robot;
import utilities.Vars;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    Joystick ps3Joy = new Joystick(1);
    Robot bot = new Robot(ps3Joy);
    Compressor compressor = new Compressor();
    Autonomous autonomous = new Autonomous(ps3Joy, bot);
    
    // This function is run when the robot is first started up and should be
    // used for any initialization code.
    public void robotInit() {
 
    }

    // This function is called when we disable the robot.
    public void disabledInit()
    {
        autonomous.resetAutonomous(); // Resets the replay to false if it was true before
    }
    
    // Called once in autonomous
    // Tells autonomous which file to play based on
    // the value of "iFileType"
    public void autonomousInit()
    {
        int iFileType = Vars.chnDigInReg;
        
        if(DriverStation.getInstance().getDigitalIn(Vars.chnDigInAutoCtr))
            iFileType = Vars.chnDigInAutoCtr;
        
        else if(DriverStation.getInstance().getDigitalIn(Vars.chnDigInAutoLft))
            iFileType = Vars.chnDigInAutoLft;
        
        else if(DriverStation.getInstance().getDigitalIn(Vars.chnDigInAutoRght))
            iFileType = Vars.chnDigInAutoRght;
        
        autonomous.changeFile(iFileType);
    }
    
    // This function is called periodically during autonomous
    public void autonomousPeriodic() {
        autonomous.replay();
    }

    // This function is called periodically during operator control
    public void teleopPeriodic(){
        
        bot.run();
        compressor.run();
        autonomous.run();
    }
}
