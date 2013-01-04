package utilities;

import core.Drive;
import core.Release;;
import core.Retrieve;

/**
 * This class encapsulates the robot systems.
 * @author fauzi
 */
public class Robot {
    
    private static MyJoystick m_joy;
    private static Drive m_drive;
    private static Release m_release;
    private static Retrieve m_retrieve;
    
    public Robot(MyJoystick joytick)
    {
        m_joy = joytick;
        m_drive = new Drive(m_joy);
        m_release = new Release(m_joy);
        m_retrieve = new Retrieve(m_joy);
    }
    
    public void run()
    {
        m_drive.run();
        m_release.run();
        m_retrieve.run();
    }
    
    /**
     * Stops the robot and sets everything to false or zero, does not disable 
     * the ability to use it though. 
     */
    public void stopRobot()
    {
        setSpeed(0, 0);
        setRetrieve(false);
    }
    
    /**
     * Get the status of the retriever, true means active.
     * @return 
     */
    public boolean getRetrieveStat()
    {
        return m_retrieve.getStatus();
    }
    
    /**
     * Gets the value of the left gyro.
     * @return 
     */
    public double getMotorLeft()
    {
        return m_drive.getMotorLeft();
    }
    
    /**
     * Gets the value of the right gyro.
     * @return 
     */
    public double getMotorRight()
    {
        return m_drive.getMotorRight();
    }
    
    /**
     * Set the speed of the left and right motors, values allowed are -1 to 1.
     * @param dLeft
     * @param dRight 
     */
    public void setSpeed(double dLeft, double dRight)
    {
        m_drive.setSpeed(dLeft, dRight);
    }
    
    /**
     * Sets the retriever, true means active.
     * @param bRetrieveVal 
     */
    public void setRetrieve(boolean bRetrieveVal)
    {
        m_retrieve.set(bRetrieveVal);
    }
}