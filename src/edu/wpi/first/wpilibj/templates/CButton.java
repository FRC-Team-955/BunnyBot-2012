/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author fauzi
 */
public class CButton {
    
    private boolean m_bCurState = false;
    private boolean m_bLastState = false;
    private boolean m_bSwitch = false;
    
    public void run(boolean button)
    {
        m_bLastState = m_bCurState;
        m_bCurState = button;
		
        if(m_bCurState == true && m_bLastState == false)
            m_bSwitch = !m_bSwitch;
    }
    
    public boolean gotPressed()
    {
        return m_bCurState == true && m_bLastState == false;
    }

    public boolean getSwitch()
    {
        return m_bSwitch;
    }
        
    public boolean isHeld()
    {
        return m_bCurState == true && m_bLastState == true;
    }
    
    public void set(boolean bSetTo)
    {
        m_bSwitch = bSetTo;
    }
}
