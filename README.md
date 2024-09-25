# Elevator Scheduling System
This project simulates the movement and operation of an elevator system in a building using multiple scheduling algorithms.
It manages passengers' requests for the elevator from different floors, transporting them to their destinations and calculating performance metrics such as waiting time and turnaround time.

## Features
Multiple Scheduling Algorithms:
- First-Come-First-Serve (FCFS)
- Shortest Job First (SJF)
- Shortest Remaining Time First (SRTF)
- Round Robin (RR)
  
Passenger Management: Track passengers' requests, their arrival and destination floors, waiting time, and turnaround time.

Elevator Movement Simulation: Simulates realistic elevator movement between floors and calculates operation time.

## Scheduling Algorithms
1. **First-Come-First-Serve (FCFS)**
The elevator services passengers in the order they requested it, without prioritization.

2. **Shortest Job First (SJF)**
The elevator prioritizes the passengers with the shortest journey (closest destinations).

3. **Shortest Remaining Time First (SRTF)**
SRTF is a dynamic algorithm that adjusts elevator service based on the shortest remaining travel time.

4. **Round Robin (RR)**
The elevator services passengers in equal time slices (quantum). If a passenger’s journey cannot be completed within a time slice, it is resumed in the next round.

## Classes
1. **Elevator Class**
The Elevator class simulates the movement and functionality of the elevator.

 *Attributes*:
- numFloors: Number of floors in the building.
- floorHeight: Height of each floor (in meters).
- speedPerMeter: Speed of the elevator (in meters per second).
- schedulingAlgorithm: The scheduling algorithm used by the elevator.
- currentFloor: The current floor the elevator is on.
- time: Total operational time of the elevator.
- stop: Whether the elevator is currently stopped or moving.
- chart: Time chart that tracks which passenger is using the elevator at each point in time.
*Methods*:
- move(int destinationFloor): Moves the elevator to the specified floor and updates time based on travel distance.
- setChart(int startTime, int endTime, int passengerNum): Records elevator usage for a specific time period for a given passenger.
- getChart(): Returns the usage chart of the elevator.

2. **Passenger Class**
The Passenger class models passengers in the elevator system.

*Attributes*:
- arrivalFloor: The floor the passenger starts from.
- destinationFloor: The floor the passenger is going to.
- arrivalTime: The time the passenger requested the elevator.
- getInElevatorTime: The time the passenger enters the elevator.
- getOutOfElevatorTime: The time the passenger exits the elevator.
- turnAroundTime: Total time from the passenger’s arrival until they reach their destination.
- waitingTime: Time spent waiting for the elevator.
*Methods*:
- setTurnAroundTime(): Calculates the turnaround time of the passenger.
- setWaitingTime(): Calculates the waiting time before entering the elevator.
- signal(): Signals the passenger that the elevator has arrived.

## Usage
To use the elevator scheduling system, instantiate the Elevator and Passenger classes, and call the appropriate scheduling method based on your requirements.

## Conclusion
This project provides a basic yet flexible framework for simulating elevator scheduling using various algorithms. The modular design allows easy addition of new algorithms or functionalities, making it ideal for further experimentation and learning.
Feel free to clone this repository and try out different algorithms to see how they affect passenger waiting times and overall elevator efficiency!

