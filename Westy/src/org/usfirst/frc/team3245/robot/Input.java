
package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.*;

//Class meant to be used once for all joysticks
public class Input{
	
	int index=0; //Used for onButtonDown class as a determinant
	Joystick controller;
	
	boolean[] rawButtons = new boolean[16]; //Button Array
	
	public Input(int portNumber){ // set up for a give number of controllers
		controller = new Joystick(portNumber);
	}
	
	public void cycleUpdate() { //Gets the value of each button each tick of the periodic class
		for (int i=0; i<16; i++) {//built for 16 buttons
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
	
	int toggle;
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