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
    
    private Victor mtRight = new Victor(1);
    private Victor mtLeft = new Victor(2);
    private double mtRightSpeed = 0;
    private double mtLeftSpeed = 0;
    private Joystick joy;
    
    public CDrive(Joystick joystick)
    {
        joy = joystick;
    }
    
//    public void run()
//    {       
//        mtRightSpeed = joy.getX() * Math.abs(joy.getX());
//        mtLeftSpeed = joy.getY() * Math.abs(joy.getY());
//        
//        if(Var.bDrive)
//        {
//            if(Math.abs(mtRightSpeed) + Math.abs(mtLeftSpeed) > 0.1)
//            {
//                this.setSpeed(mtLeftSpeed, mtRightSpeed);
//            }
//
//            else
//            {
//                this.setSpeed(0, 0);
//            }
//        }
//    }
    
	public void run()
    {		
        double y = joy.getY() * Math.abs(joy.getY());
        double x = joy.getX() * Math.abs(joy.getX());

		mtRightSpeed = (-y+x);
		mtLeftSpeed = (y+x);
        if(Var.bDrive)
        {
            if(Math.abs(mtLeftSpeed) + Math.abs(mtRightSpeed) > 0.1)
            {
                mtRight.set(mtRightSpeed);
                mtLeft.set(mtLeftSpeed);
            }

            else
            {
                mtRight.set(0);
                mtLeft.set(0);
            }
        }
    } 
		
    public void setSpeed(double setMtLeft, double setMtRight)
    {
        mtLeft.set(setMtLeft);
        mtRight.set(setMtRight);
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
