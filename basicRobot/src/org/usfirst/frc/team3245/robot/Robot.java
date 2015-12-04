/**
 * Basic Tank Drive robot with autonomous
 * By McKay
 * 
 */
package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.*;
public class Robot extends IterativeRobot {
    //buttons on joystick
	private int rightstick = 3, 
		leftstick= 1,
		fastbutton = 8, //trigger butttons
		slowbutton =7;
	
	
	
	// custom autonomous timing for lame people who don't use encoders!!
	private double[][] autoSequence = {
			//{leftSpeed, rightSpeed, time}
			{.75,.75,50}			
				
	};
	private boolean autoRunning;
	private double voltageAdjustment = 1.00;
	double drivePercent = .75;
	private int currentAutoCommand;
	private int autoCount = 0;
	private Talon left1,left2,right1,right2;
	Joystick joy1;
	private double leftSpeed;
	private double rightSpeed;
	
    public void robotInit() {
    	joy1 = new Joystick(0);
    	left1 = new Talon(0);
    	left2 = new Talon(1);
    	right1 = new Talon(3);
    	right2 = new Talon(4);
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit() {
    	autoCount = 0;
    	currentAutoCommand = 0;
    	drivePercent = .63;
    	autoRunning = true;
    }
    
    public void autonomousPeriodic() {
    	if (autoRunning==true) {
	    	if (autoSequence[currentAutoCommand][2]<autoCount) {
	    		currentAutoCommand++;
	    		autoCount = 0;
	    		if (autoSequence.length==currentAutoCommand) {
	    			autoRunning = false;
	    		}
	    	}
	    	leftSpeed = autoSequence[currentAutoCommand][0];
	    	rightSpeed = autoSequence[currentAutoCommand][1];
	    	voltageCorrect();
	    	setMotors();
    		autoCount++;
    	} else {
    		drivePercent = 0;
    		setMotors();
    	}
    }
 
    /**
     * This function is called periodically during operator control
     * about 50x second
     */
    
    public void teleopPeriodic() {
    	drivePercent = .75;
    	
        leftSpeed = joy1.getRawAxis(leftstick);
        rightSpeed= joy1.getRawAxis(rightstick);
        if (joy1.getRawButton(fastbutton)) {
        	drivePercent = 1;
        }
        else if (joy1.getRawButton(slowbutton)) {
        	drivePercent = .5;
        }
        voltageCorrect();
        setMotors();
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
    public void voltageCorrect() {
    	voltageAdjustment = 11.2/DriverStation.getInstance().getBatteryVoltage();
    }
    
    public void setMotors() {
    	left1.set(-leftSpeed * drivePercent * voltageAdjustment);
        left2.set(-leftSpeed * drivePercent * voltageAdjustment);
        right1.set(rightSpeed * drivePercent * voltageAdjustment);
        right2.set(rightSpeed * drivePercent * voltageAdjustment);
    
    }
}
