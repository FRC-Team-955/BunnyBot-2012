package edu.wpi.first.wpilibj.templates;

/*
 * @author Fauzi
 */

/*
 * This class is to reduce the procees to see if a button is pressed
 *  rather than do it manually which was tedious.
 */
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
	
    public boolean getSwitch()
    {
        return bSwitch;
    }
}