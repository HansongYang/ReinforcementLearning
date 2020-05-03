package assignment3;
import java.util.*;
import java.text.DecimalFormat;

public class elevator {
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		tsetlin tsetlin = new tsetlin();
		krinsky krinsky = new krinsky();
		krylov krylov = new krylov();
		LRI lri = new LRI();
		int experiments = 100;
		int n = 0;
		int choice = 0;
		
		while(true) {
			System.out.println("Which machine do you want to use to solve the elevator problem?");
			System.out.println("1.Tsetlin Machine  2.Krinsky Machine  3.Krylov Machine  4. LRI scheme");
			choice = sc.nextInt();
			if(choice > 0 && choice < 5) {
				break;
			}
			System.out.println("Invalid input, please enter again.");
		}
		
		if(choice < 4) {
			n = 6; //total actions
			long start = System.currentTimeMillis();
			Random r = new Random();
			int numberOfEvents = 10000;
			float i1=0, i2=0, i3=0, i4=0, i5=0, i6=0;
			
			while(true) {
				i1=0; i2=0; i3=0; i4=0; i5=0; i6=0;
			    for(int i = 0; i < experiments; i++) {
			    	int state = r.nextInt((n*36)) + 1; // random state space
			    	int iteration = 0;
			    	 
			    	while (iteration < numberOfEvents) {
			    		iteration++;
			    		if(choice == 1) {
			    			state = tsetlin.solve(n, state);
						} else if(choice == 2) {
							state = krinsky.solve(n, state);
						} else if(choice == 3) {
							state = krylov.solve(n, state);
						}
				        
				        if (state <= n) {
				            if (iteration > 9900) {
				                i1++;
				            }
				        } else if (state > n && state <= n * 2) {
				            if (iteration > 9900) {
				                i2++;
				            }
				        } else if (state > n * 2 && state <= n * 3) {
				            if (iteration > 9900) {
				                i3++;
				            }
				        } else if (state > n * 3 && state <= n * 4) {
				            if (iteration > 9900) {
				                i4++;
				            }
				        } else if (state > n * 4 && state <= n * 5) {
				            if (iteration > 9900) {
				                i5++;
				            }
				        } else {
				            if (iteration > 9900) {
				                i6++;
				            }
				        }
			    	 }
			    }
			    if(i6/100 >= 90) {
			    	break;
			    }
			}
		    
		    long end = System.currentTimeMillis();
		 
		    float accuracy = (float)((i6 / (i1+i2+i3+i4+i5+i6)) * 100);
		    System.out.println("\nWaiting time percentage: ");
		    System.out.println("Floor 1: " + i1/100 + "%.");
		    System.out.println("Floor 2: " + i2/100 + "%.");
		    System.out.println("Floor 3: " + i3/100 + "%.");
		    System.out.println("Floor 4: " + i4/100 + "%.");
		    System.out.println("Floor 5: " + i5/100 + "%.");
		    System.out.println("Floor 6: " + i6/100 + "%.\n");
		    System.out.println("Accuracy: " + accuracy + "%.");
		    if(choice == 1) {
		    	  System.out.println("Time taken by Tsetlin machine: " + (end-start) + " milliseconds.");
			} else if(choice == 2) {
				System.out.println("Time taken by Krinsky machine: " + (end-start) + " milliseconds.");
			} else if(choice == 3) {
				System.out.println("Time taken by Krylov machine: " + (end-start)  + " milliseconds.");
			}
		} else {
			long start = System.currentTimeMillis();
	        int correct = 0;
	        int num = 0;
	        int currentAction = 0;
	        Random r = new Random();
	        for(int i = 0; i<experiments; i++) {
	        	while (correct == 0) {
			        num = r.nextInt(6) + 1; //Random optimal floor
			        currentAction = lri.solve(num);
			        if ((lri.probabilities[currentAction-1] >= 0.90)) {
			        	System.out.println("\nAccuracy: " + df2.format((lri.probabilities[currentAction-1])*100) + "%.");
			            correct = 1;
			        }
			    }
			}
			long end = System.currentTimeMillis();
			System.out.println("Action with the most accurate percentage is floor: " + num);
			System.out.println("Time taken by LRI scheme: " + (end-start) + " milliseconds.");
		}
	}
}
