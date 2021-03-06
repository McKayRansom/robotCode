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
	public void cycleUpdate() { //not currently used...
		for (int i=1; i<13; i++) {
			rawButtons[i] = controller.getRawButton(i);
		}
	}
	
	public boolean onButtonDown(int button) {
		if ((controller.getRawButton(button) == true) && (rawButtons[button] == false)){
			return true;
		}
		return false;
	}
	
	public boolean getRawButton(int button){
		return controller.getRawButton(button);
	}
	
	public int getRawPOW(){
		return controller.getPOV(0);
	}
	
	public double getRawAxis(int axis) {
		return controller.getRawAxis(axis);
	}

}