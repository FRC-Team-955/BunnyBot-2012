package utilities;

import java.io.IOException;
import java.io.DataOutputStream;
import javax.microedition.io.Connector;
import com.sun.squawk.microedition.io.FileConnection;

/**
 * This class is responsible to opening/closing and writing data to the file.
 * @author Fauzi
 */
public class FileWriter {

    private final String m_sFile;
    private FileConnection m_fc;
    private DataOutputStream m_writer;  
    
    /**
     * Opens the specified file for the ability to write data to it.
     * @param sFileName 
     */
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
    
    /**
     * Writes an integer value to the file.
     * @param iData 
     */
    public void writeInt(int iData)
    {
        try
        {
            m_writer.writeInt(iData);
        }
        
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /** 
     * Writes a double value to the file.
     * @param dData 
     */
    public void writeDouble(double dData)
    {
        try
        {
            m_writer.writeDouble(dData);
        }
        
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Writes a boolean value to the file.
     * @param bData 
     */
    public void writeBoolean(boolean bData)
    {
        try
        {
            m_writer.writeBoolean(bData);
        }
        
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Closed the file, disabling use of it.
     */
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