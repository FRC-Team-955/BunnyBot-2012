package utilities;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Encapsulate the Joystick class so that buttons would be better.
 * @author fauzi
 */
public class MyJoystick extends Joystick{
    
    private boolean m_bLast[];
    private boolean m_bSwitch[];
    private final int m_iChnVert = 6;
    private final int m_iChnHorz = 5;
    private final double m_dDpadTolerance = 0.2;
    
    public MyJoystick(int iChan, int iAmountOfButtons)
    {
        super(iChan);
        m_bLast = new boolean[iAmountOfButtons];
        m_bSwitch = new boolean[iAmountOfButtons];
        
        for(int index = 0; index < iAmountOfButtons; index++)
        {
            m_bLast[index] = false;
            m_bSwitch[index] = false;
        }
    }
    
    /**
     * Determines whether the specified button was pressed.
     * @param iChan
     * @return 
     */
    public boolean gotPressed(int iChan)
    {
        boolean bOutput = (!m_bLast[iChan] && getRawButton(iChan));
        m_bLast[iChan] = getRawButton(iChan);
        return bOutput;
    }
    
    /**
     * Returns the switch value. 
     * @param iChan
     * @return 
     */
    public boolean getSwitch(int iChan)
    {
        return m_bSwitch[iChan];
    }
    
    /**
     * Sets the switch value.
     * @param iChan
     * @param bVal 
     */
    public void setSwitch(int iChan, boolean bVal)
    {
        m_bSwitch[iChan] = bVal;
    }
    
    /**
     * Flips the switch value on the specified button.
     * @param iChan 
     */
    public void flipSwitch(int iChan)
    {
        m_bSwitch[iChan] = !m_bSwitch[iChan];
    }
    
    /**
     * Gets the value of the dPad up
     * @return 
     */
    public boolean getDpadUp()
    {
        return (getRawAxis(m_iChnVert) > m_dDpadTolerance);
    }
     
    /**
     * Gets the value of the dPad right
     * @return 
     */
    public boolean getDpadRight()
    {
        return (getRawAxis(m_iChnHorz) > m_dDpadTolerance);
    }
    
    /**
     * Gets the value of the dPad down
     * @return 
     */
    public boolean getDpadDown()
    {
        return (getRawAxis(m_iChnVert) < -m_dDpadTolerance);
    }
        
    /**
     * Gets the value of the dPad left
     * @return 
     */
    public boolean getDpadLeft()
    {
        return (getRawAxis(m_iChnHorz) < -m_dDpadTolerance);
    }
}
