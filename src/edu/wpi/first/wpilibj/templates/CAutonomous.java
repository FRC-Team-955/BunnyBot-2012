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
    private final String sRegOutput = "file:///regVal.txt";
    
    private CSpecialButton btRecord = new CSpecialButton();
    private CSpecialButton btReplay = new CSpecialButton();
    private CSpecialButton btChangeMode = new CSpecialButton();
    private CSpecialButton btAllowEdit = new CSpecialButton();
    private boolean bAutoFileMode = false;
    private boolean bAnotherIsPressed = false; 
    private boolean bAutoEditMode = false;
    private String sPrintWhat = "Doing Nothing";
    private String sType = "Reg: ";
    private String sEditInfo = "Can NOT edit";
    private String sFileType = sRegOutput;
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
        btChangeMode.run(joy.getRawButton(Var.btChangeFile), bAnotherIsPressed);
        btAllowEdit.run(joy.getRawButton(Var.btAllowEdit), bAnotherIsPressed);
		
        if(btAllowEdit.gotPressed())
        {
            bAutoEditMode = !bAutoEditMode;
			
            if(bAutoEditMode)
                sEditInfo = "WARNING EDIT MODE";
            
            else
                sEditInfo = "Can NOT edit";
        }
        
        if(btChangeMode.gotPressed())
        {
            bAutoFileMode = !bAutoFileMode;
            
            if(bAutoFileMode)
            {
                sType = "Auto: ";
                sFileType = Var.sAutoOutput;
            }
            
            else
            {
                sType = "Reg: ";
                sFileType = sRegOutput;
            }
        }

        if(btRecord.getSwitch())   
            sPrintWhat = record(sFileType);		

        else if(btReplay.getSwitch())
            sPrintWhat = replay(sFileType);
        
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
}
