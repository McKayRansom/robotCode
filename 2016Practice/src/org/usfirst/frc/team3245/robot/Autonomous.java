package org.usfirst.frc.team3245.robot;
//Autonomous class that does auto stuff to keep Robot.java clear
//executes states based on SmartDashboard buttons and built-in tables below

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous extends Robot {
	//variables
	private int time, startTime; //standard timer (incremented every period)
	private int counter;
	private int state; //auto state (which thing we're doing)
	private double leftAdjust = 1, rightAdjust = 1,
			straightCorrection = 1.5, //how much the driveStraight function will try and correct
			//bigger values will correct faster but can over correct
			turningSpeed = .15,
			straightSpeed = .85,
			startAngle;
	//get dashboard buttons to set which auto routine
	boolean autoButton1 = SmartDashboard.getBoolean("DB/Button 0", false);
	boolean autoButton2 = SmartDashboard.getBoolean("DB/Button 1", false);
	boolean autoButton3 = SmartDashboard.getBoolean("DB/Button 2", false);
	boolean autoButton4 = SmartDashboard.getBoolean("DB/Button 3", false);
	//constants
	private int wait = 1, drive = 2, turn = 3, shoot = 4, armDown = 5;
	//auto routine (currently only 1)
	private double autoSequence [][] = {
		{wait, 2},
	};
	private double[][] forwardAndBack = { //action and time (or angle) in seconds or degrees
		{drive, 2, -1, 0},
		{wait, 2},
		{drive, 1, .5, 0},
	};
	private double[][] forward = { //action and time (or angle) in seconds or degrees
		{drive, 2, -1, 0},
	};
	private double[][] goal = { //action and time (or angle) in seconds or degrees
		{armDown, 5, .25},
		{drive, 4, -.5, 0},
		{shoot},
	};
	private double[] currentState;
	public Autonomous() {
		if (SmartDashboard.getBoolean("score")) {
			autoSequence = goal;
		} else if (SmartDashboard.getBoolean("forwardAndBack")) {
			autoSequence = forwardAndBack;
		} else if (SmartDashboard.getBoolean("forward")) {
			autoSequence = forward;
		}
		gyro.reset();
		time = 0;
		startTime = 0;
		counter = 0;
		state = 0;		
		currentState = autoSequence[state];
	}
	
    public void periodic() {
    	if (state == -1){
    		return;
    	}
    	counter++;
    	time = counter/50; //about 50x a second
    	if (doCurrentState()) {
    		if (autoSequence.length == (state + 1)) { //we have no more states to do
    			state = -1;
    			setDrive(0,0);
    			return;
    		}
    		state++;
    		startTime = time;
    		currentState = autoSequence[state];
        	startAngle = gyro.getAngle();
    	}
    	SmartDashboard.putNumber("Current State", (int)currentState[0]);
    	SmartDashboard.putNumber("State Time", time - startTime);
    }
    
    //does the current state's action returns true to move on
    private boolean doCurrentState() { 
    	int action = (int)currentState[0];
    	switch(action) {
	    	case 0: return true;
	    	case 1: return doNothing(currentState[1]); 
	    	case 2: return driveStraight(currentState[1], currentState[2], currentState[3]); 
	    	case 3: return turnTo(currentState[1]); 
	    	case 4: return shoot();
	    	case 5: return armDown(currentState[1], currentState[2]);
    	} return false; //will probably never execute this...
    }
    
    //use a Gyro's angle reading to turn the robot to a specified heading
    private boolean turnTo(double targetAngle) {
    	double angle = gyro.getAngle();
    	if (Math.abs(targetAngle-angle) > .2) { //if we are off by > 1 degrees
    		if (targetAngle-angle > 0) {
    			setDrive(turningSpeed, -turningSpeed);
    		} else {
    			setDrive(-turningSpeed, turningSpeed);
    		}
    		return false;
    	} else {
    		return true;
    	}
    }
    
    //uses Gyro to keep the robot going straight forward. Should work better with encoders
    private boolean driveStraight(double duration, double direction, double useGyro) {
    	if (duration < (time - startTime)) {
    		return true;
    	}
    	double angle = gyro.getAngle();
    	double offset = angle - startAngle;
    	double adjustment = 0;
    	if (useGyro == 1) {
    		adjustment = straightCorrection * (offset/90) * direction;//Math.pow(offset,2);
    	}   
    	leftAdjust = adjustment;
   		rightAdjust = -adjustment;
    	setDrive((straightSpeed + rightAdjust) * direction, (straightSpeed + leftAdjust) * direction);
    	return false;
    }
    
    public boolean armDown(double duration, double speed) {
    	
    	if (duration < (time - startTime)) {
    		return true;
    	}
    	setArm(speed);
    	return false;
    }
    public boolean shoot() {
    	int duration = time - startTime;
		if (duration < 20) {
			setFlipper(1);
		} else if (duration > 20 && duration < 40) {
			//wait..
		} else if( duration > 40 && duration < 90) {
			setFlipper(-.5);
		} else if (duration > 90){
			setFlipper(0);
			return true;
		}
    	return false;
    }
    
    //Wait or sleep function basically
    public boolean doNothing(double duration) {
    	setDrive(0,0);
    	if (duration < (time - startTime)) {
    		return true;
    	}
    	return false;
    }
    
}
