/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public JoyEmulator readAll()
    {
        JoyEmulator joyEmu = new JoyEmulator();
        
        try
        {
            joyEmu.setValues(m_reader.readDouble(), m_reader.readDouble(), m_reader.readBoolean(), m_reader.readDouble());
        }
        
        catch(IOException e)
        {
            joyEmu.setValues(0, 0, false, 0);
        }
        
        return joyEmu;
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


