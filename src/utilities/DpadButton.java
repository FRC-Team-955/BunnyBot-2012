/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author fauzi
 */
public class DpadButton {
    
    private Joystick m_Joy;
    private final int m_iChnVert = 6;
    private final int m_iChnHorz = 5;
    private final double m_dMIN_SPEED = 0.2;
    
    public DpadButton(Joystick joystick)
    {
        m_Joy = joystick;
    }
    
    public int getDpadStatus()
    {
        if(m_Joy.getRawAxis(m_iChnVert) > m_dMIN_SPEED)
            return 1;
        
        else if(m_Joy.getRawAxis(m_iChnVert) < -m_dMIN_SPEED)
            return 3;
        
        else if(m_Joy.getRawAxis(m_iChnHorz) > m_dMIN_SPEED)
            return 2;
        
        else if(m_Joy.getRawAxis(m_iChnHorz) < -m_dMIN_SPEED)
            return 4;
        
        else
            return 0;
    }
}
