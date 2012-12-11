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
    private Joystick m_Joy;
    
    public Release(Joystick joystick)
    {
       m_Joy = joystick;
    }
    
    public void run()
    {
        m_btReleaseBall.run(m_Joy.getRawButton(Vars.btReleaseBall));
        
        if(m_btReleaseBall.getSwitch())
            m_solRelease.turnOn();
        
        else
            m_solRelease.turnOff();
    }
}
