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

    String sFile;
    FileConnection fc;
    DataInputStream reader;
    
    
    public CFileReader(String sFileName)
    {
        sFile = sFileName;
        
        try
        {
            fc = (FileConnection)Connector.open(sFile, Connector.READ);
            reader= new DataInputStream(fc.openInputStream());
        }
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    
    public int readInt()
    {
        try 
        {           
            //System.out.println("The file says: " + reader.readInt());
			return reader.readInt();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
			return -1;
        }
    }
    
	public double readDouble()
    {
        try 
        {           
            //System.out.println("The file says: " + reader.readInt());
			return reader.readDouble();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
			return -1;
        }
    }
    
	public boolean readBoolean()
    {
        try 
        {           
            //System.out.println("The file says: " + reader.readInt());
			return reader.readBoolean();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
			return false;
        }
    }
    
    public void close()
    {
        try
        {
            reader.close();
			fc.close();
        }
        
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}


