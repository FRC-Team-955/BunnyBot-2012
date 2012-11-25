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
    private boolean bCurState = false;
    private boolean bLastState = false;
    private boolean bSwitch = false;
    private boolean bAnotherIsPressed = false;
    
    public boolean run(boolean button, boolean bCanWePress)
    {
        bAnotherIsPressed = bCanWePress;
        
        bLastState = bCurState;
        bCurState = button;
		
        if(bCurState == true && bLastState == false) // For setting the button switch value
        {
            if(bAnotherIsPressed && bSwitch)
            {   
                bAnotherIsPressed = false;
                bSwitch = false;
            }

            else if(!bAnotherIsPressed)
            {
                bAnotherIsPressed = true;
                bSwitch = true;
            } 
        }
        
        return bAnotherIsPressed;
    }
	
    public boolean gotPressed()
    {
        if(bCurState == true && bLastState == false)
        {
            if((bAnotherIsPressed == true && bSwitch == true) || !bAnotherIsPressed)
                return true;
            
            else 
                return false;
        }
        
        else
            return false;
    }
    
    public boolean getSwitch()
    {
        return bSwitch;
    }
	
    public boolean setOppisite()
    {
        if(bAnotherIsPressed && bSwitch)
        {   
            bAnotherIsPressed = false;
            bSwitch = false;
        }

        else if(!bAnotherIsPressed)
        {
            bAnotherIsPressed = true;
            bSwitch = true;
        }

        return bAnotherIsPressed;
    }
}