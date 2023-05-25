package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String diceRoll() {
        return "roll-dice/index";
    }

    @GetMapping("/roll-dice/{number}")
    public String showResult(@PathVariable int number, Model model) {
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int result = (int) (Math.random() * 6) + 1;
            rolls.add(result);
        }
        model.addAttribute("number", number);
        model.addAttribute("rolls", rolls);
        return "roll-dice/show";
    }


}

