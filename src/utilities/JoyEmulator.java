package utilities;

// @author Fauzi

public class JoyEmulator{
    
    private double m_dMtLeft = 0;
    private double m_dMtRight = 0;
    private double m_dTimer = 0;
    private boolean m_bRtrvStat = false;
    
    public  void add(double dMtLeft, double dMtRight, boolean bRtrvStat, double dTimer)
    {
        m_dMtLeft = dMtLeft;
        m_dMtRight = dMtRight;
        m_bRtrvStat = bRtrvStat;
        m_dTimer = dTimer;
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
    
    
    public double getTimer()
    {
        return m_dTimer;
    }
}