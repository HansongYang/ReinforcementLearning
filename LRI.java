package assignment3;

import java.util.Random;

public class LRI {
	public double smallestTime = 0.0;
    public double [] probabilities = {0.167, 0.167, 0.167, 0.167, 0.167, 0.167};
    public double lambda = 0.50;
    
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
    
    public void sendReward(int num) {
        int index = num - 1;
        double sum = 0.0;
        
        for (int i = 0; i < probabilities.length; i++) {
            if (i != index) {
                probabilities[i] = lambda * probabilities[i];
                sum += lambda * probabilities[i];
            }
        }
        probabilities[index] = 1 - sum;
    }
    
    public int solve(int num) {
        int result = environment(num);
        
        if (result == 0) {
            sendReward(num);
        }
        return num;
    }
}
