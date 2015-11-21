
package org.usfirst.frc.team3245.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	int leftStick=1, rightStick=3, fastBtn=8, slowBtn=7, frontLift=4, backLift=2, forward=4, backward=2, liftAll=2, grab=5, release=6;
	
	int timer=0, autoState=0;
	
	private double leftSpeed, rightSpeed, drivePercent, liftSpeed, liftPercent;
	
	private Talon leftMotor, rightMotor, //winchMotorLeft, winchMotorRight, squeezeFork,
	liftMotorFront, liftMotorBack;
	
	//DigitalInput outLimit, inLimit;
	
	Joystick pilotStick, copilotStick;
 
    public void robotInit(){
    pilotStick = new Joystick(0);
    copilotStick = new Joystick(1);
    leftMotor = new Talon(1);
    rightMotor = new Talon(0);
    liftMotorFront = new Talon(6);
    liftMotorBack = new Talon(2);
    //winchMotorLeft = new Talon(4);
    //winchMotorRight = new Talon(5);
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
    		
    	}
    	else if(autoButton2==true){
    		autoState=2;
    		
    	}
    	else if(autoButton3==true){
    		autoState=3;
    		
    	}
    	else if(autoButton4==true){
    		autoState=4;
    	}
    	else{
    		autoState=0;
    	}
    }

    public void autonomousPeriodic() {
    	//0, do nothing
    	//1, pick up two cans with front and back lifter and drive towards human loader station (far staging zone)
    	//2, pick up two cans and attempt to drive into auto zone
    	//3, pick up one can, drive forward, pick up next can
    	//4, pick up two cans and push third can into auto zone
    	
    	if(autoState==0){
    		zeroMotorSpeeds();
    	}
    	
    	else if(autoState==1){								//1
    		if(timer>=0 && timer<1){
    			zeroMotorSpeeds();
    		}
    		else if(timer>=1 && timer<100){
    			liftMotorBack.set(1);
    		}
    		else if(timer>=100 && timer<200){
    			leftMotor.set(0.25);
    			rightMotor.set(-0.25);
    			liftMotorBack.set(0.4);
    		}
    		else if(timer>=200 && timer<350){
    			liftMotorFront.set(0.8);
    			leftMotor.set(0);
    			rightMotor.set(0);
    			liftMotorBack.set(0);
    		}
    		else if(timer>=350 && timer<400){
    			leftMotor.set(0.4);
    			rightMotor.set(0.4);
    			liftMotorFront.set(0);
    			liftMotorBack.set(0);
    		}
    		else if(timer>=400 && timer<500){
    			leftMotor.set(0.7);
    			rightMotor.set(-0.7);
    		}
    		else if(timer>=500 && timer<550){
    			leftMotor.set(-0.4);
    			rightMotor.set(-0.4);
    		}
    		else if(timer>=550 && timer<650){
    			leftMotor.set(0.4);
    			rightMotor.set(-0.4);
    		}
    		else if(timer>=650 && timer<750){
    		zeroMotorSpeeds();
    		}
    	}
    	
    	else if(autoState==2){												//5
    		if(timer>=0 && timer<1){
    			zeroMotorSpeeds();
    		}
    		else if(timer>=1 && timer<100){
    			liftMotorBack.set(1);
    		}
    		else if(timer>=100 && timer<200){
    			leftMotor.set(0.25);
    			rightMotor.set(-0.25);
    			liftMotorBack.set(0.4);
    		}
    		else if(timer>=200 && timer<350){
    			liftMotorFront.set(0.8);
    			leftMotor.set(0.1);
    			rightMotor.set(-0.1);
    			liftMotorBack.set(0);
    		}
    		else if(timer>=350 && timer<450){
    			leftMotor.set(0.25);
    			rightMotor.set(0.25);
    			liftMotorFront.set(0);
    		}
    		else if(timer>=450 && timer<600){
    			leftMotor.set(0.6);
    			rightMotor.set(-0.6);
    			liftMotorFront.set(0);
    		}
    		else if(timer>=600 && timer<650){
    			leftMotor.set(0.3);
    			rightMotor.set(-0.3);
    		}
    		else if(timer>=650 && timer<730){
    			leftMotor.set(0.3);
    			rightMotor.set(0.3);
    		}
    		else if(timer>=730 && timer<750){
    			zeroMotorSpeeds();
    		}
    		
    	}
    	
    	if(autoState==3){
    		if(timer>=0 && timer<1){
    			zeroMotorSpeeds();
    		}
    		else if(timer>=1 && timer<100){
    			liftMotorBack.set(1);
    		}
    		else if(timer>=100 && timer<200){
    			leftMotor.set(0.20);
    			rightMotor.set(-0.20);
    			liftMotorBack.set(0.4);
    		}
    		else if(timer>=200 && timer<350){
    			liftMotorFront.set(0.8);
    			leftMotor.set(0);
    			rightMotor.set(0);
    			liftMotorBack.set(0);
    		}
    		else if(timer >= 350){
    			zeroMotorSpeeds();
    		}
    	}
    	if(autoState==4){
    		if(timer>=0 && timer<1){
    			zeroMotorSpeeds();
    		}
    		else if(timer>=1 && timer<100){//lift back
    			liftMotorBack.set(1);
    		}
    		else if(timer>=100 && timer<200){ //forward
    			leftMotor.set(0.20);
    			rightMotor.set(-0.20);
    			liftMotorBack.set(0);
    		}
    		else if(timer>=200 && timer<350){ //lift front
    			liftMotorFront.set(0.8);
    			leftMotor.set(0);
    			rightMotor.set(0);
    			liftMotorBack.set(0);
    		}
    		else if(timer >= 350 && timer<510){ //go forward
    			liftMotorFront.set(0);
    			leftMotor.set(0.5);
    			rightMotor.set(-0.5);
    		}
    		else if(timer >=510 && timer<560){ //turn right
    			leftMotor.set(0.4);
    			rightMotor.set(0.4);
    		}
    		else if(timer >=560 && timer<650){ //forward
    			leftMotor.set(0.7);
    			rightMotor.set(-0.7);
    		}
    		else if(timer >=650 && timer<740){ //turn right
    			leftMotor.set(0.3);
    			rightMotor.set(0.3);
    		}
    		else if(timer<=740 && timer<850){ //end
    			zeroMotorSpeeds();
    		}
    	}
    	
		timer++;
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
		
    	if(copilotStick.getRawButton(frontLift)){
    		liftMotorFront.set((-liftSpeed)*liftPercent);
    	}
    	else if(copilotStick.getRawButton(backLift)){
    		liftMotorBack.set((liftSpeed));
    	}
    	else if(copilotStick.getRawButton(liftAll)){
    		liftMotorFront.set(-liftSpeed*liftPercent);
    		liftMotorBack.set((liftSpeed));
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