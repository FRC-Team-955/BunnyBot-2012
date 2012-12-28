package utilities;

import java.io.IOException;
import java.io.DataInputStream;
import javax.microedition.io.Connector;
import com.sun.squawk.microedition.io.FileConnection;

/**
 *
 * @author Fauzi
 */
public class FileReader {

    private String m_sFile;
    private FileConnection m_fc;
    private DataInputStream m_reader;
    
    public FileReader(String sFileName)
    {
        m_sFile = sFileName;
        
        try
        {
            m_fc = (FileConnection)Connector.open(m_sFile, Connector.READ);
            m_reader = new DataInputStream(m_fc.openInputStream());
        }
        
        catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }

    public BotData readAll()
    {
        BotData botData = new BotData();
        
        try
        {
            botData.setValues(m_reader.readDouble(), m_reader.readDouble(), m_reader.readDouble(), m_reader.readBoolean());
        }
        
        catch(IOException e)
        {
            botData.setValues(0, 0, 0, false);
        }
        
        return botData;
    }
        
    public int readInt()
    {
        try
        {
            return m_reader.readInt();
        }
        
        catch(IOException e)
        {
            return 0;
        }
    }
    
    public void close()
    {
        try
        {
            m_reader.close();
            m_fc.close();
        }
        
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}


