package assignment3;
import java.util.*;

public class tsetlin {
    public double smallestTime = 0.0;
    public int depth = 10;
    
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
    
    public int sendReward(int state) {
    	if(state == 1 || state % depth == 1) {
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
        }else if(state > n && state <= n*2){
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
        if(feedback == 0) {
            newState = sendReward(state);
        } else {
        	newState = sendPenalty(n,state);
        }
        return newState;
    }
    
}
