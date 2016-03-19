package org.usfirst.frc.team3245.robot;
//Teleop class that does all of the teleoperated code (to keep Robot.java clear)
//should probably stay relativly small...

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop extends Robot{
	Input pilotStick, coPilotStick;
	int flipperToggle = -1;
	// Buttons
	int leftStick = 1, rightStick = 3, fastBtn = 6, slowBtn = 5, switchButton = 11, downBtn = 3,
			upBtn = 1, shooterIn = 6, shooterOut = 5;
	boolean switchDrive = false;
	public Teleop() { //init
		pilotStick = new Input(0);
		coPilotStick = new Input(1);
		SmartDashboard.putString("DB/String 0", "Teleop Teleop init");
	}
	
	public void periodic() { 
    	//mainComp.setClosedLoopControl(true);
		updateDrive();
		updateShooter();
		coPilotStick.cycleUpdate();
		pilotStick.cycleUpdate();
    }
	
	private void updateShooter() {
		int angle = coPilotStick.getRawPOW();
		SmartDashboard.putNumber("POV", angle);
		if (angle == 0) {
			setIntake(0.6);
		} else if (angle == 180) {
			setIntake(-0.6);
		} else {
			setIntake(0);
		}
		SmartDashboard.putNumber("flipper", flipperToggle);
		
		if (coPilotStick.getRawButton(upBtn)) { //Buttons start at 1
			setFlipper(1);
		} else if (coPilotStick.getRawButton(downBtn)) {
			setFlipper(-1);
		}
		else{setFlipper(0);}
		
		if (coPilotStick.onButtonDown(2)) {
			flipperToggle = 1;
		}
		if (flipperToggle > 0) {
			flipperToggle++;
			if (flipperToggle < 20) {
				setFlipper(1);
			} else if (flipperToggle > 20 && flipperToggle < 40) {
				//wait..
			} else if( flipperToggle > 40 && flipperToggle < 90) {
				setFlipper(-.5);
			} else if (flipperToggle > 90){
				setFlipper(0);
				flipperToggle = -1;
			}
		}
		
		if (coPilotStick.getRawButton(shooterIn)) {
			setArm(1);
		} else if (coPilotStick.getRawButton(shooterOut)) {
			setArm(-1);
		}
		else{
			setArm(0);
		}
	}
	
	private void updateDrive() {
		double drivePercent = 0.6;
		//double correction = 1.10;
		if (pilotStick.getRawButton(fastBtn)) {
			drivePercent = 1;
		}
		
		if (pilotStick.getRawButton(slowBtn)) {
			drivePercent = 0.3;
		}
		if (pilotStick.onButtonDown(switchButton)) {
			switchDrive = !switchDrive;
		}
		if (switchDrive == true) {
			drivePercent*=-1;
		}
		//drivePercent*=DriverStation.getInstance().getBatteryVoltage()/;
		leftSpeed = pilotStick.getRawAxis(leftStick) * drivePercent;
		rightSpeed = pilotStick.getRawAxis(rightStick) * drivePercent;
    	setDrive(rightSpeed, leftSpeed); //take negatives out and make the drive reverse button a toggle not a hold-down
	}
}
