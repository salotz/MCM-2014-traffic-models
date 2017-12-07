package traffic_model;

import java.io.PrintWriter;

public class SimulationParameters {
	final static int MAX_LANE = 2; //number of lanes on the road
	final static double TIME_STEP = .01666666;  //in minutes, 1 seconds of sim-time
	final static double SIM_TIME = 120; //in minutes, how long the simulation is to run for
	final static double ROAD_LENGTH = 5280; //distance of the stretch of road of interest

	final static double FOLLOW_DIST = 161; //in feet, is X1 in model, distance a car must follow another
	final static double PASS_DIST = 161; //feet, X2 in model, distance in front of a car it must be to pass another

	//final static double MAX_SPEED = 75; //in miles per hour, maximum speed
	//final static double MIN_SPEED = 35; //in miles per hour, minimum speed
	final static double MEAN_SPEED =55; //in miles per hour, for use in making a normal distribution
	final static double STD_DEV = 20; //variability of speeds, used for normal distribution of speeds

	final static double POP_RATE = 1;  //time between arrivals
	final static double ENTER_RATE = POP_RATE; //lambda parameter for poisson arrivals, smaller means faster time to arrival
											//or just the entrance time to arrival
	
	static int DECISION;  //randomized decision making stats
	
	final static int LANE_ONE = 1;
	final static double LENGTH = 14; //length of vehicle
	
	final static double INTERVAL = 10;  //minutes, this*60 gives seconds to data collection interval between data collection on road
	final static double POP_INTERVAL = INTERVAL; //number of time steps to a data collection
	final static String DATA_SET = "QsRun";  //name the data set folder you are creating

	// File to print to
	static PrintWriter POP_DATA_INPUT;
	static String FILE_PATH = DATA_SET + "//";
}
