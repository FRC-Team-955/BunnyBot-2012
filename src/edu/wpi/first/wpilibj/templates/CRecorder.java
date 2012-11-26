/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author fauzi
 */
public class CRecorder {

    private String sPrintWhat = "";
    private boolean bRecStarted = false;
    private CFileWriter fileWriter;
    private CTimer tmRecord = new CTimer();
    private CDrive driver;
    private CRetrieve retrieve;
    
    public CRecorder(CDrive drive, CRetrieve retrieval)
    {
        driver = drive;
        retrieve = retrieval;
    }
    
    public String record(String sFileName, boolean bAutoEditMode)
    {
        if(bAutoEditMode == false && sFileName != Var.sRegOutput)
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
        
        return sPrintWhat;
    }
    
    public void reset()
    {
        if(bRecStarted)
        {
            fileWriter.writeDouble(Var.dENDSIGNAL);
            fileWriter.close();
            tmRecord.reset(true);
            bRecStarted = false;
        }
    }
}
