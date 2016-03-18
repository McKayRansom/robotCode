//base class: contains all output objects and base code
//also contains basic helpers like setDrive()
package org.usfirst.frc.team3245.robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class Robot extends IterativeRobot {

	// Motors!
	private static CANTalon leftMotor1, leftMotor2, leftMotor3, rightMotor1, rightMotor2, rightMotor3;
	private static Talon intakeMotor, armMotor, flipperMotor;//, shootMotor2, floorMotor, leftMotor3;
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
		rightMotor1 = new CANTalon(4);
		rightMotor2 = new CANTalon(5);
		rightMotor3 = new CANTalon(6);
		leftMotor1 = new CANTalon(1);
		leftMotor2 = new CANTalon(2);
		leftMotor3 = new CANTalon(3);
		//shootMotor1 = new Talon(0);
		intakeMotor = new Talon(1);
		flipperMotor = new Talon(2);
		armMotor = new Talon(3);
		
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
		SmartDashboard.putNumber("Angle", gyro.getAngle());
    	SmartDashboard.putString("Gyro", Double.toString(gyro.getAngle()));
		leftMotor1.set(left);
		leftMotor2.set(left);
		leftMotor3.set(left);
		rightMotor1.set(-right);
		rightMotor2.set(-right);
		rightMotor3.set(-right);
	}
	
	public static void setArm(double speed) {
		armMotor.set(speed);
	}
	
	
	public static void setIntake(double speed) {
		intakeMotor.set(speed);
	}
	
	public static void setFlipper(double speed) {
		flipperMotor.set(speed);
	}
}