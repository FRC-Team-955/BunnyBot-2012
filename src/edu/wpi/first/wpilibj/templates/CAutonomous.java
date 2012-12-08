/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

// @author fauzi      
// Records robots movements and replays it later, used for autonomous
// Working on having a function to modify the autonomous
public class CAutonomous {
    
    // CONSTANTS
    private final String m_sAutoCenter = "file:///autoval.txt";
    private final String m_sAutoLeft = "file:///autoLeft.txt";
    private final String m_sAutoRight = "file:///autoRight.txt";
    private final String m_sRegOutput = "file:///regVal.txt";
    private final int m_iFileMax = 4;
        
    private String m_sAutonmousStatus = "Doing Nothing";
    private String m_sFileTypeStat = "Reg: ";
    private String m_sEditInfoStat = "Can NOT edit";
    private String m_sFileEditStat = "File Edit: ";
    private String m_sFileName = m_sRegOutput; 
    private CSpecialButton m_btRecord = new CSpecialButton();
    private CSpecialButton m_btReplay = new CSpecialButton();
    private CButton m_btChangeMode = new CButton();
    private CButton m_btAllowEdit = new CButton();
    private CButton m_btPrintFile = new CButton();
    private CButton m_btModifyAuto = new CButton();
    private CEditAuto m_editAuto = new CEditAuto();    
    private CRecorder m_recorder;
    private CReplayer m_replayer; 
    private Joystick m_joy;
    private boolean m_bAnotherIsPressed = false; 
    private boolean m_bAutoEditMode = false;
    private int m_iFileMode = 0;
    private double m_dMin = 0;
    private double m_dMax = 0;
    private double m_dMtLeft = 0;
    private double m_dMtRight = 0;

    public CAutonomous(Joystick joystick, CRobot bot)
    {
        m_joy = joystick;
        m_recorder = new CRecorder(bot);
        m_replayer = new CReplayer(bot);
    }
    
    public void run()
    {
        m_bAnotherIsPressed = m_btRecord.run(m_joy.getRawButton(Var.btRecord), m_bAnotherIsPressed);
        m_bAnotherIsPressed = m_btReplay.run(m_joy.getRawButton(Var.btReplay), m_bAnotherIsPressed);
        m_btChangeMode.run(m_joy.getRawButton(Var.btChangeFile));
        m_btAllowEdit.run(m_joy.getRawButton(Var.btAllowEdit));
        m_btPrintFile.run(m_joy.getRawButton(Var.btPrintFile));
        m_btModifyAuto.run(m_joy.getRawButton(Var.btModifyAuto));
		
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
            
            if(m_btChangeMode.gotPressed())
            {
                if(++m_iFileMode >= m_iFileMax)
                    m_iFileMode = 0;

                changeFile(m_iFileMode); // Changes the file 
            }
            
            if(canEdit())
            {
                m_dMin = DriverStation.getInstance().getAnalogIn(Var.chnAngMin);
                m_dMax = DriverStation.getInstance().getAnalogIn(Var.chnAngMax);
                m_dMtLeft = DriverStation.getInstance().getAnalogIn(Var.chnAngMtLeft);
                m_dMtRight = DriverStation.getInstance().getAnalogIn(Var.chnAngMtRight);
                
                if(m_btPrintFile.gotPressed())
                    m_sFileEditStat += m_editAuto.printFile(m_sFileName);
                
                else if(m_btModifyAuto.gotPressed())
                    m_sFileEditStat += m_editAuto.modify(m_dMin, m_dMax, m_dMtLeft, m_dMtRight);
            }
        }
        
        if(m_btRecord.getSwitch())   
            m_sAutonmousStatus = record();		

        else if(m_btReplay.getSwitch())
            m_sAutonmousStatus = replay();
        
        else
        {
            m_sAutonmousStatus = "Doing Nothing";
            m_sFileEditStat = "File Edit: Nothing";
            reset();
        }
        
        Var.drvStationPrinter.print(Var.iEditAutoMode, m_sEditInfoStat);
        Var.drvStationPrinter.print(Var.iEditFileStat, m_sFileEditStat);
        Var.drvStationPrinter.print(Var.iRecordStatusLine, m_sFileTypeStat + m_sAutonmousStatus);
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
    
    private boolean canEdit()
    {
       if(m_bAutoEditMode == false && !m_sFileName.equalsIgnoreCase(m_sRegOutput))
            return false;
       
       return true;
    }
    
    public void changeFile(int iFileType)
    {
        switch (iFileType)
        {
            case Var.chnDigInReg:   // 0
            {
                m_sFileTypeStat = "Reg: ";
                m_sFileName = m_sRegOutput;
                break;
            }

            case Var.chnDigInAutoCtr:   // 1
            {
                m_sFileTypeStat = "AutoCenter: ";
                m_sFileName = m_sAutoCenter; 
                break;
            } 

            case Var.chnDigInAutoLft:   // 2
            {
                m_sFileTypeStat = "AutoLeft: ";
                m_sFileName = m_sAutoLeft;
                break;
            }

            case Var.chnDigInAutoRght:  // 3
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
