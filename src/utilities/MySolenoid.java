package utilities;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Fauzi
 */

/**
 * Purpose of this class is so that it can simply the job of making solenoids
 * and having to manually set them individually on and off. So instead I just made
 * a MySolenoid class with functions that do it for us :D
 */
public class MySolenoid 
{

    private Solenoid m_solUp;
    private Solenoid m_solDown;
    private boolean m_bDefaultStat;
    
    public MySolenoid(int solDownChannel, int solUpChannel, boolean bDefault)
    {
        // True means its backwards, so the default is on
        m_bDefaultStat = bDefault;
        m_solUp = new Solenoid(solUpChannel);
        m_solDown = new Solenoid(solDownChannel);
        m_solUp.set(m_bDefaultStat);
        m_solDown.set(!m_bDefaultStat);
    }
    
    /**
     * Activates the solenoids
     */
    public void turnOn()
    {
        m_solUp.set(m_bDefaultStat);
        m_solDown.set(!m_bDefaultStat);
    }
    
    /**
     * Deactivates the solenoids
     */
    public void turnOff()
    {
        m_solUp.set(!m_bDefaultStat);
        m_solDown.set(m_bDefaultStat); 
    }
    
    /**
     * Sets the solenoids based on argument, true means active
     * @param bVal 
     */
    public void set(boolean bVal)
    {
        if(bVal)
        {
            m_solUp.set(m_bDefaultStat);
            m_solDown.set(!m_bDefaultStat);
        }
        else
        {
            m_solUp.set(!m_bDefaultStat);
            m_solDown.set(m_bDefaultStat);
        }
    }
}
