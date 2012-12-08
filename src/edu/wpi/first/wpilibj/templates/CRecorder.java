/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;


/**
 *
 * @author fauzi
 */
import edu.wpi.first.wpilibj.Timer;
public class CRecorder {

    private String m_sRecordStaus = "";
    private boolean m_bRecStarted = false;
	private boolean m_bTmStop = false;
    private CFileWriter m_fileWriter;
    private Timer m_tmRecord = new Timer();
    private CRobot m_bot;
    
	int iLines = 0;	// DEBUGGER
	
    public CRecorder(CRobot robot)
    {
        m_bot = robot;
    }
    
    public String record(String sFileName)
    {
        if(!m_bRecStarted)
        {
            m_fileWriter = new CFileWriter(sFileName);
            m_tmRecord.start();
			m_bTmStop = false;
            m_bRecStarted = true;
        }

		System.out.println(m_tmRecord.get());	// DEGUBBER
		
        m_sRecordStaus = "Recording: " + String.valueOf(m_tmRecord.get());
        m_fileWriter.writeData(m_tmRecord.get(), m_bot.getMtLeft(), m_bot.getMtRight(), m_bot.getRetrieveStat());

        return m_sRecordStaus;
    }
    
    public void reset()
    {
        if(m_bRecStarted)
        {
            m_fileWriter.writeDouble(Var.dENDSIGNAL);
            m_fileWriter.close();
			
			if(!m_bTmStop)
			{
				m_tmRecord.stop();
				m_bTmStop = true;
			}
            m_bRecStarted = false;
        }
    }
}
