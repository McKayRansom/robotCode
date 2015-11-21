package org.usfirst.frc.team3245.robot;
import edu.wpi.first.wpilibj.*;

public class Drive {
	//Change MC types according to Robot
	Talon[] leftDrive;
	Talon[] rightDrive;
	int fastButton=-1, slowButton=-1;
	double drivePercent, fastPercent, slowPercent, normPercent;
	
	public Drive(int fastB, double fastP, int slowB, double slowP, double normP, int[] leftPortNumbers, int[] rightPortNumbers){
		
		leftDrive = new Talon[leftPortNumbers.length];
		rightDrive = new Talon[rightPortNumbers.length];
		
		for(int port:leftPortNumbers){
			leftDrive[port] = new Talon(leftPortNumbers[port]);
		}
		for(int port:rightPortNumbers){
			rightDrive[port] = new Talon(rightPortNumbers[port]);
		}
		
		fastButton=fastB;
		slowButton=slowB;
		fastPercent=fastP;
		slowPercent=slowP;
		normPercent=normP;
	}
	
	public void setDrive(double leftStick, double rightStick){
		
	}
}
