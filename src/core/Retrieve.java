package core;

import utilities.Vars;
import utilities.MyJoystick;
import edu.wpi.first.wpilibj.Victor;

/**
 * @author Fauzi
 */
public class Retrieve {
    
    private boolean m_bRetrieveStatus = false;
    private double m_dRetrieveSpeed = -1;
    private Victor m_mtRetrieve = new Victor(Vars.chnVicRetrieve);
    private MyJoystick m_joy;
    
    public Retrieve(MyJoystick joystick)
    {
        m_joy = joystick;
    }
    
    public void run()
    {
        if(m_joy.gotPressed(Vars.btActRetrieve))
            m_bRetrieveStatus = !m_bRetrieveStatus;
        
        if(m_bRetrieveStatus)
            m_mtRetrieve.set(m_dRetrieveSpeed);
        
        else
            m_mtRetrieve.set(0);
    }
    
    public boolean getStatus()
    {
        return m_bRetrieveStatus;
    }
    
    public void set(boolean bStatus)
    {
        if(bStatus)
            m_mtRetrieve.set(m_dRetrieveSpeed);
        
        else
            m_mtRetrieve.set(0);
    }
}
