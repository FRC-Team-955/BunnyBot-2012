package edu.wpi.first.wpilibj.templates;

/*
 * @author Fauzi
 */

/*
 * This class is to reduce the procees to see if a button is pressed
 *  rather than do it manually which was tedious.
 */

// This class allows only one button to pressed at a time
public class CSpecialButton {
    private boolean m_bCurState = false;
    private boolean m_bLastState = false;
    private boolean m_bSwitch = false;
    private boolean m_bAnotherIsPressed = false;
    
    public boolean run(boolean button, boolean bCanWePress)
    {
        m_bAnotherIsPressed = bCanWePress;
        
        m_bLastState = m_bCurState;
        m_bCurState = button;
		
        if(m_bCurState == true && m_bLastState == false) // For setting the button switch value
        {
            if(m_bAnotherIsPressed && m_bSwitch)
            {   
                m_bAnotherIsPressed = false;
                m_bSwitch = false;
            }

            else if(!m_bAnotherIsPressed)
            {
                m_bAnotherIsPressed = true;
                m_bSwitch = true;
            } 
        }
        
        return m_bAnotherIsPressed;
    }
	
	public boolean getSwitch()
    {
        return m_bSwitch;
    }
	
    public boolean setOppisite()
    {
        if(m_bAnotherIsPressed && m_bSwitch)
        {   
            m_bAnotherIsPressed = false;
            m_bSwitch = false;
        }

        else if(!m_bAnotherIsPressed)
        {
            m_bAnotherIsPressed = true;
            m_bSwitch = true;
        }

        return m_bAnotherIsPressed;
    }
}