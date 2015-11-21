
package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
int leftStick=1, rightStick=3, fastBtn=8, slowBtn=7, frontLift=4, backLift=2, forward=4, backward=2, liftAll=2, grab=5, release=6;
	
	int autoState=0;
	
	private double leftSpeed, rightSpeed, drivePercent, liftSpeed, liftPercent;
	
	private Talon leftMotor1, leftMotor2, rightMotor1, rightMotor2, winchMotorLeft, winchMotorRight, //squeezeFork,
	liftMotorFront, liftMotorBack;
	
	//DigitalInput outLimit, inLimit;
	
	Joystick pilotStick, copilotStick;
 
	
	
	
	
    public void robotInit(){
    pilotStick = new Joystick(0);
    copilotStick = new Joystick(1);
    leftMotor1 = new Talon(0);
    rightMotor1 = new Talon(2);
    leftMotor2=new Talon(1);
    rightMotor2=new Talon(3);
    liftMotorFront = new Talon(4);
    liftMotorBack = new Talon(9);
    //winchMotorLeft = new Talon(4);
    //winchMotorRight = new Talon(6);
    //squeezeFork = new Talon(6);
    //outLimit = new DigitalInput(0);
    //inLimit = new DigitalInput(1);
    
    
    }

    public void autonomousInit(){
    	int[] seq = {1,3,2,3};
    	Timer timer = new Timer(50, seq);
    }
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
