
package org.usfirst.frc.team3245.robot;
import edu.wpi.first.wpilibj.*;

public class Drive {
	Input driveController;
	Talon[] leftDrive;
	Talon[] rightDrive;
	
	public Drive(Input controller, boolean shifting, int motorNumber){
		driveController = controller;
		if(motorNumber==2){
			leftDrive = new Talon[1];
			leftDrive[1] = new Talon(0);
			rightDrive = new Talon[1];
			rightDrive[1] = new Talon(1);
		}
		else if(motorNumber==4){
			leftDrive = new Talon[2];
			leftDrive[1] = new Talon(0);
			leftDrive[2] = new Talon(1);
			rightDrive = new Talon[2];
			rightDrive[1] = new Talon(2);
			rightDrive[2] = new Talon(3);
		}
		else if(motorNumber==4){
			leftDrive = new Talon[3];
			for(int i=0; i<=2; i++)
				leftDrive[i] = new Talon(i);
			
			rightDrive = new Talon[2];
			for(int i=3; i<=5; i++){
				rightDrive[i] = new Talon(i);
			}
		}
		
	}
	public void tankDrive(){
		
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