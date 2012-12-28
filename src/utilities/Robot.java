package utilities;

import core.Drive;
import core.Release;;
import core.Retrieve;

/**
 *
 * @author fauzi
 */
public class Robot {
    
    private MyJoystick m_joy;
    private Drive m_drive;
    private Release m_release;
    private Retrieve m_retrieve;
    
    public Robot(MyJoystick joytick)
    {
        m_joy = joytick;
        m_drive = new Drive(m_joy);
        m_release = new Release(m_joy);
        m_retrieve = new Retrieve(m_joy);
    }
    
    public void run()
    {
        m_drive.run();
        m_release.run();
        m_retrieve.run();
    }
    
    public boolean getRetrieveStat()
    {
        return m_retrieve.getStatus();
    }
    
    public double getMotorLeft()
    {
        return m_drive.getMotorLeft();
    }
    
    public double getMotorRight()
    {
        return m_drive.getMotorRight();
    }
    
    public void setSpeed(double dLeft, double dRight)
    {
        m_drive.setSpeed(dLeft, dRight);
    }
    
    public void setRetrieve(boolean bRetrieveVal)
    {
        m_retrieve.set(bRetrieveVal);
    }
}
