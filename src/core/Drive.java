package core;
import utilities.Vars;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;

/**
 * @author fauzi
 */

public class Drive {
    
    private double m_dRightSpeed = 0;
    private double m_dLeftSpeed = 0;
    private String m_sDriveStatus = "";
    private Victor m_mtLeft = new Victor(Vars.chnVicDrvLeft);
    private Victor m_mtRight = new Victor(Vars.chnVicDrvRight);
    private Joystick m_joy;
    
    public Drive(Joystick joystick)
    {
        m_joy = joystick;
    }
    
    public void run()
    {      
        if(Vars.canDrive())
        {
            regDrive();
            setSpeed(m_dLeftSpeed, m_dRightSpeed);
        }
        
        else
            m_sDriveStatus = "Disabled";
        
        Vars.drvStationPrinter.print(Vars.iDriveStatusLine, m_sDriveStatus);
    }

    private void regDrive()
    {
        // Setting to get regular drive Working properly on the ps3 Controller
        // should be 3, 2
        m_joy.setAxisChannel(Joystick.AxisType.kX, 3);
        m_joy.setAxisChannel(Joystick.AxisType.kY, 2);

        m_sDriveStatus = "Regular Drive";
        double y = m_joy.getY() * Math.abs(m_joy.getY());
        double x = m_joy.getX() * Math.abs(m_joy.getX());
        
        m_dRightSpeed = (-y+x);
        m_dLeftSpeed = (y+x);  
    }
    
    public void setSpeed(double setMtLeft, double setMtRight)
    {
        m_mtLeft.set(setMtLeft);
        m_mtRight.set(setMtRight);
    }
    
    public double getMtRightSpeed()
    {
        return m_dRightSpeed;
    }
    
    public double getMtLeftSpeed()
    {
        return m_dLeftSpeed;
    }
}