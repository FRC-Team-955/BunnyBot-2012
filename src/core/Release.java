package core;

import utilities.Vars;
import utilities.MySolenoid;
import utilities.MyJoystick;

/**
 *
 * @author fauzi
 */
public class Release {
    
    private boolean m_bReleaseStat = false;
    private MySolenoid m_solRelease = new MySolenoid(Vars.chnSolReleaseDown, Vars.chnSolReleaseUp, false);
    private MyJoystick m_Joy;
    
    public Release(MyJoystick joystick)
    {
       m_Joy = joystick;
    }
    
    public void run()
    {
        if(m_Joy.gotPressed(Vars.btReleaseBall))
            m_bReleaseStat = !m_bReleaseStat;
        
        m_solRelease.set(m_bReleaseStat);
    }
}
