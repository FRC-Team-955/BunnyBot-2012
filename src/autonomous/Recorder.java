package autonomous;

import utilities.FileWriter;
import utilities.Robot;
import utilities.BotData;
import java.util.Vector;
import edu.wpi.first.wpilibj.Timer;
import utilities.Vars;

/**
 * @author fauzi
 */
public class Recorder {

    private int m_Index = 0;
    private double m_dRecordTime = 0.00;
    private boolean m_bRecStarted = false;
    private Timer m_tmRecorder = new Timer();
    private Vector m_List = new Vector();
    private BotData m_botDataAuto = new BotData();
    private String m_sFile = "";
    private FileWriter m_fileWriter;
    private Robot m_bot;
    
    public Recorder(Robot robot)
    {
        m_bot = robot;
    }
    
    /**
     * Records bots movements to specified file
     * @param sFileName 
     */
    public void record(String sFileName)
    {        
        if(!m_bRecStarted)
        {
            m_sFile = sFileName;
            m_tmRecorder.start();
            m_bRecStarted = true;
        }
        
        m_Index++;
        m_dRecordTime = Vars.fnSetPrecision(m_tmRecorder.get());
        m_botDataAuto.setValues(m_tmRecorder.get(), m_bot);
        m_List.addElement(m_botDataAuto);
    }
    
    /**
     * Resets the recorder so we can record properly next time
     */
    public void reset()
    {
        if(m_bRecStarted)
        {
            writeDataToFile();
            m_tmRecorder.stop();
            m_tmRecorder.reset();
            m_dRecordTime = 0.00;
            m_bRecStarted = false;
        }
    }
    
    public double getRecordTime()
    {
        return m_dRecordTime;
    }
    
    /**
     * Actually writes the data to file
     */
    private void writeDataToFile()
    {
        m_fileWriter = new FileWriter(m_sFile);
        m_fileWriter.writeInt(m_Index);

        for(int iPos = 0; iPos < m_Index; iPos++)
        {
            m_botDataAuto.setValues((BotData) m_List.elementAt(iPos));
            m_fileWriter.writeDouble(m_botDataAuto.getTime());
            m_fileWriter.writeDouble(m_botDataAuto.getMtLeft());
            m_fileWriter.writeDouble(m_botDataAuto.getMtRight());
            m_fileWriter.writeBoolean(m_botDataAuto.getRetrieve());
        }

        m_List.removeAllElements();
        m_fileWriter.close();
        m_Index = 0;
    }
}