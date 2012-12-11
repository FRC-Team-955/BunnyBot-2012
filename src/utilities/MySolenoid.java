/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Fauzi
 */

/*
 * Purpose of this class is so that it can simply the job of making solenoids
 * and having to manualy set them indivdually on and off. So instead I just made
 * a MySolenoid class with functions that do it for us :D
 */
public class MySolenoid 
{

    private Solenoid m_solUp;
    private Solenoid m_solDown;
    private boolean m_bSetDown;
    
    public MySolenoid(int solDownChannel, int solUpChannel, boolean bDown)
    {
        m_bSetDown = bDown;
        m_solUp = new Solenoid(solUpChannel);
        m_solDown = new Solenoid(solDownChannel);
        m_solUp.set(!m_bSetDown);
        m_solDown.set(m_bSetDown);
    }
    
    public void turnOn()
    {
        m_solUp.set(m_bSetDown);
        m_solDown.set(!m_bSetDown);
    }
    
    public void turnOff()
    {
        m_solUp.set(!m_bSetDown);
        m_solDown.set(m_bSetDown); 
    }
    
    public void set(boolean bVal)
    {
        if(bVal)
        {
            m_solUp.set(true);
            m_solDown.set(false);
        }
        else
        {
            m_solUp.set(false);
            m_solDown.set(true);
        }
    }
    
    public boolean getUp()
    {
        return m_solUp.get();
    }

    public boolean getDown()
    {
        return m_solDown.get();
    }
}
