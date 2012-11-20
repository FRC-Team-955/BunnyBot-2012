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
	
    DigitalOutput digiCompressor = new DigitalOutput(Var.chnDigiCompressor);
    CButton btCompressor = new CButton();
    Joystick joy;

    public CCompressor(Joystick joystick)
    {
        joy = joystick;
    }

    public void run()
    {
        btCompressor.run(joy.getRawButton(Var.btTurnOnCompressor));

        if(btCompressor.getSwitch())
            digiCompressor.set(true);

        else
            digiCompressor.set(false);
    }
}
