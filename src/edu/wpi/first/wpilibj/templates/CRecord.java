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
    private CFileReader fileReader;
    private CButton btRecord = new CButton(false);
    private CButton btReplay = new CButton(false);
    private CButton btClear = new CButton(false);	//
    private CButton btAllowEdit = new CButton(false);
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
        btClear.run(joy.getRawButton(Var.btClearList));
        btAllowEdit.run(joy.getRawButton(Var.btAllowEdit));

        if(btRecord.getSwitch() || btReplay.getSwitch() || btClear.getSwitch())
        {
            if(btRecord.getSwitch())   
                record();

            else if(btReplay.getSwitch())
                replay();

            else if(btClear.gotPressed())
                fileWriter.reset();
        }
        
        else
        {
            sPrintWhat = "Doing Nothing";
            reset();
        }
        
        printToDriverSt.print(Var.iRecordStatusLine, sType + sPrintWhat);
    }
    
    private void reset() // Resets timer and boolean so that you can record or replay again
    {
        Var.bDrive = true;
        bRepStarted = false;
        bRecStarted = false;
        tmReplay.reset(true);
        tmRecord.reset(true);
		bDoneReplay = false;
		fileReader = null;
    }
    
    private void replay()
    {
        Var.bDrive = false;
        
        if(!bRepStarted)
        {
            tmReplay.start();
            //fileWriter.close();
            fileReader = new CFileReader(Var.sOutput);
            joyAuto.add(fileReader.readDouble(), fileReader.readDouble(), fileReader.readDouble(), fileReader.readBoolean(), fileReader.readBoolean());
            bRepStarted = true;
        }

		if(!bDoneReplay)
		{
			if(tmReplay.get() <= joyAuto.m_dReplayLength)
			{
				sPrintWhat = "Replaying";
				driver.setSpeed(joyAuto.getMtLeft(), joyAuto.getMtRight());
				retrieve.set(joyAuto.getRetrieve());
				releaser.set(joyAuto.getRelease());

				if(tmReplay.get() >= joyAuto.getTimer())
				{
					double x = fileReader.readDouble();
					if( x == -1) ;// done
					else
						joyAuto.add(x, fileReader.readDouble(), fileReader.readDouble(), fileReader.readBoolean(), fileReader.readBoolean());
				}
					joyAuto.add(fileReader.readDouble(), fileReader.readDouble(), fileReader.readDouble(), fileReader.readBoolean(), fileReader.readBoolean());
			}
			
			else
				bDoneReplay = true;
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
			if(fileWriter.isClosed())
                fileWriter.open();
			
            tmRecord.start();
            fileWriter.reset();
            bRecStarted = true;
            
            
        }
        
        if(tmRecord.get() <= Var.dRcrdLimit)	// TODO: Set this as Var variable or something, not magic number
        {
            sPrintWhat = "Recording";
            joyAuto.m_dReplayLength = tmRecord.get();
            fileWriter.writeData(tmRecord.get(), driver.getMtLeftSpeed(), driver.getMtRightSpeed(), retrieve.getStatus(), releaser.getReleaseStatus());
	}

        else
        {
            sPrintWhat = "Time Limit Reached";
            Var.bDrive = false;                
        }
    }
}
