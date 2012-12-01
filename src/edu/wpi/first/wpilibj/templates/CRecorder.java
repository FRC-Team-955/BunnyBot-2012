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

    private String m_sRecordStaus = "";
    private boolean m_bRecStarted = false;
    private CFileWriter m_fileWriter;
    private CTimer m_tmRecord = new CTimer();
    private CDrive m_driver;
    private CRetrieve m_retrieve;
    
    public CRecorder(CDrive drive, CRetrieve retrieval)
    {
        m_driver = drive;
        m_retrieve = retrieval;
    }
    
    public String record(String sFileName, boolean bAutoEditMode)
    {
        if(!m_bRecStarted)
        {
            m_sRecordStaus = "Recording";
            m_fileWriter = new CFileWriter(sFileName);
            m_tmRecord.start();
            m_bRecStarted = true;
        }

        m_fileWriter.writeData(m_tmRecord.get(), m_driver.getMtLeftSpeed(), m_driver.getMtRightSpeed(), m_retrieve.getStatus());

        return m_sRecordStaus;
    }
    
    public void reset()
    {
        if(m_bRecStarted)
        {
            m_fileWriter.writeDouble(Var.dENDSIGNAL);
            m_fileWriter.close();
            m_tmRecord.reset(true);
            m_bRecStarted = false;
        }
    }
}
