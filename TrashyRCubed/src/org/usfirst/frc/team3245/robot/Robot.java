
package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.*;


public class Robot extends IterativeRobot {
	int leftStick=1, rightStick=3, fastBtn=8, slowBtn=7, frontLift=4, backLift=2, 
			forward=4, backward=2, liftAll=2, grab=5, release=6;
	
	int timer=0, autoState=0, shooterTime = 0;
	
	double[][] autoCommands;
	double[] autoTimes;
	
	//myTimer autoTimer;
	
	private double leftSpeed, rightSpeed, drivePercent,
	debugLeftSpeed, debugRightSpeed;
	
	private Talon leftMotor, rightMotor, occupierFront, occupierMid, occupierBack;
	
	private DoubleSolenoid shooter;
	//DigitalInput outLimit, inLimit;
	
	Joystick pilotStick, copilotStick;
 
    public void robotInit(){
	    pilotStick = new Joystick(0);
	    copilotStick = new Joystick(1);
	    leftMotor = new Talon(1);
	    rightMotor = new Talon(0);
	    occupierFront = new Talon(2);
	    occupierMid = new Talon(3);
	    occupierBack = new Talon(4);
	    shooter = new DoubleSolenoid(0, 1);
    }
    public void autonomousPeriodic() {

    }

    

    
    public void teleopPeriodic() {
    	
    	//Driving
    	drivePercent = 0.6;
    	debugLeftSpeed = 0;
    	debugRightSpeed = 0; 
    	
    	
    	if(pilotStick.getRawButton(fastBtn)){
    		drivePercent = 1;
    	}
    	else if(pilotStick.getRawButton(slowBtn)){
    		drivePercent = .3;
    	}
   
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
        if(copilotStick.getRawButton(5)){ //left Bumper
        	occupierMid.set(0.2);
        } else if(copilotStick.getRawButton(6)){ //right bumper
        	occupierMid.set(-0.2);
        } else {
        	occupierMid.set(0);
        }
        
        if(copilotStick.getRawButton(7)){ //left trigger
        	occupierFront.set(0.2);
        } else if(copilotStick.getRawButton(8)){ //right trigger
        	occupierFront.set(-0.2);
        } else {
        	occupierFront.set(0);
        }
        
        if(copilotStick.getRawButton(1)){ // O or B
        	occupierBack.set(0.2);
        } else if(copilotStick.getRawButton(3)){ // square or X
        	occupierBack.set(-0.2);
        } else {
        	occupierBack.set(0);
        } 
        if(copilotStick.getRawButton(2)){
        	if(shooterTime==0) { //if the button was just pushed
        		shooter.set(DoubleSolenoid.Value.kReverse);
        	} else if (shooterTime > 12) { //shoot only for 12 cycles
        		shooter.set(DoubleSolenoid.Value.kForward);
        	}
        	shooterTime++;
        }
        else{ 
        	shooter.set(DoubleSolenoid.Value.kForward);
        	shooterTime = 0;
        }
        
    }
    
    public void zeroMotorSpeeds() {
    	 leftMotor.set(0);
    	 rightMotor.set(0);
//    	 liftMotorFront.set(0);
//    	 liftMotorBack.set(0);
    	 //winchMotorLeft.set(0);
    	 //winchMotorRight.set(0);
    	 //squeezeFork.set(0);
    }
    public void testPeriodic() {
    
    }
    
}