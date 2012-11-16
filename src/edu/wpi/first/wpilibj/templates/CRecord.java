package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Joystick;
import java.io.Writer;

// @author Fauzi
/*
 * This class will hopefully be responsible for recording the robots 
 * movements and "replaying" them.
 */
public class CRecord {
    
    private CFileWriter fileWriter = new CFileWriter(Var.sOutput);		//	<----------------------
    private CFileReader fileReader = new CFileReader(Var.sOutput);
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
    private int iWriteOpen = 0;
    private int iWriteClose = 0;
    private int iReaderOpen = 0;
    private int iReaderClose = 0;
    
    
    public CRecord(Joystick joystick, CDrive drive, CRetrieve retrieval, CRelease release)
    {
        joy = joystick;
        driver = drive;
        retrieve = retrieval;
        releaser = release;
        fileReader.close();
        fileWriter.close();
    }
    
    public void run()
    {
        sType = "Auto: ";
        btRecord.run(joy.getRawButton(Var.btRecord));
        btReplay.run(joy.getRawButton(Var.btReplay));

        if(btRecord.getSwitch() || btReplay.getSwitch())
        {
            if(btRecord.getSwitch())   
                record();

            else if(btReplay.getSwitch())
                replay();
        }
        
        else
        {
            sPrintWhat = "Doing Nothing";
            reset();
        }
        
        printToDriverSt.print(Var.iRecordStatusLine, sType + sPrintWhat);
        System.out.println("Writer open/close: " + iWriteOpen + ", " + iWriteClose);    // DEBUGGER
        System.out.println("Reader open/close: " + iReaderOpen + ", " + iReaderClose);
    }
    
    private void reset() // Resets timer and boolean so that you can record or replay again
    {
        if(bRecStarted)
        {
            fileWriter.writeDouble(Var.dCompareEnd);
            fileWriter.close();
            iWriteClose++;      // DEBUGGER
        }
        
        if(bRepStarted)
        {
            fileReader.close();
            iReaderClose++;     // DEBUGGER
        }
        
        Var.bDrive = true;
        bRepStarted = false;
        bRecStarted = false;
        tmReplay.reset(true);
        tmRecord.reset(true);
        bDoneReplay = false;
    }
    
    private void replay()
    {
        Var.bDrive = false;
        
        if(!bRepStarted)
        {
            fileReader.open();
            iReaderOpen++;      // DEBUGGER
            tmReplay.start();
            joyAuto.add(fileReader.readDouble(), fileReader.readDouble(), fileReader.readDouble(), fileReader.readBoolean(), fileReader.readBoolean());
            bRepStarted = true;
        }

        if(!bDoneReplay)
        {
            sPrintWhat = "Replaying";
            driver.setSpeed(joyAuto.getMtLeft(), joyAuto.getMtRight());
            retrieve.set(joyAuto.getRetrieve());
            releaser.set(joyAuto.getRelease());

            if(tmReplay.get() >= joyAuto.getTimer())
            {
                double dTemp = fileReader.readDouble(); // Temp var to see if we're done replay

                if(dTemp < Var.dCompareEnd+1)    // done
                    bDoneReplay = true;

                else
                    joyAuto.add(dTemp, fileReader.readDouble(), fileReader.readDouble(), fileReader.readBoolean(), fileReader.readBoolean());
            }
        }
		
        else
        {
            sPrintWhat = "Done Replaying";
            driver.setSpeed(0, 0);
        }
    }
    
    private void record()
    {
        if(!bRecStarted)
        {
            fileWriter.open();
            iWriteOpen++;      //DEBUGGER	
            tmRecord.start();
            bRecStarted = true;
        }
        
        sPrintWhat = "Recording";
        fileWriter.writeData(tmRecord.get(), driver.getMtLeftSpeed(), driver.getMtRightSpeed(), retrieve.getStatus(), releaser.getReleaseStatus());
    }
}
