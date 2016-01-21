package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous extends Robot {
	//variables
	private int time, startTime; //standard timer (incremented every period)
	private int counter;
	private int state; //auto state (which thing we're doing)
	private double leftAdjust = 1, rightAdjust = 1,
			straightCorrection = 1, //how much the driveStraight function will try and correct
			//bigger values will correct faster but can over correct
			turningSpeed = .3,
			straightSpeed = .4;
	//get dashboard buttons to set which auto routine
	boolean autoButton1 = SmartDashboard.getBoolean("DB/Button 0", false);
	boolean autoButton2 = SmartDashboard.getBoolean("DB/Button 1", false);
	boolean autoButton3 = SmartDashboard.getBoolean("DB/Button 2", false);
	boolean autoButton4 = SmartDashboard.getBoolean("DB/Button 3", false);
	//constants
	private int wait = 1, drive = 2, turn = 3, shoot = 4;
	//auto routine (currently only 1)
	private int[][] autoSequence = { //action and time (or angle) in seconds or degrees
		{wait, 2},
		{turn, 90},
	};
	private int[] currentState = {};
	public void autonomousInit() {
		gyro.reset();
		time = 0;
		startTime = 0;
		counter = 0;
		state = 0;		
	}
	
    public void autonomousPeriodic() {
    	counter++;
    	time = counter/50; //about 50x a second
    	if (doCurrentState()) {
    		state++;
    		startTime = time;
    		currentState = autoSequence[state];
        	
    	}
    }
    
    //does the current state's action returns true to move on
    private boolean doCurrentState() { 
    	int arg1 = currentState[1], arg2 = currentState[2];
    	switch(currentState[0]) {
    	case 0: return true; 
    	case 1: return doNothing(arg1); 
    	case 2: return driveStraight(arg1,arg2); 
    	case 3: return turnTo(arg1); 
    	case 4: return true; //placeholder for some sort of shooting code
    	} return false; //will probably never execute this...
    }
    
    //use a Gyro's angle reading to turn the robot to a specified heading
    private boolean turnTo(double targetAngle) {
    	double angle = gyro.getAngle();
    	if (Math.abs(targetAngle-angle) > 3) { //if we are off by > 3 degrees
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
    
    //currently uses Gyro to keep the robot going straight forward. Should work better with encoders
    private boolean driveStraight(double startAngle, int duration) {
    	if (duration > (time - startTime)) {
    		return true;
    	}
    	double angle = gyro.getAngle();
    	if (angle-startAngle < 0) {
    		leftAdjust -= straightCorrection;
    		rightAdjust += straightCorrection;
    	} else {
    		leftAdjust += straightCorrection;
    		rightAdjust -= straightCorrection;
    	}
    	setDrive(straightSpeed * rightAdjust, straightSpeed * leftAdjust);
    	return false;
    }
    
    //Wait or sleep function basically
    public boolean doNothing(int duration) {
    	if (duration > (time - startTime)) {
    		return true;
    	}
    	return false;
    }
    
}
