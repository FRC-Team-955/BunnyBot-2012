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
    private boolean m_bIsClosed = false;
    
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

    
    public int readInt()
    {
        try 
        {           
            return m_reader.readInt();
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return -10;
        }
    }
    
	public double readDouble()
    {
        try 
        {           
            return m_reader.readDouble();
        } 
		
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return -10.0;
        }
    }
    
	public boolean readBoolean()
    {
        try 
        {           
            return m_reader.readBoolean();
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public void open()
    {
        try
        {
            m_fc = (FileConnection)Connector.open(m_sFile, Connector.READ);
            m_reader = new DataInputStream(m_fc.openInputStream());
            m_bIsClosed = false;
        }
        
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
	
    public void close()
    {
        try
        {
            m_reader.close();
            m_fc.close();
            m_bIsClosed = true;
        }
        
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean isClosed()
    {
        return m_bIsClosed;
    }
}


