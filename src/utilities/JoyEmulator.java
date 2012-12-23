package utilities;

// @author Fauzi

public class JoyEmulator{
    
    private double m_dTime = 0;
    private double m_dMtLeft = 0;
    private double m_dMtRight = 0;
    private boolean m_bRtrvStat = false;
    
    public void setValues(double dMtLeft, double dMtRight, boolean bRtrvStat, double dTimer)
    {
        m_dMtLeft = dMtLeft;
        m_dMtRight = dMtRight;
        m_bRtrvStat = bRtrvStat;
        m_dTime = dTimer;
    }
    
    public void setValues(JoyEmulator emu)
    {
        m_dMtLeft = emu.getMtLeft();
        m_dMtRight = emu.getMtRight();
        m_bRtrvStat = emu.getRetrieve();
        m_dTime = emu.getTime();
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
    
    public double getTime()
    {
        return m_dTime;
    }
}