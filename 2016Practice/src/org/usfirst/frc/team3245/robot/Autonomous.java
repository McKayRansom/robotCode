package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous extends Robot {

	private int time, startTime; //standard timer (incremented every period)
	private int counter;
	private int state; //auto state (which thing we're doing)
	private double leftAdjust = 1, rightAdjust = 1,
			straightCorrection = 1, //how much the driveStraight function will try and correct
			//bigger values will correct faster but can over correct
			turningSpeed = .3,
			straightSpeed = .4;
	boolean autoButton1 = SmartDashboard.getBoolean("DB/Button 0", false);
	boolean autoButton2 = SmartDashboard.getBoolean("DB/Button 1", false);
	boolean autoButton3 = SmartDashboard.getBoolean("DB/Button 2", false);
	boolean autoButton4 = SmartDashboard.getBoolean("DB/Button 3", false);
	private int wait = 1, drive = 2, turn = 3, shoot = 4;
	private int[][] autoSequence = { //action and time (or angle) in seconds or degrees
		{wait, 2},
		{turn, 90}
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
    private boolean doCurrentState() { //does the current state's action returns true to move on
    	int arg1 = currentState[1], arg2 = currentState[2];
    	switch(currentState[0]) {
    	case 0: return true; 
    	case 1: return doNothing(arg1); 
    	case 2: return driveStraight(arg1,arg2); 
    	case 3: return turnTo(arg1); 
    	case 4: return true; //placeholder for some sort of shooting code
    	} return false; //should probably never get to here...
    }
    
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
    public boolean doNothing(int duration) {
    	if (duration > (time - startTime)) {
    		return true;
    	}
    	return false;
    }
    
}
