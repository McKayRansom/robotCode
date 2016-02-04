//base class: contains all output objects and base code
//also contains basic helpers like setDrive()
package org.usfirst.frc.team3245.robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class Robot extends IterativeRobot {

	// Motors!
	private static Talon leftMotor1, leftMotor2, leftMotor3, rightMotor1, rightMotor2, rightMotor3;
			//unused rightMotor3, shootMotor1, shootMotor2, floorMotor, leftMotor3;
	public static Gyro gyro;
	// Current Motor Speeds!
	public static double leftSpeed, rightSpeed, shootSpeed1, shootSpeed2, floorSpeed;
	Teleop tele;
	Autonomous auto;
	// Compressor!
	//Compressor mainComp;
	public void robotInit() {
		// Motors!
		gyro = new AnalogGyro(1);
		gyro.calibrate();
		rightMotor1 = new Talon(0);
		//rightMotor2 = new Talon(1);
		//rightMotor3 = new Talon(2);
		leftMotor1 = new Talon(1);
		//leftMotor2 = new Talon(4);
		//leftMotor3 = new Talon(5);
		//shootMotor1 = new Talon(6);
		//shootMotor2 = new Talon(7);
		//floorMotor = new Talon(8);
		
		// Joysticks!
		//pilotStick = new Joystick(0);
		//coPilotStick = new Joystick(1);
		
		// Compressor!
		//mainComp = new Compressor(0);
	}
	public void teleopInit() {
		//gyro.calibrate();
		gyro.reset();
		tele = new Teleop();
	}
	
    public void teleopPeriodic() {
    	//SmartDashboard.putString("DB/String 0", Double.toString(gyro.getAngle()));
    	//mainComp.setClosedLoopControl(true);
		tele.periodic();
		setDrive(rightSpeed, leftSpeed);
    }
    
    public void autonomousInit() {
    	auto = new Autonomous();
    }
    
    public void autonomousPeriodic() {
    	auto.periodic();
    }
    
    public void testInit() {
    	gyro.calibrate();
    }
    
    public void testPeriodic() {
    	
    }
	
	public static void setDrive(double right, double left) {
		SmartDashboard.putString("DB/String 0", Double.toString(gyro.getAngle()));
    	SmartDashboard.putString("DB/String 1", Double.toString(gyro.getRate()));
		leftMotor1.set(left);
		//leftMotor2.set(left);
		//leftMotor3.set(left);
		rightMotor1.set(-right);
		//rightMotor2.set(-right);
		//rightMotor3.set(-right);
	}
}