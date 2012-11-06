package edu.wpi.first.wpilibj.templates;

// @author Fauzi


class CJoyEmulator{
    private double m_dTimer = 0;
    private double m_dMtLeft = 0;
    private double m_dMtRight = 0;
    private boolean m_bRtrvStat = false;
    private boolean m_bReleaseStat = false;
    double m_dReplayLength = 0;
    
    public  void add(double dTimer, double dMtLeft, double dMtRight, boolean bRtrvStat, boolean bReleaseStat)
    {
        m_dTimer = dTimer;
        m_dMtLeft = dMtLeft;
        m_dMtRight = dMtRight;
        m_bRtrvStat = bRtrvStat;
        m_bReleaseStat = bReleaseStat;
    }
    
    public double getTimer()
    {
        return m_dTimer;
    }
    
    public double getMtLeft()
    {
        return m_dMtLeft;
    }
    
    public double getMtRight()
    {
        return m_dMtRight;
    }
    
    public boolean getRetrieve()
    {
        return m_bRtrvStat;
    }
    
    public boolean getRelease()
    {
        return m_bReleaseStat;
    }
}











//package edu.wpi.first.wpilibj.templates;
//
//// @author Fauzi
//
//
//class CJoyEmulator{
//    
//    private int m_size = 0;
//    private double m_dRplyLength = 0;
//    private double m_dMaxRrplyLimit = 0;
//    
//    private double[] m_dMtRight = new double[Var.iArrayRecrdSize];
//    private double[] m_dMtLeft = new double[Var.iArrayRecrdSize];
//    private double[] m_dTmr = new double[Var.iArrayRecrdSize];
//    private boolean[] m_bRetrieveStatus = new boolean[Var.iArrayRecrdSize];
//    private boolean[] m_bReleaseStatus = new boolean[Var.iArrayRecrdSize];
//
//    public CJoyEmulator(double dMaxRplyLimit)
//    {
//        m_dMaxRrplyLimit = dMaxRplyLimit;
//    }
//    
//    public void add(double dTimer, double dMtLeftSpeed, double dMtRightSpeed, boolean bRtvStatus, boolean bReleaseStatus)
//    {
//        if(m_size == 0)
//        {
//            m_dMtRight = new double[Var.iArrayRecrdSize];
//            m_dMtLeft = new double[Var.iArrayRecrdSize];
//            m_dTmr = new double[Var.iArrayRecrdSize];
//            m_bRetrieveStatus = new boolean[Var.iArrayRecrdSize];
//        }
//        
//        m_dTmr[m_size] = dTimer;
//        m_dMtLeft[m_size] = dMtLeftSpeed;
//        m_dMtRight[m_size] = dMtRightSpeed;
//        m_bRetrieveStatus[m_size] = bRtvStatus;
//        m_bReleaseStatus[m_size] = bReleaseStatus;
//        m_size++;
//        m_dRplyLength =+ dTimer;
//    }
//
//    public double getReplayLength()
//    {
//        return m_dRplyLength;
//    }
//    
//    public boolean getReleaseStatus(int index)
//    {
//        return m_bReleaseStatus[index];
//    }
//    
//    public double getMaxReplayLimit()
//    {
//        return m_dMaxRrplyLimit;
//    }
//    
//    public double getMtRight(int index)
//    {
//        return m_dMtRight[index];
//    }
//    
//    public double getMtLeft(int index)
//    {
//        return m_dMtLeft[index];
//    }
//    
//    public double getTmr(int index)
//    {
//        return m_dTmr[index];
//    }
//    
//    public boolean getRetrieveStatus(int index)
//    {
//        return m_bRetrieveStatus[index];
//    }
//    
//    public boolean isEmpty()
//    {
//        if(m_size < 1)
//            return true;
//        
//        else
//            return false;
//    }
//    
//    public void deleteAll()
//    {
//        m_dTmr = null;
//        m_dMtLeft = null;
//        m_dMtRight = null;
//        m_bRetrieveStatus = null;
//        m_dRplyLength = 0;
//        m_size = 0;
//    }
//    
//    public int size()
//    {
//        return m_size;
//    }       
//}
