package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	int leftStick=1, rightStick=3, fastBtn=8, slowBtn=7, frontLift=4, backLift=2, forward=4, backward=2, liftAll=2, grab=5, release=6;
	
	int autoState=0;
	
	double[][] autoCommands;
	double[] autoTimes;
	
	private double leftSpeed, rightSpeed, drivePercent, liftSpeed, liftPercent;
	
	private Talon leftMotor1, leftMotor2, rightMotor1, rightMotor2, winchMotorLeft, winchMotorRight, //squeezeFork,
	liftMotorFront, liftMotorBack;
	
	//DigitalInput outLimit, inLimit;
	
	Joystick pilotStick, copilotStick;
 
	myTimer autoTimer;
	
	
	
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
    	zeroMotorSpeeds();
    	
    	boolean autoButton1 = SmartDashboard.getBoolean("DB/Button 0", false);
    	boolean autoButton2 = SmartDashboard.getBoolean("DB/Button 1", false);
    	boolean autoButton3 = SmartDashboard.getBoolean("DB/Button 2", false);
    	boolean autoButton4 = SmartDashboard.getBoolean("DB/Button 3", false);
    	
    	if(autoButton1==true){
    		autoState=1;	//time, left speed, right speed, front lift speed, back lift speed
    		double[][] autoSeq = {
    				{2.00, 0,     0,    0,   1  }, //lift up first can
    				{2.00, 0.25, -0.25, 0,   0.4}, //drive forward
    				{3.00, 0,     0,    0.8, 0  }, //pick up next can
    				{1.00, 0.4,   0.4,  0,   0  }, //turn right
    				{2.00, 0.7,  -0.7,  0,   0  }, //drive forward
    				{1.00,-0.4,  -0.4,  0,   0  }, //turn left
    				{2.00, 0.4,  -0.4,  0,   0  }, //drive forward
    				{2.00, 0,     0,    0,   0  }  //stop
    				};
    		autoCommands = new double[autoSeq.length][autoSeq[0].length];
    		
    		for(int i=0; i<=autoSeq.length; i++){
    			for(int j=0; j<=autoSeq[0].length; j++){
    				autoCommands[i][j] = autoSeq[i][j];
    			}
    		}
    	}
    	else if(autoButton2==true){
    		autoState=2;
    		double[][] autoSeq = {
    				{2.00,  0,     0,     0,   1  },
    				{2.00,  0.25, -0.25,  0,   0.4},
    				{3.00,  0,     0,     0.8, 0  },
    				{2.00,  0.25,  0.25,  0,   0  },
    				{3.00,  0.6,  -0.6,   0,   0  },
    				{1.00,  0.3,  -0.3,   0,   0  },
    				{1.30,  0.3,   0.3,   0,   0  },
    				{0.20,  0,     0,     0,   0  }
    				};
    		autoCommands = new double[autoSeq.length][autoSeq[0].length];
    		
    		for(int i=0; i<=autoSeq.length; i++){
    			for(int j=0; j<=autoSeq[0].length; j++){
    				autoCommands[i][j] = autoSeq[i][j];
    			}
    		}
    	}
    	else if(autoButton3==true){
    		autoState=3;
    		double[][] autoSeq = {
    				{2.00, 0,    0,   0,   1  },
    				{2.00, 0.2, -0.2, 0,   0.4},
    				{3.00, 0,    0,   0.8, 0  },
    				{8.00, 0,    0,   0,   0, },
    				};
    		autoCommands = new double[autoSeq.length][autoSeq[0].length];
    		
    		for(int i=0; i<=autoSeq.length; i++){
    			for(int j=0; j<=autoSeq[0].length; j++){
    				autoCommands[i][j] = autoSeq[i][j];
    			}
    		}
    	}
    	else if(autoButton4==true){
    		autoState=4;
    		double[][] autoSeq = {
    				{2.00, 0,     0,    0,  1},
    				{2.00, 0.2,  -0.2,  0,  0},
    				{3.00, 0.3,  -0.3,  1,  0},
    				{2.00, 0.25, -0.25, 0,  0},
    				{1.00, 0.4,   0.4,  0,  0},
    				{3.00, 0.6,  -0.6,  0,  0},
    				{1.02, 0.4,   0.4,  0,  0},
    				{0.98, 0,     0,    0,  0}
    				};
    		autoCommands = new double[autoSeq.length][autoSeq[0].length];
    		
    		for(int i=0; i<=autoSeq.length; i++){
    			for(int j=0; j<=autoSeq[0].length; j++){
    				autoCommands[i][j] = autoSeq[i][j];
    			}
    		}
    	}
    	else{
    		autoState=0;
    		autoCommands = new double[15][5];
    	}
    	autoTimes = new double[autoCommands.length];
    	for(int j=0; j<=autoCommands.length; j++){
    		autoTimes[j] = autoCommands[j][0];
    	}
    	
    	autoTimer = new myTimer(50, autoTimes);
    }

    public void autonomousPeriodic() {
    	//0, do nothing
    	//1, pick up two cans with front and back lifter and drive towards human loader station (far staging zone)
    	//2, pick up two cans and attempt to drive into auto zone
    	//3, pick up one can, drive forward, pick up next can
    	//4, pick up two cans and push third can into auto zone
    	
    	autoTimer.run();
    	
    	int currentCommand = autoTimer.getOrder();
    	
    	leftMotorset(autoCommands[currentCommand][1]);
    	rightMotorset(autoCommands[currentCommand][2]);
    	liftMotorFront.set(autoCommands[currentCommand][3]);
    	liftMotorBack.set(autoCommands[currentCommand][4]);
    }
    public void teleopPeriodic() {
    	
    	//Driving
    	drivePercent = 0.6;
    	liftPercent = 0.7;
    	
    	
    	if(pilotStick.getRawButton(fastBtn)){
    		drivePercent = 1;
    	}
    	if(pilotStick.getRawButton(slowBtn)){
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
        	leftSpeed=pilotStick.getRawAxis(leftStick)*drivePercent;
        	rightSpeed=pilotStick.getRawAxis(rightStick)*drivePercent;
    	}
        
        leftMotorset(-leftSpeed);
        rightMotorset(rightSpeed);
        
        
        
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
		
    	if(copilotStick.getRawButton(frontLift)){
    		liftMotorFront.set((-liftSpeed)*liftPercent);
    	}
    	else if(copilotStick.getRawButton(backLift)){
    		liftMotorBack.set((-liftSpeed)*liftPercent);
    	}
    	else if(copilotStick.getRawButton(liftAll)){
    		liftMotorFront.set(-liftSpeed*liftPercent);
    		liftMotorBack.set((-liftSpeed)*liftPercent);
    	}
    	else{
    		liftMotorBack.set(0);
    		liftMotorFront.set(0);
    	}
    	
    	//if(copilotStick.getRawButton(grab)){
    		//winchMotorRight.set(1);
    	//}
    	//else if(copilotStick.getRawButton(release)){
    		//winchMotorRight.set(-1);
    	//}
    	//else{
    		//winchMotorRight.set(0);
    	//}
    	
    	//int initiate=0;
    	//boolean forkState=false;
    	
    	
    	//Grabbing (Limit Switch)
    	//if(copilotStick.getRawButton(grab) && initiate==0 && forkState==false){
    		//initiate=1;
    		//while(inLimit.get()==true){
    			//squeezeFork.set(0.6);
    		//}
    		//if(inLimit.get()==false){
    			//initiate=0;
    			//forkState=true;
    		//}
    	//}
    	//else if(copilotStick.getRawButton(grab) && initiate==0 && forkState==true){
    		//initiate=1;
    		//while(outLimit.get()==true){
    			//squeezeFork.set(0.6);
    		//}
    		//if(outLimit.get()==false){
    			//initiate=0;
    			//forkState=false;
    		//}
    	//}
    	//else{
    		//squeezeFork.set(0);
    	//}
    	
    	
    	//Grabbing (Simple)
    	//if(copilotStick.getRawButton(grab)){
    		//squeezeFork.set(0.6);
    	//}
    	//else{
    			//squeezeFork.set(0.0);
    		//}
    	
    	//if(copilotStick.getRawButton(release)){
    		//squeezeFork.set(-0.6);
    		//}
    	//}
    	
        
        //Winching
   
    }
    
    public void zeroMotorSpeeds() {
    	 leftMotorset(0);
    	 rightMotorset(0);
    	 liftMotorFront.set(0);
    	 liftMotorBack.set(0);
    	 //winchMotorLeft.set(0);
    	 //winchMotorRight.set(0);
    	 //squeezeFork.set(0);
    }
    public void leftMotorset(double speed){
    	leftMotor1.set(speed);
    	leftMotor2.set(speed);
    }
    public void rightMotorset(double speed){
    	rightMotor1.set(speed);
    	rightMotor2.set(speed);
    }
    public void testPeriodic() {
    
    }
    
}
