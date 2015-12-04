package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.*;

//Class meant to be used for a single Joystick
public class Input {
	
	int index=0; //Used for onButtonDown class as a determinant
	Joystick controller;
	private int toggle=0;
	
	boolean[] rawButtons = new boolean[16]; //Button Array
	
	public Input(int controllerPort){ //Constructor creates new Joystick according to given port
		controller = new Joystick(controllerPort);
	}
	public double[] getJoysticks(){ //Gets the value of the magnitude of each axis of the sticks
		double[] values = new double[4];
		for(int i=0; i<=4;i++){
			values[i]=controller.getRawAxis(i);
		}
		return values; //returns array of joystick values
					   //1:leftStick horizontal, 2:leftStick vertical, 3:rightStick horizontal, 4:rightStick vertical
	}
	public void cycleUpdate() { //Gets the value of each button each tick of the periodic class
		for (int i=0; i<16; i++) {//built for 16 buttons, change if robot specific joystick is used
			boolean value = controller.getRawButton(i);
			
			rawButtons[i] = value;
		}
		
		
	}
	public boolean isButtonDown(int button){//simply returns the value of a button from cycleUpdate each tick when used
		return rawButtons[button];
	}

	
	public boolean onButtonDown(int button) { //returns a true boolean for a single tick after button is pressed
		if(rawButtons[button]==false){
			index=0; //index is set to 0 after button is lifted
		}
		if(rawButtons[button]==true&&index==0){ //will not return true when index=1
				index=1; //index is set to 1 after button is pressed
				return true;
		}	
	
		return false;
	}
	public boolean buttonToggle(int button){ //toggles between true/false on each time the button is pressed
		if(onButtonDown(button)&&toggle==0){
			toggle=1; //Toggle is true the instant button is pressed
		}
		else if(onButtonDown(button)&&toggle==1){
			toggle=0; //Toggle is false the next instant button is pressed
		}
		if(toggle==1){
			return true; //Returns true if toggle is 1
		}
		return false;
		
	}
	
}