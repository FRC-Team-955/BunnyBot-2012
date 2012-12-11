package core;
import utilities.Vars;
import utilities.Button;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;

/**
 * @author fauzi
 */

public class Drive {
    
    // CONSTANTS 
    private final double m_dMinSpeed = 0.05;
    
    private Victor m_mtRight = new Victor(Vars.chnVicDrvRight);
    private Victor m_mtLeft = new Victor(Vars.chnVicDrvLeft);
    private double m_mtRightSpeed = 0;
    private double m_mtLeftSpeed = 0;
    private Joystick m_joy;
    private Button m_btChangeDrive = new Button();
    private Button m_btReverseDrive = new Button();
    private String m_sDriveStatus = "";
    
    public Drive(Joystick joystick)
    {
        m_joy = joystick;
    }
    
    public void run()
    {      
        m_btChangeDrive.run(m_joy.getRawButton(Vars.btChangeDrive));
        m_btReverseDrive.run(m_joy.getRawButton(Vars.btReverseDrive));
        
        if(Vars.bDrive)
        {
            if(m_btChangeDrive.getSwitch())
                tankDrive();

            else
                regDrive();
            
            if(m_btReverseDrive.getSwitch())
            {
                m_mtLeftSpeed = -m_mtLeftSpeed;
                m_mtRightSpeed = -m_mtRightSpeed;
            }
            
            if(Math.abs(m_mtLeftSpeed) + Math.abs(m_mtRightSpeed) > m_dMinSpeed)
                this.setSpeed(m_mtLeftSpeed, m_mtRightSpeed);

            else
                this.setSpeed(0, 0);
        }
        
        else
            m_sDriveStatus = "Disabled";
        
        Vars.drvStationPrinter.print(Vars.iDriveStatusLine, m_sDriveStatus);
    }
    
    private void tankDrive()
    {
        // Setting to get Tank Drive Working properly on the ps3 Controller
        // should be 2, 4
        m_joy.setAxisChannel(Joystick.AxisType.kX, 2);
        m_joy.setAxisChannel(Joystick.AxisType.kY, 4);

        m_sDriveStatus = "Tank Drive";
        m_mtLeftSpeed = m_joy.getX() * Math.abs(m_joy.getX());
        m_mtRightSpeed = -m_joy.getY() * Math.abs(m_joy.getY());  
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

        m_mtRightSpeed = (-y+x);
        m_mtLeftSpeed = (y+x);  
    }
	
    public void setSpeed(double setMtLeft, double setMtRight)
    {
        m_mtLeft.set(setMtLeft);
        m_mtRight.set(setMtRight);
    }
    
    public double getMtRightSpeed()
    {
        return m_mtRightSpeed;
    }
    
    public double getMtLeftSpeed()
    {
        return m_mtLeftSpeed;
    }
}
