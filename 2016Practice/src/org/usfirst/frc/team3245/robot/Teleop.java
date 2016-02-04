package org.usfirst.frc.team3245.robot;
//Teleop class that does all of the teleoperated code (to keep Robot.java clear)
//should probably stay relativly small...

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop extends Robot{
	Input pilotStick, coPilotStick;
	// Buttons
	int leftStick = 1, rightStick = 3, fastBtn = 8, slowBtn = 7, switchButton = 1;
	public Teleop() { //init
		pilotStick = new Input(0);
		coPilotStick = new Input(1);
		SmartDashboard.putString("DB/String 0", "Teleop Teleop init");
	}
	
	public void periodic() {
    	SmartDashboard.putString("DB/String 0", Double.toString(gyro.getAngle()));
    	SmartDashboard.putString("DB/String 1", Double.toString(gyro.getRate()));
    	//mainComp.setClosedLoopControl(true);
		updateDrive();
    }
	
	private void updateDrive() {
		double drivePercent = 0.35;
		if (pilotStick.getRawButton(fastBtn)) {
			drivePercent = .5;
		} else if (pilotStick.getRawButton(slowBtn)) {
			drivePercent = 0.150;
		}
		if (pilotStick.onButtonDown(switchButton)) {
			drivePercent *= -1;
		}
		drivePercent*=DriverStation.getInstance().getBatteryVoltage();
		leftSpeed = pilotStick.getRawAxis(leftStick) * drivePercent;
		rightSpeed = pilotStick.getRawAxis(rightStick) * drivePercent;
	}
}
