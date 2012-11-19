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

    private String sFile;
    private FileConnection fc;
    private DataOutputStream writer;
    private boolean bIsClosed = false;    
    
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
            System.out.println(e.getMessage());
        }
    }
    
    public void writeData(double dTime, double dMtLeft, double dMtRight, boolean bRtrveStat, boolean bReleaseStat)
    {
        try
        {
            writer.writeDouble(dTime);
            writer.writeDouble(dMtLeft);
            writer.writeDouble(dMtRight);
            writer.writeBoolean(bRtrveStat);
            writer.writeBoolean(bReleaseStat);
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
            writer.writeInt(iData);
            writer.flush(); 
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
            writer.writeDouble(dData);
            writer.flush();
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
            writer.writeBoolean(bData);
            writer.flush();
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
            writer.close();
            fc.close();
            bIsClosed = true;
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
            fc = (FileConnection)Connector.open(sFile, Connector.WRITE);
            fc.create();
            writer = new DataOutputStream(fc.openOutputStream(0));
            bIsClosed = false;
        }
        
        catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean isClosed()
    {
        return bIsClosed;
    }
}