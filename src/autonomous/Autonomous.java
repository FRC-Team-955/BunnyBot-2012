package autonomous;

import utilities.Robot;
import utilities.Vars;
import utilities.MyJoystick;

/**
 * Class to record/replay robots movements.
 * @author fauzi
 */
public class Autonomous {
    
    // CONSTANTS
    private static final String m_sRegOutput = "file:///regVal.txt";
    private static final String m_sAutoCenter = "file:///autoval.txt";
    private static final String m_sAutoLeft = "file:///autoLeft.txt";
    private static final String m_sAutoRight = "file:///autoRight.txt";
    private static final double m_dMaxAutoTime = 14.75;
     
    private String m_sFileName = m_sRegOutput;   
    private String m_sAutonmousStatus = "Doing Nothing";
    private String m_sFileTypeStat = "Reg: ";
    private String m_sEditInfoStat = "NON-EDIT MODE";
    private Recorder m_recorder;
    private Replayer m_replayer; 
    private MyJoystick m_joy;

    /**
     * Class used for autonomous.
     * @param joystick
     * @param bot 
     */
    public Autonomous(MyJoystick joystick, Robot bot)
    {
        m_joy = joystick;
        m_recorder = new Recorder(bot);
        m_replayer = new Replayer(bot);
    }
    
    /**
     * Allows autonomous to run.
     */
    public void run()
    {
        setButtonStat();
        
        if(m_joy.getSwitch(Vars.btRecord))   
            record();		

        else if(m_joy.getSwitch(Vars.btReplay))
            replay();
        
        else
            resetAutonomous();
        
        Vars.fnPrintToDriverstation(Vars.prEditAutoModeLine, m_sEditInfoStat);
        Vars.fnPrintToDriverstation(Vars.prAutonomousStatLine, m_sFileTypeStat + m_sAutonmousStatus);
    }
    
    /**
     * Replays previous bot movements from specified file.
     */
    public void replay()
    {
        m_replayer.replay(m_sFileName);
        
        if(m_replayer.getReplayTime() > 0.00 && !overTimeLimit(m_replayer.getReplayTime()))
            m_sAutonmousStatus = "Replaying: " + m_replayer.getReplayTime();
        
        else if(overTimeLimit(m_replayer.getReplayTime()))
        {
            m_replayer.stop();
            m_sAutonmousStatus = "Over Replay Limit";
        }
        
        else
            m_sAutonmousStatus = "Finished Replaying";
    }
    
    /**
     * Records bots movements to specified file, if allowed.
     */
    private void record()
    {
        m_recorder.record(m_sFileName);
        
        if(canEdit() && !overTimeLimit(m_recorder.getRecordTime()))
            m_sAutonmousStatus = "Recording: " + m_recorder.getRecordTime();
        
        else if(overTimeLimit(m_recorder.getRecordTime()))
        {
            m_recorder.stop();
            m_sAutonmousStatus = "Over Record Limit";
        }
        
        else
            m_sAutonmousStatus = "Can't Edit File";
    }
    
    /**
     * Resets the autonomous so we can use it again properly next time.
     */
    public void resetAutonomous()   
    {
        m_sAutonmousStatus = "Doing Nothing";
        m_joy.setSwitch(Vars.btReplay, false);
        m_joy.setSwitch(Vars.btRecord, false);
        m_replayer.reset();
        m_recorder.reset();
    }
    
    /**
     * Chooses the autonomous from the driverstation, for use right before 
     * the actual autonomous begins.
     */
    public void setFileBasedOnDriverInput()
    {
        int iFileType = Vars.stDigInReg;
        
        if(Vars.fnDriverGetDigitalIn(Vars.stDigInAutoCtr))
            iFileType = Vars.stDigInAutoCtr;
        
        else if(Vars.fnDriverGetDigitalIn(Vars.stDigInAutoLft))
            iFileType = Vars.stDigInAutoLft;
        
        else if(Vars.fnDriverGetDigitalIn(Vars.stDigInAutoRght))
            iFileType = Vars.stDigInAutoRght;
        
        changeFile(iFileType);
    }
    
    /**
     * Sets up all the buttons, determining what got pressed ect...
     */
    private void setButtonStat()
    {
        if(m_joy.gotPressed(Vars.btReplay))
            if(!m_joy.getSwitch(Vars.btRecord))
                m_joy.flipSwitch(Vars.btReplay);
                
        else if(m_joy.gotPressed(Vars.btRecord))
            if(!m_joy.getSwitch(Vars.btReplay))
                m_joy.flipSwitch(Vars.btRecord);
                        
        if(!m_joy.getSwitch(Vars.btReplay) && !m_joy.getSwitch(Vars.btRecord))
        {
            // Changes the ability for the user to edit the autonomous
            if(m_joy.gotPressed(Vars.btAllowEdit))
            {
                m_joy.flipSwitch(Vars.btAllowEdit);

                if(m_joy.getSwitch(Vars.btAllowEdit))
                    m_sEditInfoStat = "WARNING EDIT MODE";

                else
                    m_sEditInfoStat = "NON-EDIT MODE";
            }
            
            // Changes the autonomous files
            if(m_joy.getDpadUp())
                changeFile(Vars.stDigInReg);
        
            else if(m_joy.getDpadRight())
                changeFile(Vars.stDigInAutoRght);

            else if(m_joy.getDpadDown())
                changeFile(Vars.stDigInAutoCtr);

            else if(m_joy.getDpadLeft())
                changeFile(Vars.stDigInAutoLft);
        }
    }
    
    /**
     * Changes file based on number thats passed in, only 1 to 4 are valid.
     * @param iFileType 
     */
    private void changeFile(int iFileType)
    {
        switch (iFileType)
        {
            case Vars.stDigInAutoCtr:   // 1
            {
                m_sFileTypeStat = "AutoCenter: ";
                m_sFileName = m_sAutoCenter; 
                break;
            } 

            case Vars.stDigInAutoLft:   // 2
            {
                m_sFileTypeStat = "AutoLeft: ";
                m_sFileName = m_sAutoLeft;
                break;
            }

            case Vars.stDigInAutoRght:  // 3
            {
                m_sFileTypeStat = "AutoRight: ";
                m_sFileName = m_sAutoRight; 
                break;
            }
                
            case Vars.stDigInReg:   // 4
            {
                m_sFileTypeStat = "Reg: ";
                m_sFileName = m_sRegOutput;
                break;
            }
                
            default:
            {
                m_sFileTypeStat = "AutoCenter: ";
                m_sFileName = m_sAutoCenter; 
                break;
            }
        }
    }
    
    /**
     * Determines whether we can record to the file or not.
     * @return 
     */
    private boolean canEdit()
    {
       if(!m_joy.getSwitch(Vars.btAllowEdit) && !m_sFileName.equalsIgnoreCase(m_sRegOutput))
            return false;
       
       return true;
    }
    
    /**
     * Determines whether we're over the 15 sec time limit for
     * FRC Autonomous mode.
     * @return 
     */
    private boolean overTimeLimit(double dTime)
    {
        if(dTime >= m_dMaxAutoTime && !m_sFileName.equalsIgnoreCase(m_sRegOutput))
            return true;
        
        return false;
    }
}