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
    
    public void turnOn()
    {
        m_solUp.set(m_bDefaultStat);
        m_solDown.set(!m_bDefaultStat);
    }
    
    public void turnOff()
    {
        m_solUp.set(!m_bDefaultStat);
        m_solDown.set(m_bDefaultStat); 
    }
    
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
    
    public boolean getUp()
    {
        return m_solUp.get();
    }

    public boolean getDown()
    {
        return m_solDown.get();
    }
}
