// @author Fauzi
/*
 * This class will hopefully be responsible for recording the robots 
 * movements and "replaying" them.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;

public class CRecord {

    private Timer trRecord = new Timer();
    private Timer trReplay = new Timer();
    private CJoystickMimic joyEmu = new CJoystickMimic();
    private CJoystickMimic joyEmuAuto = new CJoystickMimic();
    private CButton btRecord = new CButton();
    private CButton btReplay = new CButton();
    private CButton btClear = new CButton();
    private boolean bRecStart = false;
    private boolean bRepStart = false;
    private boolean bRepDone = false;
    private String sPrintWhat = "";
    private CPrintDriver printToDriverSt = new CPrintDriver();
   
    public void run(Joystick joy, CDrive driver, CRetrieve retrieval)
    {
        btRecord.run(joy.getRawButton(Var.btRecord));
        btReplay.run(joy.getRawButton(Var.btReplay));
        btClear.run(joy.getRawButton(Var.btClearList));
        
        if(btClear.gotPressed())
                clearAll();
        
        if(btRecord.getSwitch())    // Records the joystics X's and Y's into a linked list
        {            
            if(!bRecStart)
            {
                trRecord.start();
                bRecStart = true;
            }
            
            if(trRecord.get() < Var.dRcrdLimit)
            {
                sPrintWhat = "Recording";

                try
                {
                    joyEmu.add(trRecord.get(), driver.getMtLeftSpeed(), driver.getMtRightSpeed(), retrieval.getStatus());
                }

                catch(OutOfMemoryError E)
                {
                    sPrintWhat = "Out of Memory";
                }
            }
            
            else
            {
                sPrintWhat = "Time Limit Reached";
                Var.bDrive = false;                
            }
        }
		
        else if(btReplay.getSwitch())   // Inputs the values into the drive class, etc...
        {               
            Var.bDrive = false;
            
            if(joyEmu.isEmpty() == false)
            {
                if(!bRepStart)
                {
                    trReplay.start();
                    bRepStart = true;
                }

                if(trReplay.get() <= trRecord.get() && bRepDone == false)
                {
                    sPrintWhat = "Replaying";
                    driver.setSpeed(joyEmu.getMtLeft(-1), joyEmu.getMtRight(-1));

                    if(trReplay.get() > joyEmu.getTmr(-1))
                        joyEmu.getNext();
                }

                if(joyEmu.getIter() == joyEmu.size()-1) 
                {
                    sPrintWhat = "Done Replaying";
                    trReplay.stop();
                    driver.setSpeed(0,0);
                    bRepDone = true;
                }
            }
            
            else
            {
                sPrintWhat = "Nothing Recorded";
            }
        }
        
        else
        {
            sPrintWhat = "Doing Nothing";
            Var.bDrive = true;
            bRecStart = false;
            bRepStart = false;
            bRepDone = false;
            trReplay.stop();
            trReplay.reset();
            trRecord.stop();
            joyEmu.reset();
        }
        
        printToDriverSt.print(Var.iRecordStatusLine, sPrintWhat);
    }
    
    private void clearAll() // To clear the memory so you can record something else
    {
        trRecord.stop();
        trRecord.reset();
        trReplay.stop();
        trReplay.reset();
        joyEmu.deleteAll();
        bRecStart = false;
        bRepStart = false;
        bRepDone = false;
    }
    
    public void replayAuto(CDrive driver, CRetrieve retrieval)
    {
        
        if(!bRepStart)
        {
            trReplay.start();
            bRepStart = true;
        }

        if(trReplay.get() <= joyEmu.getReplayLimit() && bRepDone == false)
        {
            sPrintWhat = "Replaying";
            driver.setSpeed(joyEmu.getMtLeft(-1), joyEmu.getMtRight(-1));

            if(trReplay.get() > joyEmu.getTmr(-1))
                joyEmu.getNext();
        }

        if(joyEmu.getIter() == joyEmu.size()-1) 
        {
            sPrintWhat = "Done Replaying";
            trReplay.stop();
            driver.setSpeed(0,0);
            bRepDone = true;
        }
    }
}