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
    private final double m_dMuiltiplier = 8;
    private final int m_iLastArraySize = 50;
    
    private double[] m_dLastArrayLeft = new double[m_iLastArraySize];
    private double[] m_dLastArrayRight = new double[m_iLastArraySize];
    private double m_dRightSpeed = 0;
    private double m_dLeftSpeed = 0;
    private String m_sDriveStatus = "";
    private Victor m_mtLeft = new Victor(Vars.chnVicDrvLeft);
    private Victor m_mtRight = new Victor(Vars.chnVicDrvRight);
    private Joystick m_joy;
    
    public Drive(Joystick joystick)
    {
        // Initialize arrays to zero
        for(int iPos = 0; iPos < m_iLastArraySize; iPos++)
        {
            m_dLastArrayLeft[iPos] = 0;
            m_dLastArrayRight[iPos] = 0;
        }
        
        m_joy = joystick;
    }
    
    public void run()
    {      
        if(Vars.bDrive)
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
        
        // Update the last variable
        addToLast(m_dLeftSpeed, m_dRightSpeed); 
        m_dRightSpeed = (-y+x);
        m_dLeftSpeed = (y+x);  
        
        
    }
    
    public void setSpeed(double setMtLeft, double setMtRight)
    {
        overDrive(setMtLeft, setMtRight);
    }
    
    private void overDrive(double dLeftSpeed, double dRightSpeed)
    {
        /*
         * Gets the difference between the wanted speed and the current speed,
         * muiltiplies it by a constant and adds it the current speed for a 
         * better response from the motors, thus called "overdrive"
         */
        
        final double dCurrentLeft = getLastLeft();
        final double dCurrentRight = getLastRight();
        final double dLeftDiff = dLeftSpeed - dCurrentLeft;
        final double dRightDiff = dRightSpeed - dCurrentRight;
        
        m_mtLeft.set(dLeftSpeed + (m_dMuiltiplier * dLeftDiff));
        m_mtRight.set(dRightSpeed + (m_dMuiltiplier * dRightDiff));
    }
    
    private void addToLast(double dNewLeft, double dNewRight)
    {
        for(int iPos = 0; iPos < m_iLastArraySize - 1; iPos++)
        {
            m_dLastArrayLeft[iPos] = m_dLastArrayLeft[iPos + 1];
            m_dLastArrayRight[iPos] = m_dLastArrayRight[iPos + 1];
        }
        
        m_dLastArrayLeft[m_iLastArraySize - 1] = dNewLeft;
        m_dLastArrayRight[m_iLastArraySize - 1] = dNewRight;
    }
    
    private double getLastLeft()
    {
        return m_dLastArrayLeft[0];
    }
    
    private double getLastRight()
    {
        return m_dLastArrayRight[0];
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