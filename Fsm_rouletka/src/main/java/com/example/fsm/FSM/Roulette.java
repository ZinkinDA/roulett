package com.example.fsm.FSM;

import com.example.fsm.FSM.enums.Events;
import com.example.fsm.FSM.enums.States;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Roulette {
    private static final String filename = "roulette.txt";
    private static Roulette roulette;
    @JsonView
    private int value = 0;
    @JsonView
    private byte userValue = 0;
    @JsonView
    private States states ;
    
    private boolean win = false;

    public Boolean getWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public static Roulette getRoulette() {
        return roulette;
    }

    public static void setRoulette(Roulette roulette) {
        Roulette.roulette = roulette;
    }

    public int getValue() {
        return value;
    }

    public void setValue(byte value) {
        final int B = 2391;
        final int A = 825372;
        final int C = 100;
        this.value = ((A * value + B) % C);
    }

    public byte getUserValue() {
        return userValue;
    }

    public void setUserValue(byte userValue) {
        this.userValue = userValue;
    }

    private Roulette() {
        value = 0;
        this.states = States.READY;
    }

    public static Roulette getInstance(){
        if(roulette == null){
            return new Roulette();
        }
        return roulette;
    }

    public String getStep(Events events,byte userValue) throws InterruptedException {
        if(userValue > 100 && userValue < 0){
            return "Не верный ввод пользователя";
        }
        if(!events.equals(Events.START)){
            return "Не верный Event";
        }
        StringBuilder stringBuilder = new StringBuilder();
        switch (events) {
            case START :
                if(this.states == States.READY){
                    this.states = States.RUNNING;
                    this.setUserValue(userValue);
                    this.setValue(userValue);
                } else {
                    break;
                }
                System.out.println("SPIN THE DRUM");
                Thread.sleep(4000);
            case CHECK:
                if(this.states.equals(States.RUNNING)) {
                    this.states = States.СHECK;
                    if(this.value == userValue){
                        win = true;
                        stringBuilder.append("Mashine send : ").append(value)
                                .append("\nUser input : ").append(userValue)
                                .append("\nAnswer : ").append(win).append("\n");
                    } else {
                        win = false;
                        stringBuilder.append("Mashine send : ").append(value)
                                .append("\nUser input : ").append(userValue)
                                .append("\nAnswer : ").append(win).append("\n");
                    }
                    System.out.println("CHECK");
                    Thread.sleep(1000);
                } else {
                    break;
                }
                this.states = States.PRINT;
            case PRINT_CHECK:
                if(states.equals(States.PRINT)) {
                    try(Writer writer = new FileWriter(filename,true)) {
                        writer.append(stringBuilder).append('\n');
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("PRINT CHECK");
                    Thread.sleep(1000);
                    System.out.println(stringBuilder);
                    this.states = States.READY;
                    return stringBuilder.toString();
                } else {
                    break;
                }
        }
        return null;
    }
    
    
    


    public String toString(){
        return "Value = " + value + "\nStatement = "+ states;
    }
}
