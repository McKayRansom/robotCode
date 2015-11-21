package org.usfirst.frc.team3245.robot;

public class Timer {
	
	int timeMod = 0;
	int order = 0;
	int time = 0;
	int[] sequence;
	boolean isRunning=false;
	
	public Timer(int mod, int[] secondSequence){
		timeMod = mod;
		sequence = new int[secondSequence.length];
		for(int i=0; i<secondSequence.length; i++){
			sequence[i]=secondSequence[i];
		}
	}
	
	public void run(){
		int limit = timeMod*sequence[order];
		if(time<limit){
			isRunning=true;
			time++;
		}
		else if(time>limit){
			isRunning=false;
			time=0;
			order++;
		}
	}
	public boolean isRunning(){
		if(isRunning==true){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int getTime(){
		
	}
	
}
