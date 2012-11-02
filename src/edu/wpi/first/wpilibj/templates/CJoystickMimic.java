// @author Fauzi
package edu.wpi.first.wpilibj.templates;

class CJoystickMimic{
    
    private class link
    {
        double dMtRight;
        double dMtLeft;
        double dTmr;
        boolean bRetrieveStatus;
        link next;
        link prev;
    }
    
    private link head;
    private link last;
    private link curLink;
    private int iNextIndex;
    private int size;
    private double dTmrLimit;
    
    CJoystickMimic()
    {
        head = null;
        curLink = null;
        last = null;
        iNextIndex = 0;
        size  = 0;
    }
    
    public void add(double dTimer, double dMtLeftSpeed, double dMtRightSpeed, boolean bRtvStatus)
    {
        size++;
        link tempLink = new link();
        tempLink.dTmr = dTimer;
        tempLink.dMtRight = dMtRightSpeed;
        tempLink.dMtLeft = dMtLeftSpeed;
        tempLink.bRetrieveStatus = bRtvStatus;
        
        if(head == null)
        {
            head = tempLink;
            curLink = head;
            last = head;
        }
        
        else
        {
            last.next = tempLink;
            tempLink.prev = last;
            last = tempLink;
        }
    }
    
    public void setReplayLimit(double dTmrLmt)
    {
        dTmrLimit = dTmrLmt;
    }
    
    public double getReplayLimit()
    {
        return dTmrLimit;
    }
    
    public double getMtRight(int index)
    {
        if(index == -1)
        {
            return curLink.dMtRight;
        }
        
        else 
        {
            link tempLink;
        
            tempLink = getObject(index);
            return tempLink.dMtRight; 
        }
    }
    
    public double getMtLeft(int index)
    {
        if(index == -1)
        {
            return curLink.dMtLeft;
        }
        
        else 
        {
            link tempLink;
        
            tempLink = getObject(index);
            return tempLink.dMtLeft; 
        }
    }
    
    public double getTmr(int index)
    {
        if(index == -1)
        {
            return curLink.dTmr;
        }
        
        else 
        {
            link tempLink;
        
            tempLink = getObject(index);
            return tempLink.dTmr; 
        }
    }
    
    public boolean getRetrieveStatus(int index)
    {
        if(index == -1)
        {
            return curLink.bRetrieveStatus;
        }
        
        else 
        {
            link tempLink;
        
            tempLink = getObject(index);
            return tempLink.bRetrieveStatus; 
        }
    }
      
    public int getIter()
    {
        return  iNextIndex;
    }
    
    public void getNext()
    {
        if(curLink.next != null)
        {
            curLink = curLink.next;
            iNextIndex++;
        }
    }
        
    public void reset()
    {
        curLink = head;
        iNextIndex = 0;
    }
    
    public boolean isEmpty()
    {
        if(size == 0)
            return true;
        
        else
            return false;
    }
    private link getObject(int index)
    {
        link tempLink;
     
        if(index <= (size/2))
        {
            tempLink = head;
            
            for(int i = 0; i < index; ++i)
               tempLink = tempLink.next;
        }
        
        else
        {
            tempLink = last;
            
            for(int i = 1; i < (size-index); ++i)
                tempLink = tempLink.prev;    
        } 
        
        return tempLink;
    }
    
    public void deleteAll()
    {
        while(head.next != null)
        {
            head = head.next;
            head.prev.next = null;
            head.prev = null;
        }
        
        head = null;
        last = null;
        curLink = null;
        iNextIndex = 0;
        size = 0;
    }
    
    public int size()
    {
        return size;
    }       
}
