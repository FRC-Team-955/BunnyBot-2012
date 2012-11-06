/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Fauzi
 */
public class CRetrieve {
    
    private Victor m_mtRetrieve = new Victor(Var.chnVicRetrieve);
    private CButton m_btRetrieve = new CButton(true);
    private boolean m_bRetrieveStatus = false;
    private Joystick m_joy;
    
    public CRetrieve(Joystick joystick)
    {
        m_joy = joystick;
    }
    
    public void run()
    {
        m_btRetrieve.run(m_joy.getRawButton(Var.btActRetrieve));
        
        if(m_btRetrieve.gotPressed())
            m_bRetrieveStatus = !m_bRetrieveStatus;
        
        if(m_bRetrieveStatus)
            m_mtRetrieve.set(1);
        
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
            m_mtRetrieve.set(1);
        
        else
            m_mtRetrieve.set(0);
    }
}