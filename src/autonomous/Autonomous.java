/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autonomous;

import utilities.Robot;
import utilities.Vars;;
import utilities.SpecialButton;
import utilities.Button;
import edu.wpi.first.wpilibj.Joystick;

// @author fauzi      
// Records robots movements and replays it later, used for autonomous
public class Autonomous {
    
    // CONSTANTS
    private final String m_sAutoCenter = "file:///autoval.txt";
    private final String m_sAutoLeft = "file:///autoLeft.txt";
    private final String m_sAutoRight = "file:///autoRight.txt";
    private final int m_iFileMax = 4;
        
    private String m_sAutonmousStatus = "Doing Nothing";
    private String m_sFileTypeStat = "Reg: ";
    private String m_sEditInfoStat = "Can NOT edit";
    private String m_sFileName = Vars.sRegOutput; 
    private SpecialButton m_btRecord = new SpecialButton();
    private SpecialButton m_btReplay = new SpecialButton();
    private Button m_btChangeFile = new Button();
    private Button m_btAllowEdit = new Button();   
    private Recorder m_recorder;
    private Replayer m_replayer; 
    private Joystick m_joy;
    private boolean m_bAnotherIsPressed = false; 
    private boolean m_bAutoEditMode = false;
    private int m_iFileMode = 0;

    public Autonomous(Joystick joystick, Robot bot)
    {
        m_joy = joystick;
        m_recorder = new Recorder(bot);
        m_replayer = new Replayer(bot);
    }
    
    public void run()
    {
        m_bAnotherIsPressed = m_btRecord.run(m_joy.getRawButton(Vars.btRecord), m_bAnotherIsPressed);
        m_bAnotherIsPressed = m_btReplay.run(m_joy.getRawButton(Vars.btReplay), m_bAnotherIsPressed);
        m_btChangeFile.run(m_joy.getRawButton(Vars.btChangeFile));
        m_btAllowEdit.run(m_joy.getRawButton(Vars.btAllowEdit));
		
        if(!m_btRecord.getSwitch() && !m_btReplay.getSwitch())
        {
            if(m_btAllowEdit.gotPressed())
            {
                m_bAutoEditMode = !m_bAutoEditMode;

                if(m_bAutoEditMode)
                    m_sEditInfoStat = "WARNING EDIT MODE";

                else
                    m_sEditInfoStat = "Can NOT edit";
            }
            
            if(m_btChangeFile.gotPressed())
            {
                if(++m_iFileMode >= m_iFileMax)
                    m_iFileMode = 0;

                changeFile(m_iFileMode); // Changes the file 
            }
        }
        
        if(m_btRecord.getSwitch())   
            m_sAutonmousStatus = record();		

        else if(m_btReplay.getSwitch())
            m_sAutonmousStatus = replay();
        
        else
        {
            m_sAutonmousStatus = "Doing Nothing";
            reset();
        }
        
        Vars.drvStationPrinter.print(Vars.iEditAutoMode, m_sEditInfoStat);
        Vars.drvStationPrinter.print(Vars.iRecordStatusLine, m_sFileTypeStat + m_sAutonmousStatus);
    }
    
    public String replay()
    {
        return m_replayer.replay(m_sFileName);
    }
    
    private String record()
    {
        if(canEdit())
            return m_recorder.record(m_sFileName);
        
        else
            return "Can't Edit Autofile";
    }
    
    private void reset() // Resets timer and booleans so that you can record or replay again
    {  
        Vars.bDrive = true;
        m_replayer.reset();
        m_recorder.reset();
    }
    
    public void resetAutonomous()
    {
        if(m_btReplay.getSwitch())
            m_bAnotherIsPressed = m_btReplay.setOppisite();
        
        else if(m_btRecord.getSwitch())
            m_bAnotherIsPressed = m_btRecord.setOppisite();

        reset();
    }
    
    private boolean canEdit()
    {
       if(!m_bAutoEditMode && !m_sFileName.equalsIgnoreCase(Vars.sRegOutput))
            return false;
       
       return true;
    }
    
    public void changeFile(int iFileType)
    {
        switch (iFileType)
        {
            case Vars.chnDigInReg:   // 0
            {
                m_sFileTypeStat = "Reg: ";
                m_sFileName = Vars.sRegOutput;
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
}