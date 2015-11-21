
package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	int leftStick=1, rightStick=3, fastBtn=8, slowBtn=7, frontLift=4, backLift=2, forward=4, backward=2, liftAll=2, grab=5, release=6;
	
	int timer=0, autoState=0;
	
	double[][] autoCommands;
	double[] autoTimes;
	
	myTimer autoTimer;
	
	private double leftSpeed, rightSpeed, drivePercent, liftSpeed, liftPercent, 
	debugLeftSpeed, debugRightSpeed, winchSpeed, winchPercent;
	
	private Talon leftMotor, rightMotor, leftGrabber, rightGrabber, winch,
	liftMotorFront, liftMotorBack;
	
	//DigitalInput outLimit, inLimit;
	
	Joystick pilotStick, copilotStick, winchStick, debugStick; //Because I got annoyed
 
    public void robotInit(){
    pilotStick = new Joystick(0);
    copilotStick = new Joystick(1);
    winchStick = new Joystick(2);
    debugStick = new Joystick(4);
    leftMotor = new Talon(1);
    rightMotor = new Talon(0);
    liftMotorFront = new Talon(6);
    liftMotorBack = new Talon(2);
    leftGrabber = new Talon(7);
    rightGrabber = new Talon(5);
    winch = new Talon(8);
    //squeezeFork = new Talon(6);
    //outLimit = new DigitalInput(0);
    //inLimit = new DigitalInput(1);
    
    
    }
    public void autonomousInit(){
zeroMotorSpeeds();
    	
    	boolean autoButton1 = SmartDashboard.getBoolean("DB/Button 0", false);
    	boolean autoButton2 = SmartDashboard.getBoolean("DB/Button 1", false);
    	boolean autoButton3 = SmartDashboard.getBoolean("DB/Button 2", false);
    	boolean autoButton4 = SmartDashboard.getBoolean("DB/Button 3", false);
    	
    	if(autoButton1==true){
    		autoState=1;	
    		//time, left speed, right speed, front lift speed, back lift speed
    		double[][] autoSeq = {
    				{0.25, 0.7, -0.7,  0,  0,  1,  1,  0,  }, //drive forward, drop can grabbers
    				{0.5,  0,    0,    0,  0,  1,  1,  0,  }, //stop
    				{0.75, 0,    0,    0,  0, -1, -1,  0.5 },
    				{5,    0,    0,    0,  0,  0,  0,  0.25},
    				{4,   -0.7,  0.7,  0,  0,  0,  0,  0.5,},
    				{4.5,  0,    0,    0,  0,  0,  0,  0,  },
    				};
    		autoCommands = new double[autoSeq.length][autoSeq[0].length];
    		
    		for(int i=0; i<autoSeq.length; i++){
    			for(int j=0; j<autoSeq[0].length; j++){
    				autoCommands[i][j] = autoSeq[i][j];
    			}
    		}
    	}
    	else if(autoButton2==true){
    		autoState=2;
    		double[][] autoSeq = {
    				{2.00,  0,     0,     0,   1  }, //Lift up first can
    				{2.00,  0.20, -0.20,  0,   0.4}, //Drive forward
    				{1.00, -0,     0,     0.8, 0  }, //Lift up next can
    				{2.00, -0.1,   0.1,   0.6, 0  }, //Drive back
    				{2.00,  0.27,  0.27,  0,   0  }, //Turn 90 degrees right
    				{3.00,  0.46, -0.46,  0,   0  }, //Drive forward
    				{1.00,  0.3,  -0.3,   0,   0  }, //Slow down
    				{1.30,  0.35,   0.35, 0,   0  }, //Turn 90 degrees to the right
    				{0.20,  0,     0,     0,   0  }  //Stop
    				};
    		autoCommands = new double[autoSeq.length][autoSeq[0].length];
    		
    		for(int i=0; i<autoSeq.length; i++){
    			for(int j=0; j<autoSeq[0].length; j++){
    				autoCommands[i][j] = autoSeq[i][j];
    			}
    		}
    	}
    	else if(autoButton3==true){
    		autoState=3;
    		double[][] autoSeq = {
    				{2.00, 0,    0,   0,   1  }, //Pick up first can
    				{2.00, 0.2, -0.2, 0,   0.4}, //Drive forward
    				{3.00, 0,    0,   0.8, 0  }, //Pick up next can
    				{8.00, 0,    0,   0,   0, }, //Stop
    				};
    		autoCommands = new double[autoSeq.length][autoSeq[0].length];
    		
    		for(int i=0; i<autoSeq.length; i++){
    			for(int j=0; j<autoSeq[0].length; j++){
    				autoCommands[i][j] = autoSeq[i][j];
    			}
    		}
    	}
    	else if(autoButton4==true){
    		autoState=4;
    		double[][] autoSeq = {
    				{2.00, 0,     0,    0,  1}, //Pick up first can
    				{1.50, 0.2,  -0.2,  0,  0}, //Drive forward
    				{2.50, 0.3,  -0.3,  1,  0}, //Pick up next can while driving forward
    				{2.00, 0.20, -0.20, 0.4,0}, //Drive forward, stop lifting can
    				{1.00, 0.4,   0.4,  0,  0}, //Turn 90 degrees to the right
    				{3.00, 0.6,  -0.6,  0,  0}, //Drive forward
    				{1.02, 0.4,   0.4,  0,  0}, //Turn 90 degrees to the right
    				{1.98, 0,     0,    0,  0}  //Stop
    				};
    		autoCommands = new double[autoSeq.length][autoSeq[0].length];
    		
    		for(int i=0; i<autoSeq.length; i++){
    			for(int j=0; j<autoSeq[0].length; j++){
    				autoCommands[i][j] = autoSeq[i][j];
    			}
    		}
    	}
    	else if(autoButton1==true && autoButton2==true){
    		autoState=5;
    		double[][] autoSeq = {
    				{2.25, 0.45, -0.45, 0,  1}, //Pick up first can
    				{12.74,0,     0,    0,  0}, //Drive forward
    				};
    		autoCommands = new double[autoSeq.length][autoSeq[0].length];
    		
    		for(int i=0; i<autoSeq.length; i++){
    			for(int j=0; j<autoSeq[0].length; j++){
    				autoCommands[i][j] = autoSeq[i][j];
    			}
    		}
    	}
    	else{
    		autoState=0;
    		autoCommands = new double[15][5];
    		for(int i=0; i<autoCommands.length; i++){
    			for(int j=0; j<autoCommands[0].length; j++){
    				autoCommands[i][j]=0;
    			}
    		}	
    	}
    	autoTimes = new double[autoCommands.length];
    	for(int j=0; j<autoCommands.length-1; j++){
    		autoTimes[j] = autoCommands[j][0];
    	}
    	autoTimer = new myTimer(50,autoTimes);
    }
    
    public void autonomousPeriodic() {
    	//0, do nothing
    	//1, That one that we do when we're useless
    	//2, pick up two cans and attempt to drive into auto zone
    	//3, pick up one can, drive forward, pick up next can
    	//4, pick up two cans and push third can into auto zone
    	autoTimer.run();
    	
    	int currentCommand = autoTimer.getOrder();
    	if(autoState>0){
    		leftMotor.set(autoCommands[currentCommand][1]);
    		rightMotor.set(autoCommands[currentCommand][2]);
    		liftMotorFront.set(autoCommands[currentCommand][3]);
    		liftMotorBack.set(autoCommands[currentCommand][4]);
    		if(autoState==1){
    			leftGrabber.set(autoCommands[currentCommand][5]);
    			rightGrabber.set(autoCommands[currentCommand][6]);
    			winch.set(autoCommands[currentCommand][7]);
    		}
    	}
    	else{
    		zeroMotorSpeeds();
    	}
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
        
        
        
        //Lifting
        
        
        
        
        if(copilotStick.getRawButton(fastBtn)){
    		liftPercent = 1;
    	}
        else if(copilotStick.getRawButton(slowBtn)){
    		liftPercent = 0.3;
    	}
        else{
        	liftPercent = 0.5;
        }
        liftSpeed=copilotStick.getRawAxis(leftStick);
		
        
        if(debugStick.getRawButton(6)){
        	liftMotorBack.set(1);
        }
        else if(debugStick.getRawButton(8)){
        	liftMotorBack.set(-1);
        }
        else if(copilotStick.getRawButton(frontLift)){
    		liftMotorFront.set((-liftSpeed)*liftPercent);
    	}
    	else if(debugStick.getRawButton(5)){
        	liftMotorFront.set(1);
        }
        else if(debugStick.getRawButton(7)){
        	liftMotorFront.set(-1);
        }
    	else if(copilotStick.getRawButton(backLift)){
    		liftMotorBack.set((-liftSpeed)*liftPercent);
    	}
    	else if(copilotStick.getRawButton(1)){
    		winch.set(liftSpeed*liftPercent);
    	}
    	else{
    		liftMotorBack.set(0);
    		liftMotorFront.set(0);
    	}
    	
    	//Grabbing
        if(winchStick.getRawButton(8)){
        	winchPercent=0.6;
        }
        else if(winchStick.getRawButton(7)){
        	winchPercent=0.4;
        }
        else{
        	winchPercent=1;
        }
        
        winchSpeed = winchStick.getRawAxis(leftStick);
        
        if(winchStick.getRawButton(1)){
        	leftGrabber.set(winchSpeed);
        }
        else if(winchStick.getRawButton(3)){
        	rightGrabber.set(winchSpeed);
        }
        else if(winchStick.getRawButton(2)){
        	winch.set(winchSpeed);
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