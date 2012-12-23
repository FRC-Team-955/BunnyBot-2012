/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autonomous;


/**
 * @author fauzi
 */
import utilities.FileWriter;
import utilities.Robot;
import utilities.JoyEmulator;
import java.util.Vector;
import edu.wpi.first.wpilibj.Timer;

public class Recorder {

    private boolean m_bRecStarted = false;
    private int m_Index = 0;
    private Timer m_tmRecord = new Timer();
    private Vector m_List = new Vector();
    private JoyEmulator m_JoyEmu = new JoyEmulator();
    private String m_sFile = null;
    private FileWriter m_fileWriter;
    private Robot m_bot;
    
    public Recorder(Robot robot)
    {
        m_bot = robot;
    }
    
    public String record(String sFileName)
    {
        if(!m_bRecStarted)
        {
            m_tmRecord.start();
            m_sFile = sFileName;
            m_bRecStarted = true;
        }
        
        m_Index++;
        m_JoyEmu.setValues(m_bot.getMtLeft(), m_bot.getMtRight(), m_bot.getRetrieveStat(), m_tmRecord.get());
        m_List.addElement(m_JoyEmu);
    
        return "Recording";
    }
    
    public void reset()
    {
        if(m_bRecStarted)
        {
            m_tmRecord.stop();
            m_tmRecord.reset();
            writeToFile();
            m_Index = 0;
            m_bRecStarted = false;
        }
    }
    
    private void writeToFile()
    {
        m_fileWriter = new FileWriter(m_sFile);
        m_fileWriter.writeInt(m_Index);

        for(int iPos = 0; iPos < m_Index; iPos++)
        {
            m_JoyEmu.setValues((JoyEmulator) m_List.elementAt(iPos));
            m_fileWriter.writeDouble(m_JoyEmu.getMtLeft());
            m_fileWriter.writeDouble(m_JoyEmu.getMtRight());
            m_fileWriter.writeBoolean(m_JoyEmu.getRetrieve());
            m_fileWriter.writeDouble(m_JoyEmu.getTime());
        }

        m_List.removeAllElements();
        m_fileWriter.close();
    }
}