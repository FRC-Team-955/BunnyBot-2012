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
    
    private boolean m_bRepStarted = false;
    private boolean m_bDoneReplay = false;
    private CJoyEmulator m_joyAuto = new CJoyEmulator();
    private CTimer m_tmReplay = new CTimer();
    private String m_sReplayStatus = "";
    private CFileReader m_fileReader;
    private CRobot m_bot;
    
    public CReplayer(CRobot robot)
    {
        m_bot = robot;
    }
    
    public String replay(String sFileName)
    {
        Var.bDrive = false;
         
        if(!m_bRepStarted)
        {
            m_fileReader = new CFileReader(sFileName);
            m_joyAuto.add(m_fileReader.readDouble(), m_fileReader.readDouble(), m_fileReader.readDouble(), m_fileReader.readBoolean());
            m_tmReplay.start();
            m_bRepStarted = true;
        }

        if(!m_bDoneReplay)
        {
            m_sReplayStatus = "Replaying: " + String.valueOf(m_tmReplay.get());
            m_bot.setSpeed(m_joyAuto.getMtLeft(), m_joyAuto.getMtRight());
            m_bot.setRetrieve(m_joyAuto.getRetrieve());

            if(m_tmReplay.get() >= m_joyAuto.getTimer())
            {
                double dTemp = m_fileReader.readDouble(); // Temp var to see if we're done replay

                if(dTemp < Var.dENDSIGNAL+1) // If true, means we're done replaying
                    m_bDoneReplay = true;

                else
                    m_joyAuto.add(dTemp, m_fileReader.readDouble(), m_fileReader.readDouble(), m_fileReader.readBoolean());
            }
        }

        else
        {
            m_sReplayStatus = "Done Replaying";
            m_bot.setSpeed(0, 0);
            m_fileReader.close();
            m_tmReplay.stop();
        }
        
        return m_sReplayStatus;
    }
    
    public void reset()
    {
        if(m_bRepStarted)
        {
            if(!m_fileReader.isClosed())
                m_fileReader.close();
            
            m_tmReplay.reset(true);
            m_bDoneReplay = false;
            m_bRepStarted = false;
        }
    }
}
