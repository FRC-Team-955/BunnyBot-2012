/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import core.Drive;
import core.Release;;
import core.Retrieve;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author fauzi
 */
public class Robot {
    
    private Joystick m_joy;
    private Drive m_drive;
    private Release m_release;
    private Retrieve m_retrieve;
    
    public Robot(Joystick joytick)
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
    
    public double getMtLeft()
    {
        return m_drive.getMtLeftSpeed();
    }
    
    public double getMtRight()
    {
        return m_drive.getMtRightSpeed();
    }
    
    public boolean getRetrieveStat()
    {
        return m_retrieve.getStatus();
    }
    
    public void setSpeed(double dLeft, double dRight)
    {
        m_drive.setSpeed(dLeft, dRight);
    }
    
    public void setRetrieve(boolean bOn)
    {
        m_retrieve.set(bOn);
    }
}
