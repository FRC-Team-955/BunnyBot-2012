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
    private final String sAutoCenter = "file:///autoval.txt";
    private final String sAutoLeft = "file:///autoLeft.txt";
    private final String sAutoRight = "file:///autoRight.txt";
    
    private CSpecialButton btRecord = new CSpecialButton();
    private CSpecialButton btReplay = new CSpecialButton();
    private CButton btChangeMode = new CButton();
    private CButton btAllowEdit = new CButton();
    private boolean bAnotherIsPressed = false; 
    private boolean bAutoEditMode = false;
    private int iFileMode = 0;
    private String sPrintWhat = "Doing Nothing";
    private String sType = "Reg: ";
    private String sEditInfo = "Can NOT edit";
    private CRecorder recorder;
    private CReplayer replayer; 
    private Joystick joy;
    
    
    public CAutonomous(Joystick joystick, CDrive drive, CRetrieve retrieval)
    {
        joy = joystick;
        recorder = new CRecorder(drive, retrieval);
        replayer = new CReplayer(drive, retrieval);
    }
    
    public void run()
    {
        bAnotherIsPressed = btRecord.run(joy.getRawButton(Var.btRecord), bAnotherIsPressed);
        bAnotherIsPressed = btReplay.run(joy.getRawButton(Var.btReplay), bAnotherIsPressed);
        btChangeMode.run(joy.getRawButton(Var.btChangeFile));
        btAllowEdit.run(joy.getRawButton(Var.btAllowEdit));
		
        if(!btRecord.getSwitch() && !btReplay.getSwitch())
        {
            if(btAllowEdit.gotPressed())
            {
                bAutoEditMode = !bAutoEditMode;

                if(bAutoEditMode)
                    sEditInfo = "WARNING EDIT MODE";

                else
                    sEditInfo = "Can NOT edit";
            }
        }

        if(!btRecord.getSwitch() && !btReplay.getSwitch())
        {
            if(btChangeMode.gotPressed())
            {
                if(++iFileMode >= Var.iFileMax)
                    iFileMode = 0;

                changeFile(iFileMode); // Changes the file 
            }
        }

        if(btRecord.getSwitch())   
            sPrintWhat = record(Var.sFileType);		

        else if(btReplay.getSwitch())
            sPrintWhat = replay(Var.sFileType);
        
        else
        {
            sPrintWhat = "Doing Nothing";
            reset();
        }
        
        Var.drvStationPrinter.print(Var.iEditAutoMode, sEditInfo);
        Var.drvStationPrinter.print(Var.iRecordStatusLine, sType + sPrintWhat);
    }
    
    public String replay(String sFileName)
    {
        return replayer.replay(sFileName);
    }
    
    private String record(String sFileName)
    {
        return recorder.record(sFileName, bAutoEditMode);
    }
    
    private void reset() // Resets timer and booleans so that you can record or replay again
    {  
        Var.bDrive = true;
        replayer.reset();
        recorder.reset();
    }
    
    public void resetAutonomous()
    {
        if(btReplay.getSwitch())
            bAnotherIsPressed = btReplay.setOppisite();
        
        else if(btRecord.getSwitch())
            bAnotherIsPressed = btRecord.setOppisite();

        reset();
    }
    
    public void changeFile(int iFileType)
    {
        switch (iFileMode)
        {
            case 0:
            {
                sType = "Reg: ";
                Var.sFileType = Var.sRegOutput;
                break;
            }

            case 1:
            {
                sType = "AutoCenter: ";
                Var.sFileType = sAutoCenter; 
                break;
            } 

            case 2:
            {
                sType = "AutoLeft: ";
                Var.sFileType = sAutoLeft;
                break;
            }

            case 3:
            {
                sType = "AutoRight: ";
                Var.sFileType = sAutoRight; 
                break;
            }
                
            default:
            {
                sType = "AutoCenter: ";
                Var.sFileType = sAutoCenter; 
                break;
            }
        }
    }
}
