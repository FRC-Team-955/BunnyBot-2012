/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;
import java.io.IOException;
import java.io.DataOutputStream;
import javax.microedition.io.Connector;
import com.sun.squawk.microedition.io.FileConnection;

/**
 *
 * @author Fauzi
 */

public class FileWriter {

    private String m_sFile;
    private FileConnection m_fc;
    private DataOutputStream m_writer;  
    
    public FileWriter(String sFileName)
    {
        m_sFile = sFileName;
                
        try
        {
            m_fc = (FileConnection)Connector.open(m_sFile, Connector.WRITE);
            m_fc.create();
            m_writer = new DataOutputStream(m_fc.openOutputStream(0));
        }
        
        catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void writeData(double dTime, Robot bot)
    {
        try
        {
            m_writer.writeDouble(dTime);
            m_writer.writeDouble(bot.getMtLeft());
            m_writer.writeDouble(bot.getMtRight());
            m_writer.writeBoolean(bot.getRetrieveStat());
        }
        
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void writeEndSignal()
    {
        try
        {
            m_writer.writeDouble(Vars.dENDSIGNAL);
            m_writer.flush();
        } 
        
        catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }
     
    public void close()
    {
        try
        {
            m_writer.close();
            m_fc.close();
        }
        
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}