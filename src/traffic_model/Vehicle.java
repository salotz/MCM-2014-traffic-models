package traffic_model;

class Vehicle
{
	double length;
	double prefSpeed;
	double actSpeed;
	double position;
	int rank;
	int lane;
	/**
	 * Creates a new vehicle
	 * @param speed
	 * @param inLane
	 * @param inRank
	 */
	public Vehicle(double speed, double maxSpeed, int inLane, int inRank){
		
		
			length = SimulationParameters.LENGTH;
			actSpeed = speed;
			prefSpeed = maxSpeed;
			position = 0;
			rank = inRank;
			lane = inLane;
	}
		
	void drive(){
		position = position + actSpeed*SimulationParameters.TIME_STEP;
	}
	
	void moveLeft(){
			lane++;
	}
	
	void moveRight(){
			lane--;
	}
		
	void decel(int inFrontSpeed){ 
			actSpeed = inFrontSpeed; //set the car's actual speed to a new speed
	}
		
	void acel(){
		actSpeed = prefSpeed;
	}
		
	boolean inZone(double inFrontPos, double inFrontLength){  //use the rank+1 of this car to determine distance to car in front of you
		if(this.position > (inFrontPos - (inFrontLength/2) - SimulationParameters.FOLLOW_DIST))
			return true;
		else
			return false;
	}
	
	///////////essentially liquidated these as class methods because I needed information individual objects could not access
	
	/*
	boolean canLeft(float toLeftPos, float toLeftLength){ //use range (position-length/2-PASS_DIST) to (position + length/2+PASS_DIST)
														//to find cars in range
		//if a car's position is in range it cannot move left
		if(toLeftPos < this->position && (toLeftPos >= (this->position - (this->length)/2 - PASS_DIST))
			return false;
		else if (toLeftPos > position && (this-> position + (this->length)/2 + FOLLOW_DIST) >= toLeftPos)
			return false;
		else if (lane == MAX_LANE)
			return false;
		else
			return true;
	}
	
	boolean canRight(float toRightPos, float toRightLength){
		
			//if a car's position is in range it cannot move right
		if(toRightPos < this->position && (toRightPos >= (this->position - (this->length)/2 - PASS_DIST))
			return false;
		else if (toRightPos > position && (this-> position + (this->length)/2 + FOLLOW_DIST) >= toRightPos)
			return false;
		else if (lane == LANE_ONE)
			return false;
		else
			return true;
	}
	*/
	
	void setRank(int newRank){
		this.rank = newRank;
	}
	
	double getPosition(){
		return this.position;
	}
	
	double getSpeed(){
		return this.actSpeed;
	}
	
	int getRank(){
		return this.rank;
	}
	
	int getLane(){
		return this.lane;
	}
	
	double getLength(){
		return this.length;
	}
	
	double getPrefSpeed(){
		return this.prefSpeed;
	}
}
	
			
			
			
			
			
			
			
			
			
			
			
			