package org.example;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("OS PROJECT");

        //top bar
        Label topBarLabel = new Label("Elevator");
        topBarLabel.setStyle("-fx-text-fill : #ffffff; -fx-font-family: fantasy; -fx-font-size : 50px;");
        topBarLabel.setAlignment(Pos.CENTER);
        topBarLabel.setLineSpacing(15);

        VBox topBar = new VBox(topBarLabel);
        topBar.setMaxWidth(900);
        topBar.setMaxHeight(80);
        topBar.setMinHeight(80);
        topBar.setMinWidth(500);
        topBar.setStyle("-fx-background-color: #34373d;");
        topBar.setAlignment(Pos.TOP_CENTER);

        //header label
        Label label = new Label("Add a passenger:");
        label.setStyle("-fx-text-fill : #ffffff; -fx-font-family: fantasy; -fx-font-size : 25px;");

        //manager box
        Label managerLabel = new Label("Set method and floors:");
        managerLabel.setStyle("-fx-text-fill : #ffffff; -fx-font-family: fantasy; -fx-font-size : 25px;");
        TextField numFloor = new TextField();
        numFloor.setMinWidth(120);
        numFloor.setMinHeight(36);
        TextField RRTQ = new TextField();
        RRTQ.setMinWidth(120);
        RRTQ.setMinHeight(36);
        RRTQ.setPromptText("RR Time Quantom");
        numFloor.setPromptText("Number of floors");
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton fcfs = new RadioButton("FCFS");
        RadioButton sjf = new RadioButton("SJF");
        RadioButton rr = new RadioButton("RR");
        RadioButton srtf = new RadioButton("SRTF");
        fcfs.setToggleGroup(toggleGroup);
        sjf.setToggleGroup(toggleGroup);
        rr.setToggleGroup(toggleGroup);
        srtf.setToggleGroup(toggleGroup);
        fcfs.setStyle("-fx-text-fill : #ffffff; -fx-font-family: fantasy; -fx-font-size : 20px;");
        sjf.setStyle("-fx-text-fill : #ffffff; -fx-font-family: fantasy; -fx-font-size : 20px;");
        rr.setStyle("-fx-text-fill : #ffffff; -fx-font-family: fantasy; -fx-font-size : 20px;");
        srtf.setStyle("-fx-text-fill : #ffffff; -fx-font-family: fantasy; -fx-font-size : 20px;");
        HBox radioBs = new HBox(fcfs, sjf, rr,srtf);
        radioBs.setSpacing(12);
        radioBs.setAlignment(Pos.CENTER);
        Button setButton = new Button("SET");
        setButton.setStyle("-fx-background-color : #459285; -fx-text-fill: white;");
        setButton.setCursor(Cursor.HAND);
        setButton.setMinHeight(36);
        setButton.setMinWidth(50);
        HBox numFandSetB = new HBox(numFloor,setButton);
        numFandSetB.setAlignment(Pos.CENTER);
        numFandSetB.setSpacing(10);
        numFandSetB.setMinHeight(36);
        numFandSetB.setMinWidth(380);
        VBox manager = new VBox(managerLabel,radioBs,numFandSetB);
        manager.setSpacing(24);
        manager.setMinHeight(36);
        manager.setMinWidth(380);
        manager.setAlignment(Pos.CENTER);

        //button add
        Button addButton = new Button("Add");
        addButton.setStyle("-fx-background-color : #459285; -fx-text-fill: white;");
        addButton.setCursor(Cursor.HAND);
        addButton.setMinHeight(36);
        addButton.setMinWidth(50);

        //button
        Button stopStartButton = new Button("START");
        stopStartButton.setStyle("-fx-background-color : #459285; -fx-text-fill: white;");
        stopStartButton.setCursor(Cursor.HAND);
        stopStartButton.setMinHeight(36);
        stopStartButton.setMinWidth(50);

        //fields
        TextField ariFloor = new TextField();
        ariFloor.setPromptText("arrival floor");
        TextField destFloor = new TextField();
        destFloor.setPromptText("destination floor");
        TextField ariTime = new TextField();
        ariTime.setPromptText("Arrival time");
        ariFloor.setMinWidth(80);
        ariFloor.setMinHeight(36);
        destFloor.setMinWidth(80);
        destFloor.setMinHeight(36);
        ariTime.setMinWidth(80);
        ariTime.setMinHeight(36);

        //results
        Button waitTimeButton = new Button("AVG Waiting time:");
        waitTimeButton.setStyle("-fx-background-color : #899985; -fx-text-fill: white;");
        waitTimeButton.setMinHeight(36);
        waitTimeButton.setMinWidth(120);
        Button turnTimeButton = new Button("AVG Turnaround time:");
        turnTimeButton.setStyle("-fx-background-color : #899985; -fx-text-fill: white;");
        turnTimeButton.setMinHeight(36);
        turnTimeButton.setMinWidth(120);
        HBox results = new HBox(waitTimeButton, turnTimeButton);
        results.setSpacing(8);
        results.setAlignment(Pos.CENTER);


        //main box
        HBox textFillAndButton = new HBox(ariFloor,destFloor,ariTime,addButton);
        textFillAndButton.setAlignment(Pos.CENTER);
        textFillAndButton.setSpacing(10);
        textFillAndButton.setMinHeight(36);
        textFillAndButton.setMinWidth(340);

        VBox mainBox = new VBox(manager,label,textFillAndButton, stopStartButton,results);
        mainBox.setMaxWidth(900);
        mainBox.setMaxHeight(900);
        mainBox.setMinHeight(900);
        mainBox.setMinWidth(900);
        mainBox.setStyle("-fx-background-color: #171b20;");
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(24);

        // Scene

        VBox container =  new VBox();
        container.getChildren().addAll(topBar, mainBox);
        Scene scene = new Scene(container,900,900);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(900);
        primaryStage.setMinWidth(900);
        primaryStage.show();

        //set button and radio button action
        Elevator elevator = new Elevator();
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            elevator.setSchedulingAlgorithm(selectedRadioButton.getText());
            System.out.println(elevator.getSchedulingAlgorithm());
            if (elevator.getSchedulingAlgorithm() == "RR")
                numFandSetB.getChildren().add(RRTQ);
            else if (numFandSetB.getChildren().contains(RRTQ)){
                numFandSetB.getChildren().remove(RRTQ);
            }
        });
        setButton.setOnAction(event -> {
            elevator.setNumFloors(Integer.parseInt(numFloor.getText()));
        });


        // add button action
        ArrayList<Passenger> passengersQueue = new ArrayList<>();
        addButton.setOnAction(event -> {
            Passenger newP = new Passenger();
            newP.setArrivalFloor(Integer.parseInt(ariFloor.getText()));
            newP.setDestinationFloor(Integer.parseInt(destFloor.getText()));
            newP.setArrivalTime(Integer.parseInt(ariTime.getText()));
            passengersQueue.add(newP);
            System.out.println(newP.getArrivalTime());
        });



            // stop/start button action
        stopStartButton.setOnAction(event -> {
            Float avgWaitingTime = new Float(0);
            Float avgTurnAroundTime = new Float(0);
            if (elevator.isStop()){
                elevator.setStop(false);
                //start
                ArrayList<Passenger> sortedPass = new ArrayList<>();
                for (int i = 0; i < passengersQueue.size(); i++)
                    passengersQueue.get(i).setNumber(i);

                switch (elevator.getSchedulingAlgorithm()){
                    case "FCFS":
                        sortedPass = Methods.FCFS(elevator,passengersQueue);
                        for (Passenger p:sortedPass)
                            avgWaitingTime += p.getWaitingTime();
                        avgWaitingTime = avgWaitingTime/sortedPass.size();
                        System.out.println(avgWaitingTime);
                        for (Passenger p:sortedPass)
                            avgTurnAroundTime += p.getTurnAroundTime();
                        avgTurnAroundTime = avgTurnAroundTime/sortedPass.size();
                        System.out.println(avgTurnAroundTime);

                        break;
                    case "SJF":
                        sortedPass = Methods.SJF(elevator,passengersQueue);
                        for (Passenger p:sortedPass)
                            avgWaitingTime += p.getWaitingTime();
                        avgWaitingTime = avgWaitingTime/sortedPass.size();
                        System.out.println(avgWaitingTime);
                        for (Passenger p:sortedPass)
                            avgTurnAroundTime += p.getTurnAroundTime();
                        avgTurnAroundTime = avgTurnAroundTime/sortedPass.size();
                        System.out.println(avgTurnAroundTime);
                        break;
                    case "RR":
                        sortedPass = Methods.RR(elevator,passengersQueue,Integer.parseInt(RRTQ.getText()));
                        for (Passenger p:sortedPass)
                            avgWaitingTime += p.getWaitingTime();
                        avgWaitingTime = avgWaitingTime/sortedPass.size();
                        System.out.println(avgWaitingTime);
                        for (Passenger p:sortedPass)
                            avgTurnAroundTime += p.getTurnAroundTime();
                        avgTurnAroundTime = avgTurnAroundTime/sortedPass.size();
                        System.out.println(avgTurnAroundTime);
                        break;
                    case "SRTF":
                        sortedPass = Methods.SRTF(elevator,passengersQueue);
                        for (Passenger p:sortedPass)
                            avgWaitingTime += p.getWaitingTime();
                        avgWaitingTime = avgWaitingTime/sortedPass.size();
                        System.out.println(avgWaitingTime);
                        for (Passenger p:sortedPass)
                            avgTurnAroundTime += p.getTurnAroundTime();
                        avgTurnAroundTime = avgTurnAroundTime/sortedPass.size();
                        System.out.println(avgTurnAroundTime);
                        break;

                }
                waitTimeButton.setText("AVG Waiting time: " + avgWaitingTime.toString());
                turnTimeButton.setText("AVG Turnaround time: " + avgTurnAroundTime.toString());
                stopStartButton.setText("STOP");
            }
            else{
                elevator.setStop(true);
                passengersQueue.clear();
                stopStartButton.setText("RESET");
                avgWaitingTime = (float) 0;
                avgTurnAroundTime = (float) 0;
            }
        });

    }
}