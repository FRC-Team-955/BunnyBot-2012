/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author fauzi
 */
public class CRobot {
    
    private Joystick m_joy;
    private CDrive m_drive;
    private CRelease m_release;
    private CRetrieve m_retrieve;
    
    public CRobot(Joystick joytick)
    {
        m_joy = joytick;
        m_drive = new CDrive(m_joy);
        m_release = new CRelease(m_joy);
        m_retrieve = new CRetrieve(m_joy);
    }
    
    public void run()
    {
        m_drive.run();
        m_release.run();
        m_retrieve.run();
    }
    
    public double getMtLeft()
    {
        return m_drive.getMtLeftSpeed();
    }
    
    public double getMtRight()
    {
        return m_drive.getMtRightSpeed();
    }
    
    public boolean getReleaseStat()
    {
        return m_release.getReleaseStatus();
    }
    
    public boolean getRetrieveStat()
    {
        return m_retrieve.getStatus();
    }
    
    public void setSpeed(double dLeft, double dRight)
    {
        m_drive.setSpeed(dLeft, dRight);
    }
    
    public void setRelease(boolean bOut)
    {
        m_release.set(bOut);
    }
    
    public void setRetrieve(boolean bOn)
    {
        m_retrieve.set(bOn);
    }
}
