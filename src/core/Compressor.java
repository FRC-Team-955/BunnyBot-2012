/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import utilities.Vars;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

/**
 *
 * @author RaiderPC
 */
public class Compressor {
	
    private DigitalInput m_digInSensor = new DigitalInput(Vars.chnDigiSensor);
    private DigitalOutput m_Compressor = new DigitalOutput(Vars.chnCompressor);
	
    public void run()
    {
        if(m_digInSensor.get())
            m_Compressor.set(true);

        else
            m_Compressor.set(false);
    }
}
