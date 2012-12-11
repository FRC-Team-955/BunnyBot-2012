/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import utilities.Vars;
import utilities.MySolenoid;
import utilities.Button;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author fauzi
 */
public class Release {
    
    private MySolenoid m_solRelease = new MySolenoid(Vars.chnSolReleaseDown, Vars.chnSolReleaseUp, true);
    private Button m_btReleaseBall = new Button();
    private boolean m_bRelease = false;
    private Joystick m_Joy;
    
    public Release(Joystick joystick)
    {
       m_Joy = joystick;
    }
    
    public void run()
    {
        m_btReleaseBall.run(m_Joy.getRawButton(Vars.btReleaseBall));
        
        if(m_btReleaseBall.gotPressed())
            m_bRelease = !m_bRelease;
        
        if(m_bRelease)
            m_solRelease.turnOn();
        
        else
            m_solRelease.turnOff();
    }
    
    public boolean getReleaseStatus()
    {
        return m_bRelease;
    }
    
    public void set(boolean bReleaseStatus)
    {
        m_solRelease.set(bReleaseStatus);
    }
}
