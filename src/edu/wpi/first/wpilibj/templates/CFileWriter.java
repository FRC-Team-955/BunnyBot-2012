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

public class CFileWriter {

    String sFile;
    FileConnection fc;
    DataOutputStream writer;
    
    
    public CFileWriter(String sFileName)
    {
        sFile = sFileName;
        try
        {
            fc = (FileConnection)Connector.open(sFile, Connector.WRITE);
            fc.create();
            writer = new DataOutputStream(fc.openOutputStream(0));
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void writeInt(int iData)
    {
        try
        {
            writer.writeInt(iData); 
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void writeDouble(double dData)
    {
        try
        {
            writer.writeDouble(dData); 
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void writeBoolean(boolean bData)
    {
        try
        {
            writer.writeBoolean(bData); 
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
       
    public void close()
    {
        try
        {
            writer.flush();
            writer.close();
            fc.close();
        }
        
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}