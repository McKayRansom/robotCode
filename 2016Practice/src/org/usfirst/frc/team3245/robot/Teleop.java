package org.usfirst.frc.team3245.robot;
//Teleop class that does all of the teleoperated code (to keep Robot.java clear)
//should probably stay relativly small...

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop extends Robot{
	Joystick pilotStick, coPilotStick;
	// Buttons
	int leftStick = 1, rightStick = 3, fastBtn = 8, slowBtn = 7;
	public Teleop() { //init
		pilotStick = new Joystick(0);
		coPilotStick = new Joystick(1);
		SmartDashboard.putString("DB/String 0", "Teleop Teleop init");
	}
	
	public void periodic() {
    	SmartDashboard.putString("DB/String 0", Double.toString(gyro.getAngle()));
    	SmartDashboard.putString("DB/String 1", Double.toString(gyro.getRate()));
    	//mainComp.setClosedLoopControl(true);
		updateDrive();
    }
	
	private void updateDrive() {
		double drivePercent = 0.55;
		if (pilotStick.getRawButton(fastBtn)) {
			drivePercent = 1.00;
		} else if (pilotStick.getRawButton(slowBtn)) {
			drivePercent = 0.30;
		}
		leftSpeed = pilotStick.getRawAxis(leftStick) * drivePercent;
		rightSpeed = pilotStick.getRawAxis(rightStick) * drivePercent;
	}
}
