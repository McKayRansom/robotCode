
package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	int leftStick=1, rightStick=3, fastBtn=8, slowBtn=7, frontLift=4, backLift=2, 
			forward=4, backward=2, liftAll=2, grab=5, release=6;
	
	int timer=0, autoState=0;
	
	double[][] autoCommands;
	double[] autoTimes;
	
	//myTimer autoTimer;
	
	private double leftSpeed, rightSpeed, drivePercent, liftSpeed, liftPercent, 
	debugLeftSpeed, debugRightSpeed, winchSpeed, winchPercent;
	
	private Talon leftMotor, rightMotor, leftGrabber, rightGrabber, winch,
	liftMotorFront, liftMotorBack, occupierFront, occupierMid, occupierBack;
	
	//DigitalInput outLimit, inLimit;
	
	Joystick pilotStick, copilotStick, winchStick, debugStick; //Because I got annoyed
 
    public void robotInit(){
    pilotStick = new Joystick(0);
    copilotStick = new Joystick(1);
    winchStick = new Joystick(2);
    debugStick = new Joystick(4);
    leftMotor = new Talon(1);
    rightMotor = new Talon(0);
    occupierFront = new Talon(2);
    occupierMid = new Talon(3);
    occupierBack = new Talon(4);
    }
    public void autonomousPeriodic() {

    }

    

    
    public void teleopPeriodic() {
    	
    	//Driving
    	drivePercent = 0.6;
    	liftPercent = 0.7;
    	debugLeftSpeed = 0;
    	debugRightSpeed = 0; 
    	
    	
    	if(pilotStick.getRawButton(fastBtn)){
    		drivePercent = 1;
    	}
    	else if(pilotStick.getRawButton(slowBtn)){
    		drivePercent = .3;
    	}
    	debugLeftSpeed = debugStick.getRawAxis(leftStick)*0.3;
    	debugRightSpeed = debugStick.getRawAxis(rightStick)*0.3;
    	
    	if(pilotStick.getRawButton(forward)){
    		leftSpeed=-0.4;
    		rightSpeed=0.4;
    	}
    	else if(pilotStick.getRawButton(backward)){
    		leftSpeed=-0.4;
    		rightSpeed=-0.4;
    	}
    	else{
    		
        	leftSpeed=(pilotStick.getRawAxis(leftStick)*drivePercent)+debugLeftSpeed;
        	rightSpeed=pilotStick.getRawAxis(rightStick)*drivePercent+debugRightSpeed;
    	}
        
        leftMotor.set(-leftSpeed);
        rightMotor.set(rightSpeed);
        
        
        
    //Occupier
        if(copilotStick.getRawButton(5)){
        	occupierFront.set(0.2);
        }
        
        if(copilotStick.getRawButton(7)){
        	occupierFront.set(-0.2);
        }
        
        if(copilotStick.getRawButton(6)){
        	occupierMid.set(0.2);
        }
        
        if(copilotStick.getRawButton(8)){
        	occupierMid.set(-0.2);
        }
        
        if(copilotStick.getRawButton(3)){
        	occupierBack.set(0.2);
        }
        
        if(copilotStick.getRawButton(1)){
        	occupierBack.set(-0.2);
        }
        
        
    }
    
    public void zeroMotorSpeeds() {
    	 leftMotor.set(0);
    	 rightMotor.set(0);
    	 liftMotorFront.set(0);
    	 liftMotorBack.set(0);
    	 //winchMotorLeft.set(0);
    	 //winchMotorRight.set(0);
    	 //squeezeFork.set(0);
    }
    public void testPeriodic() {
    
    }
    
}