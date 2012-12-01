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
public class CAutonomous {
    
    // CONSTANTS
    private final String m_sAutoCenter = "file:///autoval.txt";
    private final String m_sAutoLeft = "file:///autoLeft.txt";
    private final String m_sAutoRight = "file:///autoRight.txt";
    
    private CSpecialButton m_btRecord = new CSpecialButton();
    private CSpecialButton m_btReplay = new CSpecialButton();
    private CButton m_btChangeMode = new CButton();
    private CButton m_btAllowEdit = new CButton();
    private boolean m_bAnotherIsPressed = false; 
    private boolean m_bAutoEditMode = false;
    private int m_iFileMode = 0;
    private String m_sAutonmousStatus = "Doing Nothing";
    private String m_sFileType = "Reg: ";
    private String m_sEditInfo = "Can NOT edit";
    private CRecorder m_recorder;
    private CReplayer m_replayer; 
    private Joystick m_joy;
    
    
    public CAutonomous(Joystick joystick, CDrive drive, CRetrieve retrieval)
    {
        m_joy = joystick;
        m_recorder = new CRecorder(drive, retrieval);
        m_replayer = new CReplayer(drive, retrieval);
    }
    
    public void run()
    {
        m_bAnotherIsPressed = m_btRecord.run(m_joy.getRawButton(Var.btRecord), m_bAnotherIsPressed);
        m_bAnotherIsPressed = m_btReplay.run(m_joy.getRawButton(Var.btReplay), m_bAnotherIsPressed);
        m_btChangeMode.run(m_joy.getRawButton(Var.btChangeFile));
        m_btAllowEdit.run(m_joy.getRawButton(Var.btAllowEdit));
		
        if(!m_btRecord.getSwitch() && !m_btReplay.getSwitch())
        {
            if(m_btAllowEdit.gotPressed())
            {
                m_bAutoEditMode = !m_bAutoEditMode;

                if(m_bAutoEditMode)
                    m_sEditInfo = "WARNING EDIT MODE";

                else
                    m_sEditInfo = "Can NOT edit";
            }
            
            if(m_btChangeMode.gotPressed())
            {
                if(++m_iFileMode >= Var.iFileMax)
                    m_iFileMode = 0;

                changeFile(m_iFileMode); // Changes the file 
            }
        }
        
        if(m_btRecord.getSwitch())   
            m_sAutonmousStatus = record(Var.sFileType);		

        else if(m_btReplay.getSwitch())
            m_sAutonmousStatus = replay(Var.sFileType);
        
        else
        {
            m_sAutonmousStatus = "Doing Nothing";
            reset();
        }
        
        Var.drvStationPrinter.print(Var.iEditAutoMode, m_sEditInfo);
        Var.drvStationPrinter.print(Var.iRecordStatusLine, m_sFileType + m_sAutonmousStatus);
    }
    
    public String replay(String sFileName)
    {
        return m_replayer.replay(sFileName);
    }
    
    private String record(String sFileName)
    {
        if(m_bAutoEditMode == false && sFileName != Var.sRegOutput)
            return "Can't Edit Autofile";
        
        else
            return m_recorder.record(sFileName, m_bAutoEditMode);
    }
    
    private void reset() // Resets timer and booleans so that you can record or replay again
    {  
        Var.bDrive = true;
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
    
    public void changeFile(int iFileType)
    {
        switch (iFileType)
        {
            case Var.chnDigInReg:   // 0
            {
                m_sFileType = "Reg: ";
                Var.sFileType = Var.sRegOutput;
                break;
            }

            case Var.chnDigInAutoCtr:   // 1
            {
                m_sFileType = "AutoCenter: ";
                Var.sFileType = m_sAutoCenter; 
                break;
            } 

            case Var.chnDigInAutoLft:   // 2
            {
                m_sFileType = "AutoLeft: ";
                Var.sFileType = m_sAutoLeft;
                break;
            }

            case Var.chnDigInAutoRght:  // 3
            {
                m_sFileType = "AutoRight: ";
                Var.sFileType = m_sAutoRight; 
                break;
            }
                
            default:
            {
                m_sFileType = "AutoCenter: ";
                Var.sFileType = m_sAutoCenter; 
                break;
            }
        }
    }
}
