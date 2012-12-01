/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author RaiderPC
 */
public class CCompressor {
	
    DigitalInput m_digInSensor = new DigitalInput(Var.chnDigiSensor);
    Relay m_relay = new Relay(Var.chnRelay);
	
    public void run()
    {
        if(m_digInSensor.get())
            m_relay.set(Relay.Value.kOn);

        else
            m_relay.set(Relay.Value.kOff);
    }
}
