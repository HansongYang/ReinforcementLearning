package assignment3;
import java.util.*;

public class krylov {
	public double smallestTime = 0.0;
    
    public int environment(int num){  //0 reward, 1 penalty
        Random r = new Random();
        double noise = r.nextGaussian();
        double waitingTime = 0.8 * num + 0.4 * Math.ceil(num/2) + noise;
        
        if(waitingTime < smallestTime){
        	return 0;
        } else{
        	smallestTime = waitingTime;
            return 1;
        }
    }
    
    public int sendReward(int n, int state) {
    	if(state % n == 1) {
    		return state;
    	} else {
    		state = state - 1;
    	}
    	return state;
    }
    
    public int sendPenalty(int n, int state) {
    	if(state == n) {
    		state = 2 * n;
    	} else if(state == 2 * n) {
    		state = n;
    	} else {
    		state = state + 1;
    	}
    	return state;
    }
    
    public int solve(int n, int state) {
    	int index = 0;
    	int newState = 0;
    	
    	if(state <= n){
            index = 1;
        }else if(state > n && state <= n * 2){
            index = 2;
        }else if(state > n * 2 && state <= n * 3){
            index = 3;
        }else if(state > n * 3 && state <= n * 4){
            index = 4;
        }else if(state > n * 4 && state <= n * 5){
            index = 5;
        }else{
            index = 6;
        }

        int feedback = environment(index);
        if(feedback == 0){
        	newState = sendReward(n, state);
        } else {
        	 int num = (int)(Math.random()*2);
             if(num > 0.5) {
            	 newState = sendPenalty(n, state);
             }else{
            	 newState = sendReward(n, state);
             }
        }
        return newState;
    }
}
