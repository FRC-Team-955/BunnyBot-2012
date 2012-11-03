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
 * Purpose of this class is so that it can simply the job of making solenoids
 * and having to manualy set them indivdually on and off. So instead I just made
 * a Solenoid class with functions that do it for us :D
 */
public class CSolenoid 
{

    private Solenoid solUp;
    private Solenoid solDown;
    private boolean bSetDown;
    
    public CSolenoid(int solDownChannel, int solUpChannel, boolean bDown)
    {
        bSetDown = bDown;
        solUp = new Solenoid(solUpChannel);
        solDown = new Solenoid(solDownChannel);
        solUp.set(!bSetDown);
        solDown.set(bSetDown);
    }
    
    public void turnOn()
    {
        solUp.set(bSetDown);
        solDown.set(!bSetDown);
    }
    
    public void turnOff()
    {
        solUp.set(!bSetDown);
        solDown.set(bSetDown); 
    }
    
    public void set(boolean bVal)
    {
        if(bVal)
        {
            solUp.set(true);
            solDown.set(false);
        }
        else
        {
            solUp.set(false);
            solDown.set(true);
        }
    }
    
    public boolean getUp()
    {
        return solUp.get();
    }

    public boolean getDown()
    {
        return solDown.get();
    }
}
