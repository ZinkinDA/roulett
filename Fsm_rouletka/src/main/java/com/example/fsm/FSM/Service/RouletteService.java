package com.example.fsm.FSM.Service;

import com.example.fsm.FSM.Roulette;
import com.example.fsm.FSM.enums.Events;
import org.springframework.stereotype.Service;

@Service
public class RouletteService {

    private final Roulette roulette;

    public RouletteService() {
        this.roulette = Roulette.getInstance();
    }

    public Roulette getRoulette(){
        return roulette;
    }

    public void getStep(int value){
        if(!(value > 100 || value < 0)) {
            try {
                roulette.getStep(Events.START, (byte) value);
            } catch (InterruptedException e) {
                System.out.println("Вы ввели неверное значение");
            }
        }
    }

    public String isWin(){
        return roulette.getWin().toString();
    }
}
