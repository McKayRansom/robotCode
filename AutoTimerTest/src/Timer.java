
public class Timer {
	int timeMod;
	double[] sequence;
	int time = 0;
	boolean isRunning=true;
	int order=0;
	
	public Timer(int mod, double[] secondSequence){
		timeMod = mod;
		sequence = new double[secondSequence.length];
		for(int i=0; i<secondSequence.length; i++){
			sequence[i]=secondSequence[i];
		}
	}
	
	public void run(){
		double limit = sequence[order]*timeMod-1;
		if(time<limit){
			isRunning=true;
			time++;
		}
		else if(order>sequence.length){
			isRunning=false;
		}
		else if(time>=limit){
			isRunning=true;
			order++;
			time=0;
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
		return time;
	}
	public int getOrder(){
		return order;
	}
}
