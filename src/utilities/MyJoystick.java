package utilities;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author fauzi
 */
public class MyJoystick extends Joystick{
    
    private boolean m_bLast[];
    private final int m_iChnVert = 6;
    private final int m_iChnHorz = 5;
    private final double m_dMIN_SPEED = 0.2;
    
    public MyJoystick(int iChan, int iAmountOfButtons)
    {
        super(iChan);
        m_bLast = new boolean[iAmountOfButtons];
        
        for(int index = 0; index < iAmountOfButtons; index++)
            m_bLast[index] = false;
    }
    
    public boolean gotPressed(int iChan)
    {
        boolean bOutput = (m_bLast[iChan] == false && getRawButton(iChan));
        m_bLast[iChan] = getRawButton(iChan);
        return bOutput;
    }
    
    public boolean getDpadUp()
    {
        return (getRawAxis(m_iChnVert) > m_dMIN_SPEED);
    }
        
    public boolean getDpadRight()
    {
        return (getRawAxis(m_iChnHorz) > m_dMIN_SPEED);
    }
    
    public boolean getDpadDown()
    {
        return (getRawAxis(m_iChnVert) < -m_dMIN_SPEED);
    }
        
    public boolean getDpadLeft()
    {
        return (getRawAxis(m_iChnHorz) < -m_dMIN_SPEED);
    }
}
