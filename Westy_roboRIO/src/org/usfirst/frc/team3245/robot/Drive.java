package org.usfirst.frc.team3245.robot;
import edu.wpi.first.wpilibj.*;

public class Drive {
	//Change MC types according to Robot
	Talon[] leftDrive;
	Talon[] rightDrive;
	int fastButton=-1, slowButton=-1;
	double drivePercent, fastPercent, slowPercent, normPercent;
	
	
	public Drive(int[] leftPortNumbers, int[] rightPortNumbers){
		
		leftDrive = new Talon[leftPortNumbers.length];
		rightDrive = new Talon[rightPortNumbers.length];

		for(int port:leftPortNumbers){
			leftDrive[port] = new Talon(leftPortNumbers[port]);
		}
		for(int port:rightPortNumbers){
			rightDrive[port] = new Talon(rightPortNumbers[port]);
		}
	}
	public void drivePercentControl(Input drive,int fastB, double fastP, int slowB, double slowP, double normP){
		drive.cycleUpdate();

		fastButton=fastB;
		slowButton=slowB;
		fastPercent=fastP;
		slowPercent=slowP;
		normPercent=normP;
		
		if(drive.isButtonDown(fastB))
			drivePercent = fastPercent;
		else if(drive.isButtonDown(slowB))
			drivePercent = slowPercent;
		else
			drivePercent = normPercent;
	}
	
	
	
	public void setDrive(double leftStick, double rightStick){
		for(Talon MC:leftDrive){
			MC.set(leftStick*drivePercent);
		}
		for(Talon MC:rightDrive){
			MC.set(-rightStick*drivePercent);
		}
		
	}
}
