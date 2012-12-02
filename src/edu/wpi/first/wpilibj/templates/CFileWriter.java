/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import java.io.IOException;
import java.io.DataOutputStream;
import javax.microedition.io.Connector;
import com.sun.squawk.microedition.io.FileConnection;

/**
 *
 * @author Fauzi
 */

public class CFileWriter {

    private String m_sFile;
    private FileConnection m_fc;
    private DataOutputStream m_writer;
    private boolean m_bIsClosed = false;    
    
    public CFileWriter(String sFileName)
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
    
    public void writeData(double dTime, double dMtLeft, double dMtRight, boolean bRtrveStat)
    {
        try
        {
            m_writer.writeDouble(dTime);
            m_writer.writeDouble(dMtLeft);
            m_writer.writeDouble(dMtRight);
            m_writer.writeBoolean(bRtrveStat);
        }
        
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void writeInt(int iData)
    {
        try
        {
            m_writer.writeInt(iData);
            m_writer.flush(); 
        } 
        
        catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void writeDouble(double dData)
    {
        try
        {
            m_writer.writeDouble(dData);
            m_writer.flush();
        } 
        
        catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void writeBoolean(boolean bData)
    {
        try
        {
            m_writer.writeBoolean(bData);
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
            m_bIsClosed = true;
        }
        
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void open()
    {
        try
        {
            m_fc = (FileConnection)Connector.open(m_sFile, Connector.WRITE);
            m_fc.create();
            m_writer = new DataOutputStream(m_fc.openOutputStream(0));
            m_bIsClosed = false;
        }
        
        catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean isClosed()
    {
        return m_bIsClosed;
    }
}