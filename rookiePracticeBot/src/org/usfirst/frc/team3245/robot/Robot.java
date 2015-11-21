
package org.usfirst.frc.team3245.robot;
import edu.wpi.first.wpilibj.*;

public class Robot extends IterativeRobot {
   
	private Talon lDrive, rDrive, toteConveyor;
	Joystick pilotStick;
	private double leftSpeed, rightSpeed, drivePercent, convSpeed;
	
    public void robotInit() {
    	lDrive = new Talon(1);
    	rDrive = new Talon(3);
    	toteConveyor = new Talon(0);
    	
    	CameraServer.getInstance().startAutomaticCapture("axis-camera");
    }

    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {
    	drivePercent = 0.60;
        if(pilotStick.getRawButton(6)){
        	drivePercent = 1;
        }
        if(pilotStick.getRawButton(5)){
        	drivePercent = 0.3;
        }
        if(pilotStick.getRawButton(1)){
        	convSpeed = 1;
        }
        if(pilotStick.getRawButton(3)){
        	convSpeed = -1;
        }
        leftSpeed = pilotStick.getRawAxis(1)*drivePercent;
        rightSpeed = pilotStick.getRawAxis(3)*drivePercent;
        
        
        lDrive.set(leftSpeed);
        rDrive.set(-rightSpeed);
        toteConveyor.set(convSpeed);
        
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
