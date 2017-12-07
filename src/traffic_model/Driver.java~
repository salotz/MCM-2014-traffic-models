package traffic_model;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

////////////NO PREFERENCE Guideline////////////////////////////
public class Driver {
//initializations
static Random rand = new Random();

static LinkedList<Double> inverseSpeed = new LinkedList<Double>();
static LinkedList<LinkedList<Double>> timeSpeeds = new LinkedList<LinkedList<Double>>();
static LinkedList<Double> tempList = new LinkedList<Double>();
//data raw values from individual cars
static LinkedList<Vehicle> vehicleList = new LinkedList<Vehicle>();
static LinkedList<Integer> sampVehicleNum = new LinkedList<Integer>();
static LinkedList<Integer> lane = new LinkedList<Integer>();
static LinkedList<Double> actSpeed = new LinkedList<Double>();
static LinkedList<Double> prefSpeed = new LinkedList<Double>();
static LinkedList<Double> position = new LinkedList<Double>();

//whole road stretch data
static LinkedList<Double> sampRoadTime = new LinkedList<Double>();
static LinkedList<Integer> sampRoadNum = new LinkedList<Integer>();
static LinkedList<Integer> numVehicles = new LinkedList<Integer>();

// File to print to
static PrintWriter POP_DATA_INPUT;


//__________MAIN CLASS______________________________________________________________________
public static void main(String[] args) {




//move new cars onto start
double time = 0; //set time to zero
boolean stop = false;
double timeDiff = 0;
double timeDiffData = 0; //for collecting road data
double timeDiffDataBig =0; //for population data collection
double nextArrival = nextPoissonInterval(SimulationParameters.ENTER_RATE);

//used for searching for vehicles
int count = 0;
boolean found = false;
createOutputFiles();

//_____________INITIALIZE THE HIGHWAY____________________________________________________

while(!stop){ //loop stops if a car leaves the stretch

//______________Cars enter_________________________________________________________
if( timeDiff > SimulationParameters.POP_RATE){//nextArrival){ //
//increase all ranks by 1
enterVehicle();

timeDiff=0; //reset the timeDiff
nextArrival = nextPoissonInterval(SimulationParameters.ENTER_RATE);
}
//_________________________________________________________________________________________
//System.out.println(time);

//_________________Time Step____________________________________________________________________
//increment the time, calculate timeDiff
time = time + SimulationParameters.TIME_STEP;
timeDiff = timeDiff + SimulationParameters.TIME_STEP;
//______________________________________________________________________________________________

//_____________Drive Cars________________________________________________________________________

for(int i=vehicleList.size() - 1 ; i > 0; i--){
vehicleList.get(i).drive();
//if the car has driven off of our stretch delete it from the list
if(vehicleList.get(i).getPosition() > SimulationParameters.ROAD_LENGTH ){
vehicleList.remove(i);
stop=true;
}
}

//_______MOVE LEFTS ____________________________________________________________
//check to see if cars need to turn left
for(int i = 0; i < vehicleList.size(); i++){
//first check to see if it needs to move left

//access the vehicle with the same lane number and ahead of this car in it's zone
count = 0;
while(!found && count < vehicleList.size()){
//if the vehicle is in the lane and this car is in it's zone
if(vehicleList.get(count).getLane() == vehicleList.get(i).getLane() &&
vehicleList.get(i).inZone(vehicleList.get(i+count).getPosition(), vehicleList.get(i+count).getLength())){
found = true;
}//end if
count++;
}//end while search for inFront car

if(found){
//if it does check to see if it can move left
if(checkLeft(vehicleList.get(i)) == false){ //there is nothing there
vehicleList.get(i).moveLeft(); //so let it move left
}else{
//it can't move left so decelerate
vehicleList.get(i).decel(vehicleList.get(i).getRank());
}//end if-else
}//end if

}
//___________________________________________________________________________________


//_______MOVE RIGHTS ________________________________________________________________
//********KREP Loop (Keep Right Except to Pass)

for(int i=0; i < vehicleList.size(); i++){
if(checkRight(vehicleList.get(i)) == false)
vehicleList.get(i).moveRight();
//else no worries!!

}//end loop to move cars to the right
//_________________________________________________________________________________________

//_________Re-Accelerate___________________________________________________________________

//have cars return to preferred speed if they can
for(int i = 0; i < vehicleList.size(); i++){
//if the vehicle is not in another's zone accelerate to preferred speed
//access the vehicle with the same lane number and ahead of this car in it's zone
count = 0;
while(!found && count < vehicleList.size()){
//if the vehicle is in the lane and this car is in it's zone
if(vehicleList.get(count).getLane() == vehicleList.get(i).getLane() &&
vehicleList.get(i).inZone(vehicleList.get(i+count).getPosition(), vehicleList.get(i+count).getLength())){
//then don't accelerate

}
else{ //the vehicle is not in another's following zone
//accelerate
vehicleList.get(i).acel();
}
count++;
}//end while search for inFront car
}
//___________________________________________________________________________________________
//______________________________________________________________________________________________
}//end first loading highway

//______________________________________________________________________________________________



System.out.println("INITIALIZATION COMPLETE");

System.out.println("Number of vehicles on Road at termination: " + vehicleList.size());
System.out.println("Simulation run Time: " + SimulationParameters.SIM_TIME + " minutes sim time");
System.out.println("Length of Road: " + SimulationParameters.ROAD_LENGTH + " feet");
System.out.println("Number of Lanes: " + SimulationParameters.MAX_LANE);
System.out.println("Time step: " + SimulationParameters.TIME_STEP + " minutes sim time");
System.out.println("Entrance Rate: " + SimulationParameters.ENTER_RATE + " seconds between arrivals");

//Scanner in = new Scanner(System.in);
//String words = in.nextLine();

//________________INITIALIZATION COMPLETE______________________________________________









//____________START EXPERIMENT____________________________________________________
///Now the data collection portion of simulation can begin
time=0; //reset time
int bigCount = 0;
int dataSetNum = 0; //will be used to label different files
String fileStr;
//create a folder to put data set from this simulation in
new File("..\\..\\Desktop\\MCM_Model\\data\\" + SimulationParameters.DATA_SET).mkdir();

PrintWriter writer;
try {
	writer = new PrintWriter ("..//..//Desktop//MCM_Model//data//" + SimulationParameters.FILE_PATH + "timeSpeedsData.csv");



while(time < SimulationParameters.SIM_TIME){ //loop stops when simulation times out


//_______MOVE? ____________________________________________________________
//check to see if cars need to turn left
for(int i = 0; i < vehicleList.size(); i++){
//first check to see if it needs to move left

//access the vehicle with the same lane number and ahead of this car in it's zone
count = 0;
found=false;
while(!found && count < vehicleList.size() - i){
//if the vehicle is in the lane and this car is in it's zone
if(vehicleList.get(count).getLane() == vehicleList.get(i).getLane() &&
vehicleList.get(i).inZone(vehicleList.get(i+count).getPosition(), vehicleList.get(i+count).getLength())){
found = true;
}//end if
count++;
}//end while search for inFront car

if(found){
	//if it does check to see if it can move left
	int randNum = rand.nextInt();
	if(randNum%2 ==0){
		if(checkLeft(vehicleList.get(i)) == false){ //there is nothing there
			vehicleList.get(i).moveLeft(); //so let it move left
		}else if (checkRight(vehicleList.get(i)) == false){
			vehicleList.get(i).moveRight();
		}else{
			vehicleList.get(i).decel(vehicleList.get(i).getRank());
		}//end if-else
		
	}else{
		if(checkRight(vehicleList.get(i)) == false){ //there is nothing there
			vehicleList.get(i).moveRight(); //so let it move left
		}else if (checkLeft(vehicleList.get(i)) == false){
			vehicleList.get(i).moveLeft();
		}else{
			vehicleList.get(i).decel(vehicleList.get(i).getRank());
		}//end if-else
	}
	

}//end found
}
//___________________________________________________________________________________




//_________Re-Accelerate___________________________________________________________________

//have cars return to preferred speed if they can
for(int i = 0; i < vehicleList.size(); i++){
//if the vehicle is not in another's zone accelerate to preferred speed
//access the vehicle with the same lane number and ahead of this car in it's zone
count = 0;
while(!found && count < vehicleList.size()-i){
//if the vehicle is in the lane and this car is in it's zone
if(vehicleList.get(count).getLane() == vehicleList.get(i).getLane() &&
vehicleList.get(i).inZone(vehicleList.get(i+count).getPosition(), vehicleList.get(i+count).getLength())){
//then don't accelerate

}
else{ //the vehicle is not in another's following zone
//accelerate
vehicleList.get(i).acel();
}
count++;
}//end while search for inFront car
}
//___________________________________________________________________________________________

//System.out.println("CHECKPOINT 2");


//_________Drive Vehicles__________________________________________________________________
for(int i=vehicleList.size() - 1 ; i > 0; i--){
vehicleList.get(i).drive();
//if the car has driven off of our stretch delete it from the list
if(vehicleList.get(i).getPosition() > SimulationParameters.ROAD_LENGTH ){
vehicleList.remove(i);
stop = true;
}
}
//_________________________________________________________________________________________


//______________Pass and Swap ranks_________________________________________________________

//check for passes in list order and swap if needed
passCheck();
//_________________________________________________________________________________________


//________Cars enter Highway ______________________________________________________________


//check to see if cars enter highway
if( timeDiff > SimulationParameters.ENTER_RATE){ // nextArrival){  //nextPoissonInterval(SimulationParameters.ENTER_RATE)){  //
enterVehicle();
timeDiff=0; //reset the timeDiff
nextArrival = nextPoissonInterval(SimulationParameters.ENTER_RATE);
}
//___________________________________________________________________________________________


//________Time increases_________________________________________________________________

//increment the time, calculate timeDiff
time = time + SimulationParameters.TIME_STEP;
timeDiff = timeDiff + SimulationParameters.TIME_STEP;
timeDiffData = timeDiffData + SimulationParameters.TIME_STEP;
timeDiffDataBig = timeDiffDataBig + SimulationParameters.TIME_STEP;

//System.out.println(time);
//___________________________________________________________________________________________


//__________________________DATA COLLECTION__________________________________________________

//total road data
if(timeDiffData > SimulationParameters.INTERVAL){

sampRoadNum.addLast(bigCount);  //sample number
sampRoadTime.addLast(time);    //time in simulation
numVehicles.addLast(vehicleList.size());    //total vehicles on road

bigCount++;
timeDiffData = 0;
}


//data for each vehicle at different points in time
//approximately every 15 sec collect population data

if(timeDiffDataBig > SimulationParameters.INTERVAL){
timeDiffDataBig =0;
//sampVehicleTime.addLast(time);
for(int i=0; i < vehicleList.size(); i++){

sampVehicleNum.addLast(vehicleList.get(i).getRank());
lane.addLast(vehicleList.get(i).getLane());
actSpeed.addLast(vehicleList.get(i).getSpeed());
prefSpeed.addLast(vehicleList.get(i).getPrefSpeed());
position.addLast(vehicleList.get(i).getPosition());
}

//dynamically assigning the files to be written to
/*
for(int i=0; i < vehicleList.size(); i++){
	POP_DATA_INPUT.print(sampVehicleNum.get(i) + ",");
	POP_DATA_INPUT.print(time + ",");
	POP_DATA_INPUT.print(lane.get(i) + ",");
	POP_DATA_INPUT.print(actSpeed.get(i) + ",");
	POP_DATA_INPUT.print(prefSpeed.get(i) + ",");
	POP_DATA_INPUT.println(position.get(i));
}
*/

double sum = 0;
double inverse = 0;
sampRoadTime.addLast(time);    //time in simulation
for(int i=0; i < actSpeed.size(); i++){
	
	sum = sum + actSpeed.get(i);
}

inverseSpeed.addLast(sum/sampVehicleNum.size());



	//POP_DATA_INPUT.println();


//clear the lists so they can be refilled
sampVehicleNum.clear();
lane.clear();
actSpeed.clear();
prefSpeed.clear();
position.clear();

dataSetNum++;

}//Population data POP_INTERVAL loop


writer = new PrintWriter ("..//..//Desktop//MCM_Model//data//" + SimulationParameters.FILE_PATH + "avgVelocityData.txt");

writer.println("Density, Time, Average Velocity");

for(int i=0; i < numVehicles.size(); i++){
	
	
	writer.print(numVehicles.get(i) + "," + sampRoadTime.get(i) + "," + inverseSpeed.get(i));
	writer.println();
}
writer.close();



}//end simulation loop
writer.close();
} catch (FileNotFoundException e1) {
	// TODO Auto-generated catch block
	//e1.printStackTrace();
}


//___________________________END SIMULATION___________________________________________________


//___________________________DATA OUTPUT ______________________________________________________


try {
writer = new PrintWriter("..//..//Desktop//MCM_Model//data//" + SimulationParameters.FILE_PATH + "headerInfo.txt");
//header
writer.println("simulation title and details: ");
writer.println("Number of vehicles on Road at termination: " + vehicleList.size());
writer.println("Simulation run Time: " + SimulationParameters.SIM_TIME + " minutes");
writer.println("Length of Road: " + SimulationParameters.ROAD_LENGTH + " feet");
writer.println("Number of Lanes: " + SimulationParameters.MAX_LANE);
writer.println("Time step: " + SimulationParameters.TIME_STEP + " minutes");
writer.println("Entrance Rate: " + SimulationParameters.ENTER_RATE + " seconds between arrivals");
writer.println("Population Data is taken every: " + SimulationParameters.POP_INTERVAL * SimulationParameters.TIME_STEP*60 + " seconds");

writer.close();

writer = new PrintWriter ("..//..//Desktop//MCM_Model//data//" + SimulationParameters.FILE_PATH + "roadData.txt");

writer.println("Time (sec), Num Vehicles");
System.out.println(sampRoadTime.size());
for(int i = 0; i < sampRoadTime.size(); i++){
writer.print(sampRoadTime.get(i)*60 + ",");
writer.println(numVehicles.get(i));
}
writer.close();



} catch (FileNotFoundException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}





System.out.println("Number of vehicles on Road at termination: " + vehicleList.size());
System.out.println("Simulation run Time: " + SimulationParameters.SIM_TIME + " minutes");
System.out.println("Length of Road: " + SimulationParameters.ROAD_LENGTH + " feet");
System.out.println("Number of Lanes: " + SimulationParameters.MAX_LANE);
System.out.println("Time step: " + SimulationParameters.TIME_STEP + " minutes");
System.out.println("Entrance Rate: " + SimulationParameters.ENTER_RATE + " seconds between arrivals");


//POP_DATA_INPUT.close();
//_____________________________________________________________________________________________

//___________________________TERMINATION______________________________________________________


}//END MAIN





/////////////________METHODS for Driver class________///////////////////////////////////////////

//______________________________________________________________________________________________
/*public static int sumVehicles(double range1, double range2){
while()
}
*/

//______________________________________________________________________________________________
public static void enterVehicle(){
//increase all ranks by 1 in list
for(int i=0; i < vehicleList.size(); i++){
vehicleList.get(i).setRank(vehicleList.get(i).getRank()+1);
}
double pref = genSpeed();
double actual = pref - Math.abs(pref - genSpeed());
vehicleList.addFirst(new Vehicle(actual, pref,genLane(), 0));
}
//______________________________________________________________________________________________
public static void passCheck(){
for(int j = 0; j < vehicleList.size(); j++){//cycle through all members of list

boolean stop = false;
int count = j+1;
while(!stop && count < vehicleList.size()){//compare each member to the few in front of it it may have passed
//stop when it has not passed one

//compare positions
if(vehicleList.get(j).getPosition() > vehicleList.get(count).getPosition()){
//if it has advanced swap their nodes in the list
swapNodes(vehicleList.get(j), vehicleList.get(count));
vehicleList.get(j).setRank(j);
vehicleList.get(count).setRank(count);
}else{//if it did not swap done checking
stop = true;//exit while loop
}//end if-else
count++;
}//end while

}//end for loop through list
}

//______________________________________________________________________________________________
public static double genSpeed(){
//find a random speed that is between the max and min speeds
double newSpeed = 55; //(rand()%(SimulationParameters.MAX_SPEED - SimulationParameters.MIN_SPEED)+SimulationParameters.MIN_SPEED);
//could also set a maximum speed cars tend towards and change initial actual speed
//newSpeed = MAX_SPEED;

//ideally we would use a Normal Distribution function in whichever language we use
newSpeed = (rand.nextGaussian()*SimulationParameters.STD_DEV) + SimulationParameters.MEAN_SPEED;//*SimulationParameters.STD_DEV;
//System.out.println(newSpeed);
if(newSpeed <= 0)
return SimulationParameters.MEAN_SPEED;
else
return newSpeed;


}
//______________________________________________________________________________________________
public static int genLane(){
int newLane;
newLane = 1 + Math.abs(rand.nextInt()%SimulationParameters.MAX_LANE);
//System.out.println(newLane);
return newLane;

}

//______________________________________________________________________________________________
public static void swapNodes(Vehicle node1, Vehicle node2){
Vehicle tempVehicle = node1;
node1 = node2;
node2 = tempVehicle;
}
//______________________________________________________________________________________________
public static boolean checkLeft(Vehicle thisVehicle){

boolean flag = false;  //is raised if there is something to the left
//the forward range of the car looking to pass
double pos1 = thisVehicle.getPosition() + thisVehicle.getLength()/2 + SimulationParameters.PASS_DIST;
//the rear range of the car looking to pass
double pos2 = thisVehicle.getPosition() - thisVehicle.getLength()/2 + SimulationParameters.PASS_DIST;

//first check to see there is another lane to move into
if(thisVehicle.getLane() == SimulationParameters.MAX_LANE)
flag = true;
else{//if not check for a car
//go through the list of vehicles until you find one blocking
//or the end of the list
int car = 0;
while(!flag && car < vehicleList.size()){

//any that are in thisVehicles lane + 1 and within the range of position
if(vehicleList.get(car).getLane() == thisVehicle.getLane() + 1 &&
vehicleList.get(car).getPosition() >= pos2 && vehicleList.get(car).getPosition() <= pos1){
//raise the flag for a blockage
flag = true;
}
car++;
}
}
return flag;
}//end check left method

//______________________________________________________________________________________________
public static boolean checkRight(Vehicle thisVehicle){

boolean flag = false;  //is raised if there is something to the left
//the forward range of the car looking to pass
double pos1 = thisVehicle.getPosition() + thisVehicle.getLength()/2 + SimulationParameters.PASS_DIST;
//the rear range of the car looking to pass
double pos2 = thisVehicle.getPosition() - thisVehicle.getLength()/2 + SimulationParameters.PASS_DIST;

//first check to see there is another lane to move into
if(thisVehicle.getLane() <= SimulationParameters.LANE_ONE){
flag = true;
}
else{//if not check for a car
//go through the list of vehicles until you find one blocking
//or the end of the list
int car = 0;
while(!flag && car < vehicleList.size()){

//any that are in thisVehicles lane - 1 and within the range of position
if(vehicleList.get(car).getLane() == thisVehicle.getLane() - 1 &&
vehicleList.get(car).getPosition() >= pos2 && vehicleList.get(car).getPosition() <= pos1){
//raise the flag for a blockage
flag = true;
}//end if
car++;
}//end while
}//end if-else
return flag;
}//end check left method
//______________________________________________________________________________________________
public static double nextPoissonInterval(double lambda){
return Math.log(rand.nextDouble()/(-lambda));
}
private static void createOutputFiles() {
	File theDir = new File(SimulationParameters.DATA_SET);

	  // if the directory does not exist, create it
	  if (!theDir.exists())
	  {
	    System.out.println("creating directory: " + SimulationParameters.DATA_SET);
	    theDir.mkdir();
	  }
	String fileStr = "..//..//Desktop//MCM_Model//data//" + SimulationParameters.FILE_PATH + "CollectedData" +".csv";
	
	/*
	try {
		//popDataInput = new PrintWriter ("checkinfilepathsyo.txt");
		POP_DATA_INPUT = new PrintWriter(fileStr); //set the file path to write to
		//in csv format write the population data to a file
		POP_DATA_INPUT.println("Sample Number, Time, Lane, Actual Speed, Preferred Speed, Position");
	} catch(FileNotFoundException e) {
		e.printStackTrace();
	}
	*/
}

}//end class

