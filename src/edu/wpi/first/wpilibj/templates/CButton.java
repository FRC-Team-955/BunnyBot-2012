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
    
    private boolean bCurState = false;
    private boolean bLastState = false;
    private boolean bSwitch = false;
    private boolean bIsTrue = false;
    
    public void run(boolean button)
    {
        bLastState = bCurState;
        bCurState = button;
        
        if(bCurState == true && bLastState == false)
            bSwitch = !bSwitch;
    }
    
    public boolean gotPressed()
    {
        return bCurState == true && bLastState == false;
    }

    public boolean getSwitch()
    {
        return bIsTrue;
    }
        
    public boolean isHeld()
    {
        return bCurState == true && bLastState == true;
    }
    
    public void set(boolean bSetTo)
    {
        bSwitch = bSetTo;
    }
}
