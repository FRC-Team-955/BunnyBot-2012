package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Joystick;
import java.io.Writer;

// @author Fauzi
/*
 * This class will hopefully be responsible for recording the robots 
 * movements and "replaying" them.
 */

public class CRecord {
    
    private CFileWriter fileWriter;		//	<----------------------
    private CFileReader fileReader;
    private CButton btRecord = new CButton(false);
    private CButton btReplay = new CButton(false);
    private CPrintDriver printToDriverSt = new CPrintDriver();
    private String sPrintWhat = "";
    private String sType = "";
    private CTimer tmRecord = new CTimer();
    private CTimer tmReplay = new CTimer();
    private boolean bRepStarted = false;
    private boolean bRecStarted = false;
    private boolean bDoneReplay = false;
    private CJoyEmulator joyAuto = new CJoyEmulator();
    private Joystick joy;
    private CDrive driver;
    private CRetrieve retrieve;
    private CRelease releaser;    
    
    public CRecord(Joystick joystick, CDrive drive, CRetrieve retrieval, CRelease release)
    {
        joy = joystick;
        driver = drive;
        retrieve = retrieval;
        releaser = release;
    }
    
    public void run()
    {
        sType = "Auto: ";
        btRecord.run(joy.getRawButton(Var.btRecord));
        btReplay.run(joy.getRawButton(Var.btReplay));

        if(btRecord.getSwitch())   
            record();

        else if(btReplay.getSwitch())
            replay();
        
        else
        {
            sPrintWhat = "Doing Nothing";
            reset();
        }
        
        printToDriverSt.print(Var.iRecordStatusLine, sType + sPrintWhat);
    }
    
    private void reset() // Resets timer and booleans so that you can record or replay again
    {  
        Var.bDrive = true;
        
        if(bRecStarted)
        {
            fileWriter.writeDouble(Var.dEndSignal);
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
    
    public void replay()
    {
        Var.bDrive = false;
                
        if(!bRepStarted)
        {
            sPrintWhat = "Replaying";
            fileReader = new CFileReader(Var.sAutoOutput);
            tmReplay.start();
            bRepStarted = true;
        }

        if(!bDoneReplay)
        {
            driver.setSpeed(joyAuto.getMtLeft(), joyAuto.getMtRight());
            retrieve.set(joyAuto.getRetrieve());
            releaser.set(joyAuto.getRelease());

            if(tmReplay.get() >= joyAuto.getTimer())
            {
                double dTemp = fileReader.readDouble(); // Temp var to see if we're done replay

                if(dTemp < Var.dEndSignal+1) // If true, means we're done replaying
                    bDoneReplay = true;

                else
                    joyAuto.add(dTemp, fileReader.readDouble(), fileReader.readDouble(), fileReader.readBoolean(), fileReader.readBoolean());
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
    
    private void record()
    {
        if(!bRecStarted)
        {
            sPrintWhat = "Recording";
            fileWriter = new CFileWriter(Var.sAutoOutput);
            tmRecord.start();
            bRecStarted = true;
        }
        
        fileWriter.writeData(tmRecord.get(), driver.getMtLeftSpeed(), driver.getMtRightSpeed(), retrieve.getStatus(), releaser.getReleaseStatus());
    }
}