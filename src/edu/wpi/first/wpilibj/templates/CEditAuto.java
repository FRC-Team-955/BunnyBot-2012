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
    private final int m_iArraySize = 1500;
	
    private CFileReader m_fileReader;
    private CFileWriter m_fileWriter;
    private double[] m_dTimer = new double[m_iArraySize];
    private double[] m_dMtLeft = new double[m_iArraySize];
    private double[] m_dMtRight = new double[m_iArraySize];
    private boolean[] m_bRetrieve = new boolean[m_iArraySize];
    private int m_iArrayLength = 0;
    private String m_sFileName;
            
    public String printFile(String sFileName)
    {
        m_sFileName = sFileName;
        m_fileReader = new CFileReader(m_sFileName);
        double dTemp;
        int index = 0;
                
        while((dTemp = m_fileReader.readDouble()) > Var.dENDSIGNAL + 1)
        {	
            System.out.print(Var.setPrecision(m_dTimer[index] = dTemp) + "-");
            System.out.print(Var.setPrecision(m_dMtLeft[index] = m_fileReader.readDouble()) + "-");
            System.out.print(Var.setPrecision(m_dMtRight[index] = m_fileReader.readDouble()) + "-");
            System.out.println((m_bRetrieve[index] = m_fileReader.readBoolean()) + "-");
            m_iArrayLength = ++index;
        }
        
        return "Printed file Data";
    }
    
    public String modify(double dMin, double dMax, double dMtLeft, double dMtRight)
    {
        m_fileWriter = new CFileWriter(m_sFileName);
        
        for(int index = 0; index < m_iArrayLength; index++)
        {
            if(m_dTimer[index] >= dMin && m_dTimer[index] <= dMax)
            {   
                if(dMtLeft <=1)
                    m_dMtLeft[index] = dMtLeft;

                else if(dMtLeft >= 1 && dMtLeft <=2)
                    m_dMtLeft[index] = -(dMtLeft-1);
                
                if(dMtRight <= 1)
                    m_dMtRight[index] = dMtRight;
                
                else if(dMtRight >= 1 && dMtRight <=2)
                    m_dMtRight[index] = -(dMtRight-1);
            }
            
            m_fileWriter.writeData(m_dTimer[index], m_dMtLeft[index], m_dMtRight[index], m_bRetrieve[index]);
        }
        
        m_fileWriter.writeDouble(Var.dENDSIGNAL);
        m_fileWriter.close();
        return "Modified File";
    }
}