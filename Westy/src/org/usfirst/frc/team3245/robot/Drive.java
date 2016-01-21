
package org.usfirst.frc.team3245.robot;
import edu.wpi.first.wpilibj.*;

public class Drive {
	Input driveController;
	Talon[] leftDrive;
	Talon[] rightDrive;
	
	public Drive(Input controller, int motorNumber){
		driveController = controller;
		defineMotors(motorNumber);
		
	}
	public void tankDrive(){
		double leftAxis = driveController.getRawAxes()[1];
		double rightAxis = driveController.getRawAxes()[2];
		for(Talon mc : leftDrive){
			
		}
	}
	
	private void defineMotors(int number){ //Defines the motors in the constructor according to the number of motors
		leftDrive = new Talon[number/2];
		rightDrive = new Talon[number/2];
		
		for(int i=0; i<leftDrive.length; i++){
			leftDrive[i] = new Talon(i);
		}
		for(int i=0; i<rightDrive.length; i++){
			int newNumber = i+(number/2);
			rightDrive[newNumber] = new Talon(newNumber);
		}
	}
}