package edu.wpi.first.wpilibj.templates;

/*
 * @author Fauzi
 */

/*
 * This class is to reduce the procees to see if a button is pressed
 *  rather than do it manually which was tedious.
 */
public class CPressButton {
    private boolean bCurState = false;
    private boolean bLastState = false;
    private boolean bAnotherIsPressed = false;
    
    public void run(boolean button, boolean bCanWePress)
    {
        bAnotherIsPressed = bCanWePress;
        
        bLastState = bCurState;
        bCurState = button;
    }
    
    public boolean gotPressed()
    {
		if(bCurState == true && bLastState == false)
		{
			if(bAnotherIsPressed == false)
				return true;
			
			else
				return false;
		}
		
		else
			return false;
			
        //return bCurState == true && bLastState == false && bAnotherIsPressed == false;
    }
	
    public boolean isHeld()
    {
        if(bCurState == true && bLastState == true && bAnotherIsPressed == false)
            return true;

        else
            return false;
    }
}