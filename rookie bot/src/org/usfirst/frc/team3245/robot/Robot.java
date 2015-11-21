
/**
 * Basic Tank Drive robot with autonomous
 * By McKay
 * 
 */
package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.*;
public class Robot extends IterativeRobot {
    //buttons on joystick
	private int leftstick = 1, //left thumb joystick if mode light on Logitech controller extinguished
		rightstick = 3,  //right thumb joystick
		fastbutton = 8, //right trigger
		slowbutton = 7,  //left trigger
		outbutton = 6,  //right button above right trigger
		inbutton = 5;  //left button above left trigger
	// custom autonomous timing for lame people who don't use encoders!!
	private double[][] autoSequence = {
			//{leftSpeed, rightSpeed, time}
			{-.75,-.75,150},
			{-.5,.5,20}
				
	};
	private boolean autoRunning;
	private double voltageAdjustment = 1.00;
	double drivePercent = .75;
	private int currentAutoCommand;
	private int autoCount = 0;
	private Talon toteMotor,left1,left2,right1,right2,frontLed,backLed;
	Joystick joy1;
	private double leftSpeed;
	private double rightSpeed;
	private double wheelSpeed;
 
    public void robotInit() {
    	joy1 = new Joystick(0);
    	toteMotor = new Talon(0);
    	left1 = new Talon(3);
    	left2 = new Talon(4);
    	right1 = new Talon(1);
    	right2 = new Talon(2);
    	frontLed = new Talon(9);
    	backLed = new Talon(8);
    	
    	CameraServer.getInstance().startAutomaticCapture("axis-camera");
    }

   
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousInit() {
    	autoCount = 0;
    	currentAutoCommand = 0;
    	drivePercent = .63;
    	autoRunning = true;
    	frontLed.set(1.0);
    	backLed.set(1.0);
    	
    }
    
    public void autonomousPeriodic() {
    	if (autoRunning==true) {
    		ledFlash();
    		leftSpeed = autoSequence[currentAutoCommand][0];
	    	rightSpeed = autoSequence[currentAutoCommand][1];
	    	voltageCorrect();
	    	setMotors();
    		if (autoSequence[currentAutoCommand][2]<autoCount) {
	    		currentAutoCommand++;
	    		autoCount = 0;
	    		if (autoSequence.length==currentAutoCommand) {
	    			autoRunning = false;
	    		}
	    	}
	    	
    		autoCount++;
    	} else {
    		drivePercent = 0;
    		setMotors();
    		ledFlash();
    	}
    	
    }
 
    
    public void teleopInit() {
    	frontLed.set(1.0);
    	backLed.set(1.0);
    }
    /**
     * This function is called periodically during operator control
     * about 50x second
     */
    
    public void teleopPeriodic() {
    	drivePercent = .5; //normal .75
    	//ledFlash(Math.abs((leftSpeed+rightSpeed)/2));
        ledFlash();
    	rightSpeed = joy1.getRawAxis(rightstick);
        leftSpeed= joy1.getRawAxis(leftstick);
        if (joy1.getRawButton(fastbutton)) {
        	drivePercent = .75; //normal 1.0
        }
        else if (joy1.getRawButton(slowbutton)) {
        	drivePercent = .25; //normal .5
        }
        if (joy1.getRawButton(outbutton)) {
        	wheelSpeed = 1;
        }
        else if (joy1.getRawButton(inbutton)){
        	wheelSpeed = -1;
        }
        else {
        	wheelSpeed = 0;
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
    	right1.set(rightSpeed * drivePercent * voltageAdjustment);
        right2.set(rightSpeed * drivePercent * voltageAdjustment);
        left1.set(-leftSpeed * drivePercent * voltageAdjustment);
        left2.set(-leftSpeed * drivePercent * voltageAdjustment);
        toteMotor.set(wheelSpeed);
    }
    public void ledFlash() {
    	//not working :(
    	//ledFlashyness should be between 0 and 1 1 being most flashy 0 being no flash
    	//ledFlashyness = Math.ceil(1000/(ledFlashyness*10));
    	//double ledFlashyness = .8;
    	/*if (ledCounter >= 100) {
			if (ledOn == true) {
				ledOn = false;
				frontLed.set(1.0);
				backLed.set(0.0);
			} else {
				ledOn = true;
				frontLed.set(0.0);
				backLed.set(1.0);
			}
			ledCounter++;
    	}*/
    }
}
