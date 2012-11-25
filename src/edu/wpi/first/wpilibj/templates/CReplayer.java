/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author fauzi
 */
public class CReplayer {
    
    private boolean bRepStarted = false;
    private boolean bDoneReplay = false;
    private CJoyEmulator joyAuto = new CJoyEmulator();
    private CTimer tmReplay = new CTimer();
    private String sPrintWhat = "";
    private CFileReader fileReader;
    private CDrive driver;
    private CRetrieve retrieve;
    
    public CReplayer(CDrive drive, CRetrieve retriever)
    {
        driver = drive;
        retrieve = retriever;
    }
    
    public String replay(String sFileName)
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

                if(dTemp < Var.dENDSIGNAL+1) // If true, means we're done replaying
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
        
        return sPrintWhat;
    }
    
    public void reset()
    {
        if(bRepStarted)
        {
            if(!fileReader.isClosed())
                fileReader.close();
            
            tmReplay.reset(true);
            bDoneReplay = false;
            bRepStarted = false;
        }
    }
}
