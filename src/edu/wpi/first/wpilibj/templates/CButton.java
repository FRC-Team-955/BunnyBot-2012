package edu.wpi.first.wpilibj.templates;

/*
 * @author Fauzi
 */

/*
 * This class is to reduce the procees to see if a button is pressed
 *  rather than do it manually which was tedious.
 */
public class CButton {
    private boolean bCurState = false;
    private boolean bLastState = false;
    private boolean bSwitch = false;
    private boolean bIsTrue = false;
    private boolean bCanInterrupt = false;
    
    public CButton(boolean bCanInterrupt)
    {
        this.bCanInterrupt = bCanInterrupt;
    }
    
    public void run(boolean button)
    {
        bLastState = bCurState;
        bCurState = button;
        
        if(bCurState == true && bLastState == false)
        {    
            if(bCanInterrupt) 
            {
                bSwitch = !bSwitch;
            }
        
            else
            {
                if(Var.bAnotherIsPressed && bIsTrue)
                {   
                    Var.bAnotherIsPressed = false;
                    bIsTrue = false;
                }

                else if(!Var.bAnotherIsPressed)
                {
                    Var.bAnotherIsPressed = true;
                    bIsTrue = true;
                } 
            }
        }
    }
    
    public boolean gotPressed()
    {
        if(bCanInterrupt)
            return bCurState == true && bLastState == false;
        
        else
            return bCurState == true && bLastState == false && Var.bAnotherIsPressed == false;
    }

    public boolean getSwitch()
    {
        if(bCanInterrupt)
            return bSwitch;
        
        else
            return bIsTrue;
    }
        
    public boolean isHeld()
    {
        if(bCanInterrupt)
        {
            if(bCurState == true && bLastState == true)
                return true;

            else
                return false;
        }
        
        else
        {
            if(bCurState == true && bLastState == true && Var.bAnotherIsPressed == false)
                return true;

            else
                return false;
        }    
    }
    
    public void set(boolean bSetTo)
    {
        bSwitch = bSetTo;
    }
}
