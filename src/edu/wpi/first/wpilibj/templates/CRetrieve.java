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
    
    Victor mtRetrieve = new Victor(Var.chnVicRetrieve);
    CButton btRetrieve = new CButton();
    boolean bRetrieveStatus = false;
    
    public void run(Joystick joy)
    {
        btRetrieve.run(joy.getRawButton(Var.btActRetrieve));
        
        if(btRetrieve.gotPressed())
            bRetrieveStatus = !bRetrieveStatus;
        
        if(bRetrieveStatus)
            mtRetrieve.set(1);
        
        else
            mtRetrieve.set(0);
    }
    
    public boolean getStatus()
    {
        return bRetrieveStatus;
    }
}
