/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author fauzi
 */
public class CDrive {
    
    Victor mtRight = new Victor(1);
    Victor mtLeft = new Victor(2);
    double x = 0;
    double y = 0;
    double mtRightSpeed = 0;
    double mtLeftSpeed = 0;
    
    public void run(Joystick joy)
    {
        x = joy.getX() * Math.abs(joy.getX());
        y = joy.getY() * Math.abs(joy.getY());
        
        mtRightSpeed = -y+x;
        mtLeftSpeed = y+x;
        
        if(Math.abs(y) + Math.abs(x) > 0.1)
        {
            this.setSpeed(mtRightSpeed, mtLeftSpeed);
        }

        else
        {
            this.setSpeed(0, 0);
        }
    }
    
    public void setSpeed(double setMtRight, double setMtLeft)
    {
        mtRight.set(setMtRight);
        mtLeft.set(setMtRight);
    }
    
    public double getMtRightSpeed()
    {
        return mtRightSpeed;
    }
    
    public double getMtLeftSpeed()
    {
        return mtLeftSpeed;
    }
}
