package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.*;

public class Input {
	Joystick controller;
	boolean[] rawButtons = new boolean[16];
	
	public Input(int controllerPort){
		controller = new Joystick(controllerPort);
	}
	public double[] getJoysticks(){
		double[] values = new double[4];
		for(int i=0; i<=4;i++){
			values[i]=controller.getRawAxis(i);
		}
		return values;
	}
//	public void cycleUpdate() { //not currently used...
//		for (int i=0; i<16; i++) {
//			boolean value = controller.getRawButton(i);
//			if (value == rawButtons[i]) {
//				//on toggle stuff goes here
//			}
//			rawButtons[i] = value;
//		}
//	}
	public boolean getRawButton(int button){
		return rawButtons[button];
	}
	
	public double getRawAxis(int axis) {
		return controller.getRawAxis(axis);
	}
	
}