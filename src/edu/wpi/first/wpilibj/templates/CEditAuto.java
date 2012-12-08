/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 * @author fauzi
 */

// Meant to modfify a autonomous file a bit
public class CEditAuto {
    private final int iArraySize = 1500;
	
    private CFileReader m_fileReader;
    private CFileWriter m_fileWriter;
    private double[] m_dTimer = new double[iArraySize];
    private double[] m_dMtLeft = new double[iArraySize];
    private double[] m_dMtRight = new double[iArraySize];
    private boolean[] m_bRetrieve = new boolean[iArraySize];
    private int m_iArrayLength = 0;
    private String m_sFileName;
            
    public void printFile(String sFileName)
    {
        m_sFileName = sFileName;
        m_fileReader = new CFileReader(m_sFileName);
        double dTemp;
        int index = 0;
                
        while((dTemp = m_fileReader.readDouble()) > Var.dENDSIGNAL + 1)
        {
			m_dTimer[index] = dTemp;
			m_dMtLeft[index] = m_fileReader.readDouble();
			m_dMtRight[index] = m_fileReader.readDouble();
			m_bRetrieve[index] = m_fileReader.readBoolean();
			
            System.out.print(m_dTimer[index] + "-");
            System.out.print(m_dMtLeft[index] + "-");
            System.out.print(m_dMtRight[index] + "-");
            System.out.println(m_bRetrieve[index] + "-");
            m_iArrayLength = ++index;
        }
    }
    
    public void modify(double dMin, double dMax, double dMtLeft, double dMtRight)
    {
        m_fileWriter = new CFileWriter(m_sFileName);
        
        for(int index = 0; index < m_iArrayLength; index++)
        {
            if(m_dTimer[index] >= dMin && m_dTimer[index] <= dMax)
            {   
                if(dMtLeft > -1)
                    m_dMtLeft[index] = dMtLeft;

                if(dMtRight > -1)
                    m_dMtRight[index] = dMtRight;
            }
            
            m_fileWriter.writeData(m_dTimer[index], m_dMtLeft[index], m_dMtRight[index], m_bRetrieve[index]);
        }
        
        m_fileWriter.close();
    }
}
