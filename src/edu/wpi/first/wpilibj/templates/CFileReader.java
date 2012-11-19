/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.*;
import javax.microedition.io.Connector;

import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.microedition.io.FileConnection;

/**
 *
 * @author Fauzi
 */
public class CFileReader {

    private String sFile;
    private FileConnection fc;
    private DataInputStream reader;
    private boolean bIsClosed = false;
    
    public CFileReader(String sFileName)
    {
        sFile = sFileName;
        
        try
        {
            fc = (FileConnection)Connector.open(sFile, Connector.READ);
            reader = new DataInputStream(fc.openInputStream());
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
            return reader.readInt();
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
	public double readDouble()
    {
        try 
        {           
            return reader.readDouble();
        } 
		
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
	public boolean readBoolean()
    {
        try 
        {           
            return reader.readBoolean();
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
            fc = (FileConnection)Connector.open(sFile, Connector.READ);
            reader = new DataInputStream(fc.openInputStream());
            bIsClosed = false;
        }
        
        catch(IOException e)
        {
            e.getMessage();
        }
    }
	
    public void close()
    {
        try
        {
            reader.close();
            fc.close();
            bIsClosed = true;
        }
        
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public boolean isClosed()
    {
        return bIsClosed;
    }
}


