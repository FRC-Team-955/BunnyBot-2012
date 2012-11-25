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
	
    DigitalInput digInSensor = new DigitalInput(Var.chnDigiSensor);
    Relay relay = new Relay(Var.chnRelay);
	
    public void run()
    {
		if(digInSensor.get())
			relay.set(Relay.Value.kOn);
		
		else
			relay.set(Relay.Value.kOff);
    }
}
