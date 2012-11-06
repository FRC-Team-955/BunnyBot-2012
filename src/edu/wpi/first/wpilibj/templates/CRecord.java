package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Joystick;

// @author Fauzi
/*
 * This class will hopefully be responsible for recording the robots 
 * movements and "replaying" them.
 */
public class CRecord {

    private CButton btRecord = new CButton(false);
    private CButton btReplay = new CButton(false);
    private CButton btClear = new CButton(false);
    private String sPrintWhat = "";
    private String sType = "";
    private CTimer tmRecord = new CTimer();
    private CTimer tmReplay = new CTimer();
    private boolean bRepStarted = false;
    private Joystick joy;
    private CDrive driver;
    private CRetrieve retrieve;
    private CRelease releaser;
    private CFileWriter fileWriter = new CFileWriter(Var.sOutput);		//	<----------------------
    private CFileReader fileReader;										//
    private CPrintDriver printToDriverSt = new CPrintDriver();
    private double dJoyAutoReplayLength = 0;
    private CJoyEmulator joyAuto = new CJoyEmulator();
    
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
        btRecord.run(joy.getRawButton(Var.btRecord ));
        btReplay.run(joy.getRawButton(Var.btReplay));
        btClear.run(joy.getRawButton(Var.btClearList));

        if(btRecord.getSwitch() || btReplay.getSwitch() || btClear.getSwitch())
        {
            if(btRecord.getSwitch())   
                record();

            else if(btReplay.getSwitch())
            {
                Var.bDrive = false;
                replay();
            }

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
    
    private void reset() // To clear the memory so you can record something else
    {
        Var.bDrive = true;
        bRepStarted = false;
        tmReplay.reset(true);
        tmRecord.reset(true);
    }
    
    private void replay()
    {
        if(!bRepStarted)
        {
            tmReplay.start();
            fileWriter.close();
            fileReader = new CFileReader(Var.sOutput);
            bRepStarted = true;
            joyAuto.set(fileReader.readDouble(), fileReader.readDouble(), fileReader.readDouble(), fileReader.readBoolean(), fileReader.readBoolean());
        }

        if(tmReplay.get() <= dJoyAutoReplayLength)
        {
            sPrintWhat = "Replaying";
            driver.setSpeed(joyAuto.getMtLeft(), joyAuto.getMtRight());
            retrieve.set(joyAuto.getRetrieve());
            releaser.set(joyAuto.getRelease());

            if(tmReplay.get() > joyAuto.getTimer())
                joyAuto.set(fileReader.readDouble(), fileReader.readDouble(), fileReader.readDouble(), fileReader.readBoolean(), fileReader.readBoolean());
        }
		
        else
        {
            sPrintWhat = "Nothing Recorded";
            driver.setSpeed(0, 0);
        }
    }
    
    private void record()
    {
        tmRecord.start();
		
        if(tmRecord.get() < 100)	// TODO: Set this as Var variable or something, not magic number
        {
            sPrintWhat = "Recording";
            dJoyAutoReplayLength =+ tmRecord.get();
            fileWriter.writeData(tmRecord.get(), driver.getMtLeftSpeed(), driver.getMtRightSpeed(), retrieve.getStatus(), releaser.getReleaseStatus());
	}

        else
        {
            sPrintWhat = "Time Limit Reached";
            Var.bDrive = false;                
        }
    }
}

// DONT DELETE THIS vvvvvvvvvvv

//package edu.wpi.first.wpilibj.templates;
//import edu.wpi.first.wpilibj.Joystick;
//
//// @author Fauzi
///*
// * This class will hopefully be responsible for recording the robots 
// * movements and "replaying" them.
// */
//public class CRecord {
//
//    //private CJoyEmulator joyEmu = new CJoyEmulator(120);
//    //private CJoyEmulator joyEmuAuto = new CJoyEmulator(Var.dMaxRecordingTime);
//    private CButton btRecord = new CButton(false);
//    private CButton btReplay = new CButton(false);
//    private CButton btClear = new CButton(false);
//    private CButton btRecordAuto = new CButton(false);
//    private CButton btReplayAuto = new CButton(false);
//    private CButton btClearAuto = new CButton(false);
//    private CButton btSwitchLayout = new CButton(false);
//    private String sPrintWhat = "";
//    private String sType = "";
//    private CTimer tmRecord = new CTimer();
//    private CTimer tmReplay = new CTimer();
//    private boolean bRecStarted;
//    private boolean bNormLayout = true;
//	private boolean bRepStarted = false;
//    private int iJoyIndex = 0;
//    private Joystick joy;
//    private CDrive driver;
//    private CRetrieve retrieve;
//    private CRelease releaser;
//    private CFileWriter fileWriter = new CFileWriter(Var.sOutput);		//	<----------------------
//    private CFileReader fileReader;										//
//    private CPrintDriver printToDriverSt = new CPrintDriver();
//	private double dJoyAutoReplayLength = 0;
//	private double dAutoTimer = 0;
//	private double dAutoMtRight = 0;
//	private double dAutoMtLeft = 0;
//	private boolean bAutoRelease = false;
//	private boolean bAutoRetrieve = false;
//    
//    public CRecord(Joystick joystick, CDrive drive, CRetrieve retrieval, CRelease release)
//    {
//        joy = joystick;
//        driver = drive;
//        retrieve = retrieval;
//        releaser = release;
//    }
//    
//    public void run()
//    {
//        btSwitchLayout.run(joy.getRawButton(Var.btSwitchLayout));
//        
//        if(btSwitchLayout.gotPressed())
//            bNormLayout = !bNormLayout;
//        
//        if(bNormLayout)
//        {
//            sType = "Normal: ";
//            btRecord.run(joy.getRawButton(Var.btRecord ));
//            btReplay.run(joy.getRawButton(Var.btReplay));
//            btClear.run(joy.getRawButton(Var.btClearList));
//        }
//        
//        else
//        {
//            sType = "Auto: ";
//            btRecordAuto.run(joy.getRawButton(Var.btRecord));
//            btReplayAuto.run(joy.getRawButton(Var.btReplay));
//            btClearAuto.run(joy.getRawButton(Var.btClearList));
//        }
//        
//        if(btRecord.getSwitch() || btReplay.getSwitch() || btClear.getSwitch() || btRecordAuto.getSwitch() || btReplayAuto.getSwitch() || btClearAuto.getSwitch())
//        {
//            if(btRecord.getSwitch() || btRecordAuto.getSwitch())
//            {                
//                if(btRecord.getSwitch())
//                    record(joyEmu, false);
//                
//                else
//                    record(joyEmuAuto, true);
//            }
//
//            else if(btReplay.getSwitch() || btReplayAuto.getSwitch())
//            {
//                Var.bDrive = false;
//                
//                if(btReplay.getSwitch())
//                    replay(joyEmu, true);
//                
//                else
//                    replay(joyEmuAuto, false);
//            }
//
//            else if(btClear.gotPressed() || btClearAuto.gotPressed())
//            {
//                if(btClear.gotPressed())
//                    joyEmu.deleteAll();
//
//                else
//                    joyEmuAuto.deleteAll();
//            }
//        }
//        
//        else
//        {
//            sPrintWhat = "Doing Nothing";
//            reset();
//        }
//        
//        printToDriverSt.print(Var.iRecordStatusLine, sType + sPrintWhat);
//    }
//    
//    private void reset() // To clear the memory so you can record something else
//    {
//        Var.bDrive = true;
//        bRecStarted = false;
//		bRepStarted = false;
//        tmReplay.reset(true);
//        tmRecord.reset(true);
//        iJoyIndex = 0;
//    }
//    
//    private void replay(CJoyEmulator joyReplayer, boolean bIsAuto)
//    {
//		if(bIsAuto = true )
//		{
//			if(!bRepStarted)
//			{
//				tmReplay.start();
//				fileWriter.close();
//				fileReader = new CFileReader(Var.sOutput);
//				bRepStarted = true;
//			}
//               
//            if(tmReplay.get() <= dJoyAutoReplayLength)
//            {
//                sPrintWhat = "Replaying";
//				
//				if(tmReplay.get() > dAutoTimer)
//				{
//					dAutoTimer = fileReader.readDouble();
//					dAutoMtLeft = fileReader.readDouble();
//					dAutoMtRight = fileReader.readDouble();
//					bAutoRetrieve = fileReader.readBoolean();
//					bAutoRelease = fileReader.readBoolean();
//				}
//				
//				driver.setSpeed(dAutoMtLeft, dAutoMtRight);
//				retrieve.set(bAutoRetrieve);
//				releaser.set(bAutoRelease);
//			}
//		}
//		
////		else if(!joyReplayer.isEmpty())
////        {
////            tmReplay.start();
////               
////            if(tmReplay.get() <= joyReplayer.getReplayLength())
////            {
////                sPrintWhat = "Replaying";
////				
////				driver.setSpeed(joyReplayer.getMtLeft(iJoyIndex), joyReplayer.getMtRight(iJoyIndex));
////				retrieve.set(joyReplayer.getRetrieveStatus(iJoyIndex));
////				releaser.set(joyReplayer.getReleaseStatus(iJoyIndex));
////
////				if(tmReplay.get() >= joyReplayer.getTmr(iJoyIndex))
////					iJoyIndex++;
////            }
////
////            else 
////            {
////                sPrintWhat = "Done Replaying";
////                tmReplay.stop();
////                driver.setSpeed(0,0);
////            }
////        }
//        
//        else
//        {
//            sPrintWhat = "Nothing Recorded";
//            driver.setSpeed(0, 0);
//        }
//    }
//    
//    private void record(CJoyEmulator joyRecord, boolean bIsAuto)
//    {
//        if(!bRecStarted)
//        {
//            tmRecord.start();
//            bRecStarted = true;
//            joyRecord.deleteAll(); // Deletes previous recording to start a new one
//            iJoyIndex = 0;
//        }
//
//        if(tmRecord.get() < joyRecord.getMaxReplayLimit())
//        {
//            sPrintWhat = "Recording";
//			
//			if(bIsAuto)
//			{
//				dJoyAutoReplayLength =+ tmRecord.get();
//				fileWriter.writeDouble(tmRecord.get());
//				fileWriter.writeDouble(driver.getMtLeftSpeed());
//				fileWriter.writeDouble(driver.getMtRightSpeed());
//				fileWriter.writeBoolean(retrieve.getStatus());
//				fileWriter.writeBoolean(releaser.getReleaseStatus());
//				
//				System.out.println("Recording Length: " +  dJoyAutoReplayLength + "Record: " + tmRecord.get() + "Drive Left, right: " + driver.getMtLeftSpeed() + ", " + driver.getMtRightSpeed()
//						+ " Retrieve: " + retrieve.getStatus() + " Releaser: " + releaser.getReleaseStatus());
//			}
//			
//			else
//				joyRecord.add(tmRecord.get(), driver.getMtLeftSpeed(), driver.getMtRightSpeed(), retrieve.getStatus(), releaser.getReleaseStatus());
//			
//		}
//
//        else
//        {
//            sPrintWhat = "Time Limit Reached";
//            Var.bDrive = false;                
//        }
//    }
//}
