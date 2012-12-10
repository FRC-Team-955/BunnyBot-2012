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
public class Compressor {
	
    DigitalInput m_digInSensor = new DigitalInput(Var.chnDigiSensor);
    DigitalOutput m_Compressor = new DigitalOutput(Var.chnCompressor);
	
    public void run()
    {
        if(m_digInSensor.get())
			m_Compressor.set(true);

        else
			m_Compressor.set(false);
    }
}
