package autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import utilities.Robot;
import utilities.Vars;
import utilities.MyJoystick;

/**
 * Class to record/replay robots movements
 * @author fauzi
 */
public class Autonomous {
    
    // CONSTANTS
    private final String m_sRegOutput = "file:///regVal.txt";
    private final String m_sAutoCenter = "file:///autoval.txt";
    private final String m_sAutoLeft = "file:///autoLeft.txt";
    private final String m_sAutoRight = "file:///autoRight.txt";
    private final double m_dMaxRecordTime = 14.75;
     
    private String m_sFileName = m_sRegOutput;   
    private String m_sAutonmousStatus = "Doing Nothing";
    private String m_sFileTypeStat = "Reg: ";
    private String m_sEditInfoStat = "NON-EDIT MODE";
    private boolean m_bReplay = false; 
    private boolean m_bRecord = false;
    private boolean m_bAutoEditMode = false;
    private Recorder m_recorder;
    private Replayer m_replayer; 
    private MyJoystick m_joy;

    /**
     *  Class used for autonomous
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
     * Allows autonomous to run
     */
    public void run()
    {
        setButtonStat();
		
        if(!m_bRecord && !m_bReplay)
        {
            changeEditStatus(); // Changes the edit file status
            DpadSwitchFile();   // Changes files based on Dpad from controller
        }
        
        if(m_bRecord)   
            record();		

        else if(m_bReplay)
            replay();
        
        else
            resetAutonomous();
        
        Vars.fnPrintToDriverstation(Vars.iEditAutoModeLine, m_sEditInfoStat);
        Vars.fnPrintToDriverstation(Vars.iAutonomousStatLine, m_sFileTypeStat + m_sAutonmousStatus);
    }
    
    /**
     * Replays previous bot movements from specified file
     */
    public void replay()
    {
        m_replayer.replay(m_sFileName);
        
        if(m_replayer.getReplayTime() > 0.00)
            m_sAutonmousStatus = "Replaying: " + m_replayer.getReplayTime();
        
        else
            m_sAutonmousStatus = "Done Replaying";
    }
    
    /**
     * Records bots movements to specified file, if allowed
     */
    private void record()
    {
        if(canEdit() && !overTimeLimit())
        {
            m_recorder.record(m_sFileName);
            m_sAutonmousStatus = "Recording: " + m_recorder.getRecordTime();
        }
        
        else if(overTimeLimit())
            m_sAutonmousStatus = "Over Time Limit";
        
        else
            m_sAutonmousStatus = "Can't Edit File";
    }
    
    /**
     * Resets the autonomous so we can use it again properly next time
     */
    public void resetAutonomous()   
    {
        m_sAutonmousStatus = "Doing Nothing";
        m_bReplay = false;
        m_bRecord = false;
        m_replayer.reset();
        m_recorder.reset();
    }
    
    public void setFileBasedOnDriverInput()
    {
        int iFileType = Vars.chnDigInReg;
        
        if(DriverStation.getInstance().getDigitalIn(Vars.chnDigInAutoCtr))
            iFileType = Vars.chnDigInAutoCtr;
        
        else if(DriverStation.getInstance().getDigitalIn(Vars.chnDigInAutoLft))
            iFileType = Vars.chnDigInAutoLft;
        
        else if(DriverStation.getInstance().getDigitalIn(Vars.chnDigInAutoRght))
            iFileType = Vars.chnDigInAutoRght;
        
        changeFile(iFileType);
    }
    
    /**
     * Changes the edit capabilities if the button was pressed and 
     * we're not recording or replaying
     */
    private void changeEditStatus()
    {
        if(m_joy.gotPressed(Vars.btAllowEdit))
        {
            m_bAutoEditMode = !m_bAutoEditMode;

            if(m_bAutoEditMode)
                m_sEditInfoStat = "WARNING EDIT MODE";

            else
                m_sEditInfoStat = "NON-EDIT MODE";
        }
    }
    
    /**
     * Determines whether we can record to the file or not
     * @return 
     */
    private boolean canEdit()
    {
       if(!m_bAutoEditMode && !m_sFileName.equalsIgnoreCase(m_sRegOutput))
            return false;
       
       return true;
    }
    
    /** 
     * Switches the autonomous file based on Dpad status
     */ 
    private void DpadSwitchFile()
    {
        if(m_joy.getDpadUp())
            changeFile(Vars.chnDigInReg);
        
        else if(m_joy.getDpadRight())
            changeFile(Vars.chnDigInAutoRght);
        
        else if(m_joy.getDpadDown())
            changeFile(Vars.chnDigInAutoCtr);
        
        else if(m_joy.getDpadLeft())
            changeFile(Vars.chnDigInAutoLft);
    }
    
    /**
     * Changes file based on number thats passed in
     * @param iFileType 
     */
    private void changeFile(int iFileType)
    {
        switch (iFileType)
        {
            case Vars.chnDigInReg:   // 0
            {
                m_sFileTypeStat = "Reg: ";
                m_sFileName = m_sRegOutput;
                break;
            }

            case Vars.chnDigInAutoCtr:   // 1
            {
                m_sFileTypeStat = "AutoCenter: ";
                m_sFileName = m_sAutoCenter; 
                break;
            } 

            case Vars.chnDigInAutoLft:   // 2
            {
                m_sFileTypeStat = "AutoLeft: ";
                m_sFileName = m_sAutoLeft;
                break;
            }

            case Vars.chnDigInAutoRght:  // 3
            {
                m_sFileTypeStat = "AutoRight: ";
                m_sFileName = m_sAutoRight; 
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
     * Determines whether we are replaying or recording, allows only one to
     * be true at a time
     */
    private void setButtonStat()
    {
        if(m_joy.gotPressed(Vars.btReplay))
            if(!m_bRecord)
                m_bReplay = !m_bReplay;
                
        else if(m_joy.gotPressed(Vars.btRecord))
            if(!m_bReplay)
                m_bRecord = ! m_bRecord;
    }
    
    /**
     * Determines whether we're over the 15 sec time limit for
     * FRC Autonomous mode
     * @return 
     */
    private boolean overTimeLimit()
    {
        if(m_recorder.getRecordTime() >= m_dMaxRecordTime && !m_sFileName.equalsIgnoreCase(m_sRegOutput))
            return true;
        
        else
            return false;
    }
}