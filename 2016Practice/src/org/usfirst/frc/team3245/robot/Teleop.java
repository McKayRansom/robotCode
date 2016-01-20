package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop extends Robot{
	public void teleopInit() {

	}
	
    public void teleopPeriodic() {
    	SmartDashboard.putString("DB/String 0", Double.toString(gyro.getAngle()));
    	//mainComp.setClosedLoopControl(true);
		
		updateDrive();
    }
	public void updateDrive() {
		double drivePercent = 0.55;
		if (pilotStick.getRawButton(fastBtn)) {
			drivePercent = 1.00;
		} else if (pilotStick.getRawButton(slowBtn)) {
			drivePercent = 0.30;
		}
		leftSpeed = pilotStick.getRawAxis(leftStick) * drivePercent;
		rightSpeed = pilotStick.getRawAxis(rightStick) * drivePercent;
		setDrive(rightSpeed, leftSpeed);
	}
}
