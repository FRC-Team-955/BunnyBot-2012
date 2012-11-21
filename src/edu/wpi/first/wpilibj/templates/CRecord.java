package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Joystick;

// @author Fauzi
/*
 * This class isresponsible for recording the robots 
 * movements and "replaying" them. Theoretically, we can record
 * anything the robot does and "replay" them.
 */

public class CRecord {
    
    // CONSTANTS
    private final String sRegOutput = "file:///regVal.txt";
    private final double dEndSignal = -10.0;
    
    private CFileWriter fileWriter;		
    private CFileReader fileReader;
    private CSpecialButton btRecord = new CSpecialButton();
    private CSpecialButton btReplay = new CSpecialButton();
    private CSpecialButton btChangeMode = new CSpecialButton();
    private CSpecialButton btAllowEdit = new CSpecialButton();
    private CJoyEmulator joyAuto = new CJoyEmulator();
    private Joystick joy;
    private CDrive driver;
    private CRetrieve retrieve;
    private CTimer tmRecord = new CTimer();
    private CTimer tmReplay = new CTimer();
    private boolean bRepStarted = false;
    private boolean bRecStarted = false;
    private boolean bDoneReplay = false;
    private boolean bAutoMode = false;
    private boolean bAnotherIsPressed = false; 
    private boolean bAutoEditMode = false;
    private String sPrintWhat = "Doing Nothing";
    private String sType = "Reg: ";
    private String sEditInfo = "WARNING EDIT MODE";
    private String sFileType = sRegOutput;
    
    public CRecord(Joystick joystick, CDrive drive, CRetrieve retrieval)
    {
        joy = joystick;
        driver = drive;
        retrieve = retrieval;
    }
    
    public void run()
    {
        bAnotherIsPressed = btRecord.run(joy.getRawButton(Var.btRecord), bAnotherIsPressed);
        bAnotherIsPressed = btReplay.run(joy.getRawButton(Var.btReplay), bAnotherIsPressed);
        btChangeMode.run(joy.getRawButton(Var.btChangeMode), bAnotherIsPressed);
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
            bAutoMode = !bAutoMode;
            
            if(bAutoMode)
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
            record(sFileType);

        else if(btReplay.getSwitch())
            replay(sFileType);
        
        else
        {
            sPrintWhat = "Doing Nothing";
            reset();
        }
        
        Var.drvStationPrinter.print(Var.iEditAutoMode, sEditInfo);
        Var.drvStationPrinter.print(Var.iRecordStatusLine, sType + sPrintWhat);
    }
    
    private void reset() // Resets timer and booleans so that you can record or replay again
    {  
        Var.bDrive = true;
        
        if(bRecStarted)
        {
            fileWriter.writeDouble(dEndSignal);
            fileWriter.close();
            tmRecord.reset(true);
            bRecStarted = false;
        }

        if(bRepStarted)
        {
            if(!fileReader.isClosed())
                fileReader.close();
            
            tmReplay.reset(true);
            bDoneReplay = false;
            bRepStarted = false;
        }
    }
    
    public void replay(String sFileName)
    {
        Var.bDrive = false;
         
        if(!bRepStarted)
        {
            sPrintWhat = "Replaying";
            fileReader = new CFileReader(sFileName);
            joyAuto.add(fileReader.readDouble(), fileReader.readDouble(), fileReader.readDouble(), fileReader.readBoolean());
            tmReplay.start();
            bRepStarted = true;
        }

        if(!bDoneReplay)
        {
            driver.setSpeed(joyAuto.getMtLeft(), joyAuto.getMtRight());
            retrieve.set(joyAuto.getRetrieve());

            if(tmReplay.get() >= joyAuto.getTimer())
            {
                double dTemp = fileReader.readDouble(); // Temp var to see if we're done replay

                if(dTemp < dEndSignal+1) // If true, means we're done replaying
                    bDoneReplay = true;

                else
                    joyAuto.add(dTemp, fileReader.readDouble(), fileReader.readDouble(), fileReader.readBoolean());
            }
        }

        else
        {
            sPrintWhat = "Done Replaying";
            driver.setSpeed(0, 0);
            fileReader.close();
            tmReplay.stop();
        }
    }
    
    private void record(String sFileName)
    {
        if(bAutoEditMode == false && sFileName == Var.sAutoOutput)
            sPrintWhat = "Can't Edit Autofile";
        
        else
        {
            if(!bRecStarted)
            {
                sPrintWhat = "Recording";
                fileWriter = new CFileWriter(sFileName);
                tmRecord.start();
                bRecStarted = true;
            }

            fileWriter.writeData(tmRecord.get(), driver.getMtLeftSpeed(), driver.getMtRightSpeed(), retrieve.getStatus());
        }
    }
}