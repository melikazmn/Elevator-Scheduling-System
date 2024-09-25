package org.example;

import org.example.Passenger;

import java.util.*;

public class Elevator {
    private int numFloors;
    private int floorHeight;
    private int speedPerMeter;
    private String schedulingAlgorithm;
    private int currentFloor;
    private int time;// when we move 1 floor we add to time 3 seconds
    private boolean stop;
    private ArrayList<Integer> chart;

    public Elevator(int numFloors , String schedulingAlgorithm) {
        this.numFloors = numFloors;
        this.schedulingAlgorithm = schedulingAlgorithm;
        this.currentFloor = 1;
        this.floorHeight = 3;
        this.speedPerMeter = 1;
        this.stop = true;
        this.chart = new ArrayList<>(Collections.nCopies(500, -1));

    }

    public Elevator() {
        this.currentFloor = 1;
        this.floorHeight = 3;
        this.speedPerMeter = 1;
        this.stop = true;
        this.chart = new ArrayList<>(Collections.nCopies(500, -1));

    }

    public int getCurrentFloor() {
        return currentFloor;
    }


    public int getNumFloors() {
        return numFloors;
    }

    public int getFloorHeight() {
        return floorHeight;
    }

    public int getSpeedPerMeter() {
        return speedPerMeter;
    }

    public String getSchedulingAlgorithm() {
        return schedulingAlgorithm;
    }

    public void setNumFloors(int numFloors) {
        this.numFloors = numFloors;
    }

    public void setSchedulingAlgorithm(String schedulingAlgorithm) {
        this.schedulingAlgorithm = schedulingAlgorithm;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public int move(int destinationFloor) {
        int transPortTime = Math.abs(destinationFloor - this.currentFloor) * this.floorHeight * this.speedPerMeter;
        time += transPortTime;
        this.currentFloor = destinationFloor;
        return transPortTime;
    }
    public void setChart(int startTime,int endTime,int passangerNum){
        for (int i = startTime; i < endTime; i++) {
            chart.set(i,passangerNum);
        }
    }
    public ArrayList<Integer> getChart() {
        return chart;
    }
}
