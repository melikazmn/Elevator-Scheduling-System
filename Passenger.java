package org.example;

public class Passenger {
    private int arrivalFloor;
    private int destinationFloor;
    private int arrivalTime;
    private int getInElevatorTime;
    private int getOutOfElevatorTime;
    private int turnAroundTime;
    private int waitingTime;
    private int number;

    public Passenger(int arrivalFloor, int destinationFloor, int arrivalTime) {
        this.arrivalFloor = arrivalFloor;
        this.destinationFloor = destinationFloor;
        this.arrivalTime = arrivalTime;
        this.getInElevatorTime = arrivalTime;
        this.getOutOfElevatorTime = arrivalTime;
        this.turnAroundTime = 0;
        this.waitingTime = 0;

    }
    public Passenger() {
        this.arrivalFloor = arrivalFloor;
        this.destinationFloor = destinationFloor;
        this.arrivalTime = arrivalTime;
        this.getInElevatorTime = arrivalTime;
        this.getOutOfElevatorTime = arrivalTime;
        this.turnAroundTime = 0;
        this.waitingTime = 0;
    }

    public int getArrivalFloor() {
        return arrivalFloor;
    }

    public void setArrivalFloor(int arrivalFloor) {
        this.arrivalFloor = arrivalFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getGetInElevatorTime() {
        return getInElevatorTime;
    }

    public void setGetInElevatorTime(int getInElevatorTime) {
        this.getInElevatorTime = getInElevatorTime;
    }

    public int getGetOutOfElevatorTime() {
        return getOutOfElevatorTime;
    }

    public void setGetOutOfElevatorTime(int getOutOfElevatorTime) {
        this.getOutOfElevatorTime = getOutOfElevatorTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime() {
        this.turnAroundTime += this.getOutOfElevatorTime - this.arrivalTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime() {
        this.waitingTime += this.getInElevatorTime - this.arrivalTime;
    }

    public void signal() {
        System.out.println("Passenger"+number+"elevator is here.Get in!");
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
