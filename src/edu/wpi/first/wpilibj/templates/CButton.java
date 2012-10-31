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

/*
 * This class is to reduce the procees to see if a button is pressed
 *  rather than do it manually which was tedious.
 */
public class CButton {
    private boolean curState = false;
    private boolean lastState = false;
    private boolean Bswitch = false;
    
    public void run(boolean button)
    {
        lastState = curState;
        curState = button;
        
        if(curState == true && lastState == false)
            Bswitch = !Bswitch;
    }
    
    public boolean gotPressed()
    {
        return curState == true && lastState == false;
    }
    
    public boolean getSwitch()
    {
        return Bswitch;
    }
    
    public boolean isHeld()
    {
        if(curState == true && lastState == true)
            return true;
        
        else
            return false;
    }
    
    public void set(boolean bSetTo)
    {
        Bswitch = bSetTo;
    }
}
