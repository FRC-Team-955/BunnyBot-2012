package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Timer;

/*
 * @author fauzi
 */

public class CTimer {
    
    Timer m_Timer = new Timer();
    boolean m_bIsRunning = false;
    
    public void start()
    {
        if(!m_bIsRunning)
        {
            m_Timer.start();
            m_bIsRunning = true;
        }
    }
    
    public void stop()
    {
        if(m_bIsRunning)
        {
            m_Timer.stop();
            m_bIsRunning = false;
        }
    }
    
    public double get()
    {
        return m_Timer.get();
    }
    
    public void reset(boolean bStopAsWell)
    {
        if(bStopAsWell)
            m_Timer.stop();
        
        m_Timer.reset();
    }
}
