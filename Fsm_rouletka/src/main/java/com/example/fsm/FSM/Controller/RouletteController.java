package com.example.fsm.FSM.Controller;

import com.example.fsm.FSM.Service.RouletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fsm/")
public class RouletteController {

    private final RouletteService rouletteService;

    public RouletteController(@Autowired  RouletteService rouletteService) {
        this.rouletteService = rouletteService;
    }

    @PostMapping("step")
    public void step(@RequestBody int value){
        rouletteService.getStep(value);
    }

    @GetMapping()
    public ResponseEntity<?> getRoulette(){
        return ResponseEntity.ok(rouletteService.getRoulette());
    }

    @GetMapping("isWin")
    public ResponseEntity<?> getIsWin(){
        return ResponseEntity.ok(rouletteService.isWin());
    }


}
