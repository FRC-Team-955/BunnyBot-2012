package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;

/**
 * @author fauzi
 */

public class CDrive {
    
    // CONSTANTS 
    private double dMinSpeed = 0.05;
    
    private Victor mtRight = new Victor(Var.chnVicDrvRight);
    private Victor mtLeft = new Victor(Var.chnVicDrvLeft);
    private double mtRightSpeed = 0;
    private double mtLeftSpeed = 0;
    private Joystick joy;
    private CButton btChangeDrive = new CButton();
    private String sPrintWhat = "";
    
    public CDrive(Joystick joystick)
    {
        joy = joystick;
    }
    
    public void run()
    {      
        btChangeDrive.run(joy.getRawButton(Var.btChangeDrive));
        
        if(Var.bDrive)
        {
            if(btChangeDrive.getSwitch())
            {
                // Setting to get Tank Drive Working properly on the ps3 Controller
                // should be 2, 4
                joy.setAxisChannel(Joystick.AxisType.kX, 2);
                joy.setAxisChannel(Joystick.AxisType.kY, 4);
                
                sPrintWhat = "Tank Drive";
                mtLeftSpeed = -joy.getX() * Math.abs(joy.getX());
                mtRightSpeed = joy.getY() * Math.abs(joy.getY());
            }

            else
            {	
                // Setting to get regular drive Working properly on the ps3 Controller
                // should be 3, 2
                joy.setAxisChannel(Joystick.AxisType.kX, 3);
                joy.setAxisChannel(Joystick.AxisType.kY, 2);
                
                sPrintWhat = "Regular Drive";
                double y = joy.getY() * Math.abs(joy.getY());
                double x = joy.getX() * Math.abs(joy.getX());

                mtRightSpeed = (-y+x);
                mtLeftSpeed = (y+x);
            }
            
            if(Math.abs(mtLeftSpeed) + Math.abs(mtRightSpeed) > dMinSpeed)
                this.setSpeed(mtLeftSpeed, mtRightSpeed);

            else
                this.setSpeed(0, 0);   
        }
        
        else
            sPrintWhat = "Disabled";
        
        Var.drvStationPrinter.print(Var.iDriveStatusLine, sPrintWhat);
    }
    
    public void setSpeed(double setMtLeft, double setMtRight)
    {
        mtLeft.set(setMtLeft);
        mtRight.set(setMtRight);
    }
    
    public double getMtRightSpeed()
    {
        return mtRightSpeed;
    }
    
    public double getMtLeftSpeed()
    {
        return mtLeftSpeed;
    }
}
