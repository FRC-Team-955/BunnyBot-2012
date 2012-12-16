/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autonomous;

import utilities.JoyEmulator;
import utilities.FileReader;
import utilities.Robot;
import utilities.Vars;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author fauzi
 */
public class Replayer {
    
    //Constants
    private final double m_dMaxReplay = 14.75;
    
    private boolean m_bRepStarted = false;
    private boolean m_bDoneReplay = false;
    private JoyEmulator m_joyAuto = new JoyEmulator();
    private Timer m_tmReplay = new Timer();
    private String m_sReplayStatus = "";
    private FileReader m_fileReader;
    private Robot m_bot;
    
    public Replayer(Robot robot)
    {
        m_bot = robot;
    }
    
    public String replay(String sFileName)
    {
        Vars.bDrive = false;
         
        if(!m_bRepStarted)
        {
            m_fileReader = new FileReader(sFileName);
            m_joyAuto = m_fileReader.readAll();
            m_tmReplay.start();
            m_bRepStarted = true;
        }

        if(!m_bDoneReplay)
        {           
            if(m_tmReplay.get() >= m_joyAuto.getTimer())
                m_joyAuto = m_fileReader.readAll();
            
            m_sReplayStatus = "Replaying: " + Vars.setPrecision(m_tmReplay.get());
            m_bot.setSpeed(m_joyAuto.getMtLeft(), m_joyAuto.getMtRight());
            m_bot.setRetrieve(m_joyAuto.getRetrieve());
            
            if(m_tmReplay.get() >= m_dMaxReplay && !sFileName.equalsIgnoreCase(Vars.sRegOutput) || EndOfFile())
                m_bDoneReplay = true;
        }

        else
        {
            m_sReplayStatus = "Done Replaying";
            m_bot.setSpeed(0, 0);
            m_tmReplay.stop();
        }
        
        return m_sReplayStatus;
    }
    
    public void reset()
    {
        if(m_bRepStarted)
        {
            m_fileReader.close();
            m_tmReplay.stop();
            m_tmReplay.reset();
            m_bDoneReplay = false;
            m_bRepStarted = false;
        }
    }
    
    private boolean EndOfFile()
    {
        if(m_joyAuto.getTimer() < Vars.dENDSIGNAL+1)
            return true;
        
        else 
            return false;
    }
}
