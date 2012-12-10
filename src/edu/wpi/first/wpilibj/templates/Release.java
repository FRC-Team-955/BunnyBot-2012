/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author fauzi
 */
public class Release {
    
    private MySolenoid m_solRelease = new MySolenoid(Var.chnSolReleaseDown, Var.chnSolReleaseUp, true);
    private Button m_btReleaseBall = new Button();
    private boolean m_bRelease = false;
    private Joystick m_Joy;
    
    public Release(Joystick joystick)
    {
       m_Joy = joystick;
    }
    
    public void run()
    {
        m_btReleaseBall.run(m_Joy.getRawButton(Var.btReleaseBall));
        
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
