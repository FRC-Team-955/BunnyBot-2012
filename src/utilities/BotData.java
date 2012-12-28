package utilities;

/**
 * 
 * @author Fauzi
 */ 
public class BotData{
    
    private double m_dTimer = 0;
    private double m_dMtLeft = 0;
    private double m_dMtRight = 0;
    private boolean m_bRtrvStat = false;
    
    public void setValues(double dTimer, double dMtLeft, double dMtRight, boolean bRtrvStat)
    {
        m_dTimer = dTimer;
        m_dMtLeft = dMtLeft;
        m_dMtRight = dMtRight;
        m_bRtrvStat = bRtrvStat;
    }
    
    public void setValues(double dTimer, Robot bot)
    {
        m_dTimer = dTimer;
        m_dMtLeft = bot.getMotorLeft();
        m_dMtRight = bot.getMotorRight();
        m_bRtrvStat = bot.getRetrieveStat();
    }
    
    public void setValues(BotData emu)
    {
        m_dTimer = emu.getTime();
        m_dMtLeft = emu.getMtLeft();
        m_dMtRight = emu.getMtRight();
        m_bRtrvStat = emu.getRetrieve();
    }
    
    public double getTime()
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
}