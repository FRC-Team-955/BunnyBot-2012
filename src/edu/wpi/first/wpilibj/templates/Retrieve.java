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
public class Retrieve {
    
    private Victor m_mtRetrieve = new Victor(Var.chnVicRetrieve);
    private Button m_btRetrieve = new Button();
    private boolean m_bRetrieveStatus = false;
    private double m_dRetrieveSpeed = -1;
    private Joystick m_joy;
    
    public Retrieve(Joystick joystick)
    {
        m_joy = joystick;
    }
    
    public void run()
    {
        //m_dRetrieveSpeed = DriverStation.getInstance().getAnalogIn(Var.chnAnlgRetrieveSpeed);
        m_btRetrieve.run(m_joy.getRawButton(Var.btActRetrieve));
        
        if(m_btRetrieve.gotPressed())
            m_bRetrieveStatus = !m_bRetrieveStatus;
        
        if(m_bRetrieveStatus)
            m_mtRetrieve.set(m_dRetrieveSpeed);
        
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
            m_mtRetrieve.set(m_dRetrieveSpeed);
        
        else
            m_mtRetrieve.set(0);
    }
}
