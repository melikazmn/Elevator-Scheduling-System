package org.example;

import org.example.Passenger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;

public class Methods {

    public static ArrayList<Passenger> sortByArrivalTime(ArrayList<Passenger> passengers){
        for (int i = 0; i < passengers.size(); i++) {
            for (int j = i; j < passengers.size(); j++) {
                if(passengers.get(i).getArrivalTime()> passengers.get(j).getArrivalTime()){
                    Passenger temp = new Passenger();
                    temp = passengers.get(j);
                    passengers.set(j,passengers.get(i));
                    passengers.set(i,temp);
                }
            }
        }
        return  passengers;
    }

    public static ArrayList<Passenger> sortByShortestPath(ArrayList<Passenger> passengers){
        for (int i = 0; i < passengers.size(); i++) {
            for (int j = i; j < passengers.size(); j++) {
                if(Math.abs(passengers.get(i).getArrivalFloor()-passengers.get(i).getDestinationFloor()) >
                        Math.abs(passengers.get(j).getArrivalFloor()-passengers.get(j).getDestinationFloor())
                        && passengers.get(i).getArrivalTime()>passengers.get(j).getArrivalTime()){
                    Passenger temp = new Passenger();
                    temp = passengers.get(j);
                    passengers.set(j,passengers.get(i));
                    passengers.set(i,temp);
                }
            }
        }
        return  passengers;
    }

    public static ArrayList<Passenger> sortByPathOnly(ArrayList<Passenger> passengers){
        for (int i = 0; i < passengers.size(); i++) {
            for (int j = i; j < passengers.size(); j++) {
                if(Math.abs(passengers.get(i).getArrivalFloor()-passengers.get(i).getDestinationFloor()) >
                        Math.abs(passengers.get(j).getArrivalFloor()-passengers.get(j).getDestinationFloor())){
                    Passenger temp = new Passenger();
                    temp = passengers.get(j);
                    passengers.set(j,passengers.get(i));
                    passengers.set(i,temp);
                }
            }
        }
        return  passengers;
    }



    public static ArrayList<Passenger> FCFS(Elevator elevator,ArrayList<Passenger> pass){
        ArrayList<Passenger> passengers = sortByArrivalTime(pass);
        for (int i = 0; i < passengers.size(); i++) {
            if (elevator.getTime()<passengers.get(i).getArrivalTime())
                elevator.setTime(passengers.get(i).getArrivalTime());

            if (elevator.getCurrentFloor() == passengers.get(i).getArrivalFloor()){
                passengers.get(i).setGetInElevatorTime(elevator.getTime());
                elevator.move(passengers.get(i).getDestinationFloor());
                passengers.get(i).setGetOutOfElevatorTime(elevator.getTime());
                elevator.setChart(passengers.get(i).getGetInElevatorTime(),passengers.get(i).getGetOutOfElevatorTime(),passengers.get(i).getNumber());
            }
            else{
                elevator.move(passengers.get(i).getArrivalFloor());
                passengers.get(i).setGetInElevatorTime(elevator.getTime());
                elevator.move(passengers.get(i).getDestinationFloor());
                passengers.get(i).setGetOutOfElevatorTime(elevator.getTime());
                elevator.setChart(passengers.get(i).getGetInElevatorTime(),passengers.get(i).getGetOutOfElevatorTime(),passengers.get(i).getNumber());
            }
            passengers.get(i).setWaitingTime();
            passengers.get(i).setTurnAroundTime();
        }
        System.out.println(elevator.getChart());
        return passengers;
    }

    public static ArrayList<Passenger> SJF(Elevator elevator,ArrayList<Passenger> pass){
        ArrayList<Passenger> passengers = sortByShortestPath(pass);
        for (int i = 0; i < passengers.size(); i++) {
            if (elevator.getCurrentFloor() == passengers.get(i).getArrivalFloor()){
                passengers.get(i).setGetInElevatorTime(elevator.getTime());
                int trnTime = elevator.move(passengers.get(i).getDestinationFloor());
                passengers.get(i).setGetOutOfElevatorTime(elevator.getTime());
                elevator.setChart(elevator.getTime()-trnTime,elevator.getTime(),passengers.get(i).getNumber());
            }
            else{
                int trnTime =elevator.move(passengers.get(i).getArrivalFloor());
                passengers.get(i).setGetInElevatorTime(elevator.getTime());
                trnTime += elevator.move(passengers.get(i).getDestinationFloor());
                passengers.get(i).setGetOutOfElevatorTime(elevator.getTime());
                elevator.setChart(passengers.get(i).getGetInElevatorTime(),passengers.get(i).getGetOutOfElevatorTime(),passengers.get(i).getNumber());
            }
            passengers.get(i).setWaitingTime();
            System.out.println(passengers.get(i).getWaitingTime());
            passengers.get(i).setTurnAroundTime();
        }
        System.out.println(elevator.getChart());
        return passengers;
    }

    public static ArrayList<Passenger> RR(Elevator elevator,ArrayList<Passenger> pass,int timeQunatom){
        ArrayList<Passenger> passengers = sortByArrivalTime(pass);
        ArrayList<Passenger> removingP = new ArrayList<>();
        int floorPerQ = timeQunatom/(elevator.getFloorHeight()*elevator.getSpeedPerMeter());

        while(removingP.size()< passengers.size()){
            for (Passenger passenger:passengers) {
                if (removingP.contains(passenger))
                    System.out.println("Next");
                else {
                    if (elevator.getTime() < passenger.getArrivalTime() && passenger == passengers.get(0))
                        elevator.setTime(passenger.getArrivalTime());
                    else if (elevator.getTime() < passenger.getArrivalTime() && passenger != passengers.get(0))
                        break;

                    if (elevator.getCurrentFloor() == passenger.getArrivalFloor()) {
                        passenger.setGetInElevatorTime(elevator.getTime());
                    } else {
                        elevator.move(passenger.getArrivalFloor());
                        passenger.setGetInElevatorTime(elevator.getTime());
                    }
                    passenger.setWaitingTime();

                    if (passenger.getDestinationFloor() > passenger.getArrivalFloor()) {//be samt bala
                        if (floorPerQ > passenger.getDestinationFloor() - passenger.getArrivalFloor()) {
                            elevator.move(passenger.getDestinationFloor());
                            passenger.setGetOutOfElevatorTime(elevator.getTime());
                            elevator.setChart(passenger.getGetInElevatorTime(), passenger.getGetOutOfElevatorTime(), passenger.getNumber());
                            passenger.setTurnAroundTime(); //turnaround time
                            removingP.add(passenger);
                        } else {
                            elevator.move(passenger.getArrivalFloor() + floorPerQ);
                            passenger.setGetOutOfElevatorTime(elevator.getTime());
                            passenger.setTurnAroundTime(); //turnaround time
                            passenger.setArrivalTime(elevator.getTime());
                            passenger.setArrivalFloor(passenger.getArrivalFloor() + floorPerQ);
                            elevator.setChart(passenger.getGetInElevatorTime(), passenger.getGetOutOfElevatorTime(), passenger.getNumber());
                        }
                    } else {//be samt paeen
                        if (floorPerQ > passenger.getArrivalFloor() - passenger.getDestinationFloor()) {
                            elevator.move(passenger.getDestinationFloor());
                            passenger.setGetOutOfElevatorTime(elevator.getTime());
                            elevator.setChart(passenger.getGetInElevatorTime(), passenger.getGetOutOfElevatorTime(), passenger.getNumber());
                            passenger.setTurnAroundTime(); //turnaround time
                            removingP.add(passenger);
                        } else {
                            elevator.move(passenger.getArrivalFloor() - floorPerQ);
                            passenger.setGetOutOfElevatorTime(elevator.getTime());
                            passenger.setTurnAroundTime(); //turnaround time
                            passenger.setArrivalTime(elevator.getTime());
                            passenger.setArrivalFloor(passenger.getArrivalFloor() - floorPerQ);
                            elevator.setChart(passenger.getGetInElevatorTime(), passenger.getGetOutOfElevatorTime(), passenger.getNumber());
                        }
                    }
                }//end of first for
            }
        }//end of while
        System.out.println(elevator.getChart());
        return passengers;
    }

    public static Passenger whoIsNext(ArrayList<Passenger> passengers, Elevator elevator,ArrayList<Passenger> removed){
        ArrayList<Passenger> newlySorted = sortByShortestPath(passengers);
        ArrayList<Passenger> passAfterArrivalTime = new ArrayList<>();
        for (Passenger p:passengers){
            if (p.getArrivalTime()<= elevator.getTime() && !removed.contains(p))
                passAfterArrivalTime.add(p);
        }
        ArrayList<Passenger> finalPassCompare = sortByPathOnly(passAfterArrivalTime);
        for (Passenger p:newlySorted){
            if (!finalPassCompare.contains(p) && !removed.contains(p))
                finalPassCompare.add(p);
        }
        return  finalPassCompare.get(0);
    }

    public static ArrayList<Passenger> SRTF(Elevator elevator,ArrayList<Passenger> pass){
        ArrayList<Passenger> passengers = sortByShortestPath(pass);
        ArrayList<Passenger> removingP = new ArrayList<>();

        while(removingP.size() < pass.size()){
            Passenger p = whoIsNext(passengers,elevator,removingP);
            for (Passenger passenger:passengers) {
                if (p.equals(passenger)){
                    if (elevator.getTime() < passenger.getArrivalTime())
                        elevator.setTime(passenger.getArrivalTime());
                    if (elevator.getCurrentFloor() == passenger.getArrivalFloor()) {
                        passenger.setGetInElevatorTime(elevator.getTime());
                    } else {
                        elevator.move(passenger.getArrivalFloor());
                        passenger.setGetInElevatorTime(elevator.getTime());
                    }
                    passenger.setWaitingTime();
                    if (passenger.getDestinationFloor() > passenger.getArrivalFloor()) { //going 1 floor up
                        elevator.move(passenger.getArrivalFloor()+1);
                        passenger.setGetOutOfElevatorTime(elevator.getTime());
                        passenger.setTurnAroundTime(); //turnaround time
                        passenger.setArrivalTime(elevator.getTime());
                        passenger.setArrivalFloor(passenger.getArrivalFloor() + 1);
                        elevator.setChart(passenger.getGetInElevatorTime(), passenger.getGetOutOfElevatorTime(), passenger.getNumber());
                    } else {//going 1 floor down
                        elevator.move(passenger.getArrivalFloor() - 1);
                        passenger.setGetOutOfElevatorTime(elevator.getTime());
                        passenger.setTurnAroundTime(); //turnaround time
                        passenger.setArrivalTime(elevator.getTime());
                        passenger.setArrivalFloor(passenger.getArrivalFloor() - 1);
                        elevator.setChart(passenger.getGetInElevatorTime(), passenger.getGetOutOfElevatorTime(), passenger.getNumber());
                    }
                    if (elevator.getCurrentFloor() == passenger.getDestinationFloor())
                        removingP.add(passenger);
                }
            }
        }//end of while
        System.out.println(elevator.getChart());
        return passengers;
    }

}
