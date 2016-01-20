package org.usfirst.frc.team3245.robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.interfaces.Gyro;
public class Robot extends IterativeRobot {
	// Buttons
	int leftStick = 2, rightStick = 4, fastBtn = 8, slowBtn = 7;
	// Motors!
	private Talon leftMotor1, leftMotor2, leftMotor3, rightMotor1, rightMotor2,
			rightMotor3, shootMotor1, shootMotor2, floorMotor;
	protected Gyro gyro;
	// Current Motor Speeds!
	double leftSpeed, rightSpeed, shootSpeed1, shootSpeed2, floorSpeed;
	// Controllers!
	Input pilotStick, coPilotStick;
	// Compressor!
	//Compressor mainComp;
	public void robotInit() {
		// Motors!
		gyro = new AnalogGyro(1);
		gyro.reset();
		rightMotor1 = new Talon(3);
		rightMotor2 = new Talon(4);
		//rightMotor3 = new Talon(5);
		leftMotor1 = new Talon(0);
		leftMotor2 = new Talon(1);
		//leftMotor3 = new Talon(2);
		shootMotor1 = new Talon(6);
		shootMotor2 = new Talon(7);
		floorMotor = new Talon(8);
		
		// Joysticks!
		pilotStick = new Input(0);
		coPilotStick = new Input(1);

		// Compressor!
		//mainComp = new Compressor(0);

	}
	
	public void setDrive(double right, double left) {
		leftMotor1.set(left);
		leftMotor2.set(left);
		//leftMotor3.set(left);
		rightMotor1.set(right);
		rightMotor2.set(right);
		//rightMotor3.set(right);
	}

}
